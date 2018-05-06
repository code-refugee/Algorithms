package basics;
import edu.princeton.cs.algs4.*;
public class Exam1_1_5 {
	public static void main(String[] args){
//		double x;
//		double y;
//		StdOut.println("请输入两个double数");
//		x=StdIn.readDouble();
//		y=StdIn.readDouble();
//		StdOut.print(Compare(x,y));
//		System.out.println('b');
//		System.out.println('b'+'c');
//		System.out.println((char)('a'+4));
		System.out.println(binaryString(3));
	}
//	public static boolean Compare(double x,double y){
//		if(x>0&&x<1&&y>0&&y<1){
//			return true;
//		}
//		else
//			return false;
//	}
	public static String binaryString(int n){
		String s="";
		for(int i=31;i>=0;i--){
			s=s+(n>>i&1);
		}
		return s;
	}

}
