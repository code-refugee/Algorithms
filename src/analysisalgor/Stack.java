package analysisalgor;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Stack<Item> implements Iterable<Item>{
	private Node first;
	private int N;
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
	}
	public Item pop(){
		if(isEmpty())
			throw new NoSuchElementException("stack underflow");
		Item item=first.item;
		first=first.next;
		N--;
		return item;
	}
	public Iterator<Item> iterator(){
		return new ArrayIterator();
	}
	private class ArrayIterator implements Iterator<Item>{
		private Node current=first;
		public boolean hasNext(){
			return current!=null;
		}
		public Item next(){
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
		for(String str:s)
			System.out.println(str);
		System.out.println(s.pop());
		System.out.println(s.pop());
		System.out.println(s.pop());
		System.out.println(s.size());
	}

}
