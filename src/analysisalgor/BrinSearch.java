package analysisalgor;

import java.util.Arrays;

/*修改二分查找算法,使之总是返回和被查找的键匹配索引的最小元素
 * (且仍然能够保证对数级别的运行时间)  */
public class BrinSearch {
    //这种算法在极端情况，比如有无穷个全为1的数组，那么增长数量级就变为N了，不是NlgN
//	public static int rank(int key,int[] a){
//		Arrays.sort(a);
//		int lo=0;
//		int hi=a.length-1;
//		while(lo<=hi){
//			int mid=lo+(hi-lo)/2;
//			if(key>a[mid])
//				lo=mid+1;
//			else if(key<a[mid])//一点要写成else if 不能写成if
//				hi=mid-1;
//			else{
//				int j=mid;
//				while(j>0&&a[j]==a[mid]){
//					j--;
//				}
//				return j;
//			}
//				
//		}
//		return -1;
//	}
	//这是另一个版本的算法,该版本增长数量级为对数级别
	public static int rank(int key,int[] a){
		Arrays.sort(a);
		int lo=0;
		int hi=a.length-1;
		while(lo<=hi){
			int mid=lo+(hi-lo)/2;
			if(key>a[mid])
				lo=mid+1;
			else if(key<a[mid])
				hi=mid-1;
			else if(mid>0&&a[mid-1]==key)
				hi=mid-1;
			else
				return mid;
		}
		return -1;
	}
	public static void main(String[] args) {
		int[] a={ 1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1 };
		int N=1;
		System.out.println(rank(N, a));
				
	}

}
