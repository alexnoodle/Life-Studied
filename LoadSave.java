import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class LoadSave {
	
	public ArrayList<int[][]> load(String a){
		ArrayList<int[][]> toLoad = new ArrayList<int[][]>();
		try{
			Scanner openFile = null;
			openFile = new Scanner(new File(a));
			int dim;
			dim = openFile.nextInt();
			int[][] blank = new int[dim][dim];
			while(openFile.hasNextLine()){
				int x;
				int y;
				int next;
				next = openFile.nextInt();
				if(!(next == -1)){
					x = next;
					y = openFile.nextInt();
					blank[x][y] = 1;
				}
				else{
					toLoad.add(blank);
					blank = new int[dim][dim];
				}
			}
			openFile.close();
		}
		catch(Exception e){
			System.out.println("Loading didn't work");
		}
		return toLoad;
	}
	
	public void save(ArrayList<int[][]> toSave, String a){
		try{
			PrintWriter writer = new PrintWriter(a, "UTF-8");
			writer.println(toSave.get(0).length);
			for(int i = 0; i < toSave.size(); i++){
				for(int j = 0; j < toSave.get(i).length; j ++){
					for(int k = 0; k < toSave.get(i).length; k ++){
						if(toSave.get(i)[j][k] == 1){
							writer.println(j);
							writer.println(k);
						}
					}
				}
				writer.println("-1");
			}
			writer.println("finished");
			writer.close();
		}
		catch(Exception e){
			System.out.println("Saving didn't work");
		}
	}
}
