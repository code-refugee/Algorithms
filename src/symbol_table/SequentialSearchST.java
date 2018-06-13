package symbol_table;

import java.util.NoSuchElementException;
import java.util.Scanner;

import bagstatckqueue.QueueDemo;;

/*顺序查找（基于无序链表）最坏情况下查找和插入的成本都为N
 * 平均情况下查找的成本为N/2，插入为N，因此该数据结构
 * 适用于小型问题，对于大型符号表很慢*/
public class SequentialSearchST<Key, Value> {
	
	//首节点
	private Node first;
	
	//数组大小
	private int N;
	
	/*节点内部类，该类有一个键和一个值
	 * 还有一个next指向下一个节点*/
	private class Node{
		Key key;
		Value val;
		Node next;
		
		//定义构造方法
		public Node(Key key,Value val,Node next){
			this.key=key;
			this.val=val;
			this.next=next;
		}
	}
	
	//链表是否为空
	public boolean isEmpty(){
		return size()==0;
	}
	
	//链表中键值对的数量
	public int size(){
		return N;
	}
	
	//键key是否存在于表中
	public boolean contains(Key key){
		//key为空则抛出异常，因为规定插入的key值不得为null
		if(key==null)
			throw new IllegalArgumentException("argument to contains() is null");
		
		return get(key)!=null;
	}
	
	//获取键key对应的值（若键key不存在则返回null）
	public Value get(Key key){
		if(key==null)
			throw new IllegalArgumentException("argument to get() is null");
		
		//循环遍历链表，直到找到key值
		for(Node x=first;x!=null;x=x.next){
			//注意此处比较相等是用equals,不能直接用=比较
			if(key.equals(x.key))
				return x.val;
		}
		
		return null;
	}
	
	//将键值对存入表中（若值为空则将键key删去）
	public void put(Key key,Value val){
		if(key==null)
			throw new IllegalArgumentException("arguments to put() is null");
		if(val==null){
			delete(key);
			return;
		}
		
		//若链表中存在key则跟新val值
		for(Node x=first;x!=null;x=x.next){
			if(key.equals(x.key)){
				x.val=val;
				return;
			}
		}
		
		//若不存在key值则将键值对插入链表的首节点
		first=new Node(key,val,first);
		N++;
	}
	
	//从表中删去键key（及其对应的值）
	public void delete(Key key){
		if(key==null)
			throw new IllegalArgumentException("arguments to delete() is null");
		first=delete(first,key);
	}
	
	private Node delete(Node x,Key key){
		if(x==null)
			return null;
		
		/*注意此处return的是x.next并不是x所以其实就是将
		 * 该节点给删除了,相当于是遍历了整个链表查找key*/
		if(key.equals(x.key)){
			N--;
			return x.next;
		}
		x.next=delete(x.next,key);
		return x;
	}
	
	//表中所有键的集合
	public Iterable<Key> keys(){
		QueueDemo<Key> queue=new QueueDemo<>();
		for(Node x=first;x!=null;x=x.next)
			queue.enqueue(x.key);
		return queue;
	}
	
	public static void main(String[] args){
		SequentialSearchST<String, Integer> st=new SequentialSearchST<>();
		System.out.println("please write keys");
		
		//从控制台获得输入流
		Scanner sc=new Scanner(System.in);
		
		//以0作为结束标志
		for(int i=0;!sc.hasNext("0");i++){
			st.put(sc.next(), i);
		}
		
//		System.out.println(st.size());
//		st.delete("A");
//		System.out.println(st.size());
		for(String s:st.keys())
			System.out.println(s+" "+st.get(s));
	}
}
