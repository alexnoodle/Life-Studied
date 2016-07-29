import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

public class Main {
	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException{
		//SwingControl SwingControl = new SwingControl();  
	    //SwingControl.showEventDemo();
		LoadSave a = new LoadSave();
 		Tester b = new Tester(a.load("SavedRun.txt"));
		
		
		
		
		System.out.println("Done");
	}
}
