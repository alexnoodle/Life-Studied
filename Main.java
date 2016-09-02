import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Main {
	static DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
	
	public static void main(String[] args) {

		Parcel.deBug = true;
		
		
		ArrayList<Integer> x = new ArrayList<Integer>();
		x.add(0);
	/*	x.add(1);
		x.add(1);
		x.add(0);*/

		ArrayList<Integer> y = new ArrayList<Integer>();
		y.add(0);
	/*	y.add(0);
		y.add(1);
		y.add(1);
*/
		Shape shape = new Shape(x, y);
		Tree tree = //Spock.calculateTree(1,shape.getMap());
			(new Tree(new Node(shape.getMap())));
		Spock.extendTree(tree);
		Spock.extendTree(tree);
		Spock.extendTree(tree);

		/*shape.setTree();
		Cullen cullen1 = new Cullen();
		cullen1.solidStore(shape, "name");*/
		

		Date dateobj = new Date();
		Parcel.trace("Done: " +  df.format(dateobj));

	}
	
	

}
