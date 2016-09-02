import java.util.ArrayList;

public class Node {
	public ArrayList<Node> underlings = new ArrayList<Node>();
	public int[][] map;

	public Node(int[][] map) {
		this.map = map;
	}

	public void loadUnderlings(ArrayList<Node> n) {
		for (int i = 0; i < n.size(); i++) {
			underlings.add(n.get(i));
		}
	}

	public ArrayList<Node> getLowest() {
		ArrayList<Node> returnSet = new ArrayList<Node>();
		if (underlings.size() == 0 || underlings == null) {
			returnSet.add(this);
			return returnSet;
		} else {
		
			for (int i = 0; i < underlings.size(); i++) {
				ArrayList<Node> temp = new ArrayList<Node>();

				temp = underlings.get(i).getLowest();
				for (int j = 0; j < temp.size(); j++) {
					returnSet.add(temp.get(j));
				}
			}
			return returnSet;
		}

	}

	public String mapToString() {
		String out = "";
		out += map.length + "\n ";
		out += map[0].length + "\n ";
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				out += i + "\n";
				out += j + "\n";
			}
		}
		out += -1 + "\n ";
		return out;
	}

	@Override
	public String toString() {
		String out = "";
		Parcel.trace(underlings.size());
		if (underlings.size() > 0) {
			for (int i = 0; i < underlings.size(); i++) {
				out += underlings.get(i).toString();
			}
		}
		return out;

	}
}
