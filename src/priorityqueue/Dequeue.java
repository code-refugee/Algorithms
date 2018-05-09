package priorityqueue;

import java.util.NoSuchElementException;

public class Dequeue<Key> {
	private Node first;
	private Node last;
	private int N;
	private class Node{
		Key key;
		Node pre;
		Node next;
	}
	public Dequeue() {
		N=0;
	}
	public boolean isEmpty(){
		return N==0;
	}
	public int size(){
		return N;
	}
	public void addAtFirst(Key key){
		Node oldfirst=first;
		first=new Node();
		first.key=key;
		first.next=null;
		first.pre=null;
		if(last==null)
			last=first;
		else{
			first.next=oldfirst;
			oldfirst.pre=first;
		}
		N++;
	}
	public void addAtLast(Key key){
		Node oldlast=last;
		last=new Node();
		last.key=key;
		last.next=null;
		last.pre=null;
		if(first==null)
			first=last;
		else{
			last.pre=oldlast;
			oldlast.next=last;
		}
		N++;
	}
	public Key removeFromFirst(){
		if(isEmpty())
			throw new NoSuchElementException();
		Key temp=first.key;
		first=first.next;
		N--;
		return temp;
	}
	public Key removeFromLast(){
		if(isEmpty())
			throw new NoSuchElementException();
		Key temp=last.key;
		last=last.pre;
		N--;
		return temp;
	}
	public Key peek(){
		if(isEmpty())
			throw new NoSuchElementException();
		Key temp=first.key;
		return temp;
	}

}
