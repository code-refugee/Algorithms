package priorityqueue;

import java.util.Iterator;
import java.util.NoSuchElementException;

/*索引优先队列的实现*/
public class IndexMinPQ<Key extends Comparable<Key>> implements Iterable<Integer>{
	private int N;//PQ中的元素数量
	private int[] pq;//存储索引，由1开始
	private int[] qp;//存储索引的位置
	private Key[] keys;//有优先级之分的元素
	
	public IndexMinPQ(int max) {
		keys=(Key[])new Comparable[max+1];
		pq=new int[max+1];
		qp=new int[max+1];
		//如果索引i不在队列中，则qp[i]=-1
		for(int i=0;i<max+1;i++)
			qp[i]=-1;
	}
	
	public boolean isEmpty(){
		return N==0;
	}
	
	public int size(){
		return N;
	}
	
	public boolean contains(int k){
		return qp[k]!=-1;
	}
	
	/*插入一个元素，它和索引k关联*/
	public void insert(int k,Key key){
		if(contains(k))
			throw new IllegalArgumentException();
		N++;
		pq[N]=k;
		qp[k]=N;
		keys[k]=key;
		swim(N);
	}
	
	/*将索引为k的元素设为key*/
	public void change(int k,Key key){
		if(!contains(k))
			throw new NoSuchElementException();
		keys[k]=key;
		//因为不知道它会比它上面的大还是比它下面的小所以两个操作都要做
		swim(qp[k]);
		sink(qp[k]);
	}
	
	public void delete(int k){
		if(!contains(k))
			throw new NoSuchElementException();
		int index=qp[k];
		exch(index,N--);
		swim(index);
		sink(index);
		keys[k]=null;
		pq[N+1]=-1;
		qp[k]=-1;
	}
	
	public Key min(){
		if(isEmpty())
			throw new NoSuchElementException();
		return keys[pq[1]];
	}
	
	public int minIndex(){
		if(isEmpty())
			throw new NoSuchElementException();
		return pq[1];
	}
	
	public int delMin(){
		if(isEmpty())
			throw new NoSuchElementException();
		int min=pq[1];
		exch(1,N--);
		sink(1);
		qp[min]=-1;
		keys[min]=null;
		pq[N+1]=-1;
		return min;		
	}
	
	public void swim(int k){
		while(k>1&&less(k/2,k)){
			exch(k/2,k);
			k=k/2;
		}
	}
	
	public void sink(int k){
		while(2*k<=N){
			int j=2*k;
			if(j<N&&less(j,j+1))
				j++;
			if(!less(k,j))break;
			exch(k,j);
			k=j;
		}
	}
	
	private boolean less(int i,int j){
		return keys[pq[i]].compareTo(keys[pq[j]])>0;
	}
	
	private void exch(int i,int j){
		int swap=pq[i];
		pq[i]=pq[j];
		pq[j]=swap;
		
		//因为索引的位置变了，所以记录所以位置的数组的值也要变
		qp[pq[i]]=i;
		qp[pq[j]]=j;
	}
	
	public Iterator<Integer> iterator(){
		return new HeapIterator();
	}
	
	private class HeapIterator implements Iterator<Integer>{
		private IndexMinPQ<Key> copy;
		public HeapIterator(){
			copy=new IndexMinPQ<>(pq.length-1);//因为下标0不用
			for(int i=1;i<=N;i++){
				copy.insert(pq[i], keys[pq[i]]);
			}
		}
		
		public boolean hasNext(){
			return !copy.isEmpty();
		}
		
		public Integer next(){
			if(!hasNext())
				throw new NoSuchElementException();
			return copy.delMin();
		}
	}
	
	public static void main(String[] args){
		String[] strings = { "it", "was", "the", "best", "of", "times", "it", "was", "the", "worst" };
		IndexMinPQ<String> imp=new IndexMinPQ<>(strings.length);
		for(int i=0;i<strings.length;i++){
			imp.insert(i, strings[i]);
		}
		for(int i:imp){
			System.out.println(i+" "+strings[i]);
		}
	}

}
