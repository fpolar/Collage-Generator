package DataContainers;

import java.util.concurrent.ThreadLocalRandom;

import javafx.util.Pair;

/**
 * Class defines a single Image, which is an entity of a Collage, that
 * is defined by a position and a rotation angle.
 * @author leighrubin
 *
 */
public class Image extends Picture {
	private static final int MIN_ROTATION = -45;
	private static final int MAX_ROTATION = 46;
	
	private Pair<Integer, Integer> mPosition;
	private int mRotation = 0;
	
	public Image(String source, int width, int height) {
		super(source, width, height);
		mPosition = new Pair<Integer, Integer>(0,0);
		this.setRandomRotation();
	}
	public Pair<Integer, Integer> getPosition() {
		return mPosition;
	}
	public void setPosition(Pair<Integer, Integer> mPosition) {
		this.mPosition = mPosition;
	}
	public int getRotation() {
		return mRotation;
	}
	public void setRotation(int mRotation) {
		this.mRotation = mRotation;
	}
	public void setRandomRotation() {
		mRotation = ThreadLocalRandom.current().nextInt(MIN_ROTATION, MAX_ROTATION);
	}
}
