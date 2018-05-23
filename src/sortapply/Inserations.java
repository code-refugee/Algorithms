package sortapply;

import sort.Inversion;

/*统计给定数组中的倒置数量（即插入排序所需的交换次数）
 * 因为要求增长数量级为线性对数级别的，所以用插入排序肯定不行
 * 所以联想到归并排序*/
public class Inserations {
	private static long merge(Comparable[] a,Comparable[] aux,int lo,int mid,int hi){
		
		//用于记录交换的数量
		long exch=0;
		int i=lo,j=mid+1;
		
		//数组的复制，不用多说
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
				//因为如果aux[j]比aux[i]小的话，因为是归并排序，那么i后面的元素也比aux[j]大，共有mid-i+1个
				exch+=(mid-i+1);
			}
			else
				a[k]=aux[i++];
				
		}
		return exch;
	}
	
	public static long count(Comparable[] a){
		Comparable[] aux=new Comparable[a.length];
		
		//这个数组b用于验证结果是否正确
		Comparable[] b=a.clone();
//		for(int i=0;i<a.length;i++)
//			b[i]=a[i];
		return count(a,b,aux,0,a.length-1);
	}
	
	//这个方法貌似很眼熟（其实和归并排序中的sort()方法类似，只不过它需要返回一个值）
	private static long count(Comparable[] a,Comparable[] b,Comparable[] aux,int lo,int hi){
		if(hi<=lo)
			return 0;
		
		long exch=0;
		
		int mid=lo+(hi-lo)/2;
		
		exch+=count(a,b,aux,lo,mid);
		
		exch+=count(a,b,aux,mid+1,hi);
		
		exch+=merge(a,aux,lo,mid,hi);
		
		assert exch==brut(b,lo,hi);
		return exch;
	}
	
	//用于验证结果是否正确
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
