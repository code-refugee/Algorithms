package quicklysort;
/*快速排序改进版，若想看有注释的，请到sortrecover包中看QuickSort*/
import edu.princeton.cs.algs4.*;
public class QuickX {
	private static final int INSERTION_SORT_CUTOFF=8;
	public static void sort(Comparable[] a){
//		StdRandom.shuffle(a);//因为用三取样切分所以不需要随机打乱
		sort(a,0,a.length-1);
		assert isSorted(a);
	}
	
	private static void sort(Comparable[] a,int lo,int hi){
		//这个if是否可以去掉？
		if(hi<=lo)
			return;
		int n=hi-lo+1;
		if(n<=INSERTION_SORT_CUTOFF){
			insertionSort(a, lo, hi);
			return;
		}
		int j=partition(a,lo,hi);
		sort(a,lo,j-1);
		sort(a,j+1,hi);
	}
	
	private static int partition(Comparable[] a,int lo,int hi){
		//是否可以不加1？
		int n=hi-lo+1;
		//用三取样切分可以不打乱数组
		int m=median3(a, lo, lo+n/2, hi);
		exch(a,lo,m);
		int i=lo,j=hi+1;
		Comparable v=a[lo];
		while(less(a[++i],v)){
			if(i==hi){
				exch(a,lo,hi);
				return hi;
			}
		}
		while(less(v,a[--j])){
			if(j==lo+1)
				return lo;
		}
		//因为循环中i<j所以i一定不会大于hi
		while(i<j){
			exch(a,i,j);
			while(less(a[++i],v));
			while(less(v,a[--j]));
		}
		exch(a,lo,j);
		return j;
	}
	
	private static int median3(Comparable[] a,int i,int j,int k){
		return (less(a[i],a[j]) ?
				(less(a[j],a[k]) ? j:less(a[i],a[k]) ? k:i):
				(less(a[k],a[j]) ? j:less(a[k],a[i]) ? k:i));
	}
	
	private static boolean less(Comparable v,Comparable w){
		return v.compareTo(w)<0;
	}
	
	private static void insertionSort(Comparable[] a,int lo,int hi){
		int exchange=0;
		for(int i=hi;i>lo;i--){
			if(less(a[i],a[i-1])){
				exch(a,i,i-1);
				exchange++;
			}
		}
		if(exchange==0) return;
		for(int i=lo+1;i<=hi;i++){
			Comparable v=a[i];
			int j=i;
			while(less(v,a[j-1])){
				a[j]=a[j-1];
				j--;
			}
			a[j]=v;
		}
	}
	
	private static boolean isSorted(Comparable[] a){
		for(int i=1;i<a.length;i++){
			if(less(a[i],a[i-1]))
				return false;
		}
		return true;
	}
	
	private static void exch(Comparable[] a,int i,int j){
		Comparable t=a[i];
		a[i]=a[j];
		a[j]=t;
	}
	
	public static void show(Comparable[] a){
		for(int i=0;i<a.length;i++){
			StdOut.println(a[i]);
		}
	}
	public static void main(String[] args) {
		String[] a=In.readStrings(args[0]);
		sort(a);
		show(a);
	}
}
