
import java.util.ArrayList;

public class Shape {
	private String name;
	private ArrayList<Integer> xDeviation;
	private ArrayList<Integer> yDeviation;
	private ArrayList<Integer> aoexDeviation = new ArrayList<Integer>();
	private ArrayList<Integer> aoeyDeviation = new ArrayList<Integer>();
	private int[][] map;
	private int[][] aoe;
	private int type;
	private Tree tree;
	public Shape(String name, ArrayList<Integer> xDeviation,
			ArrayList<Integer> yDeviation, int[][] aoe) {
		super();
		this.name = name;
		this.xDeviation = xDeviation;
		this.yDeviation = yDeviation;
		this.aoe = aoe;
		buildBitMap();

	}
	public Shape(ArrayList<Integer> xDeviation,
			ArrayList<Integer> yDeviation){
		this.xDeviation = xDeviation;
		this.yDeviation = yDeviation;
		
		buildBitMap();
	//	buildAoe();
		
	}

	public int[][] buildBitMap() {
		int maxX = 0;
		for (int a : xDeviation) {
			if (maxX < a)
				maxX = a;
		}
		int maxY = 0;
		for (int a : yDeviation) {
			if (maxY < a)
				maxY = a;
		}
		int[][] map = new int[maxX+1][maxY+1];

		for (int i = 0; i < xDeviation.size(); i++) {
			
			map[xDeviation.get(i)][yDeviation.get(i)] = 1;
		}

		this.map = map;

		return map.clone();
	}

	public void buildAoe() {
		int[][] temp1 = new int[9999][9999];
		for (int q = 0; q < xDeviation.size(); q++) {
			temp1[4999 + xDeviation.get(q)][4999 + yDeviation.get(q)] = 1;
		}

		Simulator a = new Simulator(map);

		ArrayList<int[][]> temp = a.run();
		type = temp.size();
		for (int i = 0; i < temp.size(); i++) {
			for (int j = 0; j < temp.get(i).length; j++) {
				for (int k = 0; k < temp.get(i)[j].length; k++) {
					if (temp.get(i)[j][k] != 0) {
						aoexDeviation.add(j - 4999);
						aoeyDeviation.add(k - 4999);
					}
				}
			}
		}
		/*for (int i = 0; i < aoexDeviation.size(); i++) {
			for (int j = i; j < aoexDeviation.size(); j++) {
				if (aoexDeviation.get(i) == aoexDeviation.get(j)
						&& aoeyDeviation.get(i) == aoeyDeviation.get(j)) {
					aoexDeviation.remove(j);
					aoexDeviation.remove(i);
				}
			}
		}*/
		int xMax = 0;
		int yMax = 0;
		for (int i = 0; i < aoexDeviation.size(); i++) {
			if (aoexDeviation.get(i)+4999 > xMax)
				xMax = aoexDeviation.get(i)+4999;
			if (aoeyDeviation.get(i)+4999 > yMax)
				yMax = aoeyDeviation.get(i)+4999;
		}

		aoe = new int[xMax+1][yMax+1];
		int xMid = (int) Math.floor(xMax/2);
		int yMid = (int) Math.floor(yMax/2);

		
		for(int i = 0; i < aoexDeviation.size(); i++){
			aoe[aoexDeviation.get(i)+4999][aoeyDeviation.get(i)+4999] = 3;
		}
		for(int i = 0; i < xDeviation.size(); i++){
			aoe[xDeviation.get(i)][ yDeviation.get(i)] = 1;
		}
		for (int i = 0; i < aoexDeviation.size(); i++) {
			
			aoexDeviation.set(i, aoexDeviation.get(i)+4999);
			
			aoeyDeviation.set(i,aoeyDeviation.get(i)+4999);
		}
		
	}

	public int[][] addToMap(int[][] map, int x, int y) {
		for (int i = 0; i < xDeviation.size(); i++) {
			map[x + xDeviation.get(i)][y + yDeviation.get(i)] = 1;
		}

		return map;
	}

	public boolean isMatch(int[][] map, int x, int y) {

		for (int i = 0; i < this.map.length; i++) {
			for (int j = 0; j < this.map[i].length; j++) {
				if (this.map[i][j] != map[i][j]) {
					return false;
				}
			}
		}

		return true;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Integer> getxDeviation() {
		return xDeviation;
	}

	public void setxDeviation(ArrayList<Integer> xDeviation) {
		this.xDeviation = xDeviation;
	}

	public ArrayList<Integer> getyDeviation() {
		return yDeviation;
	}

	public void setyDeviation(ArrayList<Integer> yDeviation) {
		this.yDeviation = yDeviation;
	}

	public int[][] getAoe() {
		return aoe;
	}

	public void setAoe(int[][] aoe) {
		this.aoe = aoe;
	}

	public int[][] getMap() {
		return map;
	}

	public void setMap(int[][] map) {
		this.map = map;
	}

	public ArrayList<Integer> getAoexDeviation() {
		return aoexDeviation;
	}

	public void setAoexDeviation(ArrayList<Integer> aoexDeviation) {
		this.aoexDeviation = aoexDeviation;
	}

	public ArrayList<Integer> getAoeyDeviation() {
		return aoeyDeviation;
	}

	public void setAoeyDeviation(ArrayList<Integer> aoeyDeviation) {
		this.aoeyDeviation = aoeyDeviation;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	public Tree getTree() {
		return tree;
	}
	public void setTree(Tree tree) {
		this.tree = tree;
	}

}
