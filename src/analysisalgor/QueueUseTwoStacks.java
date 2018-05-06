package analysisalgor;

import java.util.Iterator;
import java.util.NoSuchElementException;

/*两个栈实现的队列。用两个栈实现一个队列，使得每个队列操作所需要的堆栈操作均摊后为一个常数。
 * 提示：如果将所有元素压入栈再弹出，它们的顺序就被颠倒了。如果再次重复这个过程，它们的顺序则会复原。*/
public class QueueUseTwoStacks<Item> implements Iterable<Item>{
	private Stack<Item> st1;
	private Stack<Item> st2;
	public QueueUseTwoStacks(){
		st1=new Stack<Item>();
		st2=new Stack<Item>();
	}
	public boolean isEmpty(){
		return st1.isEmpty();
	}
	public int size(){
		return st1.size();
	}
	public void enqueue(Item item){
		st1.push(item);
	}
	public Item dequeue(){
		//方法一：太慢
//		if(st1.size()<1)
//			throw new NoSuchElementException("queue underflow");
//		while(st1.size()>1)
//			st2.push(st1.pop());
//		Item temp=st1.pop();
//		while(st2.size()>0)
//			st1.push(st2.pop());
//		return temp;
		//方法二
		if(st1.size()<1&&st2.size()<1)
			throw new NoSuchElementException();
		if(st2.size()>0)
			return st2.pop();
		else{
			while(st1.size()>0)
				st2.push(st1.pop());
			return st2.pop();
		}
	}
	public Iterator<Item> iterator(){
		return new QueueList();
	}
	private class QueueList implements Iterator<Item>{

		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public Item next() {
			// TODO Auto-generated method stub
			return null;
		}
		
	}
	public static void main(String[] args) {
		QueueUseTwoStacks<String> qe=new QueueUseTwoStacks<>();
		qe.enqueue("你的");
		qe.enqueue("名字");
		qe.enqueue("：");
		qe.enqueue("三叶");
		qe.enqueue("泷");
		System.out.println(qe.dequeue());
		System.out.println(qe.dequeue());
		System.out.println(qe.dequeue());
		System.out.println(qe.dequeue());
		qe.enqueue("三叶");
		System.out.println(qe.dequeue());
		System.out.println(qe.dequeue());
	}

}
