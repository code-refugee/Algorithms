package basics;
import edu.princeton.cs.*;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
/*打印出一个M行N列的二维数组转置*/
public class Exam1_1_13 {
	public static void main(String[] args){
		int m=5;//行
		int n=5;//列
		int[][] a=new int[m][n];
		int[][] b=new int[n][m];
		StdOut.println("转置前:");
		a=createArray(a,n);
		printArray(a);
		StdOut.println("转置后:");
		b=convertArray(a,b);
		printArray(b);
	}
	public static int[][] createArray(int[][] a,int n){
		for(int i=0;i<a.length;i++){
			for(int j=0;j<a.length;j++){
				a[i][j]=StdRandom.uniform(n);
			}
		}
		return a;
	} 
	
	public static void printArray(int[][] a){
		for(int i=0;i<a.length;i++){
			for(int j=0;j<a.length;j++){
				StdOut.print(a[i][j]);
			}
			StdOut.println();
		}
	}
	
	public static int[][] convertArray(int[][] a,int[][] b){
		for(int i=0;i<a.length;i++){
			for(int j=0;j<a.length;j++){
				b[j][i]=a[i][j];
			}
		}
		return b;
	}

}
