package DataContainers;

import javafx.util.Pair;

public class Image extends Picture {
	private Pair<Integer, Integer> mPosition;
	private float mRotation;
	
	
	public Pair<Integer, Integer> getPosition() {
		return mPosition;
	}
	public void setPosition(Pair<Integer, Integer> mPosition) {
		this.mPosition = mPosition;
	}
	public float getRotation() {
		return mRotation;
	}
	public void setRotation(float mRotation) {
		this.mRotation = mRotation;
	}
	
	
}
