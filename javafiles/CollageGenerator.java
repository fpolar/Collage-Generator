package DataContainers;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
public class CollageGenerator {
	private static final int MIN_ROTATION = -45;
	private static final int MAX_ROTATION = 46;
	private Collage mCollage;
	
	private boolean isFull() {
		return true;
	}
	
	public String buildCollage(String query) {
		//HIGH LEVEL
		//request images from fetcher
		//place all of the images 
		//generate the png
		//return the string with the location
		
		//DETAILED
		//grab images from fetcher
		//grad one of these
		//give it a size large enough for background
		//give it a random rotation
		//place it in center
		//add it to collage
		//then loop through 29 times 
		//place all other images in random location within bounds
		//give all images fixed size 
		//give all images random rotation
		//add to collage
		//generate png in collage 
		//return string to location of png
		
		
		return "";
	}
	
	private void placeImage(Image img) {
		
	}
	private void placeImages (ArrayList<Image> images) {
		
	}
	private void setRandomRotation(Image img) {
		int rotation = ThreadLocalRandom.current().nextInt(MIN_ROTATION, MAX_ROTATION);
		img.setRotation(rotation);
		System.out.println(img.getRotation());
	}
}
