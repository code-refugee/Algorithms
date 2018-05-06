package basics;
/*使用1.1.6.4 中的 rank()递归方法重新实现 BinarySearch 并跟踪该方法的调用。
 * 每当该方法被调用时，打印出它的参数 lo 和 hi 并按照递归的深度缩进。提示 :
 * 为递归方法加一个参数来保存递归的深度。*/
public class Exam1_1_22 {
	public static int rank(int key,int[] a){
		return rank(key,a,0,a.length-1,1);
	}
	public static int rank(int key,int[] a,int lo,int hi,int deep){
		//如果key存在于a[]中，它的索引不会小于lo，且不会大于hi
		if(lo>hi)
			return -1;
		int mid=lo+(hi-lo)/2;
		for(int i=0;i<deep;i++){
			System.out.print(" ");
		}
		System.out.println(lo+" "+hi);
		if(key<a[mid])
			return rank(key,a,lo,mid-1,++deep);
		else if(key>a[mid])
			return rank(key,a,mid+1,hi,++deep);
		else
			return mid;
	}
	public static void main(String[] args) {
		int[] a={0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16};
		int key=6;
		rank(key,a);
	}

}
