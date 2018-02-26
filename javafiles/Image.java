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
	private Pair<Integer, Integer> mPosition;
	private double mRotation;
	
	public Image(String source, int width, int height) {
		super(source, width, height);
		mPosition = new Pair(0,0);
		mRotation = setRandomRotation();
	}
	public Pair<Integer, Integer> getPosition() {
		return mPosition;
	}
	public void setPosition(Pair<Integer, Integer> mPosition) {
		this.mPosition = mPosition;
	}
	public double getRotation() {
		return mRotation;
	}
	public void setRotation(double mRotation) {
		this.mRotation = mRotation;
	}
	private void setRandomRotation() {
		return ThreadLocalRandom.current().nextInt(MIN_ROTATION, MAX_ROTATION);
	}
	
	
}
