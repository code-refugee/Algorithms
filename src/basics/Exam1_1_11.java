package basics;
import javax.swing.plaf.synth.SynthSpinnerUI;

import edu.princeton.*;
import edu.princeton.cs.algs4.StdRandom;
/*编写一行代码，打印出一个二维布尔数组的内容
 * 其中，使用*表示真，空格表示假。打印出行号
 * 和列号*/
public class Exam1_1_11 {
	public static void main(String[] args) {
		boolean[][] a=new boolean[10][10];
		a=RandomInitial(a);
		printBoolean(a);

	}
	public static boolean[][] RandomInitial(boolean[][] a){
		for(int i=0;i<a.length;i++){
			for(int j=0;j<a.length;j++){
				//利用作者写的方法，返回真的概率为0.5
				if(StdRandom.bernoulli(0.5)){
					a[i][j]=true;
				}
				else
					a[i][j]=false;
			}
		}
		return a;
	}
	public static void printBoolean(boolean[][] a){
		System.out.print(" ");
		for(int i=0;i<a.length;i++){
			System.out.print(i+1);
			System.out.print(" ");
		}
		System.out.print("\n");
		for(int i=0;i<a.length;i++){
			System.out.print(i+1);
			for(int j=0;j<a.length;j++){
				if(a[i][j]){
					System.out.print("*");
					System.out.print(" ");
				}
				else{
					System.out.print(" ");
					System.out.print(" ");
				}
			}
			System.out.print("\n");
		}
	}
}
