package sort;
/*间接排序。这是一个不改变数组的归并排序，它返回一个int[]数组perm
 * 其中perm[i]是元素组中第i小的元素的位置*/
public class MergeIndirect {
	public static void merge(Comparable[] a,int[] aux,int[] index,int lo,int mid,int hi){
		int i=lo,j=mid+1;
		for(int k=lo;k<=hi;k++){
			aux[k]=index[k];
		}
		for(int k=lo;k<=hi;k++){
			if(i>mid)
				index[k]=aux[j++];
			else if(j>hi)
				index[k]=aux[i++];
			else if(less(a[aux[j]],a[aux[i]]))
				index[k]=aux[j++];
			else
				index[k]=aux[i++];
		}
	}
	public static int[] sort(Comparable[] a){
		int[] aux=new int[a.length];
		int[] index=new int[a.length];
		for(int i=0;i<a.length;i++){
			index[i]=i;
		}
		sort(a,aux,index,0,a.length-1);
		return index;
	}
	private static void sort(Comparable[] a,int[] aux,int[] index,int lo,int hi){
		if(lo>=hi)
			return;
		int mid=lo+(hi-lo)/2;
		sort(a,aux,index,lo,mid);
		sort(a,aux,index,mid+1,hi);
		merge(a,aux,index,lo,mid,hi);
	}
	
	private static boolean less(Comparable v,Comparable w){
		return v.compareTo(w)<0;
	}
	
}
