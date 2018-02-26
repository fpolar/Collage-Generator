package DataContainers;

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

import javax.imageio.ImageIO;

public class Collage extends Picture {
	private ArrayList<Image> mImages;
	private static final int NUM_IMAGES = 30;
	private String mName;
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
	public String convertToPng() {
		ArrayList<BufferedImage> images = new ArrayList<BufferedImage>();
		
		for (int i = 0; i < NUM_IMAGES; i++) {
			BufferedImage image = null;
			try {
			    URL url = new URL("https://cloud.netlifyusercontent.com/assets/344dbf88-fdf9-42bb-adb4-46f01eedd629/66a56988-2537-426f-b25b-149c7a720ac7/07-functions-opt.jpg");
			    image = ImageIO.read(url);
			   // File file = new File("test.jpg");
			    //ImageIO.write(image, "png", file);
			    
			    
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
			
		return "";
	}
	public static void main(String [] args) {
		Collage c = new Collage();
		c.convertToPng();
		
	}
	
}
