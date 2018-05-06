package bagstatckqueue;

import java.util.Iterator;

/*一个以栈为目标的队列（或称steque），是一种支持push、pop和enqueue操作的数据类型。
 * 为这种抽象数据类型定义一份API并给出一份基于链表的实现。*/
public class Steque<Item> implements Iterable<Item>{
	private Node first;
	private Node last;
	private int N;
	private class Node{
		Item item;
		Node next;
	}
	public int size(){
		return N;
	}
	public boolean isEmpty(){
		return first==null;
	}
	public void push(Item item){
		Node x=new Node();
		x.item=item;
		if(isEmpty()){
			first=x;
			last=x;
			N++;
		}
		else{
			Node old=first;
			first=x;
			first.next=old;
			N++;
		}
	}
	public void enqueue(Item item){
		Node old=last;
		last=new Node();
		last.item=item;
		last.next=null;
		if(isEmpty())
			first=last;
		else
			old.next=last;
		N++;
	}
	public Item pop(){
		Item item;
		if(isEmpty())
			throw new RuntimeException("Illegal operation");
		item=first.item;
		first=first.next;
		N--;
		return item;
	}
	public Iterator<Item> iterator() {
		return new ListIterator();
	}
	private class ListIterator implements Iterator<Item>{
		private Node current=first;
		public boolean hasNext() {
			return current!=null;
		}

		public Item next() {
			Item item=current.item;
			current=current.next;
			return item;
		}
		public void remove(){}
	}
	public static void main(String[] args) {
		Steque<String> stringSteque1 = new Steque<String>();
        stringSteque1.enqueue("我");
        stringSteque1.enqueue("的");
        stringSteque1.enqueue("名字");
        stringSteque1.enqueue("泷");
        stringSteque1.enqueue("微博:https://m.weibo.cn/p/1005056186766482");
        stringSteque1.push("你好");
        for (String string : stringSteque1) {
            System.out.println(string);
        }
        System.out.println(stringSteque1.size());
        Steque<String> stringSteque2 = new Steque<String>();
        stringSteque2.push("我");
        stringSteque2.push("的");
        stringSteque2.push("名字");
        stringSteque2.push("三叶");
        stringSteque2.push("微博:https://m.weibo.cn/p/1005056186766482");        
        for (String string : stringSteque2) {
            System.out.println(string);
        }


	}

}
