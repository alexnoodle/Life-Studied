import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Main {
	public static void main(String[] args) {

		
		
		
		
		
		
		Parcel.trace("Done");

	}
	
	public static void analyzeAndClean(String path, int dimension){
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
			if(!listing[i].getName().equals( "endReport.txt")){
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
				File file = new File(folder + "\\"+ i + "." + j + ".txt");
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
