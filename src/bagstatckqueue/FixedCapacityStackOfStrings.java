package bagstatckqueue;
import java.util.NoSuchElementException;

/*为FixedCapacityStackOfStrings添加一个方法isFull(). 
 * 为Stack添加一个方法peek(),返回栈中最近添加的元素(而不弹出它).*/
import edu.princeton.cs.algs4.*;
public class FixedCapacityStackOfStrings<Item> {
	private Item[] a;
	private int N;
	public FixedCapacityStackOfStrings(int cap){
		a=(Item[])new Object[cap];
	}
	public void push(Item item){
		a[N++]=item;
	}
	public boolean isEmpty(){
		return N==0;
	}
	public Item peek(){
		if(isEmpty())
			throw new NoSuchElementException("Stack is empty");
		return a[--N];
	}
	public Item pop(){
		Item item=a[--N];
		a[N]=null;
		System.out.println(a.length);
		return item;
	}
	public int size(){
		return N;
	}
	public boolean isFull(){
		return N==a.length;
	}
	public static void main(String[] args) {
		FixedCapacityStackOfStrings<String> fix=new FixedCapacityStackOfStrings<String>(3);
		while(!fix.isFull())
			fix.push("first");
		while(!fix.isEmpty())
			StdOut.println(fix.pop());
	}

}
