package analysisalgor;

import java.util.Arrays;

/* 最接近一对（一维）。编写一个程序，给定一个含有 N 个 double 值的数组 a[]，
 * 在其中找到一对最接近的值：两者之差（绝对值）最小的两个数。
 * 程序在最坏情况下所需的运行时间应该是线性对数级别的。*/
public class ClosestPair {
	public static double[] find(double[] a){
		double[] result=new double[2];
		Arrays.sort(a);
		double differ=Math.abs(a[1]-a[0]);
		result[0]=a[0];
		result[1]=a[1];
		for(int i=1;i<a.length-1;i++){
			double temp=Math.abs(a[i+1]-a[i]);
			if(temp<differ){
				differ=temp;
				result[0]=a[i];
				result[1]=a[i+1];
			}
		}
		return result;
	}
	public static void main(String[] args) {
		double[] a={1,1,1,1,1,2,1,1,1,1,3,1};
		double[] result=find(a);
		for(int i=0;i<result.length;i++)
			System.out.println(result[i]);
			
	}

}
