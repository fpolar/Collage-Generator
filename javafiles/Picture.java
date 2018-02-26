package DataContainers;

import javafx.util.Pair;

public abstract class Picture { 
	protected Pair<Integer, Integer> mDimensions;
	protected String mSource;

	public void setDimensions(Pair<Integer, Integer> dimensions) {
		this.mDimensions = dimensions;
	}
	public Pair<Integer, Integer> getDimensions() {
		return mDimensions;
	}
	public void setSource (String source) {
		this.mSource = source;
	}
	public String getSource() {
		return mSource;
	}
}
