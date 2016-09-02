import java.util.ArrayList;
import java.util.Stack;

public class Spock {

	public static int[][] makeFrame(int[][] bigPicture, int x1, int x2, int y1,
			int y2) {
		int[][] newPic = new int[x2 - x1 + 1][y2 - y1 + 1];

		for (int x = x1; x <= x2; x++) {
			for (int y = y1; y <= y2; y++) {
				// Parcel.trace(x-x1 + " " + );
				newPic[x - x1][y - y1] = bigPicture[x][y];
			}
		}

		return newPic;
	}

	public static ArrayList<int[][]> makeFrameList(ArrayList<int[][]> og,
			int x1, int x2, int y1, int y2) {
		ArrayList<int[][]> newFrame = new ArrayList<int[][]>();
		for (int[][] a : og) {
			newFrame.add(makeFrame(a, x1, x2, y1, y2));
		}

		return newFrame;

	}

	public static ArrayList<Shape> patternCatch(int maxPeriod,
			ArrayList<int[][]> oMap) {

		ArrayList<Shape> shapes = new ArrayList<Shape>();
		int dim = oMap.get(0).length;
		for (int x1 = 0; x1 < dim; x1++) {
			Parcel.trace("Loop 1:");
			for (int y1 = 0; y1 < dim; y1++) {

				Parcel.trace("Loop 2:");
				here: for (int x2 = x1 + 1; x2 < dim; x2++) {
					Parcel.trace("Loop 3:");
					for (int y2 = y1 + 1; y2 < dim; y2++) {

						Parcel.trace("Loop 4:");
						help: for (int period = 1; period <= maxPeriod; period++) {

							Parcel.trace("Loop 5:");

							for (int startFrame = 0; startFrame < oMap.size()
									- period; startFrame++) {
								if (getAdjacent(oMap.get(startFrame),
										new CoordPair(x1, y1)).length == 0)
									break here;
								if (getAdjacent(oMap.get(startFrame),
										new CoordPair(x2, y2)).length == 0)
									break here;

								Parcel.trace(x1 + "." + y1 + "." + x2 + "."
										+ y2 + "." + period);
								Parcel.trace("Loop 6:");
								ArrayList<int[][]> map = makeFrameList(oMap,
										x1, x2, y1, y2);
								boolean pattern;
								boolean notBlank;
								if (period == 1) {

									pattern = true;
									notBlank = false;
									for (int j = x1; j < x2; j++) {
										for (int k = y1; k < y2; k++) {

											if (map.get(startFrame)[j - x1][k
													- y1] != map.get(startFrame
													+ period)[j - x1][k - y1]) {
												pattern = false;
											}
											if (map.get(startFrame)[j - x1][k
													- y1] == 1) {
												notBlank = true;
											}

										}
									}

								} else if (period % 2 == 1) {
									Parcel.trace("Odd Period");

									pattern = true;
									notBlank = false;
									outerloop: for (int i = 0; i < (period - 1) / 2; i++) {
										for (int j = x1; j < x2; j++) {
											for (int k = y1; k < y2; k++) {

												if (map.get(startFrame + i)[j
														- x1][k - y1] != 0) {
													notBlank = true;
												}
												if (map.get(startFrame + i)[j
														- x1][k - y1] != map
														.get(startFrame
																+ period - i)[j
														- x1][k - y1]) {
													pattern = false;
													break outerloop;
												}
											}
										}
									}

								} else {
									Parcel.trace("Even Period");

									pattern = true;
									notBlank = false;
									outerloop: for (int i = 0; i < period / 2; i++) {
										for (int j = x1; j <= x2; j++) {
											for (int k = y1; k <= y2; k++) {
												if (map.get(startFrame + i)[j
														- x1][k - y1] != 0) {
													Parcel.trace("Selection is not blank");
													notBlank = true;
												}

												if (map.get(startFrame + i)[j
														- x1][k - y1] != map
														.get(startFrame
																+ period - i)[j
														- x1][k - y1]) {
													Parcel.trace("Section is not patter for period "
															+ period);
													pattern = false;
													break outerloop;
												}
											}
										}
									}
								}

								if (pattern && notBlank) {
									Parcel.trace("Pattern Found");

									Simulator sim = new Simulator(
											map.get(startFrame));
									Parcel.value = -1;
									sim.run();
									// Parcel.trace(Parcel.value);
									if (Parcel.value == period) {
										Parcel.trace("Simulator confirms period");
										ArrayList<Integer> xDeviation = new ArrayList<Integer>();
										ArrayList<Integer> yDeviation = new ArrayList<Integer>();
										for (int x = 0; x <= x2 - x1; x++) {
											for (int y = 0; y <= y2 - y1; y++) {
												if (map.get(startFrame)[x][y] == 1) {

													xDeviation.add(x);
													yDeviation.add(y);
												}
											}
										}
										int xAdjust = map.get(0).length;
										int yAdjust = map.get(0).length;

										for (int i = 0; i < xDeviation.size(); i++) {
											if (xDeviation.get(i) < xAdjust) {

												xAdjust = xDeviation.get(i);

											}
											if (yDeviation.get(i) < yAdjust) {
												yAdjust = yDeviation.get(i);
											}

										}

										for (int i = 0; i < xDeviation.size(); i++) {
											xDeviation.set(i, xDeviation.get(i)
													- xAdjust);
											yDeviation.set(i, yDeviation.get(i)
													- yAdjust);
										}

										Shape temp = new Shape(xDeviation,
												yDeviation);
										boolean dupe = false;
										if (shapes.size() != 0) {
											dupe = true;
											for (int l = -1; l < shapes.size(); l++) {
												if (l == -1) {
													l = 0;
												}
												Shape c = shapes.get(l);
												int tempCount = 0;
												int cCount = 0;

												for (int i = 0; i < temp
														.getxDeviation().size(); i++) {
													if (temp.getxDeviation()
															.get(i) != 0
															|| temp.getyDeviation()
																	.get(i) != 0) {
														tempCount++;

													}
												}

												for (int i = 0; i < c
														.getxDeviation().size(); i++) {
													if (c.getxDeviation()
															.get(i) != 0
															|| c.getyDeviation()
																	.get(i) != 0) {
														cCount++;

													}
												}
												if (cCount == tempCount) {
													dupe = false;
													Parcel.trace("dupe1");
													break;
												}
												for (int i = 0; i < c
														.getxDeviation().size(); i++) {

													if (c.getxDeviation()
															.get(i) != temp
															.getxDeviation()
															.get(i)) {
														dupe = false;
														Parcel.trace("dupe2");
														break;
													}
													if (c.getyDeviation()
															.get(i) != temp
															.getyDeviation()
															.get(i)) {
														dupe = false;
														Parcel.trace("dupe3");
														break;
													}
												}
											}
										}

										boolean go = true;
										for (int i = 0; i < shapes.size(); i++) {
											if (isSame(temp.getxDeviation(),
													shapes.get(i)
															.getxDeviation())) {
												go = false;
												break;
											}
											if (isSame(temp.getyDeviation(),
													shapes.get(i)
															.getyDeviation())) {
												go = false;
												break;
											}
										}
										if (go) {
											shapes.add(temp);
											Parcel.trace("Shape Created!");

											break help;
										}

									}

									pattern = false;
								}

							}

						}
					}
				}
			}

		}

		Parcel.trace("Shapes added: " + shapes.size());

		return shapes;
	}

	public static CoordPair[] getAdjacent(int[][] map, CoordPair a) {

		ArrayList<CoordPair> adjac = new ArrayList<CoordPair>();

		if (a.getyCoord() + 1 < map[0].length) {
			// Parcel.trace(a.getxCoord());
			if (map[a.getxCoord()][a.getyCoord() + 1] == 1) {
				adjac.add(new CoordPair(a.getxCoord(), a.getyCoord() + 1));
			}
		}

		if (a.getxCoord() + 1 < map.length) {
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

		if (a.getxCoord() + 1 < map.length && a.getyCoord() + 1 < map[0].length) {
			if (map[a.getxCoord() + 1][a.getyCoord() + 1] == 1) {
				adjac.add(new CoordPair(a.getxCoord() + 1, a.getyCoord() + 1));
			}
		}
		if (a.getxCoord() - 1 > -1 && a.getyCoord() - 1 > -1) {
			if (map[a.getxCoord() - 1][a.getyCoord() - 1] == 1) {
				adjac.add(new CoordPair(a.getxCoord() - 1, a.getyCoord() - 1));
			}
		}

		if (a.getxCoord() + 1 < map.length && a.getyCoord() - 1 > -1) {
			if (map[a.getxCoord() + 1][a.getyCoord() - 1] == 1) {
				adjac.add(new CoordPair(a.getxCoord() + 1, a.getyCoord() - 1));
			}
		}

		if (a.getxCoord() - 1 > -1 && a.getyCoord() + 1 < map[0].length) {
			if (map[a.getxCoord() - 1][a.getyCoord() + 1] == 1) {
				adjac.add(new CoordPair(a.getxCoord() + 1, a.getyCoord() - 1));
			}
		}

		CoordPair[] reto = new CoordPair[adjac.size()];
		for (int k = 0; k < adjac.size(); k++) {
			reto[k] = adjac.get(k);
		}
		return reto;
	}

	public static boolean isSame(int[][] a, int[][] b) {

		if (a.length != b.length || a[0].length != b[0].length) {
			return false;
		}
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < b[i].length; j++) {
				if (a[i][j] != b[i][j]) {
					return false;
				}
			}
		}

		return true;
	}

	public static boolean isSame(ArrayList<Integer> a, ArrayList<Integer> b) {

		if (a.size() != b.size())
			return false;
		for (int i = 0; i < a.size(); i++) {
			if (a.get(i) != b.get(i))
				return false;
		}
		return true;
	}

	public static Tree calculateTree(int maxDepth, int[][] rootMap) {

		Node root = new Node(rootMap);
		root.map = rootMap.clone();
		root.loadUnderlings(getUnderlings(rootMap, 1, maxDepth));

		return new Tree(root);
	}

	public static boolean thisAndOnly(int[][] toSearch, int[][] searchFor) {

		for (int i = 0; i < toSearch.length; i++) {
			for (int j = 0; j < toSearch[i].length; j++) {
				// Parcel.trace(i + " " + j);
				if (i == 0 || i == toSearch.length - 1) {

					if (toSearch[i][j] == 1) {

						return false;
					}
				} else if (j == 0 || j == toSearch[i].length - 1) {

					if (toSearch[i][j] == 1) {
						return false;
					}
				} else {

					if (searchFor[i - 1][j - 1] != toSearch[i][j]) {
						// Parcel.trace(searchFor[k][l]);

						return false;

					}
				}

			}
		}

		return true;
	}

	public static ArrayList<Node> getUnderlings(int[][] map, int depth,
			int maxDepth) {
		Parcel.trace("New Call" + depth);
		int[][] blank = new int[map.length + 2][map[0].length + 2];
		ArrayList<Node> underlings = new ArrayList<Node>();
		int square = blank.length * blank[0].length;
		int[][] nextStep;
		int max = 0;
		for (int i = 0; i < square; i++) {
			max += Math.pow(2, i);
		}

		String string;

		int percent = 0;
		Parcel.trace(max);
		for (int i = 0; i < max; i++) {

			blank = new int[map.length + 2][map[0].length + 2];
			string = (String.format("%" + (square) + "s",
					Integer.toBinaryString(i)).replace(" ", "0"));
			// Parcel.trace(string);

			int count = -1;

			for (int j = 0; j < blank.length; j++) {
				for (int k = 0; k < blank[j].length; k++) {
					count++;
					blank[j][k] = (int) string.charAt(count) - 48;
				}

			}
			nextStep = returnNextStep(blank);
			if (thisAndOnly(nextStep, map)) {
				Node next = new Node(blank);
				if (depth + 1 <= maxDepth) {
					next.loadUnderlings(getUnderlings(blank, depth + 1,
							maxDepth));
				}
				// Parcel.trace(i);
				underlings.add(next);
			}

		}

		return underlings;
	}

	public static Tree extendTree(Tree tree){
		ArrayList<Node> workingSet = tree.root.getLowest();
		
		
		int blankX = workingSet.get(0).map.length+2;
		int blankY = workingSet.get(0).map[0].length+2;
		int[][] blank = new int[blankX][blankY];
		
		int square = blankX * blankY;
		int max = 0;
		for (int i = 0; i < square; i++) {
			max += Math.pow(2, i);
		}
		
		String string;
		Parcel.trace(max);
		for (int i = 0; i < max; i++) {
			blank = new int[blankX][blankY];
			string = (String.format("%" + (square) + "s",
					Integer.toBinaryString(i)).replace(" ", "0"));

			int count = -1;

			for (int l = 0; l < blank.length; l++) {
				for (int k = 0; k < blank[l].length; k++) {
					count++;
					blank[l][k] = (int) string.charAt(count) - 48;
				}

			}
			int[][] nextStep = returnNextStep(blank);
		for(int l = 0; l < workingSet.size(); l++){
			if (thisAndOnly(nextStep, workingSet.get(l).map)) {
				workingSet.get(l).underlings.add(new Node(blank));
			}
		}
		
		
		
		
		}
		return tree;
	}
	
	@SuppressWarnings("unchecked")
	public static int[][] returnNextStep(int[][] map) {
		int counter = -1;
		Stack stack = new Stack();
		int[][] blank = new int[map.length][map[0].length];
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {

				if (map[i][j] == 0
						&& getAdjacent(map, new CoordPair(i, j)).length == 3) {
					stack.push(new Order(new CoordPair(i, j), 1));

				}

				if (map[i][j] == 1
						&& getAdjacent(map, new CoordPair(i, j)).length > 3) {
					stack.push(new Order(new CoordPair(i, j), 0));

				}

				if (map[i][j] == 1
						&& getAdjacent(map, new CoordPair(i, j)).length == 2
						|| getAdjacent(map, new CoordPair(i, j)).length == 3) {
					stack.push(new Order(new CoordPair(i, j), 1));
					// Parcel.trace("dang");

				}

				if (map[i][j] == 1
						&& getAdjacent(map, new CoordPair(i, j)).length < 2) {
					stack.push(new Order(new CoordPair(i, j), 0));

				}

			}

		}
		Order current;
		while (!stack.isEmpty()) {
			current = (Order) stack.pop();
			blank[current.getLoc().getxCoord()][current.getLoc().getyCoord()] = current
					.getSetValue();
		}

		return blank;
	}

	public ArrayList<ArrayList<Node>> returnChildren(ArrayList<Node> root,
			int maxDepth, int depth) {
		ArrayList<Node> children = new ArrayList<Node>();

		int blankX = root.get(0).map.length;
		int blankY = root.get(0).map[0].length;
		int[][] blank = new int[blankX][blankY];

		ArrayList<ArrayList<Node>> wow = new ArrayList<ArrayList<Node>>();

		int square = blank.length * blank[0].length;
		int max = 0;
		for (int i = 0; i < square; i++) {
			max += Math.pow(2, i);
		}

		String string;

		int percent = 0;
		Parcel.trace(max);
		for (int i = 0; i < max; i++) {
			blank = new int[blankX][blankY];
			string = (String.format("%" + (square) + "s",
					Integer.toBinaryString(i)).replace(" ", "0"));

			int count = -1;

			for (int l = 0; l < blank.length; l++) {
				for (int k = 0; k < blank[l].length; k++) {
					count++;
					blank[l][k] = (int) string.charAt(count) - 48;
				}

			}
			int[][] nextStep = returnNextStep(blank);
			Node toAdd;
			for (int j = 0; j < root.size(); j++) {

				if (thisAndOnly(nextStep, root.get(j).map)) {

					wow.get(j).add(new Node(blank));

				}

			}

		}
		ArrayList<Node> passIn = new ArrayList<Node>();
		for(int i = 0; i < wow.size(); i++){
			for(int j = 0; j < wow.get(i).size(); j++){
				passIn.add(wow.get(i).get(j));
			}
		}
		
		if (depth + 1 <= maxDepth) {
			ArrayList<ArrayList<Node>> againWow = returnChildren(passIn, maxDepth, depth +1);
			for(int i = 0; i < wow.size(); i++){
				for(int j = 0; j < againWow.get(i).size(); j++){
					
				}
			}
		}

		return null;
	}

}
