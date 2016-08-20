import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class SaveLoadv2 {

	public ArrayList<int[][]> load(File file) {
		ArrayList<int[][]> map = new ArrayList<int[][]>();
		try {
			String verify, putData;
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			verify = br.readLine();
			int dim = 0;
			if(verify != null && !(verify.equals("-1"))){
				dim = Integer.parseInt(verify);
			}
			verify = br.readLine();
			int dim2 = 0;
			if(verify != null && !(verify.equals("-1"))){
				dim2 = Integer.parseInt(verify);
			}
			int[][] blank = new int[dim][dim2];
			while ((verify = br.readLine()) != null) {
				if (verify != null) {
					if(!(verify.equals("-1"))){
						String x = verify;
						verify = br.readLine();
					
						if(verify != null){
						if (!(verify.equals("-1"))) {
							
							blank[Integer.parseInt(x)][Integer.parseInt(verify)] = 1;
						// Do the Thing
					
						}}
					}
					else{
							map.add(blank);
							blank = new int[dim][dim2];
						
					}
					
					map.add(blank);
				}
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return map;
	}

	public File save(String path, String fileName, ArrayList<int[][]> contents) {
		File a = newFile(path, fileName);
		save(a , contents);
		return a;
	}

	public void save(File file, ArrayList<int[][]> contents) {

		try {

			FileWriter fw = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(Integer.toString(contents.get(0).length));
			bw.newLine();
			bw.write(Integer.toString(contents.get(0)[0].length));
			bw.newLine();
			for(int i = 0; i < contents.size(); i++){
				for(int j = 0; j < contents.get(i).length; j ++){
					for(int k = 0; k < contents.get(i)[0].length; k ++){
						if(contents.get(i)[j][k] == 1){
							bw.write(Integer.toString(j));
							bw.newLine();
							bw.write(Integer.toString(k));
							bw.newLine();
						}
					}
				}
				bw.write("-1");
				bw.newLine();
				}

			
			bw.flush();
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public File newFile(String path, String fileName) {
		File f = new File(path + "\\" + fileName);
		try {

			if (f.createNewFile())
				Parcel.trace("File creation successfull");
			else
				System.out
						.println("Error while creating File, file already exists in specified path");

		} catch (IOException io) {
			io.printStackTrace();
		}
		return f;
	}
}
