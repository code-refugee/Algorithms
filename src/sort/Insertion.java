package sort;
/*插入排序*/
public class Insertion {
	public static void sort(Comparable[] a){
		int N=a.length;
		for(int i=1;i<N;i++){
			for(int j=i;j>0&&less(a[j],a[j-1]);j--)
				exch(a,j,j-1);
		}
		assert isSorted(a);
	}
	//返回一个整形数组index[] 使得a[index[0]]到a[index[N-1]]正好是升序
	public static int[] indirectSort(Comparable[] a){
		int N=a.length;
		int[] index=new int[a.length];
		for(int i=0;i<N;i++)
			index[i]=i;
		for(int i=1;i<N;i++){
			for(int j=i;j>0&&less(a[index[j]],a[index[j-1]]);j--)
				exch(index,j,j-1);
		}
		assert isSorted(a);
		return index;
	}
	private static boolean less(Comparable v,Comparable w){
		return v.compareTo(w)<0;
	}
	private static void exch(Comparable[] a,int i,int j){
		Comparable t=a[i];
		a[i]=a[j];
		a[j]=t;
		
	}
	private static void exch(int[] index,int i,int j){
		int temp=index[i];
		index[i]=index[j];
		index[j]=temp;
		
	}
	private static void show(Comparable[] a){
		for(int i=0;i<a.length;i++)
			System.out.print(a[i]+" ");
		System.out.println();
	}
	public static boolean isSorted(Comparable[] a){
		for(int i=1;i<a.length;i++){
			if(less(a[i],a[i-1]))
				return false;
		}
		return true;	
	}

}
