package basics;
/* 给出使用欧几里得算法计算 105 和 24 的最大公约数的过程中得到的一系列 p 和 q 的值。
 * 扩展该算法中的代码得到一个程序Euclid，从命令行接受两个参数，计算它们的最大公
 * 约数并打印出每次调用递归方法时的两个参数。使用你的程序计算 1 111 111 和 1 234
 *  567 的最大公约数。
*/
public class Exam1_1_23 {
	public static int gcb(int p,int q){
		if(q==0)
			return p;
		System.out.println(p+" "+q);
		int r=p%q;
		return gcb(q,r);
	}
	public static void main(String[] args) {
		System.out.println(gcb(1111111,123456));
	}

}
