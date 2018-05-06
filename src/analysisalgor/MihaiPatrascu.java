package analysisalgor;

import java.util.Arrays;

/*仅用加减实现的二分查找(Mihai Patrascu). 编写一个程序,
 * 给定一个含有N个不同int值的按照升序排列的数组,
 * 判断他是否含有给定的整数.只能使用加法或减法以及常数的额外内存空间.
 * 程序的运行时间在最坏情况下应该和logN成正比.*/
public class MihaiPatrascu {
	private int[] a;
	public MihaiPatrascu(int[] a){
		this.a=new int[a.length];
		for(int i=0;i<a.length;i++)
			this.a[i]=a[i];
		Arrays.sort(this.a);
	}
	

}
