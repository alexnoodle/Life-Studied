import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

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
		SwingControl go = new SwingControl();
		go.setVisible(true);
		
		
		System.out.println("Done");
		
	}
}
