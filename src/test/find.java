package test;
import edu.princeton.cs.algs4.*;
public class find {
	public static Comparable select(Comparable[] a,int k){
		StdRandom.shuffle(a);
		int lo=0,hi=a.length-1;
		while(hi>lo){
			int j=partition(a, lo, hi);
			if(j==k)
				return a[k];
			else if(j>k)
				hi=j-1;
			else
				lo=j+1;
		}
		return a[k];
	}
	private static int partition(Comparable[] a, int lo, int hi) {
		int i = lo, j = hi;
		Comparable v = a[lo];
		while (true) {
			while (less(a[++i], v))
				;// 因为最右边永远是最大的数，所以不需要边界检测
			while (less(v, a[--j]))
				;// 因为最左边就是它自己，所以也不需要边界检测
			if (i >= j)
				break;
			exch(a, i, j);
		}
		exch(a, lo, j);
		return j;
	}
	private static boolean less(Comparable v, Comparable w) {
		return v.compareTo(w) < 0;
	}

	private static void exch(Comparable[] a, int i, int j) {
		Comparable t = a[i];
		a[i] = a[j];
		a[j] = t;
	}
}
