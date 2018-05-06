package bagstatckqueue;

import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.*;
/*实现随机背包,这意味着迭代应该随机访问背包中的所有元素
 *(对于每次迭代，所有的 N! 种排列出现的可能性均相同)。*/
public class RandomBag<Item> implements Iterable<Item>{
	private Item[] a=(Item[])new Object[1];
	private int N=0;
	public void resize(int n){
		Item[] temp=(Item[])new Object[n];
		for(int i=0;i<N;i++){
			temp[i]=a[i];
		}
		a=temp;
	}
	public boolean isEmpty(){
		return N==0;
	}
	public int size(){
		return N;
	}
	public void add(Item item){
		a[N++]=item;
		if(N==a.length)
			resize(2*a.length);
	}
	
	//迭代
	public Iterator<Item> iterator(){
		return new ArrayIterator();
	}
	private class ArrayIterator implements Iterator<Item>{ 
		private int[] seq=new int[N];
		private int index=0;
		public ArrayIterator(){
			for(int i=0;i<N;i++){
				seq[i]=i;
			}
			StdRandom.shuffle(seq);
		}
		public boolean hasNext(){
			return index<N;
		}
		public Item next(){
			if(!hasNext())
				throw new NoSuchElementException("bag underflow");
			return a[seq[index++]];
		}
		public void remove(){
			throw new UnsupportedOperationException();
		} 
	}
	public static void main(String[] args) {
		RandomBag<String> rb=new RandomBag<>();
		rb.add("我的");
		rb.add("名字");
		rb.add("叫");
		rb.add(":");
		rb.add("三叶");
		rb.add("泷");
		for(String str:rb){
			System.out.println(str);
		}
	}

}
