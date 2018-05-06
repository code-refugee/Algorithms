package dataabstraction;
import edu.princeton.cs.algs4.*;
/*基于String的split()方法实现In的静态方法readInts()*/
public class NewIn{
	public static int[] readInts(String name){
		In in=new In(name);
		String s=in.readAll();
		String[] str=s.split("\\s+");
		int[] a=new int[str.length];
		for(int i=0;i<str.length;i++){
			a[i]=Integer.parseInt(str[i]);
		}
		return a;
	}
}
