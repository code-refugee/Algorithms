package quicklysort;
/*快速三向切分*/
import edu.princeton.cs.algs4.*;
public class Quick3wayX {
	public static void sort(Comparable[] a){
		StdRandom.shuffle(a);
		sort(a,0,a.length-1);
		assert isSorted(a);
	}
	
	private static void sort(Comparable[] a,int lo,int hi){
		if(hi<=lo)
			return;
//		int lt=lo,i=lo+1,gt=hi;
		Comparable v=a[lo];
		int i=lo,j=hi+1;
		int p=lo,q=hi+1;
		while(true){
			while(less(a[++i],v)){
				if(i==hi){
					break;
				}
			}
			while(less(v,a[--j])){
//              这个判断条件是多余的
//				if(j==lo)//为什么不能j==lo+1：因为最后要把lo与j做交换（可以想一下极端情况：v是最小的元素）
//					break;
			}
			if(i>=j)
				break;
			exch(a,i,j);//先交换再比较是否有相等
			if(eq(a[i],v)) exch(a,++p,i);
			if(eq(a[j],v)) exch(a,--q,j);
		}
		exch(a,lo,j);
		i=j+1;
		j=j-1;
		for(int k=lo+1;k<=p;k++) exch(a,k,j--);
		for(int k=hi;k>=q;k--) exch(a,k,i++);
		sort(a,lo,j);
		sort(a,i,hi);
//     方法二
//		while(i<=gt){
//			int cmp=a[i].compareTo(v);
//			if(cmp<0) exch(a,lt++,i++);
//			else if(cmp>0) exch(a,i,gt--);
//			else i++;
//		}
//		sort(a,lo,lt-1);
//		sort(a,gt+1,hi);
	}
	
	private static boolean less(Comparable v,Comparable w){
		return v.compareTo(w)<0;
	}
	
	private static boolean eq(Comparable v,Comparable w){
		return v.compareTo(w)==0;
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
