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
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import javax.imageio.ImageIO;

import javafx.util.Pair;

public class Collage extends Picture {
	private ArrayList<Image> mImages;
	private static final int NUM_IMAGES = 30;
	private String mName;
	private static int id = 0;
	
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
	
	public void setName(String name) {
		this.mName = name;
	}
	public String getName(){
		return mName;
	}
	//converts the collage to a png, saves it to the server's HD, 
	//and returns the string with the location
	private ArrayList<BufferedImage> downloadImages() {
		ArrayList<BufferedImage> images = new ArrayList<BufferedImage>();
		for (int i = 0; i < NUM_IMAGES; i++) {
			BufferedImage image = null;
			try {
			    URL url = new URL("https://cloud.netlifyusercontent.com/assets/344dbf88-fdf9-42bb-adb4-46f01eedd629/66a56988-2537-426f-b25b-149c7a720ac7/07-functions-opt.jpg");
			    image = ImageIO.read(url);
			    images.add(image);
			   
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return images;
	}
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
//	    AffineTransform tx = new AffineTransform();
//	    tx.translate(wPrime/2, hPrime/2);
//	    tx.rotate(toRad, -w/2, -h/2);
//
//	    AffineTransformOp op = new AffineTransformOp(tx,
//	        AffineTransformOp.TYPE_BILINEAR);
//	    rotatedImage = op.filter(rotatedImage, null);
	    g.translate(wPrime/2, hPrime/2);
	    g.rotate(toRad);
	    g.translate(-w/2, -h/2);
	    g.setComposite(AlphaComposite.SrcOver.derive(0.8f));
	    g.drawImage(originalImage, 0, 0, null);
	   
	    g.dispose();  // release used resources before g is garbage-collected
	    return rotatedImage;
		
	}
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
	public String convertToPng() {
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
			currentImage = this.rotateImage(currentImage, rotation);
	        g.drawImage(currentImage ,position.getKey(), position.getValue(), null);
	    }
		Collage.id++;
		File file = new File("Collage"+ id + ".png");
	    try {
	    	
			ImageIO.write(result, "png", file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
		return "Collage"+ id + ".png";
	}
	public static void main(String [] args) {
		Collage c = new Collage("", 800, 600);
		for (int i = 0; i < 2; i++) {
			Image image = new Image("", 100, 100);
			Pair<Integer, Integer> position = new Pair<Integer,Integer>((i+1)*100, (i+1)*100);
			image.setPosition(position);
			int rotation = ThreadLocalRandom.current().nextInt(-45, 46);
			image.setRotation(rotation);
			c.addImage(image);
		}
		
		c.convertToPng();
		System.out.println("Success!");
		
	}
	
}
