package bagstatckqueue;

import java.util.NoSuchElementException;

/*删除第 k 个元素。用链表实现该数据类型*/
public class GeneralizedQueue2<Item> {
	private Node first;
	private Node last;
	private int N;
	public GeneralizedQueue2(){
		first=null;
		last=null;
		N=0;
	}
	private class Node{
		Item item;
		Node pre;//指向前驱
		Node next;//指向后面
	}
	public boolean isEmpty(){
		return N==0;
	}
	public int size(){
		return N;
	}
	public void insert(Item x){
		Node old=last;
		last=new Node();
		last.item=x;
		last.pre=null;
		last.next=null;
		if(isEmpty())
			first=last;
		else{
			old.next=last;
			last.pre=old;
		}
		N++;
	}
	public Item delete(int k){
		if(isEmpty()||k<0||k>N-1)
			throw new NoSuchElementException();
		Item temp;
		Node current=first;
		if(k==0){
			temp=first.item;
			first=first.next;
			first.pre=null;
		}
		if(k==N-1){
			temp=last.item;
			last=last.pre;
			last.next=null;
		}
		else{
			for(int i=0;i<k;i++){
				current=current.next;
			}
			temp=current.item;
			current.pre.next=current.next;
			current.next.pre=current.pre;
		}
		N--;
		return temp;
	}
	public static void main(String[] args) {
		GeneralizedQueue2<String> ge=new GeneralizedQueue2<>();
		ge.insert("我的");
		ge.insert("名字");
		ge.insert("叫");
		ge.insert("：");
		ge.insert("三叶");
		ge.insert("泷");
		System.out.println(ge.size());
		System.out.println(ge.delete(3));
		System.out.println(ge.delete(4));
		System.out.println(ge.size());
	}

}
