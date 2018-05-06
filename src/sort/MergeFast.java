package sort;
/*快速归并*/
public class MergeFast {
	public static void merge(Comparable[] a,Comparable[] aux,int lo,int mid,int hi){
		assert isSorted(a,lo,mid);
		assert isSorted(a,mid+1,hi);
		for(int k=lo;k<=hi;k++){
			aux[k]=a[k];
		}
		for(int k=hi-1;k>=mid+1;k--){
			for(int i=k;i<hi&&less(aux[i],aux[i+1]);i++){
				exch(aux,i,i+1);
			}
		}
		int i=lo,j=hi;
		for(int k=lo;k<=hi;k++){
			if(less(aux[j],aux[i]))
				a[k]=aux[j--];
			else
				a[k]=aux[i++];
		}
		assert isSorted(a,lo,hi);
	}
	public static void sort(Comparable[] a){
		Comparable[] aux=new Comparable[a.length];
		sort(a,aux,0,a.length-1);
		assert isSorted(a);
	}
	private static void sort(Comparable[] a,Comparable[] aux,int lo,int hi){
		if(lo>=hi)
			return;
		int mid=lo+(hi-lo)/2;
		sort(a,aux,lo,mid);
		sort(a,aux,mid+1,hi);
		merge(a,aux,lo,mid,hi);
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
		Integer[] a={2,3,4,5,8,3,2,1,5,7,9,10,21,13,14,15};
		sort(a);
		show(a);
	}

}
