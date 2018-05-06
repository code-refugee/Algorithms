package basics;

import java.util.Arrays;

/*等值键。为 BinarySearch 类添加一个静态方法 rank()，
 * 它接受一个键和一个整型有序数组(可能存在重复键)作为
 * 参数并返回数组中小于该键的元素数量，以及一个类似的方法 count() 
 * 来 返回数组中等于该键的元素的数量。注意:如果 i 和 j 分别是 rank(key,a)
 *  和 count(key,a) 的返回值，那么 a[i..i+j-1] 就是数组中所有和 key 相等的元素。
*/
public class Exam1_1_29 {
	public static void main(String[] args) {
		int[] a={3,3,2,1,2,1,2,3,4,5,6,8,7,9};
		a=sort(a);
		System.out.println(Arrays.toString(a));
		System.out.println(rank(3,a));
		System.out.println(count(3,a));
	}
	//对数组进行排序
	public static int[] sort(int[] a){
		for(int i=0;i<a.length;i++){
			for(int j=i+1;j<a.length;j++){
				int k;
				if(a[i]>a[j]){
					k=a[i];
					a[i]=a[j];
					a[j]=k;
				}
			}		
		}
		return a;
	}
	//二分查找:在数组中找到相等的键
	public static int BrinSerch(int key,int[] a){
		int lo=0;
		int hi=a.length-1;
		while(lo<hi){
			int mid=lo+(hi-lo)/2;
			if(key<a[mid])
				hi=mid-1;
			if(key>a[mid])
				lo=mid+1;
			else
				return mid;
		}
		return -1;
	}
	//返回数组中小于键的元素数量
	public static int rank(int key,int[] a){
		int k=0;
		int brin=BrinSerch(key, a);
		System.out.println(brin);
		for(int i=0;i<a.length;i++){
			if(a[i]==a[brin]){
				k=i;
				break;
			}
		}
//		for(int i=0;i<a.length;i++){
//			if(key>a[i]){
//				k++;
//			}
//			if(key==a[i])
//				break;
//		}
		return k;
	}
	
	//返回数组中等于键的元素数量
	public static int count(int key,int[] a){
		int k=0;
		for(int i=0;i<a.length;i++){
			if(key==a[i])
				k++;
			if(key<a[i])
				break;
		}
		return k;
	}

}
