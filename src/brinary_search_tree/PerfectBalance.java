package brinary_search_tree;

import java.util.Arrays;
import java.util.Scanner;
import edu.princeton.cs.algs4.*;
/*完美平衡。编写一段程序，用一组键构造一棵树和二分查找等价的二叉查找树。也就是说，
 * 在这棵树中查找任意键所产生的比较序列和在这组键中使用二分查找所产生的比较
 * 序列完全相同*/
public class PerfectBalance {
	
	/*解题思路:二分查找是利用一组有序的数组然后对其进行折半查找
	 * 那么，我们只要用一组有序的数组来构造一颗二叉树即可*/
	public static<T> void perfect(BST bs,T[] keys){
		Arrays.sort(keys);
		perfect(bs,keys,0,keys.length-1);
	}
	private static<T> void perfect(BST bs,T[] keys,int lo,int hi){
		if(hi<lo)
			return;
		int mid=lo+(hi-lo)/2;
		bs.put((Comparable)keys[mid], mid);
		perfect(bs,keys,lo,mid-1);
		perfect(bs,keys,mid+1,hi);
	}
	

	public static void main(String[] args) {
		BST<String, Integer> bs=new BST<>();
		String[] s=new String[10];
		System.out.println("请输入:");
		Scanner in=new Scanner(System.in);
		for(int i=0;i<10;i++)
			s[i]=in.nextLine();
		perfect(bs,s);
		for(String str:bs.keys())
			System.out.println(str+" "+bs.get(str));
			
	}
}
