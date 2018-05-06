package analysisalgor;
/*1.数组的局部最小元素。编写一个程序，给定一个含有 N 个不同整数的数组，
 * 找到一个局部最小元素：满足 a[i] < a[i-1]，且 a[i] < a[i+1] 
 * 的索引 i。程序在最坏情况下所需的比较次数为 ~ 2lgN
 * 
 * 
 * 2.矩阵的局部最小元素。给定一个含有 N^2 个不同整数的 N×N 数组 a[]。
 * 设计一个运算时间和 N 成正比的算法来找出一个局部最小元素：
 * 满足 a[i][j] < a[i+1][j]、a[i][j] < a[i][j+1]、a[i][j] < a[i-1][j] 
 * 以及 a[i][j] < a[i][j-1] 的索引 i 和 j。程序运行时间在最坏情况下应该和 N 成正比。(未做)

 * */
public class MinimunPair {
	public static int findMinimum(int[] x){
		if (x == null || x.length == 0) {
	        return -1;
	    }
	    if (x.length == 1 || x[0] < x[1]) {
	        return 0;
	    }
	    if (x[x.length - 1] < x[x.length - 2]) {
	        return x.length - 1;
	    }

	    int mid = 0;
	    int left = 1;
	    int right = x.length - 2;
	    while (left <right) {
	        mid = (left + right) / 2;
	        if (x[mid - 1] < x[mid]) {
	            right = mid - 1;
	        } else if (x[mid + 1] < x[mid]) {
	            left = mid + 1;
	        } else {
	            return mid;
	        }
	    }
	    return left;
	}
	
//	public static int matrixMinimumPair(int[][] a){
//		int N=a.length-1;
//		if(a==null||a.length==0)
//			return -1;
//		if(a.length==1)
//			return a[0][0];
//		if(a[0][0]<a[1][0]&&a[0][0]<a[0][1])
//			return a[0][0];
//		if(a[N][0]<a[N-1][0]&&a[N][0]<a[N][1])
//			return a[N][0];
//		if(a[0][N]<a[1][N]&&a[0][N]<a[0][N-1])
//			return a[0][N];
//		if(a[N][N]<a[N-1][N]&&a[N][N]<a[N][N-1])
//			return a[N][N];
//		for(int i=1;i<N-1;i++){
//			if(a[i][0]<a[i-1][0]&&a[i][0]<a[i+1][0]&&a[i][0]<a[i][1])
//				return a[i][0];
//			if(a[i][N]<a[i-1][N]&&a[i][N]<a[i+1][N]&&a[i][N]<a[i][N-1])
//				return a[i][N];
//		}
//		for(int j=1;j<N-1;j++){
//			if(a[0][j]<a[0][j-1]&&a[0][j]<a[0][j+1]&&a[0][j]<a[1][j])
//				return a[0][j];
//			if(a[N][j]<a[N][j-1]&&a[N][j]<a[N][j+1]&&a[N][j]<a[N-1][j])
//				return a[N][j];
//		}
//		
//		
//	}
	public static void main(String[] args) {
		int[] a = {100, 1, 2, 3, 4, 5, 6, 7, 8, 100};
		System.out.println(findMinimum(a));
//		int[][] a=new int[4][4];
//		for(int i=0;i<4;i++)
//			for(int j=0;j<4;j++)
//				a[i][j]=1;
//		System.out.println(a.length);
	}

}
