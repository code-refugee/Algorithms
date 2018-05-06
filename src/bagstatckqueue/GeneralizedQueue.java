package bagstatckqueue;

import java.util.NoSuchElementException;

/*删除第 k 个元素,用数组实现该数据类型。*/
public class GeneralizedQueue<Item> {
	private Item[] a;
	private int N;
	public GeneralizedQueue(){
		a=(Item[])new Object[1];
		N=0;
	}
	private void resize(int n){
		Item[] temp=(Item[])new Object[n];
		for(int i=0;i<N;i++)
			temp[i]=a[i];
		a=temp;
	}
	public boolean isEmpty(){
		return N==0;
	}
	public int size(){
		return N;
	}
	public void insert(Item x){
		if(N==a.length)
			resize(2*a.length);
		a[N++]=x;
	}
	public Item delete(int k){
		if(isEmpty()||k>N-1||k<0)
			throw new NoSuchElementException("queue underflow");
		if(N>0&&N==a.length/4)
			resize(a.length/2);
		Item item=a[k];
		for(int i=k+1;i<N;i++)
			a[i-1]=a[i];
		a[--N]=null;
		return item;
	}
	public static void main(String[] args) {
		GeneralizedQueue<String> ge=new GeneralizedQueue<String>();
		ge.insert("我的");
		ge.insert("名字");
		ge.insert("叫");
		ge.insert("：");
		ge.insert("三叶");
		ge.insert("泷");
		System.out.println(ge.size());
		System.out.println(ge.delete(4));
		System.out.println(ge.delete(4));
		System.out.println(ge.size());
	}

}
