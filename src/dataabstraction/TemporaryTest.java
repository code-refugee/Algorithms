package dataabstraction;
/*将输入的字符串反过来输出
 * 如Hello World变为dlroWkolleH*/
import edu.princeton.cs.algs4.*;
public class TemporaryTest {
//	public static String mystery(String s){
//		int n=s.length();
//		if(n<=1)
//			return s;
//		String a=s.substring(0, n/2);
//		String b=s.substring(n/2, n);
//		return mystery(b)+mystery(a);
//	}
//public static void main(String[] args) {
//	String s="Hello World";
//	s.toUpperCase();//使字符串全部变为大写
//	s.substring(6, 11);
//	System.out.println(s);
//	System.out.println(mystery("Hello World"));;
//}
	public static void main(String[] args) {
        // TODO Auto-generated method stub

        int[] numArray = { 1, 2, 3, 4, 67, 88, 89, 101, 222, 788, 999 };
        Counter counter = new Counter("rank");
        int index = rank(222, numArray, counter);

        System.out.println("index: " + index + "\ncouter:" + counter.tally());
    }

    public static int rank(int t, int[] array, Counter counter) {

        int lo = 0;
        int hi = array.length - 1;
        int mid = (lo + hi) / 2;

        while (t != array[mid]) {
            counter.increment();;
            if (t < array[mid]) {

                if (hi == mid) {
                    return -1;
                }
                hi = mid;
            } else if (t > array[mid]) {
                if (lo == mid) {
                    return -1;
                }
                lo = mid;
            } else {
                return mid;
            }

            mid = (lo + hi) / 2;
        }

        return mid;
    }


}
