package bagstatckqueue;

import java.util.NoSuchElementException;

/*前移编码。从标准输入读取一串字符，使用链表保存这些字符并删除重复字符。
 * 当你读取了一个从未见过的字符时，将它插入表头。当你读取了一个重复的字符时，
 * 将它从链表中删去并再次插入表头。将你的程序命名为 MoveToFront：它实现了著名的前移编码策略，
 * 这种策略假设最近访问过的元素很可能会再次访问，因此可以用于缓存、数据压缩等许多场景。
 * (这段代码还可以改进，不然数据大的话容易变慢可以参考百度搜索的答案)
 */
public class MoveToFront {
	private Node first;
	private Node last;
	private int N;
	private class Node{
		char c;
		Node next;
	}
	public boolean isEmpty(){
		return N==0;
	}
	public int size(){
		return N;
	}
	public void addCharacter(MoveToFront m,char x){
		int n=m.N;
		int k=n;
		if(n==0){
			m.enQueue(x);
		}
		else{
			for(int i=0;i<n;i++){
				char temp=m.deQueue();
				if(temp==x){
//					Node old=first;
//					first=new Node();
//					first.c=x;
//					first.next=old;
//					break;
					k--;
				}
				else{
					m.enQueue(temp);
				}
			}
			if(k!=n){
				Node old=first;
				first=new Node();
				first.c=x;
				first.next=old;
				N++;
			}
			else{
				m.enQueue(x);
			}
		}
		
	}
	private void enQueue(char x){
		Node old=last;
		last=new Node();
		last.c=x;
		last.next=null;
		if(isEmpty())
			first=last;
		else{
			old.next=last;
		}
		N++;
	}
	public char deQueue(){
		if(isEmpty())
			throw new NoSuchElementException();
		char temp;
		temp=first.c;
		first=first.next;
		N--;
		return temp;
	}
	public String toString(){
		String str="";
		if(isEmpty()){
			return str;
		}
		Node current=first;
		str=str+current.c;
		while(current.next!=null){
			str=str+current.next.c;
			current=current.next;
		}
		return str;
	}
	public static void main(String[] args) {
		MoveToFront m=new MoveToFront();
		m.addCharacter(m, 'a');
//		m.addCharacter(m, 'b');
//		m.addCharacter(m, 'c');
//		m.addCharacter(m, 'd');
//		m.enQueue('a');
//		m.enQueue('b');
//		m.enQueue('c');
//		m.enQueue('d');	
		System.out.println(m);
		m.addCharacter(m, 'a');
		System.out.println(m.size());
		System.out.println(m);
		m.addCharacter(m, 'e');
		System.out.println(m);
		m.addCharacter(m, 'a');
		System.out.println(m);
		m.addCharacter(m, 'd');
		System.out.println(m);
		m.addCharacter(m, 'c');
		System.out.println(m);
		m.addCharacter(m, 'c');
		System.out.println(m);
//		System.out.println(m.deQueue());
	}

}
