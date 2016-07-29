/**
 * @author Jack D'Amelio
 * @date 7/1/2016
 */
public class CoordPair {

	//Fields
	private int xCoord;	
	private int yCoord;
	
	//Constructors
	public CoordPair(int firstCoord, int secondCoord) {
		super();
		this.xCoord = firstCoord;
		this.yCoord = secondCoord;
	}
	
	
	
	//Setters and Getters
	public int getxCoord() {
		return xCoord;
	}
	public void setxCoord(int firstCoord) {
		this.xCoord = firstCoord;
	}
	public int getyCoord() {
		return yCoord;
	}
	public void setyCoord(int secondCoord) {
		this.yCoord = secondCoord;
	}
	
	
}
