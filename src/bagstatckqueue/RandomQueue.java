package bagstatckqueue;
import java.util.NoSuchElementException;
import java.util.*;
/*编写一个随机队列，实现RandomQueue<Card>在桥牌中发牌（每人13张）*/
import edu.princeton.cs.algs4.*;
public class RandomQueue<Item> implements Iterable<Item>{
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
	public void enqueue(Item item){
		a[N++]=item;
		if(N==a.length)
			resize(2*a.length);
	}
	public Item dequeue(){
		if(isEmpty())
			throw new NoSuchElementException("queue underflow");
		int i=StdRandom.uniform(N);
		Item temp;
		temp=a[i];
		a[i]=a[N-1];
		a[N-1]=null;
		N--;
		if(N>0&&N==a.length/4)
			resize(a.length/2);
		return temp;		
	}
	public Item sample(){
		if(isEmpty())
			throw new NoSuchElementException("queue underflow");
		int i=StdRandom.uniform(N);
		return a[i];
	}
	public Iterator<Item> iterator(){
		return new RandomQueueIterator();
	}
	private class RandomQueueIterator implements Iterator<Item>{
		private int[] seq=new int[N];
		private int index=0;
		public RandomQueueIterator(){
			for(int i=0;i<N;i++)
				seq[i]=i;
			StdRandom.shuffle(seq);
		}

		public boolean hasNext() {
			return index<N;
		}

		public Item next() {
			if(!hasNext())
				throw new NoSuchElementException("queue underflow");
			return a[seq[index++]];
		}
		public void remove(){
			throw new UnsupportedOperationException();
		}
		
	}
	public static void main(String[] args) {
		RandomQueue<Card> ra=new RandomQueue<Card>();
		for(int i=0;i<13;i++)
			ra.enqueue(new Card());
		for(Card c:ra){
			System.out.println(c);
		}
		System.out.println("--------------------------------");
		for(int i=0;i<13;i++){
			System.out.println(ra.dequeue());
		}
		System.out.println(ra.size());
	}
	

}
