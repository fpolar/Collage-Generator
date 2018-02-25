package DataContainers;

import java.util.ArrayList;

public class Collage extends Picture {
	private ArrayList<Image> mImages;

	public ArrayList<Image> getImages() {
		return mImages;
	}

	public void setImages(ArrayList<Image> mImages) {
		this.mImages = mImages;
	}
	public void addImage(Image image) {
		mImages.add(image);
	}
	
	public Image getImageByName(String name) {
		for (int i = 0; i < mImages.size(); i++) {
			if (mImages.get(i).getName().equals(name)) {
				return mImages.get(i);
			}
		}
		return null;
	}
	//converts the collage to a png, saves it to the server's HD, 
	//and returns the string with the location
	public String convertToPng() {
		
		return "";
	}
	
	
}
