package DataContainers;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import javafx.util.Pair;
/**
 * Initializes Images to be constructed into a Collage.
 * @author leighrubin
 *
 */
public class CollageGenerator {
	//width and height of the collage
	private static final int WIDTH = 800;
	private static final int HEIGHT = 600;
	
	//[min, max) rotation range for individual images
	private static final int MIN_ROTATION = -45;
	private static final int MAX_ROTATION = 46;
	
	private Collage mCollage;
	
	/**
	 * Initialize the Collage with a WIDTH and a HEIGHT.
	 */
	public CollageGenerator() {
		this.mCollage = new Collage("",WIDTH, HEIGHT);
	}
	
	/**
	 * Retrieves and manipulates images to be placed into the collage by
	 * the Collage class. This function adjust the size, rotation angle,
	 * and position of each individual image.
	 * @param query Search query
	 * @param mPath Path to collage
	 * @return Path to collage of 30 images
	 * @return "ERROR" if insufficeint images are found
	 */
	public String buildCollage(String query, String mPath) {
		//set the name of the Collage to what was searched
		this.mCollage.setName(query);
		
		//retrieve images from Google API
		ArrayList<Image> images = Fetcher.getImageList(query);
		
		//check if insufficient images found
		if(images.size() < 30) {
			return "ERROR";
		}
		
		//set the distribution of possible rotations
		ArrayList<Integer> rotations = new ArrayList<Integer>();
		for(int i = 0; i < 29; i++)
		{
			//add a random rotation angle to the distribution
			rotations.add(ThreadLocalRandom.current().nextInt(MIN_ROTATION, MAX_ROTATION));
		}
		//add 0 degrees of rotation to distribution
		rotations.add(0);
		
		//determines if an image has been set to the background yet
		boolean backgroundAdded = false;
		
		//size of the size of one of the smaller foreground images
		int tileSide = 92;
		
		//loop over all images, setting the size, rotation, and position of each
		for(int i = 29; i >= 0; i--){
			Image tile = images.get(i);
			
			//random index to selected a rotation angle from
			int rand = ThreadLocalRandom.current().nextInt(0, i+1);
			
			//sets first non rotated image to entire background size in order
			//to eliminate whitespace
			if(rotations.get(rand) == 0 && !backgroundAdded) {
				tile.setRotation(rotations.get(rand));
				tile.setDimensions(new Pair<Integer,Integer>(594,794));
				tile.setPosition(new Pair<Integer,Integer>(0,0));
				backgroundAdded = true;
				mCollage.addBackground(tile);
			}
			else { //all 29 foreground images
				//tiles the images in 3 rows and 10 columns
				int x = (i%10)*(WIDTH-tileSide)/10;
				int y = (i%3)*(HEIGHT-tileSide)/3;
				
				//staggers the images from one another in a single row
				if((i%10)%2==0) {
					y+=HEIGHT/6;
				}
				tile.setRotation(rotations.get(rand));
				tile.setPosition(new Pair<Integer, Integer>(x,y));
				tile.setDimensions(new Pair<Integer, Integer>(tileSide,tileSide));
				mCollage.addImage(tile);
			}
			//remove the rotation angle from the distribution
			rotations.remove(rand);
		}
		//return the path to where the collage should be constructed
		return mCollage.convertToPng(mPath);
	}
	
	public static void main (String args[]) {
		CollageGenerator cg = new CollageGenerator();
		System.out.println(cg.buildCollage("usc trojans fight on",""));
	}
}
