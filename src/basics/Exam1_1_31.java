package basics;
/* 随机连接。编写一段程序，从命令行接受一个整数 N 和 double 值 p(0 到 1 之间)作为参数，
 *  在一个圆上画出大小为 0.05 且间距相等的 N 个点，然后将每对点按照概率 p 用灰线连接。*/
import edu.princeton.cs.algs4.*;
public class Exam1_1_31 {
	public static void main(String[] args) {
		drawPit(6, 0.8);
	}
	//写一个点的内部类
	public static class point{
		double x;//横坐标
		double y;//纵坐标
		public point(double x,double y){
			this.x=x;
			this.y=y;
		}
	}
	public static void drawPit(int n,double p){
		//画圆
		StdDraw.setPenColor(StdDraw.RED);
		StdDraw.filledCircle(0.5, 0.5, 0.5);
		
		//画点
		point[] point=new point[n];
		StdDraw.setPenColor(StdDraw.BLACK);
		for(int i=0;i<n;i++){
			point[i]=new point(0.5+0.5*Math.cos(2*Math.PI*i/n),0.5+0.5*Math.sin(2*Math.PI*i/n));
			StdDraw.point(point[i].x, point[i].y);
		}
		
		//连线
		StdDraw.setPenColor(StdDraw.LIGHT_GRAY);//画笔为灰色
		for(int i=0;i<n;i++){
			for(int k=0;k<n;k++){
				if(StdRandom.bernoulli(p)){
					StdDraw.line(point[i].x, point[i].y,point[k].x, point[k].y);
				}
			}
		}
	}
	




}
