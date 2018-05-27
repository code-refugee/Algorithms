package quicklysort;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdRandom;

/*算法2.3.17 哨兵：去掉内循环while中的边界检查
 * 没有边界检查，算法速度能提升*/
public class QuickBest {
	public static void sort(Comparable[] a) {
		StdRandom.shuffle(a);
		int t = findLargest(a);// 将最大的数放到数组最右边
		exch(a, t, a.length - 1);
		sort(a, 0, a.length - 1);
		assert isSorted(a);
	}

	private static void sort(Comparable[] a, int lo, int hi) {
		if (hi <= lo)
			return;
		int j = partition(a, lo, hi);
		sort(a, lo, j);// 注意：将切分元素也一并放进去，保证最右边最大
		sort(a, j + 1, hi);
	}

	private static int findLargest(Comparable[] a) {
		int t = 0;
		for (int i = 1; i < a.length; i++) {
			if (less(a[t], a[i])) {
				t = i;
			}
		}
		return t;
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

	private static boolean isSorted(Comparable[] a) {
		for (int i = 1; i < a.length; i++) {
			if (less(a[i], a[i - 1]))
				return false;
		}
		return true;
	}

	public static void show(Comparable[] a) {
		for (int i = 0; i < a.length; i++) {
			System.out.println(a[i]);
		}
	}

	public static void main(String[] args) {
		for (int N = 10; N <= 100000; N *= 10) {

			Integer[] a = new Integer[N];
			for (int i = 0; i < N; i++)
				a[i] = (int) StdRandom.uniform();
			sort(a);
			
		}

	}

}
