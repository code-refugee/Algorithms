package dataabstraction;

/*编写一个Interval2D的用例，从命令行接受参数N、min和max。
 * 生成N个随机的2D间隔，其宽度和高均匀地分布在单位正方形中的min和max之间。
 * 用StdDraw画出它们并打印出相交的间隔对的数量以及有包含关系的间隔对数量。
*/
import edu.princeton.cs.algs4.*;                       
public class Interval2 {
	private int N;
	private double min;
	private double max;
	private Interval2D[] interval2;
	
	public Interval2(int N,double min,double max){
		this.N=N;
		this.min=min;
		this.max=max;
		interval2=new Interval2D[N];
	}
	
	public void createInterval2D(){
		Interval1D xinterval;
		Interval1D yinterval;
		for(int i=0;i<N;i++){
			xinterval=new Interval1D(StdRandom.uniform(min, max),StdRandom.uniform(min, max));
			yinterval=new Interval1D(StdRandom.uniform(min, max),StdRandom.uniform(min, max));
			interval2[i]=new Interval2D(xinterval,yinterval);
			interval2[i].draw();
		}
	}
	
	public void sysNum(){
		int inter=0;
		int contain=0;
		for(int i=0;i<N;i++){
			for(int k=i+1;k<N;k++){
				if(interval2[i].intersects(interval2[k]))
					inter++;
			}
		}
		System.out.println(inter);
	}
	
	public static void main(String[] args) {
		Interval2 in=new Interval2(10,0.6,0.8);
		in.createInterval2D();
		in.sysNum();
	}

}
