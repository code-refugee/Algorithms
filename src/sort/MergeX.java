package sort;
/*（改进归并排序）：加快小数组的排序速度，检测数组是否已经有序
 * 以及通过在递归中交换参数来避免数组复制*/
import edu.princeton.cs.algs4.*;
public class MergeX {
	private static final int CUTOFF=7;
	public static void merge(Comparable[] src,Comparable[] dst,int lo,int mid,int hi){
		assert isSorted(src,lo,mid);
		assert isSorted(src,mid+1,hi);
		int i=lo,j=mid+1;
//		for(int k=lo;k<=hi;k++)
//			aux[k]=a[k];
		for(int k=lo;k<=hi;k++){
			if(i>mid)
				dst[k]=src[j++];
			else if(j>hi)
				dst[k]=src[i++];
			else if(less(src[j],src[i]))
				dst[k]=dst[j++];
			else
				dst[k]=dst[i++];
		}
		assert isSorted(dst,lo,hi);
	}
	


	public static void sort(Comparable[] a){
		Comparable[] aux=a.clone();
//		Comparable[] aux=new Comparable[a.length];
		sort(aux,a,0,a.length-1);
		assert isSorted(a);
	}
	
	
	private static void sort(Comparable[] src,Comparable[] dst,int lo,int hi){
//		if(hi<=lo)
//			return;
		//加快小数组排序（对小规模的数组用插入排序）
		if(hi<=lo+CUTOFF){
			insertionSort(dst, lo, hi);
			return;
		}
		int mid=lo+(hi-lo)/2;
		sort(dst,src,lo,mid);
		sort(dst,src,mid+1,hi);
		//检测数组是否有序若有序则不需要归并
		if(!less(src[mid+1],src[mid])){
			System.arraycopy(src, lo, dst, lo, hi - lo + 1);
			return;
		}
		merge(src,dst,lo,mid,hi);
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
			Comparable t=a[i];
			int j=i;
			//和插入排序差不多只不过不是交换两个元素而是平移来实现
			while(less(t,a[j-1])){
				a[j]=a[j-1];
				j--;
			}
			a[j]=t;
		}
	}
	
	
	private static boolean less(Comparable v,Comparable w){
		return v.compareTo(w)<0;
	}
	
	
	private static void exch(Comparable[] a,int i,int j){
		Comparable t=a[i];
		a[i]=a[j];
		a[j]=t;
	}
	
	
	private static boolean isSorted(Comparable[] a){
		return isSorted(a,0,a.length-1);
	}
	
	
	private static boolean isSorted(Comparable[] a,int lo,int hi){
		for(int i=lo+1;i<=hi;i++){
			if(less(a[i],a[i-1]))
				return false;
		}
		return true;
	}
	
	public static void show(Comparable[] a){
		for(int i=0;i<a.length;i++)
			System.out.println(a[i]);
	}
    

	public static void main(String[] args) {
		String[] a=In.readStrings(args[0]);
//		Integer[] a={1,2,3,256,126,114,135,8,7,6,5};
		sort(a);
		show(a);
	}

}
