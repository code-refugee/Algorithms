package analysisalgor;

import edu.princeton.cs.algs4.In;

/*修改TreeSum,正确处理两个较大的int值相加可能溢出的情况*/
public class ThreeSumModify {
	//统计和为0的元组数量
	public static int count(int[] a){
		int N=a.length;
		int cnt=0;
		for(int i=0;i<N;i++){
			for(int j=i+1;j<N;j++){
				for(int k=j+1;k<N;k++)
					if((long)a[i]+a[j]+a[k]==0)//使用long来避免溢出
						cnt++;
			}
		}
		return cnt;
	}
	public static void main(String[] args) {
		int[] a=In.readInts(args[0]);
		System.out.println(count(a));
	}

}
