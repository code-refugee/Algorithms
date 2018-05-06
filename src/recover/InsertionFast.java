package recover;

public class InsertionFast {
	public static void sort(Comparable[] a){
		int exchange=0;
		for(int i=a.length-1;i>0;i--){
			if(less(a[i],a[i-1])){
				exch(a,i,i-1);
				exchange++;
			}
		}
		if(exchange==0)
			return;
		for(int i=1;i<a.length;i++){
			Comparable v=a[i];
			int j=i;
			while(less(v,a[j-1])){
				a[j]=a[j-1];
				j--;
			}
			a[j]=v;
		}
		assert isSorted(a):"排序失败";
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
		for(int i=1;i<a.length;i++){
			if(less(a[i],a[i-1])){
				return false;
			}
		}
		return true;
	}

}
