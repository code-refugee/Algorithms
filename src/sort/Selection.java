package sort;

import edu.princeton.cs.algs4.*;

/*选择排序*/
public class Selection {
	public static void sort(Comparable[] a){
		int N=a.length;
		for(int i=0;i<N;i++){
			int min=i;
			for(int j=i+1;j<N;j++){
				if(less(a[j],a[min]))
					min=j;
			}
			exch(a,i,min);
			show(a);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(i!=N-1)
				StdDraw.clear();
		}
	}
	private static boolean less(Comparable v,Comparable w){
		return v.compareTo(w)<0;
	}
	private static void exch(Comparable[] a,int i,int j){
		Comparable t=a[i];
		a[i]=a[j];
		a[j]=t;
	}
	//绘制棒状图
	private static void show(Comparable[] a){
		int N=a.length;
		for(int i=0;i<N;i++){
			double x=1.0*i/N;
			double y=(double)a[i]/2.0;
			double rw=0.5/N;
			double rh=(double)a[i]/2.0;
			StdDraw.filledRectangle(x, y, rw, rh);
		}
	} 
	public static boolean isSorted(Comparable[] a){
		for(int i=1;i<a.length;i++){
			if(less(i,i-1))
				return false;
		}
		return true;	
	}
	public static void main(String[] args) {
		Double[] b=new Double[10];
		for(int i=0;i<10;i++)
			b[i]=StdRandom.uniform();
		sort(b);
	}

}
