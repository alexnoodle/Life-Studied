import java.io.File;
import java.util.ArrayList;

public class StaticElec {
	public static ArrayList<int[][]> yes;
	public static void tryIt(File inFile){
		SaveLoadv2 l = new SaveLoadv2();
		ArrayList<int[][]> toOpen = l.load(inFile);
       try{
    	   Tester a = new Tester(toOpen);
       }
       catch(Exception e){
    	   System.out.println("another failure");
       }
		
	}
}
