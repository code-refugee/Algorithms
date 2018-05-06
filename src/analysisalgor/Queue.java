package analysisalgor;

public class Queue<Item> {
	private Node first;
	private Node last;
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
	public void enqueue(Item item){
		Node temp=last;
		last=new Node();
		last.item=item;
		last.next=null;
		if(first==null)
			first=last;
		else
			temp.next=last;
		N++;
	}
	public Item dequeue(){
		Item temp=first.item;
		first=first.next;
		N--;
		return temp;
	}
	public static void main(String[] args) {
		Queue<String> qe=new Queue<>();
		qe.enqueue("你的");
		qe.enqueue("名字");
		qe.enqueue("：");
		qe.enqueue("三叶");
		qe.enqueue("泷");
		System.out.println(qe.dequeue());
		System.out.println(qe.dequeue());
		System.out.println(qe.dequeue());
		System.out.println(qe.dequeue());
	}

}
