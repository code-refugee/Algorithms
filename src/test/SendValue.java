package test;

public class SendValue {
	private static void insertionPQ(Comparable[] a){
		for(int i=a.length-1;i>0;i--){
			if(less(a[i],a[i-1])){
				exch(a,i,i-1);
				
			}
		}
		
	}
	private static boolean less(Comparable v,Comparable w){
		return v.compareTo(w)<0;
	}
//	public static boolean less(Comparable v,Comparable w){
//		return v.compareTo(w)<0;
//	}
	private static void exch(Comparable[] a,int i,int j){
		Comparable t=a[i];
		a[i]=a[j];
		a[j]=t;
	}
	
	public static void main(String[] args){
		Integer[] a={9,6,2,102,101,52,13,68,45,1,2,4,10,7,8};
		insertionPQ(a);
		for(int i=0;i<a.length-1;i++){
			System.out.println(a[i]);
		}

	}

}
