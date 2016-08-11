import java.util.ArrayList;

public class patternCatcher {

	public static int[][] makeFrame(int[][] bigPicture, int x1, int x2, int y1,
			int y2) {
		int[][] newPic = new int[x2 - x1 + 1][y2 - y1 + 1];

		for (int x = x1; x <= x2; x++) {
			for (int y = y1; y <= y2; y++) {
				// System.out.println(x-x1 + " " + );
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
		ArrayList<Shape> shapes = new ArrayList<Shape>();
		int dim = oMap.get(0).length;
		for (int x1 = 8; x1 < dim; x1++) {
			// System.out.println("Loop 1:");
			 for (int y1 = 8; y1 < dim; y1++) {
				
				// System.out.println("Loop 2:");
				 here:for (int x2 = x1 + 1; x2 < dim; x2++) {
					// System.out.println("Loop 3:");
					for (int y2 = y1 + 1; y2 < dim; y2++) {
						// System.out.println("Loop 4:");
						help:
						for (int period = 1; period <= maxPeriod; period++) {
							
							
							// System.out.println("Loop 5:");

							for (int startFrame = 0; startFrame < oMap.size()
									- period; startFrame++) {
								if(oMap.get(startFrame)[x1][x2] == 0)//break here;
								System.out.println(x1 + "." + y1 + "." + x2+ "." + y2+ "." + period);
								// System.out.println("Loop 6:");
								ArrayList<int[][]> map = makeFrameList(oMap,
										x1, x2, y1, y2);
								boolean pattern;
								boolean notBlank;
								if (period % 2 == 1) {
									// odd
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
									// even
									pattern = true;
									notBlank = false;
									outerloop: for (int i = 0; i < period / 2; i++) {
										for (int j = x1; j <= x2; j++) {
											for (int k = y1; k <= y2; k++) {
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
								}
								if (pattern && notBlank) {

									Simulator sim = new Simulator(
											map.get(startFrame));// Need to
																	// modify
																	// start
																	// frame
																	// to
																	// size
									Parcel.value = 0;
									sim.run();
									if (Parcel.value == period) {

										ArrayList<Integer> xDeviation = new ArrayList<Integer>();
										ArrayList<Integer> yDeviation = new ArrayList<Integer>();
										for (int x = x1; x <= x2; x++) {
											for (int y = x1; y <= y2; y++) {
												if (map.get(0)[x][y] == 1) {

													xDeviation.add(x);
													yDeviation.add(y);
												}
											}
										}
										int xAdjust = map.get(0).length;
										int yAdjust = map.get(0).length;

										for (int i = 0; i < xDeviation.size(); i++) {
											if (xDeviation.get(i) < xAdjust
													&& yDeviation.get(i) < yAdjust) {

												xAdjust = xDeviation.get(i);
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
										for (Shape c : shapes) {
											if (c.getxDeviation().size() == temp
													.getxDeviation().size()
													|| c.getyDeviation().size() == temp
															.getyDeviation()
															.size()) {
												dupe = true;
												System.out.println("dupe");
												break;
											}
											for(int i = 0; i < c.getxDeviation().size(); i++){
												if(c.getxDeviation().get(i) != temp.getxDeviation().get(i)){
													dupe = true;
													System.out.println("dupe");
													break;
												}
												if(c.getyDeviation().get(i) != temp.getyDeviation().get(i)){
													dupe = true;
													System.out.println("dupe");
													break;
												}
												if(c.getAoexDeviation().get(i) != temp.getAoexDeviation().get(i)){
													dupe = true;
													System.out.println("dupe");
													break;
												}
												if(c.getAoeyDeviation().get(i) != temp.getAoeyDeviation().get(i)){
													dupe = true;
													System.out.println("dupe");
													break;
												}
												
											}
										}
										if (!dupe) {
											shapes.add(temp);
											System.out.println("Shape Created!");
											break help;
										}

									}

									// pattern = false;
								}

							}
						}
					}
				}
			}
		}

		System.out.println("Shapes added: " + shapes.size());
		return shapes;
	}
}
