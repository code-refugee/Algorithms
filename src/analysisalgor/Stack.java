package analysisalgor;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Stack<Item> implements Iterable<Item>{
	private Node first;
	private int N;
	private int pushnum=0;
	private int popnum=0;
	private class Node{
		Item item;
		Node next;
	}
	public boolean isEmpty(){
		return N==0;
	}
	public int size(){
		return N;
	}
	public void push(Item item){
		Node old=first;
		first=new Node();
		first.item=item;
		first.next=old;
		N++;
		pushnum++;
	}
	public Item pop(){
		if(isEmpty())
			throw new NoSuchElementException("stack underflow");
		Item item=first.item;
		first=first.next;
		N--;
		popnum++;
		return item;
	}
	public Iterator<Item> iterator(){
		return new ArrayIterator();
	}
	private class ArrayIterator implements Iterator<Item>{
		private Node current=first;
		private int punum=pushnum;
		private int ponum=popnum;
		public boolean hasNext(){
			//快速失败机制（一旦用例在迭代器中修改集合数据就抛出异常）
			if(punum!=pushnum||ponum!=popnum)
				throw new ConcurrentModificationException();
			return current!=null;
		}
		public Item next(){
			if(punum!=pushnum||ponum!=popnum)
				throw new ConcurrentModificationException();
			if(!hasNext())
				throw new NoSuchElementException();
			Item item=current.item;
			current=current.next;
			return item;				
		}
	}
	public static void main(String[] args) {
		Stack<String> s=new Stack<String>();
		s.push("名");
		s.push("君");
		s.push("叶");
		s.push("三");
		Iterator<String> it=s.iterator();
		while(it.hasNext()){
			s.pop();
			System.out.println(it.next());
		}
		for(String str:s){
			System.out.println(str);
		}
		System.out.println(s.pop());
		System.out.println(s.pop());
		System.out.println(s.pop());
		System.out.println(s.size());
	}

}
