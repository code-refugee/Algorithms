package sort;

import edu.princeton.cs.algs4.In;

/*统计给定数组中的倒置数量（即插入排序所需的交换次数）
 * 注意：该算法是线性对数级别的*/
public class Inversion {
	public Inversion(){}
	public static  long merge(Comparable[] a,Comparable[] aux,int lo,int mid,int hi){
		long inversion=0;
		int i=lo,j=mid+1;
		for(int k=lo;k<=hi;k++){
			aux[k]=a[k];
		}
		for(int k=lo;k<=hi;k++){
			if(i>mid)
				a[k]=aux[j++];
			else if(j>hi)
				a[k]=aux[i++];
			else if(less(aux[j],aux[i])){
				a[k]=aux[j++];
				inversion+=(mid-i+1);
			}
			else
				a[k]=aux[i++];
		}
		return inversion;
	}
	
	public static  long count(Comparable[] a){
		Comparable[] aux=a.clone();
		Comparable[] b=a.clone();
		for(int i=0;i<a.length;i++)
			b[i]=a[i];
		long inversion=count(a,b,aux,0,a.length-1);
		return inversion;
	}
	
	private static  long count(Comparable[] a,Comparable[] b,Comparable[] aux,int lo,int hi){
		long inversion=0;
		if(lo>=hi)
			return 0;
		int mid=lo+(hi-lo)/2;
		inversion+=count(a,b,aux,lo,mid);
		inversion+=count(a,b,aux,mid+1,hi);
		inversion+=merge(a,aux,lo,mid,hi);
		assert inversion==brut(b,lo,hi);
		return inversion;
	}
	
	private static  long brut(Comparable[] b,int lo,int hi){
		long inversion=0;
		for(int i=lo;i<=hi;i++){
			for(int j=i+1;j<=hi;j++){
				if(less(b[j],b[i]))
					inversion++;
			}
		}
		return inversion;
	}
	
	
	private static  boolean less(Comparable v,Comparable w){
		return (v.compareTo(w)<0);
	}

	public static void main(String[] args) {
		Integer[] a={2,3,4,5,8,3,2,1,5,7,9,10,21,13,14,15};
		System.out.println(Inversion.count(a));
	}
}
