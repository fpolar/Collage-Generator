package DataContainers;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class Collage extends Picture {
	private ArrayList<Image> mImages;
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
		URL url;
		try {
			url = new URL("https://cloud.netlifyusercontent.com/assets/344dbf88-fdf9-42bb-adb4-46f01eedd629/66a56988-2537-426f-b25b-149c7a720ac7/07-functions-opt.jpg");
			InputStream in = new BufferedInputStream(url.openStream());
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			byte[] buf = new byte[1024];
			int n = 0;
			while (-1!=(n=in.read(buf)))
			{
			   out.write(buf, 0, n);
			}
			out.close();
			in.close();
			byte[] response = out.toByteArray();
			FileOutputStream fos = new FileOutputStream("test.jpg");
			fos.write(response);
			fos.close();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "";
	}
	public static void main(String [] args) {
		Collage c = new Collage();
		c.convertToPng();
	}
	
}
