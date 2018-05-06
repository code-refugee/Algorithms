package priorityqueue;
/*使用无序数组和有序数组实现优先队列
 * 支持插入元素和删除最大元素的操作*/
import edu.princeton.cs.algs4.*;
public class OrderedArrayMaxPQ<key extends Comparable<key>> {
	private key[] pq;
	private int N;
	public OrderedArrayMaxPQ(int maxN){
		pq=(key[])new Comparable[maxN];
		N=0;
	}
	public void insert(key v){
		pq[N]=v;
		insertionPQ(pq,0,N);
		N++;
//		int i = N-1;
//        while (i >= 0 && less(v, pq[i])) {
//            pq[i+1] = pq[i];
//            i--;
//        }
//        pq[i+1] = v;
//        N++;
	}
	public key delMax(){
		return pq[--N];
	}
	private  void insertionPQ(key[] pq,int lo,int hi){
		int exchange=0;
		for(int i=hi;i>lo;i--){
			if(less(pq[i],pq[i-1])){
				exch(pq,i,i-1);
				exchange++;
			}
		}
		if(exchange==0) return;
		for(int i=lo+1;i<=hi;i++){
			key v=pq[i];
			int j=i;
			while(less(v,pq[j-1])){
				pq[j]=pq[j-1];
				j--;
			}
			pq[j]=v;
		}
	}
	private  boolean less(key v,key w){
		return v.compareTo(w)<0;
	}
	private  void exch(key[] a,int i,int j){
		key t=a[i];
		a[i]=a[j];
		a[j]=t;
	}
	public boolean isEmpty(){
		return N==0;
	}
	public int size(){
		return N;
	}
	public static void main(String[] args) {
		  OrderedArrayMaxPQ<String> pq = new OrderedArrayMaxPQ<String>(10);
	        pq.insert("this");
	        pq.insert("is");
	        pq.insert("a");
	        pq.insert("test");
	        while (!pq.isEmpty())
	            StdOut.println(pq.delMax());
	}
}
