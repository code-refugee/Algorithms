package dataabstraction;
/*编写一个Point2D的用例，从命令行接受一个整数N。
 * 在单位正方形内生成N个随机点，然后计算两点之间的最近距离*/
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.StdIn;;
public class Point {
	private int n;
	private Point2D[] p;
	public Point(int N){
		n=N;
		p=new Point2D[n];
	}
	public void drawPoint(){
		for(int i=0;i<n;i++){
			double m=(double)(Math.random());
			double n=(double)(Math.random());
			p[i]=new Point2D(m,n);
			p[i].draw();
		}
	}
	public double minDistance(){
		double result=p[0].distanceTo(p[1]);
		for(int i=0;i<n;i++){
			for(int k=i+1;k<n;k++){
				double distance=p[i].distanceTo(p[k]);
//				System.out.println(distance);
				if(distance<result)
					result=distance;
			}
		}
		return result;
	}
	
	public static void main(String[] args){
		Point po=new Point(StdIn.readInt());
		po.drawPoint();
		System.out.println(po.minDistance());
		
	}

}
