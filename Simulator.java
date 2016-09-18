import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Stack;

public class Simulator {

	private int[][] rootMap;
	private ArrayList<int[][]> mapProgress = new ArrayList<int[][]>();
	private Stack<Order> stack = new Stack<Order>();

	public Simulator(int dim, CoordPair[] initOn) {
		rootMap = new int[dim][dim];

		for (CoordPair a : initOn) {
			rootMap[a.getxCoord()][a.getyCoord()] = 1;

		}

	}

	public Simulator(int[][] map) {
		ArrayList<int[][]> a = new ArrayList<int[][]>();
		rootMap = map;
		a.add(map);
	}

	public Simulator() {
		rootMap = new int[20][20];
		rootMap[8][8] = 1;
		rootMap[8][9] = 1;
		rootMap[8][10] = 1;
		rootMap[8][11] = 1;
		rootMap[9][8] = 1;
		rootMap[9][9] = 1;
		rootMap[9][10] = 1;
		rootMap[9][11] = 1;
	}

	public ArrayList<int[][]> run() {

		int numChanges = 1;
		mapProgress.add(rootMap.clone());
		int counter = 1;
		int[][] blank;
		while (numChanges != 0) {
			// if(counter > 1000)break;
			numChanges = 0;
			
			blank = new int[rootMap.length][rootMap[0].length];
			for (int i = 0; i < rootMap.length; i++) {
				for (int j = 0; j < rootMap[i].length; j++) {

					if (mapProgress.get(counter - 1)[i][j] == 0
							&& getAdjacent(mapProgress.get(counter - 1),
									new CoordPair(i, j)).length == 3) {
						stack.push(new Order(new CoordPair(i, j), 1));
						numChanges++;
					}

					if (mapProgress.get(counter - 1)[i][j] == 1
							&& getAdjacent(mapProgress.get(counter - 1),
									new CoordPair(i, j)).length > 3) {
						stack.push(new Order(new CoordPair(i, j), 0));
						numChanges++;
					}

					if (mapProgress.get(counter - 1)[i][j] == 1
							&& getAdjacent(mapProgress.get(counter - 1),
									new CoordPair(i, j)).length == 2
							|| getAdjacent(mapProgress.get(counter - 1),
									new CoordPair(i, j)).length == 3) {
						stack.push(new Order(new CoordPair(i, j), 1));
						//Parcel.trace("dang");
						numChanges++;
					}

					if (mapProgress.get(counter - 1)[i][j] == 1
							&& getAdjacent(mapProgress.get(counter - 1),
									new CoordPair(i, j)).length < 2) {
						stack.push(new Order(new CoordPair(i, j), 0));
						numChanges++;
					}

				}

			}
			Order current;
			while (!stack.isEmpty()) {
				current = stack.pop();
				blank[current.getLoc().getxCoord()][current.getLoc()
						.getyCoord()] = current.getSetValue();
			}

			
		//	Parcel.trace("1");
			// Checks for a perfect loop from initial conditions
			if (mapProgress.size() > 1) {
		//		Parcel.trace("2");
				if (isSame(0, mapProgress.size() - 1)) {
				//	Parcel.trace("3");
				/*	Parcel.trace("Ini: Period: "
							+ (mapProgress.size() - 1));*/
					Parcel.value = mapProgress.size()-1;
					return mapProgress;
				}

				// Checks for a loop of non dynamic nature
				if (mapProgress.size() > 2) {

					if (isSame(mapProgress.size() - 1, mapProgress.size() - 2)) {
						/*Parcel.trace("stagnant: Period: "
								+ (mapProgress.size() - 1));*/
					//	Parcel.value = mapProgress.size() - 1;
						return mapProgress;
					}
				}

				// Checks for loop forming after an initial state
				if (mapProgress.size() > 1) {
					for (int i = mapProgress.size() - 1; i > 0; i--) {
						for (int j = i - 1; j > 0; j--) {
							if (isSame(i, j)) {
						/*		Parcel.trace("Ini: Formed Period: "
										+ (i - j));*/
								//Parcel.value = - 1;
								return mapProgress;
							}
						}
					}
				}
			}
				mapProgress.add(blank);
				counter++;

			
		}
		Parcel.value = -1;
		return mapProgress;

	}

	public boolean isSame(int a, int b) {
		for (int i = 0; i < mapProgress.get(0).length; i++) {
			for (int j = 0; j < mapProgress.get(0)[i].length; j++) {
				if (mapProgress.get(a)[i][j] != mapProgress.get(b)[i][j]) {
					return false;
				}
			}
		}

		return true;
	}
	





	public CoordPair[] getAdjacent(int[][] map, CoordPair a) {

		ArrayList<CoordPair> adjac = new ArrayList<CoordPair>();

		if (a.getyCoord() + 1 < rootMap[0].length) {
			if (map[a.getxCoord()][a.getyCoord() + 1] == 1) {
				adjac.add(new CoordPair(a.getxCoord(), a.getyCoord() + 1));
			}
		}

		if (a.getxCoord() + 1 < rootMap.length) {
			if (map[a.getxCoord() + 1][a.getyCoord()] == 1) {
				adjac.add(new CoordPair(a.getxCoord() + 1, a.getyCoord()));
			}
		}

		if (a.getyCoord() - 1 >= 0) {
			if (map[a.getxCoord()][a.getyCoord() - 1] == 1) {
				adjac.add(new CoordPair(a.getxCoord(), a.getyCoord() - 1));
			}
		}

		if (a.getxCoord() - 1 >= 0) {
			if (map[a.getxCoord() - 1][a.getyCoord()] == 1) {
				adjac.add(new CoordPair(a.getxCoord() - 1, a.getyCoord()));
			}
		}
		//

		if (a.getxCoord() + 1 < rootMap.length
				&& a.getyCoord() + 1 < rootMap[0].length) {
			if (map[a.getxCoord() + 1][a.getyCoord() + 1] == 1) {
				adjac.add(new CoordPair(a.getxCoord() + 1, a.getyCoord() + 1));
			}
		}
		if (a.getxCoord() - 1 > -1 && a.getyCoord() - 1 > -1) {
			if (map[a.getxCoord() - 1][a.getyCoord() - 1] == 1) {
				adjac.add(new CoordPair(a.getxCoord() - 1, a.getyCoord() - 1));
			}
		}

		if (a.getxCoord() + 1 < rootMap.length && a.getyCoord() - 1 > -1) {
			if (map[a.getxCoord() + 1][a.getyCoord() - 1] == 1) {
				adjac.add(new CoordPair(a.getxCoord() + 1, a.getyCoord() - 1));
			}
		}

		if (a.getxCoord() - 1 > -1 && a.getyCoord() + 1 < rootMap[0].length) {
			if (map[a.getxCoord() - 1][a.getyCoord() + 1] == 1) {
				adjac.add(new CoordPair(a.getxCoord() + 1, a.getyCoord() - 1));
			}
		}

		//

		CoordPair[] reto = new CoordPair[adjac.size()];
		for (int k = 0; k < adjac.size(); k++) {
			reto[k] = adjac.get(k);
		}
		return reto;
	}

	public int[][] getRootMap() {
		return rootMap;
	}

	public void setRootMap(int[][] rootMap) {
		this.rootMap = rootMap;
	}

	public ArrayList<int[][]> getMapProgress() {
		return mapProgress;
	}

	public void setMapProgress(ArrayList<int[][]> mapProgress) {
		this.mapProgress = mapProgress;
	}

	public Stack<Order> getStack() {
		return stack;
	}

	public void setStack(Stack<Order> stack) {
		this.stack = stack;
	}

}
