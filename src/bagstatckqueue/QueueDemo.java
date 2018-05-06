package bagstatckqueue;

import java.util.Iterator;
import java.util.NoSuchElementException;

/*用环形链表实现Queue。环形链表也是一条链表，只是没有任何结点链接为空，
 * 且只要链表非空则last.next的值就为first。只能使用一个Node类型的实例变量（last）。*/
public class QueueDemo<Item> implements Iterable<Item>{
	private Node last;
	private int N;
	private class Node{
		Item item;
		Node next;
	}
	public boolean isEmpty(){
		return last==null;
	}
	public int size(){
		return N;
	}
	//返回最近进队列的值
	public Item peek(){
		if(isEmpty())
			throw new NoSuchElementException("queue is upflow");
		return last.item;
	}
	//进队列
	public void enqueue(Item item){
		Node x = new Node();
        x.item = item;
        if (isEmpty())
            x.next = x;
        else
        {
            x.next = last.next;
            last.next = x;
        }
        last = x;
        N++;
	}
	//出队列
	public Item dequeue(){
		if(isEmpty())
			throw new NoSuchElementException("Illegal operation");
		Item item=last.next.item;
		if(last.next==last)
			last=null;
		else
			last.next=last.next.next;
		N--;
		return item;
	}

	public Iterator<Item> iterator() {
		return new ListIterator();
	}
	
	private class ListIterator implements Iterator<Item>{
		private Node temp=last;
		private int n=N;
		public boolean hasNext() {
			return n>0;
		}

		public Item next() {
			if (!hasNext()) throw new NoSuchElementException();
			Item item=temp.next.item;
			temp=temp.next;
			n--;
			return item;
		}
		
		public void remove(){
			
		}
	}
	public static void main(String[] args) {
		QueueDemo<String> q=new QueueDemo<String>();
		q.enqueue("你的");
		q.enqueue("名字");
		q.enqueue("三叶");
		q.enqueue("泷");
		System.out.println(q.peek()+q.size());
		for(String s:q){
			System.out.println(s);
		}
		System.out.println(q.dequeue());
		q.dequeue();
		System.out.println(q.peek()+q.size());
		for(String s:q){
			System.out.println(s);
		}
		q.dequeue();
		q.dequeue();
		System.out.println(q.size());
		for(String s:q){
			System.out.println(s);
		}
	}

}
