package bagstatckqueue;
/*链表题专用程序*/
import java.util.Iterator;
import java.util.NoSuchElementException;

/*删除链表的*/
public class LinkListExecisel<Item> implements Iterable<Item>{
	private Node first;
	private int N;
	private class Node{
		Item item;
		Node next;
	}
	//删除尾节点
	public Item deleteLastNode(){
		Item item;
		if(isEmpty())
			throw new NoSuchElementException("linklist is null");
		else if(first.next==null){
			item=first.item;
			first=null;
			N--;
		}
		else{
//			Node current=first;
			Node move=first;
			while(move.next.next!=null){
				move=move.next;
//				current=current.next;
			}				
			item=move.next.item;
			/*一定要是move.next为空，不能让move为最后一个节点
			 * 然后为空（起别名），具体原因参考浅拷贝
			 */
			move.next=null;
			N--;
		}
		return item;	
	}
	//删除指定位置的节点
	public Item delete(int k){
		Item item;
		if(k>N||k<1)
			throw new NoSuchElementException("Illegal input");
		else if(k==1){
			item=first.item;
			first=first.next;
			N--;
		}
		else{
			int sum=2;
			Node previous = first;
			Node move=first.next;
			while(sum!=k){
				previous=move;
				move=move.next;
				sum++;
			}
			item=(Item)move.item;
			previous.next=move.next;
			move=null;
			N--;
		}
		return item;
	}
	//删除指定节点的后续节点
	public void removeAfter(Node d){
		if(first==null||d==null||d.next==null){
			//什么也不做
		}
		else{
			Node temp=d.next;
			while(temp!=null){
				Node old=temp;
				temp=new  Node();
				temp=old.next;
				old=null;
			}
		}
	}
	//查找指定字符串
	public boolean find(LinkListExecisel<Item> l,Item item){
		for(Item temp:l){
			if(temp.equals(item)){
				return true;
			}	
		}
		return false;
	}
	//删除链表中所有item域为key的节点
	public void remove(LinkListExecisel<Item> l,Item key){
		Iterator<Item> it=l.iterator();
		while(it.hasNext()){
			if(it.next().equals(key)){
				it.remove();
			}
		}
	}
	//添加节点
	public void addNode(Item item){
		Node oldnode=first;
		first=new Node();
		first.item=item;
		first.next=oldnode;
		N++;
	}
	//删除节点
	public Item bringNode(){
		Item temp=first.item;
		first=first.next;
		N--;
		return temp;
	}
	public int size(){
		return N;
	}
	public boolean isEmpty(){
		return first==null;
	}
	//迭代
	public Iterator<Item> iterator(){
		return new ListIterator();
	}
	//实现迭代的方法
	private class ListIterator implements Iterator<Item>{
		private Node current=first;
		private Node lastnode=null;
		private Node secondlastnode=null;
		public boolean hasNext(){
			return current!=null;
		}
		public Item next(){
			Item temp=current.item;
			secondlastnode=lastnode;
			lastnode=current;
			current=current.next;
			return temp;
		}
		public void remove(){
			if(secondlastnode==null){
				first=first.next;
				N--;
			}
				
			else{
				Node oldnode=lastnode;
				lastnode=new Node();
				lastnode=oldnode.next;
				secondlastnode.next=lastnode;
				oldnode=null;
				N--;
			}
		}
		
	}
	public static void main(String[] args) {
		LinkListExecisel<String> l=new LinkListExecisel<String>();
		l.addNode("TaoYuHang");
		l.addNode("我是");
		l.addNode("名字");
		l.addNode("君名");
		l.addNode("三叶");
		l.addNode("TaoYuHang");
		String s="TaoYuHang";
		l.remove(l, s);
		for(String str:l){
			System.out.println(str);
		}
		if(l.find(l, s))
			System.out.println("成功");
		else
			System.out.println("失败");
		System.out.println(l.size());
		System.out.println(l.deleteLastNode());
		System.out.println(l.delete(1));
		System.out.println(l.size());
		System.out.println(l.deleteLastNode());
		System.out.println(l.delete(1));
		System.out.println(l.size());
		for(String str:l){
			System.out.println(str);
		}
	}

}
