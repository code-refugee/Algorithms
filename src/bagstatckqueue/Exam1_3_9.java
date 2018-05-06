package bagstatckqueue;

/*编写一段程序,从标准输入得到一个缺少左括号的表达式并打印出补全括号之后的中序表达式.
 * 此题比较关键在于")"输入时的情况---重点题
 *例如： 输入 1+2)*3-4)*5-6)))       输出  ((1+2)*((3-4)*(5-6)))*/
import edu.princeton.cs.algs4.*;

public class Exam1_3_9 {
	public static String outComplete(String[] s) {
		Stack<String> ops = new Stack<String>();
		Stack<String> vals = new Stack<String>();
		for (int i = 0; i < s.length; i++) {
			if (s[i].equals("+") || s[i].equals("-") || s[i].equals("*") || s[i].equals("/") || s[i].equals("sqrt")) {
				ops.push(s[i]);
			} else if (s[i].equals(")")) {
				String o = ops.pop();
				String v = vals.pop();
				String ne;
				if (o.equals("sqrt")) {
					ne = "(" + o + v + ")";
				} else {
					ne = "(" + vals.pop() + o + v + ")";
				}
				vals.push(ne);
			} else
				vals.push(s[i]);
		}
		return vals.pop();
	}

	public static void main(String[] args) {
		In in = new In();
		String[] s = in.readStrings(args[0]);
		for (int i = 0; i < s.length; i++) {
			System.out.printf("%s",s[i]);//%s表示输出的是字符串
		}
		System.out.println();
		System.out.println(outComplete(s));

//		 String[] s={"1","+","2",")","*","3","-","4",")","*","5","-","6",")",")",")"};
//		 for(int i=0;i<s.length;i++){
//		 StdOut.printf("%s",s[i]);
//		 }
//		 System.out.println();
//		 System.out.println(outComplete(s));

	}

}
