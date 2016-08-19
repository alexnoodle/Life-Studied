import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class Main {
	public static void main(String[] args) {
		
		
		SaveLoadv2 a = new SaveLoadv2();
		String[] contents = new String[]{"a","a","a","a"};
		a.load(a.save("C:\\Users\\Jack\\Desktop", "testFile.txt", contents));
		
		System.out.println("Done");
	}
}
