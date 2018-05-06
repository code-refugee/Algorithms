package bagstatckqueue;
import edu.princeton.cs.algs4.*;

public class Date2 {
	public static void main(String[] args) {
		 Date[] date=readDate(args[0]);  
	        for(int i=0;i<date.length;i++)
	            StdOut.println(date[i].toString());
	}
	public static Date[] readDate(String name){
		In in=new In(name);
		QueueDemo<Date> q=new QueueDemo<Date>();
		while(!in.isEmpty())
			q.enqueue(new Date(in.readString()));
		int N=q.size();
		Date[] a=new Date[N];
		for(int i=0;i<N;i++){
			a[i]=q.dequeue();
		}
		return a;
	}

}
