package basics;
import edu.princeton.cs.algs4.*;
/*编写一个静态方法lg()，接受一个整型参数N，
 * 返回不大于log2N 的最大整数。不要使用Math 库。*/
public class Exam1_1_14 {
	public static int lg(int N){
		int product=1;
		int x=-1;
		while(product<=N){
			product*=2;
			x++;
		}
		return x;
//		return Integer.toBinaryString(N).length()-1;
				
	}
	public static void main(String[] args){
		int N=2;
		StdOut.print(lg(N));
	}

}
