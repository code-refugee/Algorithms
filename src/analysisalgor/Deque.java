package analysisalgor;

import java.util.NoSuchElementException;

/*双向循环队列*/
public class Deque<Item> {
	private Node first;
	private Node last;
	private Node current;
	private int N;
	private class Node{
		Item item;
		Node pre;//指向前驱
		Node next;//指向后继
	}
	public boolean isEmpty(){
		return N==0;
	}
	public int size(){
		return N;
	}
	public void enQuene(Item item){
		Node oldnode=last;
		last=new Node();
		last.item=item;
		last.pre=last;
		last.next=last;
		if(first==null){
			first=last;
			current=first;
		}
		else{
			last.pre=oldnode;
			oldnode.next=last;
			last.next=first;
			first.pre=last;
		}
		N++;
	}
	public Item moveRight(){
		if(isEmpty())
			throw new NoSuchElementException("Deque is underflow");
		Item temp=current.next.item;
		current=current.next;
		return temp;
	}
	public Item moveLeft(){
		if(isEmpty())
			throw new NoSuchElementException("Deque is underflow");
		Item temp=current.pre.item;
		current=current.pre;
		return temp;
	}
	public static void main(String[] args) {
		Deque<Integer> dq=new Deque<>();
		dq.enQuene(0);
		dq.enQuene(1);
		dq.enQuene(2);
		dq.enQuene(3);
		System.out.println(dq.moveRight());
		System.out.println(dq.moveRight());
		System.out.println(dq.moveRight());
		System.out.println(dq.moveLeft());
	}
	

}
