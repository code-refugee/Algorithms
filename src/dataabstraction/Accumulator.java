package dataabstraction;
/*累加器的方差      回顾1.1.34中内容---此处采用的求平均值和方差的方式很好,
 * 比直接对所有数据的平方求和能更好的避免四舍五入带来的误差，记忆之*/
public class Accumulator {
	private double m;//所有数据的平均值
	private double s;//总和
	private int N;//个数
	public void addDataValue(double x){
		N++;
		s=s+1.0*(N-1)/N*(x-m)*(x-m);
		m=m+(x-m)/N;
	}
	public double mean(){
		return m;
	}
	//计算方差
	public double var(){
		return s/(N-1);
	}
	//计算标准差
	public double stddev(){
		return Math.sqrt(this.var());
	}

}
