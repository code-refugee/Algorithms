package sort;
/*插入排序升级版*/
import edu.princeton.cs.algs4.*;
public class InsertionFast {
	public static void sort(Comparable[] a){
		int exchange=0;
		/*这段代码做到了将最小的数作为哨兵放在了第一位（在后面的代码中用于防止数组越界，
		 * 同时也去掉了下一个for循坏的边界判定，加快速度）
		*这个for循环还可以避免若全是相同的元素，下一个for循环还会运行代码*/
		for(int i=a.length-1;i>0;i--){
			if(less(a[i],a[i-1])){
				exch(a,i,i-1);
				exchange++;
			}
		}
		
		
		if(exchange==0) return;
		for(int i=1;i<a.length;i++){
			Comparable v=a[i];
			int j=i;
			while(less(v,a[j-1])){//若没有做上一个for循环，此段代码还要加（j>0）这个条件
				a[j]=a[j-1];//注意这行代码实现的是右移而不是交换两个元素（速度加快了）！！
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
		Integer[] a={8,7,10,102,101,52,13,68,45,1,2,4,9,6,2};
		sort(a);
		show(a);
	}



}
