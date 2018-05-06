package analysisalgor;
/* 双调查找。如果一个数组中的所有元素是先递增后递减的，则称这个数组为双调的。
 * 编写一个程序，给定一个含有 N 个不同 int 值的双调数组，判断它是否含有给定的整数。
 * 程序在最坏情况下所需的比较次数为 ~3lgN。*/
import edu.princeton.cs.algs4.*;
public class DoubleLookup {
	private int findTransition(int[] a,int left,int right){
		if(left==right)
			return left;
		int mid=left+(right-left)/2;
		if(a[mid]<a[mid+1])
			return findTransition(a, mid+1, right);
		else if(a[mid+1]<a[mid])
			return findTransition(a, left, mid);
		else 
			return mid;
	}
	private int rank(int key,int[] a,int lo,int hi,int flg){
		if(lo>hi)
			return -1;
		int mid=lo+(hi-lo)/2;
		if(key<a[mid]){
			if(flg==1)
				return rank(key,a,mid+1,hi,flg);
			return rank(key,a,lo,mid-1,flg);
		}
		else if(key>a[mid]){
			if(flg==1)
				return rank(key,a,lo,mid-1,flg);
			return rank(key,a,mid+1,hi,flg);
		}
		else
			return mid;
	}
	public boolean ifExists(int[] a,int N){
		int transition=findTransition(a, 0, a.length-1);
		if(rank(N,a,0,transition,0)!=-1||rank(N,a,transition+1,a.length-1,1)!=-1)
			return true;
		return false;
	}
	public static void main(String[] args) {
		DoubleLookup dl=new DoubleLookup();
		int[] a={1,2,3,4,5,6,7,8,-1,-2,-3,-4,-5,-6,-7,-8};
		if(dl.ifExists(a,-5))
			System.out.println("存在");
		else
			System.out.println("不存在");
//		System.out.println(dl.findTransition(a, 0, a.length-1));
	}

}
