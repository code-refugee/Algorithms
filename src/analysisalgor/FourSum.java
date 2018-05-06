package analysisalgor;
import java.util.Arrays;

/*4-sum。为 4-sum 设计一个算法。*/
import edu.princeton.cs.algs4.*;
public class FourSum {
	public static int count(int[] a){
		Arrays.sort(a);
		int len=a.length;
		int sum=0;
		//太慢
		for(int i=0;i<a.length;i++)
			for(int j=i+1;j<a.length;j++)
				for(int k=j+1;k<a.length;k++){
					if(BrinSearch.rank(-a[i]-a[j]-a[k], a)>k)
						sum++;
				}
				//更快
//		for(int i=0;i<len;i++)
//			for(int j=i+1;j<len;j++)
//				for(int k=j+1,h=len-1;k<h;){
//					if(a[i]+a[j]+a[k]+a[h]>0)
//						h--;
//					else if(a[i]+a[j]+a[k]+a[h]<0)
//						k++;
//					else{
//						sum++;
//						h--;
//						k++;
//					}
//				}
		return sum;
	}
	public static void main(String[] args) {
		int[] a={-9,-8,-5,-3,-6,-4,13,12};
		System.out.println(count(a));
	}

}
