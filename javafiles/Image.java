package DataContainers;

import javafx.util.Pair;

public class Image extends Picture {
	private Pair<Integer, Integer> mPosition;
	private double mRotation;
	
	
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
	
	
}
