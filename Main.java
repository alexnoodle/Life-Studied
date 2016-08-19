import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class Main {
	public static void main(String[] args) {
		/*
		SaveLoadv2 b = new SaveLoadv2();
		File c = new File("C:\\Users\\Alex\\Desktop\\Life Studied\\LifeStudied\\SecondSaved.txt");
		try{
			Tester a = new Tester(b.load(c));
		}
		catch (Exception e){
			System.out.println("Fuck");
		}
		
		SaveLoadv2 a = new SaveLoadv2();
		String[] contents = new String[]{"a","a","a","a"};
		a.load(a.save("C:\\Users\\Jack\\Desktop", "testFile.txt", contents));
		
		SaveLoadv2 go = new SaveLoadv2();
		Simulator i = new Simulator();
		
		File file = new File("C:\\Users\\Alex\\Desktop\\Life Studied\\LifeStudied\\SavedRun.txt");
		go.save(file, i.run());
		try {
			Tester a = new Tester(go.load(file));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
		//File file = new File("C:\\Users\\Alex\\Desktop\\Life Studied\\LifeStudied\\Build.txt");
		File fileTwo = new File("C:\\Users\\Alex\\Desktop\\Life Studied\\LifeStudied\\SecondSaved.txt");
		SaveLoadv2 b = new SaveLoadv2();
		//Simulator a = new Simulator(b.load(file).get(0));
		//b.save(fileTwo, a.run());
		patternCatcher.analyze(10, b.load(fileTwo));
	
		System.out.println("Done");
		
	}
	public static void look(String filePath){
		SaveLoadv2 a = new SaveLoadv2();
		File f = new File(filePath);
		File[] b = f.listFiles();
		Tester c;
		for(int i = 0; i < b.length; i ++){
			try{
				ArrayList<int[][]> g = a.load(b[i]);
				c= new Tester(g);
				Thread.sleep((g.size() * 100) + 500);
				System.out.println(b[i].getPath());
			}
			catch(Exception e){
				System.out.println("It was not possible to load everything");
			}
		}
	}
}
