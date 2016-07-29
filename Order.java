public class Order {
	private CoordPair loc;
	private int setValue;

	public Order(CoordPair loc, int setValue) {
		super();
		this.loc = loc;
		this.setValue = setValue;
	}

	public CoordPair getLoc() {
		return loc;
	}

	public void setLoc(CoordPair loc) {
		this.loc = loc;
	}

	public int getSetValue() {
		return setValue;
	}

	public void setSetValue(int setValue) {
		this.setValue = setValue;
	}
}
