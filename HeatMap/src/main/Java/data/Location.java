package data;

/**
 * Struct class to represent a location on a map.  For now I am considering this a simple (X,Y) coordinate
 * on an open world map.  However depending upon how the maps are generated more specific information
 * may be needed to accurately define a specific location.
 */
public class Location {

	private int x = Integer.MIN_VALUE;
	private int y = Integer.MIN_VALUE;
	
	public Location() {
		// Default constructor for compatibility
	}
	
	public Location(int x, int y) {
		this.setX(x);
		this.setY(y);
	}

	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @param x the x to set
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/**
	 * @param y the y to set
	 */
	public void setY(int y) {
		this.y = y;
	}
	
}
