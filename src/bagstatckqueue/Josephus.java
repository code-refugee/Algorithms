package bagstatckqueue;
import java.util.Iterator;

/*Josephus 问题。在这个古老的问题中，N 个身陷绝境的人一致同意通过以下方式减少生存人数
 * 他们围坐成一圈（位置记作 0 到 N-1）并从第一个人开始报数，报到 M 的人会被杀死，
 * 直到最后一个人留下来。传说中 Josephus 找到了不会被杀死的位置。
 * 编写一个 Queue 的用例 Josephus，从命令行接受 N 和 M 并打印出人们被杀死的顺序
 * （这也将显示 Josephus 在圈中的位置）。
 */
import edu.princeton.cs.algs4.*;
public class Josephus {
	public QueueDemo<Integer> killedOrder(int N,int M){
		QueueDemo<Integer> q1=new QueueDemo<Integer>();
		QueueDemo<Integer> result=new QueueDemo<Integer>();
		for(int i=0;i<N;i++){
			q1.enqueue(i);
		}
		int sum=1;
		while(!q1.isEmpty()){
			int i=q1.dequeue();
			if(sum%M==0){
				result.enqueue(i);
			}
			else
				q1.enqueue(i);
			sum++;
		}
		return result;
	}
	public static void main(String[] args) {
		Josephus j=new Josephus();
		QueueDemo<Integer> q=j.killedOrder(8, 4);
		for(int i:q)
			System.out.println(i);
	}

}
