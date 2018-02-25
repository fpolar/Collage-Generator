package DataContainers;

import javafx.util.Pair;

public abstract class Picture {
	protected String mName; 
	protected Pair<Integer, Integer> mDimensions;
	protected String mSource;
	
	public void setName(String name) {
		this.mName = name;
	}
	public String getName(){
		return mName;
	}
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
