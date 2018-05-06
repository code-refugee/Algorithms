package basics;

import java.util.Arrays;

/*数组练习。编写一段程序，创建一个 N×N 的布尔数组 a[][]。
 * 其中当 i 和 j 互质时(没有相同 因子)，a[i][j] 为 true，
 * 否则为 false。*/
public class Exam1_1_30 {
	public static void main(String[] args) {
		boolean[][] c=creatArray();
		for(int i=0;i<c.length;i++){
			for(int k=0;k<c.length;k++){
				System.out.println(c[i][k]);
			}
		}
	}
	public static int gcb(int m,int n){
		if(n==0)
			return m;
		int r=m%n;
		return gcb(n,r);
	}
	public static boolean isCoprie(int m,int n){
		if(gcb(m,n)==1)
			return true;
		return false;
	}
	public static boolean[][] creatArray(){
		boolean[][] a=new boolean[10][10];
		for(int i=0;i<10;i++){
			for(int k=0;k<i+1;k++){
				if(i==0||k==0){
					a[i][k]=false;
				}else{
					a[i][k]=a[k][i]=isCoprie(i, k);
				}
				
			}
		}
		return a;
	
	}

}
