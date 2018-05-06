package dataabstraction;

/*编写一个类 VisualCounter，支持加一和减一操作。它的构造函数接受两个参数 N 和 max，
 * 其中 N 指定了操作的最大次数， max 指定了计数器的最大绝对值。作为副作用，
 * 用图像显示每次计数器变化后的值。*/
import edu.princeton.cs.algs4.*;
public class VisualCounter {
	private int N;
	private int total;
	private int m;
	private int n;
	public VisualCounter(int m,int n){
		this.m=m;
		this.n=n;
		StdDraw.setXscale(0, m);
		StdDraw.setYscale(-n, n);
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.setPenRadius(0.005);
	}
	public void increment(){
		if(N<m&&total<n){
			N++;
			total++;
			StdDraw.point(N,total);
		}
	}
	public void decrement(){
		if(N<m&&-total<n){
			N++;
			total--;
			StdDraw.point(N, total);
		}
	}
	public static void main(String[] args) {
		VisualCounter counter = new VisualCounter(300, 150);
        for (int i = 0; i < 10000; i++)
            if (StdRandom.random() > 0.5) {
                counter.increment();
            }else {
                counter.decrement();
            }
        


	}

}
