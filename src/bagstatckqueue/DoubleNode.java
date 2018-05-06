package bagstatckqueue;

import java.util.NoSuchElementException;

/*实现一个嵌套类DoubleNode用来构造双向链表，其中每个结点都含有一个指向前驱元素的引用和
 * 一个指向后续元素的引用（如果不存在则为null）。为以下任务实现若干静态方法：
 * 在头插入结点、在表尾插入结点、从表头删除结点、从表尾删除结点、在指定结点前插入新结点、
 * 在指定结点之后插入新结点、删除指定结点。
 */
public class DoubleNode<Item> {
	private Node first;
	private Node last;
	private int N;
	private class Node<Item>{
		Item item;
		Node pre;//指向前驱元素
		Node next;//指向后继元素
	}
	public boolean isEmpty(){
		return N==0;
	}
	public int size(){
		return N;
	}
	public void addAtHead(Item item){
		Node old=first;
		first=new Node();
		first.item=item;
		first.pre=null;
		first.next=null;
		if(isEmpty())
			last=first;
		else{
			first.next=old;
			old.pre=first;
		}
		N++;
	}
	public void addAtTail(Item item){
		Node old=last;
		last=new Node();
		last.item=item;
		last.pre=null;
		last.next=null;
		if(isEmpty())
			first=last;
		else{
			last.pre=old;
			old.next=last;
		}
		N++;
	}
	public Item deleteAtHead(){
		if(isEmpty())
			throw new NoSuchElementException();
		Item item=(Item) first.item;
		first=first.next;
		first.pre=null;
		N--;
		return item;
	}
	public Item deleteAtTail(){
		if(isEmpty())
			throw new NoSuchElementException();
		Item item=(Item) last.item;
		last=last.pre;
		last.next=null;
		N--;
		return item;
	}
	public void insertBefor(Node d,Item item){
		if(isEmpty())
			throw new NoSuchElementException();
		
	}

}
