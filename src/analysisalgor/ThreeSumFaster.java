package analysisalgor;

import java.util.Arrays;

/*快速 3-sum。作为热身，使用一个线性级别的算法（而非基于二分查找的线性对数级别的算法）
 * 实现 TwoSumFaster 来计算已排序的数组中和为 0 的整数对的数量。用相同的思想为 3-sum 
 * 问题给出一个平方级别的算法。*/
public class ThreeSumFaster {
	public static int count(int[] a){
		Arrays.sort(a);
		int N=a.length;
		int cnt=0;
		for(int i=0;i<N;i++)
			for(int k=i+1,h=N-1;k<h;){
				if(a[i]+a[k]+a[h]>0)
					h--;
				else if(a[i]+a[k]+a[h]<0)
					k++;
				else{
					cnt++;
					k++;
					h--;
				}
			}
			
		return cnt;
	}
	public static void main(String[] args) {
		int[] a={-3,-2,1,5,-7,-8,10,9};
		System.out.println(count(a));
	}

}
