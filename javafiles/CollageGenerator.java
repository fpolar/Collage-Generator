package DataContainers;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import javafx.util.Pair;
public class CollageGenerator {
	private static final int WIDTH = 800;
	private static final int HEIGHT = 400;
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
		ArrayList<Image> images = Fetcher.getImageList(query);
		int backgroundSide = (int)Math.sqrt(WIDTH*WIDTH + HEIGHT*HEIGHT);
		//grad one of these
		for(int i = 0; i < 4; i++) {
			Image background = images.get(i);
			//give it a size large enough for background
			background.setDimensions(new Pair<Integer, Integer>(backgroundSide,backgroundSide));
			//give it a random rotation (this is already set by the constructor for Image)
			//place it in center
			int x = 0;
			int y = 0;
			if(i%2==1) {
				x = WIDTH/2;
			}
			if(i > 1) {
				y = HEIGHT/2;
			}
				
			background.setPosition(new Pair<Integer, Integer>(x,y)); //may need to change position
			//add it to collage
			mCollage.addImage(background);
		}
		//then loop through 26 times 
		for(int i = 4; i < 30; i++){
			Image tile = images.get(i);
			int tileSide = ((WIDTH*HEIGHT*30)/20 - 4*(backgroundSide*backgroundSide)) / 26;
			tile.setDimensions(new Pair<Integer,Integer>(tileSide,tileSide));
			
			int x = ThreadLocalRandom.current().nextInt(0, WIDTH-tileSide);
			int y = ThreadLocalRandom.current().nextInt(0, HEIGHT-tileSide);
			tile.setPosition(new Pair<Integer, Integer>(x,y));
			
			mCollage.addImage(tile);
		}
		//place all other images in random location within bounds
		//give all images fixed size 
		//give all images random rotation
		//add to collage
		//generate png in collage 
		
		//return string to location of png
		
		
		return mCollage.convertToPng();
	}
	
	private void placeImage(Image img) {
		
	}
	private void placeImages (ArrayList<Image> images) {
		
	}
}
