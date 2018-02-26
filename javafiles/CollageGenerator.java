package DataContainers;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import javafx.util.Pair;
public class CollageGenerator {
	private static final int WIDTH = 800;
	private static final int HEIGHT = 600;
	private Collage mCollage;
	
	private boolean isFull() {
		return true;
	}
	
	public CollageGenerator() {
		this.mCollage = new Collage("",WIDTH, HEIGHT);
	}
	public String buildCollage(String query, String mPath) {
		//HIGH LEVEL
		//request images from fetcher
		//place all of the images 
		//generate the png
		//return the string with the location
		
		//DETAILED
		//grab images from fetcher
		System.out.println("getting here");
		this.mCollage.setName(query);
		ArrayList<Image> images = Fetcher.getImageList(query);
		//int backgroundSide = (int)Math.sqrt((WIDTH/4)*(WIDTH/4) + (HEIGHT/4)*(HEIGHT/4));
		//int backgroundSide = 155;
		int x = 134;
		int y = 178;
		//System.out.println(backgroundSide);
		//System.out.println(backgroundSide);
		//grad one of these
		int counter = 0;
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 6; j++) {
				Image background = images.get(counter);
				//give it a size large enough for background
				background.setDimensions(new Pair<Integer, Integer>(x,y));
				//give it a random rotation (this is already set by the constructor for Image)
				//place it in center
//				int x = 0;
//				int y = 0;
//				if(i%2==1) {
//					x = WIDTH/2;
//				}
//				if(i > 1) {
//					y = HEIGHT/2;
//				}
				background.setPosition(new Pair<Integer, Integer>((int) ((double)WIDTH*((double)((double)(i-.2))/5)),(int) ((double)HEIGHT*(double)(((double)(j-.2))/(double)6)))); //may need to change position
				//add it to collage
				mCollage.addImage(background);
				counter++;
			}
			
		}
		
		//then loop through 26 times 
//		for(int i = 16; i < 30; i++){
//			Image tile = images.get(i);
//			//System.out.println((((WIDTH*HEIGHT*3)/2) - (4*(backgroundSide*backgroundSide))) / 26);
//			//int tileSide = (int) Math.sqrt((((WIDTH*HEIGHT*3)/2) - (16*(backgroundSide*backgroundSide))) / 14);
//			int tileSide = 155;
//			System.out.println((((WIDTH*HEIGHT*3)/2) - (16*(backgroundSide*backgroundSide))) / 14);
//			tile.setDimensions(new Pair<Integer,Integer>(tileSide,tileSide));
//			
//			int x = ThreadLocalRandom.current().nextInt(0, WIDTH);
//			int y = ThreadLocalRandom.current().nextInt(0, HEIGHT);
//			tile.setPosition(new Pair<Integer, Integer>(x,y));
//			
//			mCollage.addImage(tile);
//		}
		//place all other images in random location within bounds
		//give all images fixed size 
		//give all images random rotation
		//add to collage
		//generate png in collage 
		
		//return string to location of png
		
		
		return mCollage.convertToPng(mPath);
	}
	
	private void placeImage(Image img) {
		
	}
	private void placeImages (ArrayList<Image> images) {
		
	}
//	public static void main (String args[]) {
//		CollageGenerator cg = new CollageGenerator();
//		System.out.println(cg.buildCollage("puppy"));
//	}
}
