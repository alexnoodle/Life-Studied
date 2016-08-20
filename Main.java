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
/*
		ArrayList<Integer> x = new ArrayList<Integer>();
		ArrayList<Integer> y = new ArrayList<Integer>();
		x.add(0);
		x.add(0);
		x.add(1);
		x.add(1);
		y.add(1);
		y.add(0);
		y.add(1);
		y.add(0);
		
		Spock.calculateTree(new Shape(x,y), 0);*/
		massPatternCatch(2);
		System.out.println("done");
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
				System.out.println(b[i].getPath());

			} catch (Exception e) {
				System.out.println("Zues!");
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
			// System.out.println(listing[i].getPath());
			temp = loader.load(listing[i]);
			boolean bandit = true;
			for (int j = 0; j < shapes.size(); j++) {
				if (isSame(temp.get(0), shapes.get(j))) {
					System.out.println("Bandit");
					bandit = false;
					break;
				}
			}
			if (bandit) {
				shapes.add(temp.get(0));
			} else {
				listing[i].delete();
				System.out.println("Mr Clean");
			}
		}
		return shapes.size();
	}

	public static void massPatternCatch(int dimension) {
		DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
		Date dateobj = new Date();
		
		String endReport = "";
		endReport += "Start Time: " + df.format(dateobj) + "\n";
		File folder = new File("C:\\Users\\Jack\\Desktop\\Cullen\\tempDataDump\\massAnalyzeResults(D:" + dimension);
		
		System.out.println(folder.mkdirs());
		
			
		
		int square = (int) Math.pow(dimension, 2);
		int max = 0;
		for (int i = 0; i < square; i++) {
			max += Math.pow(2, i);
		}
		System.out.println(max);

		String string;
		int[][] temp = new int[dimension][dimension];
		int percent = 0;
		for (int i = 0; i < (max); i++) {

			string = (String.format("%" + (square) + "s",
					Integer.toBinaryString(i)).replace(" ", "0"));
			// System.out.println(string);

			int count = -1;

			for (int j = 0; j < temp.length; j++) {
				for (int k = 0; k < temp[j].length; k++) {
					count++;
				//	System.out.println(count);
					temp[j][k] = (int) string.charAt(count) - 48;
				}

			}

			Simulator sim = new Simulator(temp);
			ArrayList<Shape> shapes = Spock.patternCatcher(100, sim.run());
			System.out.println(shapes.size());
			for (int j = 0; j < shapes.size(); j++) {
				File file = new File(
						folder.getPath() + i + "."
								+ j + ".txt");
				ArrayList<int[][]> trop = new ArrayList<int[][]>();
				trop.add(shapes.get(j).getMap());
				new SaveLoadv2().save(file, trop);

			}
			if ((double) (i / max) * 100 > percent) {
				System.out.println("Percent Complete: " + (double) (i / max)
						* 100 + "%");
			}
		}
	
	//	endReport += "Shapes found: " + cleanUp(folder.getPath() + "\n" + "End Time: " + df.format(dateobj));
		
	}
}
