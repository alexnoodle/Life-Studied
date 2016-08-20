import java.util.ArrayList;


public class Node {
public ArrayList<Node> underlings = new ArrayList<Node>();
public int[][] map;

public Node(int[][] map){
	this.map = map;
}

public void loadUnderlings(ArrayList<Node> n){
	for(int i = 0; i < n.size(); i++){
		underlings.add(n.get(i));
	}
}
}
