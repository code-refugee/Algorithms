package test;

public class ExceptionTest {
	public static void test(int key){
		assert key>0:"输入值不符合";
		System.out.println(key);
	}
	public static void main(String[] args) {
		test(-1);
	}

}
