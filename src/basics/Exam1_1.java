package basics;
import edu.princeton.cs.algs4.*; //这是用来算法一书中作者自己写的API
public class Exam1_1 {
	public static void main(String[] args){
//		int a,b,c;
//		a=b=c=0;
//		StdOut.println("请输入数字");
//		a=StdIn.readInt();
//		b=StdIn.readInt();
//		c=StdIn.readInt();
//		if(equals(a,b,c)==1){
//			StdOut.print("equal");
//		}
//		else{
//			StdOut.print("not equal");
//		}
		int[] a=new int[10];
		for(int i=0;i<10;i++){
			a[i]=9-i;
		}
		for(int i =0;i<10;i++){
			a[i]=a[a[i]];
			
		}
		for(int i=0;i<10;i++){
			System.out.println(i);
		}
	}
	public static int equals(int a,int b,int c){
		if(a==b&&b==c){
			return 1;
		}
		else
			return -1;
	}

}
