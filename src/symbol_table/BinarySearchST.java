package symbol_table;

import java.util.NoSuchElementException;
import java.util.Scanner;

import bagstatckqueue.QueueDemo;

/*二分查找（基于有序数组）在最坏情况下查找的成本为lgN，插入的成本为2N
 * 平均情况下查找的成本为lgN,插入成本为N。该数据结构有最优的查找效率
 * 和空间需求，能够进行有序性相关操作，但对插入操作很慢*/
public class BinarySearchST<Key extends Comparable<Key>, Value> {
	
	//指定初始化数组大小
	private static final int INIT_CAPACITY = 2;
	
	//用于存储键
	private Key[] keys;
	
	//用于存储值
	private Value[] vals;
	
	//用于表示键值对的数量
	private int n;
	
	//构造方法，初始化数组大小，默认初始大小为1
	public BinarySearchST(){
		this(INIT_CAPACITY);
	}
	public BinarySearchST(int capacity){
		keys=(Key[])new Comparable[capacity];
		vals=(Value[])new Object[capacity];
		n=0;
	}
	
	//调整数组大小，变为原数组大小的2倍
	private void resize(int capacity){
		assert capacity>size();
		Key[] temp_keys=(Key[])new Comparable[capacity];
		Value[] temp_vals=(Value[])new Object[capacity];
		for(int i=0;i<size();i++){
			temp_keys[i]=keys[i];
			temp_vals[i]=vals[i];
		}
		keys=temp_keys;
		vals=temp_vals;
	}
	
	//返回键值对的数量
	public int size(){
		return n;
	}
	
	//[lo..hi]之间键的数
	public int size(Key lo,Key hi){
		if(lo==null)
			throw new IllegalArgumentException("first argument to size() is null");
		if(hi==null)
			throw new IllegalArgumentException("second argument to size() is null");
		if(lo.compareTo(hi)>0)
			return 0;
		if(contains(hi))
			return rank(hi)-rank(lo)+1;
		else
			return rank(hi)-rank(lo);
	}
	
	//表是否为空
	public boolean isEmpty(){
		return n==0;
	}
	
	//键key是否存在于表中
	public boolean contains(Key key){
		return get(key)!=null;
	}
	
	//获取键key对应的值（若键key不存在则返回空）
	public Value get(Key key){
		
		//注意key不能为null，因为后面需要用compareTo()方法对key值进行比较
		if(key==null)
			throw new IllegalArgumentException("argument to get() is null");
		
		if(isEmpty())
			return null;
		
		int i=rank(key);
		
		if(i<size()&&keys[i].compareTo(key)==0)
			return vals[i];
		
		else
			return null;
	}
	
	//将键值对存入表中（若值为空则将键key从表中删除）
	public void put(Key key,Value val){
		if(key==null)
			throw new IllegalArgumentException("argument to put() is null");
		if(val==null){
			delete(key);
			return;
		}
			
		int i=rank(key);
		
		//key值已经存在于符号表，更新值
		if(i<size()&&keys[i].compareTo(key)==0){
			vals[i]=val;
			return;
		}
		
		//检测数组是否已经存满，若存满，则将数组大小变为原来的2倍
		if(n==keys.length)
			resize(2*size());
		
		/*若key值不存在，则将key-val值存入符号表，并保证符号表中的key值有序.
		 * 在rank()中已经说过，若key存在于符号表中，返回的是key在数组中的下标，
		 * 若key不存在于符号表中，则返回的值表示小于key的数量，即下标在[0,i)的
		 * 值小于key,在[i,n)的值大于key*/
		for(int j=size();j>i;j--){
			
			//将[i,n)的值向后移动
			keys[j]=keys[j-1];
			vals[j]=vals[j-1];
		}
		
		//将键值对插入下标i的位置，使得keys数组有序
		keys[i]=key;
		vals[i]=val;
		n++;
		
		assert check();
	}
	
	//从表中删去键key（及其对应的值）
	public void delete(Key key){
		if(key==null)
			throw new IllegalArgumentException("argument to delete() is null");
		
		//符号表为空，则不做任何事情
		if(isEmpty())
			return;
		
		int i=rank(key);
		
		/*要删的键不存在，则不做任何操作；这里说明一下为什么会有i==size()
		 * 正如rank()方法中所描述的，lo初始值为0，且永不变小，i值得到的如果
		 * 不是key在数组中的下标，就是小于key的数量，小于key的数量的最大值是size()
		 * 即keys数组中的所有值都比key要小*/
		if(i==size()||keys[i].compareTo(key)!=0)
			return;
		
		/*为什么是j<size()-1？因为接下来j要进行+1操作，
		 * 若j<size()则数组会越界*/
		for(int j=i;j<size()-1;j++){
			keys[j]=keys[j+1];
			vals[j]=vals[j+1];
		}
		n--;
		//防止对象游离
		keys[n]=null;
		vals[n]=null;
		
		//若n的大小为数组的1/4，则将数组大小减半
		if(n>0&&n==keys.length/4)
			resize(keys.length/2);
		
		assert check();
	}
	
	//删除最小的键
	public void deleteMin(){
		if (isEmpty())
			throw new NoSuchElementException("Symbol table underflow error");
		delete(min());
	}
	
	//删除最大的键
	public void deleteMax(){
		if (isEmpty())
			throw new NoSuchElementException("Symbol table underflow error");
		delete(max());
	}
	
	//最小的键
	public Key min(){
		if(isEmpty())
			throw new NoSuchElementException("called min() with empty symbol table");
		return keys[0];
	}
	
	//最大的键
	public Key max(){
		if(isEmpty())
			throw new NoSuchElementException("called max() with empty symbol table");
		return keys[n-1];
	}
	
	//小于等于key的最大键
	public Key floor(Key key){
		if (key == null)
			throw new IllegalArgumentException("argument to floor() is null"); 
		
		int i=rank(key);
		if(i<size()&&keys[i].compareTo(key)==0)
			return key;
		if(i==0)
			return null;
		else
			return keys[i-1];
		
	}
	
	//大于等于key的最小值
	public Key ceiling(Key key){
		if(key==null)
			throw new IllegalArgumentException("argument to ceiling() is null"); 
		
		int i=rank(key);
		if(i==size())
			return null;
		else
			return keys[i];
	}
	
	//排名为k的键
	public Key select(int k){
		if (k < 0 || k >= size()) {
            throw new IllegalArgumentException("called select() with invalid argument: " + k);
        }
        return keys[k];
	}
	
	//二分查找
	public int rank(Key key){
		
		return rank(key,0,size()-1);
	}
	
	//重载rank方法
	private int rank(Key key,int lo,int hi){
		
		/*注意此处并不是像第一章一样返回-1而是返回lo，
		 * lo代表小于key键的数量（lo的初始值为0且永远不会变小）*/
		if(lo>hi)
			return lo;
		
		int mid=lo+(hi-lo)/2;
		
		//使用compareTo()方法比较大小（不能用>、<、=比较）
		int cmp=key.compareTo(keys[mid]);
		
		if(cmp>0)
			return rank(key,mid+1,hi);
		
		else if(cmp<0)
			return rank(key,lo,mid-1);
		
		else
			return mid;
	}
	
	//表中所有键的集合（已排序）
	public Iterable<Key> keys(){
		return keys(min(),max());
	}
	
	//[lo..hi]之间的所有键（已排序）
	public Iterable<Key> keys(Key lo,Key hi){
		if (lo == null)
			 throw new IllegalArgumentException("first argument to keys() is null");
		if (hi == null)
	    	 throw new IllegalArgumentException("second argument to keys() is null"); 
		QueueDemo<Key> queue=new QueueDemo<>();
		if(lo.compareTo(hi)>0)
			return queue;
		for(int i=rank(lo);i<rank(hi);i++){
			queue.enqueue(keys[i]);
		}
		if(contains(hi))
			queue.enqueue(keys[rank(hi)]);
		return queue;
	}
	
	//用于检测keys数组是否有序及数据结构的完整性
	private boolean check(){
		return isSorted()&&rankCheck();
	}
	
	private boolean isSorted(){
		for(int i=1;i<size();i++){
			if(keys[i].compareTo(keys[i-1])<0)
				return false;
		}
		return true;
	}
	
	private boolean rankCheck(){
		for(int i=0;i<size();i++){
			if(i!=rank(select(i)))
				return false;
		}
		for(int i=0;i<size();i++){
			if(keys[i].compareTo(select(rank(keys[i])))!=0)
				return false;
		}
		return true;
	}
	
	//主方法
	public static void main(String[] args){
		BinarySearchST<String, Integer> st=new BinarySearchST<>();
		System.out.println("please write keys");
		
		//从控制台获得输入流
		Scanner sc=new Scanner(System.in);
		
		//以0作为结束标志
		for(int i=0;!sc.hasNext("0");i++){
			st.put(sc.next(), i);
		}
		
//		System.out.println(st.min());
//		st.delete("A");
//		System.out.println(st.max());
		for(String s:st.keys())
			System.out.println(s+" "+st.get(s));
	}
}
