package basics;
import edu.princeton.cs.algs4.*;
/*编写一个递归的静态方法计算ln(N!)的值;
 * 题目中我们使用归纳法来解决问题
令f(N)= ln(N!)
f(1) = ln(1!) = 0
f(2) = ln(2!) = ln(2 * 1) = ln2 + ln1
f(3) = ln(3!) = ln(3 * 2 * 1) = ln3 + ln2 + ln1 = f2 + ln3
f(4) = ln(4!) = ln(4 * 3 * 2 * 1) = ln4 + ln3 + ln2 + ln1 = f(3) + ln4
f(5) = ln(5!) = ln(5 * 4 * 3 * 2 * 1) = ln5 + ln4 + ln3 + ln2 + ln1 = f(4) + ln5

...
f(n) = f(n-1) +lnn
*/
public class Exam1_1_20 {
	public static int F(int N){
		if(N==0)
			return 0;
		if(N==1)
			return 0;
		return (int) (Math.log(N)+F(N-1));
	}
	public static void main(String[] args) {
		System.out.println(F(20));
	}

}
