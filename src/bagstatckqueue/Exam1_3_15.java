package bagstatckqueue;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.*;
/* 编写一个Queue的用例，接受一个命令行参数k并打印出标准输入中的倒数第k个字符串
 * （假设标准输入中至少有k个字符串）。*/
public class Exam1_3_15 {
	public static void main(String[] args) {
		String[] s={"4","2","1","7","6","5","8","9"};
		System.out.println(appointString(1, s));
	}
	public static String appointString(int k,String[] s){
		QueueDemo<String> que=new QueueDemo<String>();
		for(int i=0;i<s.length;i++){
			que.enqueue(s[i]);
		}
		if(k>que.size())
			throw new NoSuchElementException("outof queue");
		int j=que.size()-k;
		for(int i=0;i<j;i++){
			que.dequeue();
		}
		return que.dequeue();
	}

}
