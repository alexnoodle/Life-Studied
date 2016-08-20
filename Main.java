import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class Main {
	public static void main(String[] args) {
		
		
		SaveLoadv2 a = new SaveLoadv2();
		File f = new File(filePath);
		File[] b = f.listFiles();
		Tester c;
		for(int i = 0; i < b.length;i++){
			try{
				ArrayList<int[][]> g = a.load(b[i]);
				c = new Tester(g);
				Thread.sleep(g.size()*100 + 500);
				System.out.println(b[i].getPath());
				
			}catch(Exception e){
				System.out.println("Zues!");
			}
		}
	}
	
	
	public static boolean isSame(int[][] a,int[][] b) {
		
		if(a.length != b.length || a[0].length != b[0].length ){
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
	public static void cleanUp(String path){
		File directory = new File(path);
		File[] listing = directory.listFiles();
		ArrayList<int[][]> shapes = new ArrayList<int[][]>();
		ArrayList<int[][]> temp;
		SaveLoadv2 loader = new SaveLoadv2();
		
		for(int i = 0; i < listing.length; i++){
			//System.out.println(listing[i].getPath());
			temp = loader.load(listing[i]);
			boolean bandit = true;
			for(int j = 0; j < shapes.size(); j++){
				if(isSame(temp.get(0),shapes.get(j))){
					System.out.println("Bandit");
					bandit = false;
					break;
				}
			}
			if(bandit){
				shapes.add(temp.get(0));
			}else{
				listing[i].delete();
				System.out.println("Mr Clean");
			}
		}
	}

	public static void massAnalyze(int dimension) {
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
			//System.out.println(string);
			
			int count = -1;
			
			for (int j = 0; j < temp.length; j++) {
				for (int k = 0; k < temp[j].length; k++) {
					count++;
					temp[j][k] = (int) string.charAt(count)-48;
				}
				
			}

		
		Simulator sim = new Simulator(temp);
		ArrayList<Shape> shapes = patternCatcher.analyze(100, sim.run());
		for (int j = 0; j < shapes.size(); j++) {
			File file = new File(
					"C:\\Users\\Jack\\Desktop\\data dump\\file" +i  + "."
							+ j + ".txt");
			ArrayList<int[][]> trop = new ArrayList<int[][]>();
			trop.add(shapes.get(j).getMap());
			new SaveLoadv2().save(file, trop);


		}
		if((double)(i/max)*100 > percent){
		System.out.println("Percent Complete: " + (double)(i/max)*100 + "%");
		}
	}}
}
