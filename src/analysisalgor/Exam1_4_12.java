package analysisalgor;

import java.util.Arrays;

/* 编写一个程序，有序打印给定的两个有序数组（含有 N 个 int 值） 中的所有公共元素，
 * 程序在最坏情况下所需的运行时间应该和 N 成正比。*/
public class Exam1_4_12 {
	private int[] a;
	private int[] b;
	public Exam1_4_12(int[] a,int[] b){
		this.a=new int[a.length];
		this.b=new int[b.length];
		//保护性复制
		for(int i=0;i<a.length;i++)
			this.a[i]=a[i];
		for(int j=0;j<b.length;j++)
			this.b[j]=b[j];
		Arrays.sort(this.a);
		Arrays.sort(this.b);
	}
//	//这个增长数量级是对数
//	public void sameElements(){
//		for(int i=0;i<this.a.length;i++){
//			int k=rank(a[i],this.b);
//			if(k!=-1)
//				System.out.println(a[i]);
//		}
//	}
	//这个增长数量级为线性级别，为N
	public void sameElements(){
		for(int i=0,k=0;i<a.length&&k<b.length;){
			if(a[i]<b[k])
				i++;
			else if(a[i]>b[k])
				k++;
			else{
				System.out.println(a[i]);
				i++;
				k++;
			}
		}	
	}
	private int rank(int key,int[] a){
		int lo=0;
		int hi=a.length-1;
		while(lo<=hi){
			int mid=lo+(hi-lo)/2;
			if(key>a[mid])
				lo=mid+1;
			else if(key<a[mid])
				hi=mid-1;
			else
				return mid;
		}
		return -1;
	}
	public static void main(String[] args) {
		int[] a={1,0,3,4,5,6,7,8};
		int[] b={8,7,5,10,2,0};
		Exam1_4_12 e=new Exam1_4_12(a, b);
		e.sameElements();
	}

}
