import java.util.ArrayList;

public class patternCatcher {

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

	public static ArrayList<Shape> analyze(int maxPeriod,
			ArrayList<int[][]> oMap) {
		ArrayList<Integer> deadZoneYIn = new ArrayList<Integer>();
		ArrayList<Integer> deadZoneXIn = new ArrayList<Integer>();
		ArrayList<Integer> deadZoneXOut = new ArrayList<Integer>();
		ArrayList<Shape> shapes = new ArrayList<Shape>();
		int dim = oMap.get(0).length;
		for (int x1 = 0; x1 < dim; x1++) {
			 Parcel.trace("Loop 1:");
			 for (int y1 = 0; y1 < dim; y1++) {
				
				 Parcel.trace("Loop 2:");
				 here:for (int x2 = x1 + 1; x2 < dim; x2++) {
					 Parcel.trace("Loop 3:");
					for (int y2 = y1 + 1; y2 < dim; y2++) {
						for(int i = 0; i < deadZoneYIn.size(); i++){
							/*if(y1 == deadZoneYIn.get(i) && x1 == deadZoneXIn.get(i)){
								x1 = deadZoneXOut.get(i);
								x2 = deadZoneXOut.get(i);
								break;
							}*/
						}
						 Parcel.trace("Loop 4:");
						help:
						for (int period = 1; period <= maxPeriod; period++) {
							
							
							 Parcel.trace("Loop 5:");

							for (int startFrame = 0; startFrame < oMap.size()
									- period; startFrame++) {
								if(getAdjacent(oMap.get(startFrame), new CoordPair(x1,y1)).length == 0)break here;
								if(getAdjacent(oMap.get(startFrame), new CoordPair(x2,y2)).length == 0)break here;
								
								Parcel.trace(x1 + "." + y1 + "." + x2+ "." + y2+ "." + period);
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
								//	Parcel.trace(Parcel.value);
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
										if(shapes.size() != 0){
										dupe = true;
										for (int l = -1; l < shapes.size(); l++) {
											if( l == -1){
												l = 0;
											}
											Shape c = shapes.get(l);
											int tempCount = 0;
											int cCount = 0;
											
											for(int i = 0; i < temp.getxDeviation().size();i++){
												if(temp.getxDeviation().get(i) != 0||temp.getyDeviation().get(i)!=0){
													tempCount++;
													
												}
											}
											
											for(int i = 0; i < c.getxDeviation().size();i++){
												if(c.getxDeviation().get(i) != 0||c.getyDeviation().get(i)!=0){
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
												
												if (c.getxDeviation().get(i) != temp
														.getxDeviation().get(i)) {
													dupe = false;
													Parcel.trace("dupe2");
													break;
												}
												if (c.getyDeviation().get(i) != temp
														.getyDeviation().get(i)) {
													dupe = false;
													Parcel.trace("dupe3");
													break;
												}
											}
											}
										}
										
										boolean go = true;
										for(int i = 0; i < shapes.size(); i++){
											if(isSame(temp.getxDeviation(), shapes.get(i).getxDeviation()))
											{
												go = false;
												break;
												}
											if(isSame(temp.getyDeviation(), shapes.get(i).getyDeviation()))
											{
												go = false;
												break;
												}
											}
										if (go) {
											shapes.add(temp);
											Parcel.trace("Shape Created!");
											
											
											for(int y = y1; y <= y2; y++){
												deadZoneYIn.add(y);
												deadZoneXIn.add(x1);
												deadZoneXOut.add(x2);
											}
									//		Parcel.trace("hi");
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
	//	Parcel.trace(a.getxCoord());
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
	
	public static boolean isSame(ArrayList<Integer> a, ArrayList<Integer> b){
		if(a.size() != b.size()) return false;
		for(int i = 0; i < a.size(); i++){
			if(a.get(i) != b.get(i))return false;
		}
		return true;
	}
}
