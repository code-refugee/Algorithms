package sortrecover;
import edu.princeton.cs.algs4.*;
/*堆排序。思路：将数组变为堆有序的二叉堆，这里我们将堆中的最大元素删除，然后
 * 放入堆缩小后数组中空出的位置*/
public class HeapSort {
	
	//静态的排序方法，所需参数为实现了Comparable接口类的对象数组(注意数组a的下标从1开始)
	public static void sort(Comparable[] a){
		int n=a.length-1;
		
		//因为底部的节点没有子节点，所以不需要下沉到正确位置
		for(int i=n/2;i>=1;i--)
			sink(a,i,n);
		
		//将位于根节点的最大元素与下标为n的元素交换实现数组的递增
		while(n>1){
			exch(a,1,n--);
			sink(a,1,n);
		}
		
		//验证数组是否有序
		assert isSorted(a);
	}
	
	//下沉方法，需要传三个参数：数组，起始位置，和最后一个节点所在的位置
	private static void sink(Comparable[] a,int i,int j){
		while(2*i<=j){
			int k=2*i;
			if(k<j&&less(a[k],a[k+1])) k++;
			if(!less(a[i],a[k])) break;
			exch(a,i,k);
			i=k;
		}
	}
	
	//判断v是否比 w小，若为真返回true
	private static boolean less(Comparable v,Comparable w){
		return v.compareTo(w)<0;
	}
	
	//交换两个元素
	private static void exch(Comparable[] a,int i,int j){
		Comparable t=a[i];
		a[i]=a[j];
		a[j]=t;
	}
	
	//验证是否有序
	private static boolean isSorted(Comparable[] a){
		for(int i=2;i<a.length;i++){
			if(less(a[i],a[i-1]))
				return false;
		}
		return true;
	}
	
	//显示数组里的内容
	public static void show(Comparable[] a){
		for(int i=1;i<a.length;i++){
			System.out.println(a[i]);
		}
	}
	
	public static void main(String[] args) {
		/*这里注意，我tinyW.txt里的数据是整数，但我这里将它读进来变成了字符串数组
		 * 所以它的比较方式是按照字符串来比较的，经验证，该算法无误*/
		String[] s=In.readStrings(args[0]);
		
		//注意按照堆排序，数组的下标是从1开始的!
		String[] a=new String[s.length+1];
		for(int i=1;i<a.length;i++){
			a[i]=s[i-1];
		}
		sort(a);
		show(a);
	}

}
