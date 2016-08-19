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
			while ((verify = br.readLine()) != null) {
				if (verify != null) {
					// Do the Thing
					map.add(stringToMap(verify));
					System.out.println(verify);
				}
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return map;
	}

	public int[][] stringToMap(String verify){
		
		//Do the thing
		return null;
	}
	public File save(String path, String fileName, String[] contents) {
		File a = newFile(path, fileName);
		save(a , contents);
		return a;
	}

	public void save(File file, String[] contents) {

		try {

			FileWriter fw = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(fw);
			for (String a : contents) {
				bw.write(a);
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
				System.out.println("File creation successfull");
			else
				System.out
						.println("Error while creating File, file already exists in specified path");

		} catch (IOException io) {
			io.printStackTrace();
		}
		return f;
	}
}
