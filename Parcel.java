
public class Parcel {
public static int value;
public static boolean deBug;


public static void trace(String a){
	if(deBug)System.out.println(a);
}
public static void trace(int a){
	if(deBug)System.out.println(a);
}
public static void trace(Boolean a){
	if(deBug)System.out.println(a);
}
public static void setParcel(int a){
	value = a;
}
}
