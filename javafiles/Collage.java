package DataContainers;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import javax.imageio.ImageIO;

import javafx.util.Pair;

public class Collage extends Picture {
	private ArrayList<Image> mImages;
	private static final int NUM_IMAGES = 30;
	private String mName;
	private static int id = 0;
	private ArrayList<String> extraImages;
	
	public Collage(String link, int width, int height) {
		super(link, width, height);
		mImages = new ArrayList<Image>();
	}
	public ArrayList<Image> getImages() {
		return mImages;
	}

	public void setImages(ArrayList<Image> mImages) {
		this.mImages = mImages;
	}
	public void addImage(Image image) {
		mImages.add(image);
	}
	public void addBackground(Image image) {
		mImages.add(0, image);
	}
	
	public void setName(String name) {
		this.mName = name;
		extraImages = Fetcher.extraImages(name);
	}
	public String getName(){
		return mName;
	}
	
	//downloads all of the images to memory
	private ArrayList<BufferedImage> downloadImages() {
		ArrayList<BufferedImage> images = new ArrayList<BufferedImage>();
		int index = 0;
		boolean retry = false;
		for (int i = 0; i < mImages.size(); i++) {
			
			BufferedImage image = null;
			
			try {
				URL url = null;
				if (retry) {
					url = new URL(extraImages.get(index));
					retry = false;
				} else {
					url = new URL(mImages.get(i).getSource());
				}
			    
			    HttpURLConnection myconn = (HttpURLConnection) url.openConnection();
			    myconn.addRequestProperty("User-Agent", "");
			    image = ImageIO.read(myconn.getInputStream());
			    //resizeImage(image, 10, 10);
			    if (image != null) {
			    	images.add(image);
			    } else {
			    	throw new IOException();
			    }
			    
			   
			} catch (IOException e) {
				index++;
				i--;
				retry = true;
				System.out.println("trying again");
			} catch (Exception e) {
				index++;
				i--;
				retry = true;
				System.out.println("trying again");
			}
		}
		return images;
	}
	
	//rotates images to their corresponding mImage angle
	private BufferedImage rotateImage(BufferedImage originalImage, double degree) {
		
		int w = originalImage.getWidth();
	    int h = originalImage.getHeight();
	    double toRad = Math.toRadians(degree);
	    int hPrime = (int) (w * Math.abs(Math.sin(toRad)) + h * Math.abs(Math.cos(toRad)));
	    int wPrime = (int) (h * Math.abs(Math.sin(toRad)) + w * Math.abs(Math.cos(toRad)));

	    BufferedImage rotatedImage = new BufferedImage(wPrime, hPrime, BufferedImage.TYPE_INT_ARGB);
	    Graphics2D g = rotatedImage.createGraphics();
	    g.setComposite(AlphaComposite.SrcOver.derive(0.0f));
	    g.setColor(Color.WHITE);
	    g.fillRect(0, 0, wPrime, hPrime);  // fill entire area
	    g.translate(wPrime/2, hPrime/2);
	    g.rotate(toRad);
	    g.translate(-w/2, -h/2);
	    g.setComposite(AlphaComposite.SrcOver.derive(1f));
	    g.drawImage(originalImage, 0, 0, null);
	   
	    g.dispose();  // release used resources before g is garbage-collected
	    return rotatedImage;
		
	}
	private BufferedImage addBorder(BufferedImage image) {
		int width = image.getWidth() + 6;
		int height = image.getHeight() + 6;
		BufferedImage newImage = new BufferedImage(width, height, image.getType()); 
		Graphics2D g = newImage.createGraphics();  
		g.setColor(Color.WHITE);
	    g.fillRect(0, 0, width, height);
	    g.drawImage(image,  3,  3,  null);
	    return newImage;
	}
	//resizes images to their corresponding mImage size
	private BufferedImage resizeImage(BufferedImage image, int newHeight, int newWidth) {
		 int width = image.getWidth();  
		 int height = image.getHeight();  
		 BufferedImage newImage = new BufferedImage(newWidth, newHeight, image.getType()); 
		 Graphics2D g = newImage.createGraphics();  
		 g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		 g.drawImage(image, 0, 0, newWidth, newHeight, 0, 0, width, height, null);  
		 g.dispose();  
		 return newImage;
	}
	
	//converts the collage to a png, saves it to the server's HD, 
	//and returns the string with the location

	public String convertToPng(String mPath) {
		//find old images with same names and delete them
		ArrayList<BufferedImage> images = this.downloadImages();
		BufferedImage result = new BufferedImage(
                800, 600, //work these out
                BufferedImage.TYPE_INT_ARGB);
		Graphics g = result.getGraphics();
		for (int i = 0; i < mImages.size(); i++) {
			
	        //BufferedImage bi = ImageIO.read(new File(image));
			Pair<Integer, Integer> dimensions = mImages.get(i).getDimensions();
			Pair<Integer, Integer> position = mImages.get(i).getPosition();
			double rotation = mImages.get(i).getRotation();
			BufferedImage currentImage = images.get(i);
			currentImage = this.resizeImage(currentImage, dimensions.getKey(), dimensions.getValue());
			currentImage = this.addBorder(currentImage);
			currentImage = this.rotateImage(currentImage, rotation);
	        g.drawImage(currentImage ,position.getKey(), position.getValue(), null);
	    }
		Collage.id++;
		
		File file = new File(mPath + "Collage"+ id + ".png");
	    try {
	    	
			ImageIO.write(result, "png", file);

			System.out.println("File write complete! Saved to: "+ file.getAbsolutePath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
		return "Collage"+ id + ".png";
	}
//	public static void main(String [] args) {
//		Collage c = new Collage("", 800, 600);
//		for (int i = 0; i < 30; i++) {
//			Image image = new Image("", 100, 100);
//			Pair<Integer, Integer> position = new Pair<Integer,Integer>((i+1)*10, (i+1)*10);
//			image.setPosition(position);
//			int rotation = ThreadLocalRandom.current().nextInt(-45, 46);
//			image.setRotation(rotation);
//			c.addImage(image);
//		}
//		
//		c.convertToPng();
//		System.out.println("Success!");
//		
//		
//	}
	
}
