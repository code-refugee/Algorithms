package sortrecover;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

import basicsrecover.Card;
import priorityqueue.MaxPQ;
import edu.princeton.cs.algs4.*;
/*最小优先队列,使用数组来实现
 * 这是一个稳定的最小优先队列，实现的思想是利用time数组来存储插入的顺序
 * 稳定的含义是：对于重复元素，它们的返回顺序与它们被插入时的顺序相同*/
public class StableMinPQ<Key> implements Iterable<Key>{
	
	//该数组用于存储插入的元素
	private Key[] pq;
	
	//用于存储插入的顺序比如第一个插入的是1
	private long[] time;
	
	//自定义比较方法
	private Comparator<Key> comparator;
	
	//pq数组内元素的个数
	private int N;
	
	//记录time数组中插入的顺序
	private long timemap;
	
	//含有一个参数的构造方法，该形参指定了数组的初始容量
	public StableMinPQ(int max) {
		//注意不能直接new Key[],也不能直接new Object必须强转
		//注意是max+1因为下标为0的不用
		pq=(Key[]) new Object[max+1];
		time=new long[max+1];
		N=0;
		timemap=0;
	}
	
	//空的构造方法，无参
	public StableMinPQ(){
		//调用含有一个参数的构造方法
		this(1);
	}
	
	//含有两个参数的构造方法，第一个形参用于指定数组的初始容量，第二个指定比较的方式
	public StableMinPQ(int max,Comparator<Key> comparator){
		this.comparator=comparator;
		pq=(Key[]) new Object[max+1];
		time=new long[max+1];
		N=0;
		timemap=0;
	}
	
	//含有一个参数的构造方法，该形参指定了比较的方法
	public StableMinPQ(Comparator<Key> comparator){
		//调用含有两个参数的构造方法
		this(1,comparator);
	}
	
	//含有一个参数的构造方法，该形参指定了数组的大小和含有的元素
	public StableMinPQ(Key[] key){
		N=key.length;
		pq=(Key[])new Object[N+1];
		time=new long[N+1];
		//数组下标从1开始
		for(int i=0;i<N;i++){
			pq[i+1]=key[i];
			time[i+1]=++timemap;
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
	
	//动态调整数组的大小，除了pq数组，time数组的大小也要调整
	private void resize(int capacity){
		assert capacity>size();
		Key[] temp=(Key[])new Object[capacity];
		long[] timetemp=new long[capacity];
		for(int i=1;i<=size();i++){
			temp[i]=pq[i];
			timetemp[i]=time[i];
		}
		pq=temp;
		time=timetemp;
	}
	
	//向优先队列中插入一个元素
	public void insert(Key v){
		//若数组元素个数接近数组容量，数组大小扩大为原来的两倍
		if(N==pq.length-1)
			resize(2*pq.length);
		pq[++N]=v;
		time[N]=++timemap;
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
		time[N+1]=0;
		//将数组大小调整为原来的一半
		if(N>0&&N==(pq.length-1)/4)
			resize((pq.length-1)/2);
		
		//检测是否是面向最小元素的堆
		assert isMinHeap();
		
		return min;
				
	}
	
	/*比较两个元素的大小,此处的比较方式与我们常用的less方法类似
	 * 只不过当两个元素相同时，它会去比较它们的插入顺序，插入时间
	 * 比要比较的节点晚的话则会交换*/
	private boolean greater(int i,int j){
		if(comparator==null){
			int cmp=((Comparable<Key>)pq[i]).compareTo(pq[j]);
			if(cmp>0)
				return true;
			if(cmp<0)
				return false;
			return time[i]>time[j];
		}
		int cmp=comparator.compare(pq[i], pq[j]);
		if(cmp>0)
			return true;
		if(cmp<0)
			return false;
		return time[i]>time[j];					
	}
	
	//交换两个元素 注意：time数组的元素也要进行交换
	private void exch(int i,int j){
		Key temp=pq[i];
		pq[i]=pq[j];
		pq[j]=temp;
		long timetemp=time[i];
		time[i]=time[j];
		time[j]=timetemp;
	}
	
	//若元素比父节点小，则上浮
	private void swim(int k){
		while(k>1&&greater(k/2,k)){
			exch(k/2,k);
			k=k/2;
		}		
	}
	
	//如元素比它的孩子节点大，则下沉
	private void sink(int k){
		while(2*k<=N){
			int j=2*k;
			if(j<N&&greater(j,j+1)) j++;
			if(!greater(k,j)) break;
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
		if(left<=N&&greater(k,left)) return false;
		if(right<=N&&greater(k,right)) return false;
		return isMinHeap(left)&&isMinHeap(right);		
	}
	
	//给该类创建一个迭代器
	public Iterator<Key> iterator(){
		return new HeapIterator();
	}
	
	private class HeapIterator implements Iterator<Key>{
		private StableMinPQ<Key> copy;
		public HeapIterator(){
			if(comparator==null)
				copy=new StableMinPQ<Key>(size());
			else
				copy=new StableMinPQ<Key>(size(),comparator);
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
	
//	public static void main(String[] args) {
//		Card[] cd=new Card[10];
//		for(int i=0;i<10;i++){
//			cd[i]=new Card();
//		}
//		MinPQ<Card> pq=new MinPQ<>(cd);
//		for(Card m:pq){
//			System.out.println(m);
//		}
//			
//	}
	private static final class Tuple implements Comparable<Tuple> {
        private String name;
        //这个id是用来观察是否稳定的
        private int id;

        private Tuple(String name, int id) {
            this.name = name;
            this.id = id;
        }

        public int compareTo(Tuple that) {
            return this.name.compareTo(that.name);
        }

        public String toString() {
            return name + " " + id;
        }
    }

    // test client
    public static void main(String[] args) {
        StableMinPQ<Tuple> pq = new StableMinPQ<Tuple>();
        
        // insert a bunch of strings
        String text = "it was the best of times it was the worst of times it was the "
                    + "age of wisdom it was the age of foolishness it was the epoch "
                    + "belief it was the epoch of incredulity it was the season of light "
                    + "it was the season of darkness it was the spring of hope it was the "
                    + "winter of despair";
        String[] strings = text.split(" ");
        for (int i = 0; i < strings.length; i++) {
            pq.insert(new Tuple(strings[i], i));
        }


        // delete and print each key
        while (!pq.isEmpty()) {
            StdOut.println(pq.delMin());
        }
        StdOut.println();

    }


}
