package bagstatckqueue;

import java.util.NoSuchElementException;

/*复制队列。编写一个新的构造函数，使以下代码：
Queue r = new Queue(q);
得到的 r 指向队列 q 的一个新的独立的副本。可以对 q 或 r 进行任意入列或出列操作但它们不会相互影响。*/
public class Queue<Item> {
	private Node first;
	private Node last;
	private int N;
	public Queue(){
		
	}
	public Queue(Queue<Item> q){
		int m=q.size();
		for(int i=0;i<m;i++){
			Item temp=q.dequeue();
			this.enququ(temp);
			q.enququ(temp);
		}
	}
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
	public void enququ(Item item){
		Node old=last;
		last=new Node();
		last.item=item;
		last.next=null;
		if(isEmpty()){
			first=last;
		}
		else
			old.next=last;
		N++;
	}
	public Item dequeue(){
		if(isEmpty())
			throw new NoSuchElementException();
		Item temp=first.item;
		first=first.next;
		N--;
		return temp;
	}
	public String toString(){
		String str="";
		if(isEmpty())
			return str;
		Node current=first;
		str=str+current.item;
		while(current.next!=null){
			str=str+current.next.item;
			current=current.next;
		}
		return str;
	}
	public static void main(String[] args) {
		Queue<String> q=new Queue<String>();
		q.enququ("我的");
		q.enququ("名字");
		q.enququ("叫");
		q.enququ("：");
		q.enququ("三叶");
		System.out.println(q);
		Queue<String> t=new Queue<String>(q);
		System.out.println(t);
		t.enququ("泷");
		q.dequeue();
		System.out.println(q);
		System.out.println(t);
		
	}

}
