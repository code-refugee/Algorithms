package sort;
/*自顶向下的归并排序(没有用静态数组作为成员变量，不然会在多个程序使用这个类时出现问题)*/
public class Merge {
	public static void sort(Comparable[] a){
		int N=a.length;
		Comparable[] aux=new Comparable[N];
		int lo=0;
		int hi=N-1;
		sort(a,aux,lo,hi);
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
	public static void merge(Comparable[] a,Comparable[] aux,int lo,int mid,int hi){
		assert isSorted(a,lo,mid);
		assert isSorted(a,mid+1,hi);
		int N=a.length;
		int i=lo,j=mid+1;
		for(int k=lo;k<=hi;k++)
			aux[k]=a[k];
		for(int k=lo;k<=hi;k++){
			if(i>mid)
				a[k]=aux[j++];
			else if(j>hi)
				a[k]=aux[i++];
			else if(less(aux[j],aux[i]))
				a[k]=aux[j++];
			else
				a[k]=aux[i++];
		}
		assert isSorted(a,lo,hi);
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
		Integer[] a={1,2,3,6,4,3,8,7,5};
		sort(a);
		show(a);
	}

}
