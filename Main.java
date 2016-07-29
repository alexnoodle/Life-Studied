import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class Main {
	public static void main(String[] args) throws FileNotFoundException,
			UnsupportedEncodingException {
		// Tester a = new Tester();

		File f = new File("C:\\Users\\Jack\\Desktop\\jack.txt");
		try {
			f.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Builder a = new Builder();
		a.newMap(10);

		a.launchBuilder(null);

		Simulator b = new Simulator(a.getMap().get(0));
				Tester c = new Tester(b.run());
int counter = 0;
				
		
		
		c.go();
		
		
		System.out.println("Done");
	}
}
