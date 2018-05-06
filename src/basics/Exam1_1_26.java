package basics;
/*二项分布。估计用以下代码计算binomial(100,50,0.25)将会产生的递归调用次数*/
import edu.princeton.cs.algs4.*;
public class Exam1_1_26 {
	
	//若使用该方法，因为递归，每次都在做上一次做过的事，运行速度非常慢
//	public static double binomial(int N, int k, double p)
//	{
//	    if (N == 0 && k == 0)
//	        return 1.0;
//	    if (N < 0 || k < 0) 
//	        return 0.0;
//	     return (1.0 - p)*binomial(N-1, k, p) + p*binomial(N-1, k-1,p);
//	}
	//求n！的2种方法
//	public static int Sun(int n){
		//法一
//		int sum=1;
//		while(n>0){
//			sum=sum*n;
//			n--;
//		}
//		return sum;
		//法二
//		if(n==0)
//			return 1;
//		return n*Sun(n-1);
//	}
//	public static void main(String[] args) {
//		System.out.println(Sun(15));
//	}
	public static double[][] binomial(int N,int k,double p){
		double[][] array=new double[N+1][k+1];
        //给二维数组初始化第一列，避免下面执行时出现数组下标越界
        array[0][0]=1.0;
        for(int i=1;i<N+1;i++)
            array[i][0]=array[i-1][0]*(1-p);
        for(int i=1;i<N+1;i++)
            for(int j=1;j<=i && j<k+1;j++){
            	array[i][j]=(1-p)*array[i-1][j] + p*array[i-1][j-1];
            	System.out.println(array[i-1][j]);
            }
                
        return array;


		
	}
	public static void main(String[] args) {
		double[][] array=binomial(100,50,0.25);
        System.out.println(array[100][50]);
	}

}
