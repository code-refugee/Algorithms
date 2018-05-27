package priorityqueue;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

import basicsrecover.*;
/*在线性时间内检测数组pq[]是否是一个面向最小元素的堆*/
public class MaxPQ<Key> implements Iterable<Key>{
	private Key[] pq;
	private int n;
	private Comparator<Key> comparator;
	public MaxPQ(int initCapacity){
		pq=(Key[])new Object[initCapacity+1];
		n=0;
	}
	public MaxPQ(){
		this(1);
	}
	public MaxPQ(int initCapacity,Comparator<Key> comparator){
		this.comparator=comparator;
		pq=(Key[])new Object[initCapacity+1];
		n=0;
	}
	public MaxPQ(Comparator<Key> comparator){
		this(1,comparator);
	}
	public MaxPQ(Key[] key){
		n=key.length;
		pq=(Key[])new Object[n+1];
		for(int i=0;i<n;i++)
			pq[i+1]=key[i];
		//进行下沉操作，保证最大元素在pq[1]
		for(int k=n/2;k>=1;k--)
			sink(k);
		assert isMaxHeap();
	}
	public boolean isEmpty(){
		return n==0;
	}
	public int size(){
		return n;
	}
	public Key max(){
		if(isEmpty())
			throw new NoSuchElementException("priority queue underflow");
		return pq[1];
	}
	private void resize(int capacity){
		assert capacity>n;
		Key[] temp=(Key[])new Object[capacity];
		for(int i=1;i<=n;i++)
			temp[i]=pq[i];
		pq=temp;
	}
	public void insert(Key x){
		//因为下标从1开始0未用，所以长度得减1
		if(n==pq.length-1)
			resize(2*pq.length);
		pq[++n]=x;
		swim(n);
		assert isMaxHeap();
	}
	public Key delMax(){
		if(isEmpty())
			throw new NoSuchElementException("priority queue underflow");
		Key max=pq[1];
		exch(1,n--);
		sink(1);
		pq[n+1]=null;
		if(n>0&&(n==(pq.length-1)/4))
			resize(pq.length/2);
		assert isMaxHeap();
		return max;
	}
	private void swim(int k){
		while(k>1&&less(k/2,k)){
			exch(k/2,k);
			k=k/2;
		}
	}
	private void sink(int k){
		while(2*k<=n){
			int j=2*k;
			if(j<n&&less(j,j+1)) j++;
			if(!less(k,j)) break;//
			exch(k,j);
			k=j;
		}
	}
	private boolean less(int i,int j){
		if(comparator==null){
			return ((Comparable<Key>)pq[i]).compareTo(pq[j])<0;
		}
		else
			return comparator.compare(pq[i], pq[j])<0;
	}
	private void exch(int i,int j){
		Key temp=pq[i];
		pq[i]=pq[j];
		pq[j]=temp;
	}
	private boolean isMaxHeap(){
		return isMaxHeap(1);
	}
	private boolean isMaxHeap(int k){
		if(k>n)
			return true;
		int left=2*k;
		int right=2*k+1;
		if(left<=n&&less(k,left)) return false;
		if(right<=n&&less(k,right)) return false;
		return isMaxHeap(left)&&isMaxHeap(right);
	}
	public Iterator<Key> iterator(){
		return new HeapIterator();
	}
	private class HeapIterator implements Iterator<Key>{
		private MaxPQ<Key> copy;
		public HeapIterator(){
			if(comparator==null)
				copy=new MaxPQ<Key>(size());
			else
				copy=new MaxPQ<Key>(size(),comparator);
			for(int i=1;i<=n;i++)
				copy.insert(pq[i]);
		}
		public boolean hasNext(){
			return !copy.isEmpty();
		}
		public Key next(){
			if(!hasNext())
				throw new NoSuchElementException("");
			return copy.delMax();
		}
	}
	public static void main(String[] args) {
		Card[] cd=new Card[10];
		for(int i=0;i<10;i++){
			cd[i]=new Card();
		}
		MaxPQ<Card> pq=new MaxPQ<>(cd);
		for(Card m:pq){
			System.out.println(m);
		}
			
	}
}
