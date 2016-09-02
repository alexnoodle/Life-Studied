import java.io.File;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Cullen {

	String path = "C:\\Users\\Jack\\Desktop\\Cullen\\";

	public Cullen() {

	}

	public void solidStore(Shape shape, String shapeName) {
		String folder = path + "Shape Storage\\" + shapeName;
		new File(folder).mkdirs();
		SaveLoadv2 saveLoad = new SaveLoadv2();
		ArrayList<int[][]> format = new ArrayList<int[][]>();
		format.add(shape.getAoe());
		saveLoad.save(folder, shapeName + "Aoe.txt", format);
		format = new ArrayList<int[][]>();
		format.add(shape.getMap());
		saveLoad.save(folder, shapeName + "Map.txt", format);
		int depth = 1;
		recurSaveTree(shape.getTree().root,folder,depth);
		

	}
	public void recurSaveTree(Node node, String path,int depth){
		String localPath = "";
		ArrayList<int[][]> temp;
		for (int i = 0; i < node.underlings.size(); i++) {
			temp = new ArrayList<int[][]>();
		localPath = path + "\\" +i;
		new File(localPath).mkdirs();
		temp.add(node.underlings.get(i).map);
		new SaveLoadv2().save(localPath,depth +" "+ i +".txt",temp);
		recurSaveTree(node.underlings.get(i),localPath, depth+1);
		}
	}

	public Shape retrieveFromStore(String path) {
		SaveLoadv2 load = new SaveLoadv2();

		return new Shape(load.load(new File(path + "\\" + "Aoe.txt")),
				load.load(new File(path + "\\" + "map.txt")),
				load.loadTree(new File(path + "\\" + "tree.txt")));
	}

	public static void analyzeAndClean(String path, int dimension) {
		massPatternCatch(dimension, path);
		cleanUp(path + "shapePatterns" + dimension);

	}

	public static void look(String filePath) {
		SaveLoadv2 a = new SaveLoadv2();
		File f = new File(filePath);
		File[] b = f.listFiles();
		Tester c;
		for (int i = 0; i < b.length; i++) {
			try {
				ArrayList<int[][]> g = a.load(b[i]);
				c = new Tester(g);
				Thread.sleep(g.size() * 100 + 500);
				Parcel.trace(b[i].getPath());

			} catch (Exception e) {
				Parcel.trace("Zues!");
			}
		}
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

	public static int cleanUp(String path) {
		File directory = new File(path);
		File[] listing = directory.listFiles();
		ArrayList<int[][]> shapes = new ArrayList<int[][]>();
		ArrayList<int[][]> temp;
		SaveLoadv2 loader = new SaveLoadv2();

		for (int i = 0; i < listing.length; i++) {
			// Parcel.trace(listing[i].getPath());
			if (!listing[i].getName().equals("endReport.txt")) {
				temp = loader.load(listing[i]);
				boolean bandit = true;

				for (int j = 0; j < shapes.size(); j++) {

					if (isSame(temp.get(0), shapes.get(j))) {
						// Parcel.trace("Bandit");
						bandit = false;
						break;
					}
				}
				if (bandit) {
					shapes.add(temp.get(0));
				} else {
					listing[i].delete();
					Parcel.trace("Mr Clean");
				}
			}
		}
		return shapes.size();
	}

	public static void massPatternCatch(int dimension, String path) {
		String endReport = "";
		String folder = path + "\\shapePatterns" + dimension;
		DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
		Date dateobj = new Date();

		endReport += "Start Time: " + df.format(dateobj) + "\n ";

		Parcel.trace(new File(folder).mkdir());
		int square = (int) Math.pow(dimension, 2);
		int max = 0;
		for (int i = 0; i < square; i++) {
			max += Math.pow(2, i);
		}
		// Parcel.trace(max);

		String string;
		int[][] temp = new int[dimension][dimension];
		int percent = 0;
		for (int i = 0; i < (max); i++) {

			string = (String.format("%" + (square) + "s",
					Integer.toBinaryString(i)).replace(" ", "0"));
			// Parcel.trace(string);

			int count = -1;

			for (int j = 0; j < temp.length; j++) {
				for (int k = 0; k < temp[j].length; k++) {
					count++;
					temp[j][k] = (int) string.charAt(count) - 48;
				}

			}

			Simulator sim = new Simulator(temp);
			ArrayList<Shape> shapes = Spock.patternCatch(100, sim.run());
			for (int j = 0; j < shapes.size(); j++) {
				File file = new File(folder + "\\" + i + "." + j + ".txt");
				ArrayList<int[][]> trop = new ArrayList<int[][]>();
				trop.add(shapes.get(j).getMap());
				new SaveLoadv2().save(file, trop);

			}

		}
		dateobj = new Date();
		String end = df.format(dateobj);
		endReport += "End TIme: " + end;
		new SaveLoadv2().makeTxT(folder, "endReport.txt", endReport);
	}

}
