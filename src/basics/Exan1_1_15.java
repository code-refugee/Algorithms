package basics;

import java.util.Arrays;
import edu.princeton.cs.algs4.*;

public class Exan1_1_15 {

	public static long[] F(long[] a)  {
//        if (N == 0) return 0;
//        if (N == 1) return 1;
//        return F(N-1) + F(N-2);
		a[0]=0;
		a[1]=1;
		for(int i=2;i<100;i++){
			a[i]=a[i-1]+a[i-2];
		}
		return a;
		
		
  }
	/*需注意long类型大小限制后面会溢出，导致出现负数*/
  public static void main(String[] args) { 
      long[] a=new long[100];
      a=F(a);
      for(int i=0;i<100;i++){
    	  System.out.println(i+" "+a[i]);
      }
  }



}
