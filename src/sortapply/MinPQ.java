package sortapply;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

import priorityqueue.MaxPQ;
import recover.Card;

/*最小优先队列,使用数组来实现*/
public class MinPQ<Key> implements Iterable<Key>{
	
	//该数组用于存储插入的元素
	private Key[] pq;
	
	//自定义比较方法
	private Comparator<Key> comparator;
	
	//数组内元素的个数
	private int N;
	
	//含有一个参数的构造方法，该形参指定了数组的初始容量
	public MinPQ(int max) {
		//注意不能直接new Key[],也不能直接new Object必须强转
		pq=(Key[]) new Object[max];
		N=0;
	}
	
	//空的构造方法，无参
	public MinPQ(){
		//调用含有一个参数的构造方法
		this(1);
	}
	
	//含有两个参数的构造方法，第一个形参用于指定数组的初始容量，第二个指定比较的方式
	public MinPQ(int max,Comparator<Key> comparator){
		this.comparator=comparator;
		pq=(Key[]) new Object[max];
		N=0;
	}
	
	//含有一个参数的构造方法，该形参指定了比较的方法
	public MinPQ(Comparator<Key> comparator){
		//调用含有两个参数的构造方法
		this(1,comparator);
	}
	
	//含有一个参数的构造方法，该形参指定了数组的大小和含有的元素
	public MinPQ(Key[] key){
		N=key.length;
		pq=(Key[])new Object[N+1];
		
		//数组下标从1开始
		for(int i=0;i<N;i++){
			pq[i+1]=key[i];
		}
		
		//进行下沉操作，保证最小的元素在pq[1]
		for(int k=N/2;k>=1;k--){
			sink(k);
		}
		
		//检测是否是面向最小元素的堆
		assert isMinHeap();
	}
	
	//检测数组是否为空
	public boolean isEmpty(){
		return N==0;
	}
	
	//返回数组的大小
	public int size(){
		return N;
	}
	
	//返回最小的元素
	public Key min(){
		if(isEmpty())
			throw new NoSuchElementException();
		return pq[1];
	}
	
	//动态调整数组的大小
	private void resize(int capacity){
		assert capacity>size();
		Key[] temp=(Key[])new Object[capacity];
		for(int i=1;i<=size();i++){
			temp[i]=pq[i];
		}
		pq=temp;
	}
	
	//向优先队列中插入一个元素
	public void insert(Key v){
		//若数组元素个数接近数组容量，数组大小扩大为原来的两倍
		if(N==pq.length-1)
			resize(2*pq.length);
		pq[++N]=v;
		
		//将元素上浮至合适的位置
		swim(N);
		
		//检测是否是面向最小元素的堆
		assert isMinHeap();
	}
	
	//删除最小元素并返回它的值
	public Key delMin(){
		if(isEmpty())
			throw new NoSuchElementException();
		
		Key min=pq[1];
		
		//交换头尾的元素
		exch(1,N--);
		
		//进行下沉操作
		sink(1);
		pq[N+1]=null;//防止对象游离
		
		//将数组大小调整为原来的一半
		if(N>0&&N==(pq.length-1)/4)
			resize((pq.length-1)/2);
		
		//检测是否是面向最小元素的堆
		assert isMinHeap();
		
		return min;
				
	}
	
	//比较两个元素的大小
	private boolean less(int i,int j){
		if(comparator==null){
			return ((Comparable<Key>)pq[i]).compareTo(pq[j])>0;
		}
			
		return comparator.compare(pq[i], pq[j])>0;
			
	}
	
	//交换两个元素
	private void exch(int i,int j){
		Key temp=pq[i];
		pq[i]=pq[j];
		pq[j]=temp;
	}
	
	//若元素比父节点小，则上浮
	private void swim(int k){
		while(k>1&&less(k/2,k)){
			exch(k/2,k);
			k=k/2;
		}		
	}
	
	//如元素比它的孩子节点大，则下沉
	private void sink(int k){
		while(2*k<=N){
			int j=2*k;
			if(j<N&&less(j,j+1)) j++;
			if(!less(k,j)) break;
			exch(k,j);
			k=j;
		}
	}
	
	//检测是否是面向最小元素的堆
	private boolean isMinHeap(){
		return isMinHeap(1);
	}
	private boolean isMinHeap(int k){
		if(k>N)
			return true;
		int left=2*k;
		int right=2*k+1;
		if(left<=N&&less(k,left)) return false;
		if(right<=N&&less(k,right)) return false;
		return isMinHeap(left)&&isMinHeap(right);		
	}
	
	//给该类创建一个迭代器
	public Iterator<Key> iterator(){
		return new HeapIterator();
	}
	
	private class HeapIterator implements Iterator<Key>{
		private MinPQ<Key> copy;
		public HeapIterator(){
			if(comparator==null)
				copy=new MinPQ<Key>(size());
			else
				copy=new MinPQ<Key>(size(),comparator);
			for(int i=1;i<=N;i++){
				copy.insert(pq[i]);
			}
		}
		public boolean hasNext(){
			return !copy.isEmpty();
		}
		public Key next(){
			if(!hasNext())
				throw new NoSuchElementException("");
			return copy.delMin();
		}
	}
	
	public static void main(String[] args) {
		Card[] cd=new Card[10];
		for(int i=0;i<10;i++){
			cd[i]=new Card();
		}
		MinPQ<Card> pq=new MinPQ<>(cd);
		for(Card m:pq){
			System.out.println(m);
		}
			
	}
	

}
