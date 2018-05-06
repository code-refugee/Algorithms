package bagstatckqueue;

import java.util.Iterator;
import java.util.NoSuchElementException;

/*用动态数组实现双向队列--与用数组实现队列差不多，略难*/
public class ResizingArrayDeque<Item> implements Iterable<Item>{
	private Item[] a;
	private int head;
	private int tail;
	public ResizingArrayDeque(){
		a=(Item[])new Object[2];
		head=1;
		tail=1;
	}
	//动态调整数组大小，这次调整是变为原数组的三倍
	public void resize(int n){
		if(n<3)
			n=3;
		Item[] temp=(Item[])new Object[n];
		int j=n/3;
		for(int i=head;i<tail;i++){
			temp[j++]=a[i];
		}
		a=temp;
		head=n/3;
		tail=j;
	}
	public boolean isEmpty(){
		return head==tail;
	}
	public int size(){
		return tail-head;
	}
	public void pushLeft(Item item){
		a[--head]=item;
		if(head==0)
			resize(3*size());
	}
	public void pushRight(Item item){
		a[tail++]=item;
		if(tail==a.length)
			resize(3*size());
	}
	public Item popLeft(){
		if(isEmpty())
			throw new RuntimeException("Illegal Operation");
		if(size()==a.length/6)
			resize(size()*3);
		return a[head++];
	}
	public Item popRight(){
		if(isEmpty())
			throw new RuntimeException("Illegal Operation");
		if(size()==a.length/6)
			resize(size()*3);
		return a[--tail];
	}
	//迭代
	public Iterator<Item> iterator(){
		return new ArrayIterator();
	}
	private class ArrayIterator implements Iterator<Item>{
		int i=0;
		public boolean hasNext(){
			return i<size();
		}
		public void remove(){
			throw new UnsupportedOperationException();
		}
		public Item next(){
			if(!hasNext())
				throw new NoSuchElementException();
			Item item=a[head+i];
			i++;
			return item;
		}
	}
	public static void main(String[] args) {
		ResizingArrayDeque<String> re=new ResizingArrayDeque<String>();
		re.pushRight("你的");
		re.pushRight("名字");
		re.pushRight("是");
		re.pushRight("：");
		re.pushRight("我叫泷");
		for(String str:re){
			System.out.println(str);
		}
		System.out.println(re.size());
		System.out.println(re.popLeft());
		System.out.println(re.popLeft());
		System.out.println(re.popLeft());
		System.out.println(re.popLeft());
		System.out.println(re.popLeft());
	}

}
