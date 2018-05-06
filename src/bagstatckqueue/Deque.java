package bagstatckqueue;
/*Deque。一个双向队列(或者称为deque)和栈或队列类似，但它同时支持在两端添加或删除元素*/
public class Deque<Item> {
	private Node first;
	private Node last;
	private int N;
	private class Node{
		Item item;
		Node pre;//指向前一个节点
		Node next;//指向后一个节点
	}
	public Deque(){
		first=null;
		last=null;
		N=0;
	}
	public boolean isEmpty(){
		return N==0;
	}
	public int size(){
		return N;
	}
	public void pushLeft(Item item){
		Node old=first;
		first=new Node();
		first.item=item;
		first.next=null;
		first.pre=null;
		if(N==0)
			last=first;
		else{
			first.next=old;
			old.pre=first;
		}
		N++;	
	}
	public void pushRight(Item item){
		Node old=last;
		last=new Node();
		last.item=item;
		last.next=null;
		last.pre=null;
		if(N==0)
			first=last;
		else{
			old.next=last;
			last.pre=old;
		}
		N++;
	}
	public Item popLeft(){
		if(isEmpty())
			throw new RuntimeException("Illegal operation");
		Item item=first.item;
		first=first.next;
		N--;
		return item;
	}
	public Item popRight(){
		if(isEmpty())
			throw new RuntimeException("Illegal operation");
		Item item=last.item;
		last=last.pre;
		N--;
		return item;
	}
	public static void main(String[] args) {
		Deque<String> d=new Deque<String>();
//		d.pushRight("我的");
//		d.pushRight("名字");
//		d.pushRight("叫");
//		d.pushRight("三叶");
		System.out.println(d.size());
		d.pushLeft("我的");
		d.pushLeft("名字");
		d.pushLeft("叫");
		d.pushLeft("泷");
//		System.out.println(d.popLeft());
//		System.out.println(d.popLeft());
//		System.out.println(d.popLeft());
//		System.out.println(d.size());
		System.out.println(d.popRight());
		System.out.println(d.popRight());
		System.out.println(d.popRight());
		System.out.println(d.size());
		
	}

}
