package analysisalgor;

import java.util.Arrays;

import edu.princeton.cs.algs4.In;

/* 编写一个程序,计算输入文件中相等的整数对的数量.如果你的第一个程序是平方级别的,
 * 请继续思考并以Array.sort()给出一个线性对数级别的解答*/
public class Exam1_4_8 {
	
	//平方级别算法
	public static int countEqual(int[] a){
		int cnt=0;
		int N=a.length;
		for(int i=0;i<N;i++){
			for(int j=i+1;j<N;j++)
				if(a[i]==a[j])
					cnt++;
		}
		return cnt;
	}
	
	//线性对数级别算法
	public static int countEqualAnother(int[] a){
		int cnt=0;
		int N=a.length;
		Arrays.sort(a);
		for(int i=0;i<N-1;i++){
			int j=i;
			while(j<N&&a[i]==a[j+1]){
				cnt++;
				j++;
			}
		}
			
		return cnt;
	}
	public static void main(String[] args) {
		int[] a={1,2,2,3,4,5,6,4,8,5,6,2,4};
		System.out.println(countEqual(a));
		System.out.println(countEqualAnother(a));

	}

}
