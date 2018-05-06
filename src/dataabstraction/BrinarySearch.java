package dataabstraction;
import java.util.Arrays;
import java.util.Scanner;

/*修改 BinarySearch（请见 1.1.10.1 节中的二分查找代码），
 * 使用 Counter 统计在有查找中被检查的键的总数并在查找全部结束后打印该值。
 *提示：在 main() 中创建一个 Counter 对象并将它作为参数传递给 rank()*/
import edu.princeton.cs.algs4.*;
public class BrinarySearch {
	public static int rank(int[] a,int key,Counter c){
		c.increment();
		return rank(a,key,0,a.length-1,c);
	}
	public static int rank(int[] a,int key,int lo,int hi,Counter c){
		if(lo>hi)
			return -1;
		int mik=lo+(hi-lo)/2;
		if(key>a[mik]){
//			c.increment();
			return rank(a,key,mik+1,hi,c);
		}
		if(key<a[mik]){
//			c.increment();
			return rank(a,key,lo,mik-1,c);
		}
		else
//			c.increment();
			return mik;
	}
	public static void main(String[] args) {
//		 int[] numArray = { 1, 2, 3, 4, 67, 88, 89, 101, 222, 788, 999 };
	     Counter counter = new Counter("rank");
	     int[] w=In.readInts(args[0]);
	     Arrays.sort(w);
	     for(int i=0;i<w.length;i++){
	    	 System.out.println(w[i]);
	     }
	     while(!StdIn.isEmpty()){
	    	 int key=StdIn.readInt();
	    	 System.out.println(key);
	    	 rank(w,key,counter);
	     }
//	     rank(numArray,222,counter);
	     System.out.println(counter.tally());
	}

}
