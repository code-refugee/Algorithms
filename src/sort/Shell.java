package sort;
/*希尔排序（插入排序的变种）*/
import edu.princeton.cs.algs4.*;
public class Shell {
	public static int cnt;
	public static void sort(Comparable[] a){
		int N=a.length;
		int h=1;
		cnt=0;
		while(h<N/3)h=3*h+1;
		while(h>=1){
			for(int i=h;i<N;i++){
				cnt++;
				for(int j=i;j>=h&&less(a[j],a[j-h]);j-=h)
					exch(a,j,j-h);
			}
			h=h/3;
		}
	}
	private static boolean less(Comparable v,Comparable w){
		cnt++;
		return v.compareTo(w)<0;
	}
	private static void exch(Comparable[] a,int i,int j){
		Comparable t=a[i];
		a[i]=a[j];
		a[j]=t;
	}
	public static void main(String[] args) {
		for(int i=0;i<30;i++){
			int N=100+100*i;
			Double[] b=new Double[N];
			for(int j=0;j<N;j++){
				b[j]=StdRandom.uniform();
			}
			Shell s=new Shell();
			s.sort(b);
			System.out.printf("比较次数%d\t数组大小%d\t比值大小%f\n",s.cnt,N,(double)s.cnt/N);
		}
	}

}
