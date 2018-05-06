package analysisalgor;
/*最遥远的一对（一维）。编写一个程序，给定一个含有 N 个 double 值的数组 a[]，
 * 在其中找到一对最遥远的值：两者之差（绝对值）最大的两个数。
 * 程序在最坏情况下所需的运行时间应该是线性级别的。*/
public class FarthestPair {
	public static double[] findFarthest(double[] a){
		double max=Double.MIN_NORMAL;
		double min=Double.MAX_VALUE;
		double[] result=new double[2];
		int N=a.length;
		for(int i=0;i<N;i++){
			if(a[i]>max)
				max=a[i];
			if(a[i]<min)
				min=a[i];
		}
		result[0]=min;
		result[1]=max;
		return result;
	}
	public static void main(String[] args) {
		double[] a = {1,2,3,4,5,888,76,45};
		double[] result=findFarthest(a);
		System.out.printf("%.2f,%.2f",result[0],result[1]);
	}
	

}
