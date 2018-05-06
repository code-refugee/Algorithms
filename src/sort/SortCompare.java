package sort;
import edu.princeton.cs.algs4.*;
import quicklysort.*; 
public class SortCompare {
	public static double time(String alg,Double[] a){
		Stopwatch timer=new Stopwatch();
		if(alg.equals("Insertion")) Insertion.sort(a);
		if(alg.equals("Selection")) Selection.sort(a);
		if(alg.equals("Shell")) Shell.sort(a);
		if(alg.equals("InsertionFast")) InsertionFast.sort(a);
		if(alg.equals("Sort2distinct")) Sort2distinct.sort(a);
		return timer.elapseTime();
	}
	public static double timeRandomInput(String alg,int N,int T){
		double total=0.0;
		Double[] a=new Double[N];
		for(int i=0;i<T;i++){
			for(int j=0;j<N;j++){
				a[j]=StdRandom.uniform();
			}
			total+=time(alg,a);
		}
		return total;
	}
	public static double timeTrial(String alg,int N){
		double total=0.0;
		Double[] b=new Double[N];
		for(int i=0;i<N;i++)
			b[i]=StdRandom.uniform();
		total=time(alg,b);
		return total;
	}
	public static void main(String[] args) {
		double first=1.0;
		double second;
		String alg="Sort2distinct";
		for(int N=250;true;N+=N){
			second=timeTrial(alg, N);
			System.out.printf("%2.1f",second);
		}
	
	}

}
