package bagstatckqueue;
import edu.princeton.cs.algs4.*;
public class TemporaryTest {
	public static void main(String[] args) {
//		In in=new In(args[0]);
//		String[] s=in.readAllStrings();
//		for(int i=0;i<s.length;i++){
//			System.out.printf(s[i]);
//		}
		String[] a={"1","2","3","4","5","6"};
		System.out.println(a.length);
		a[0]= null;
		a[1]=null;
		System.out.println(a[0]);
		System.out.println(a.length);
	}

}
