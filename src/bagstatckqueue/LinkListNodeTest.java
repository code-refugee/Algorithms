package bagstatckqueue;
/*链表节点专用程序*/
import edu.princeton.cs.algs4.*;
public class LinkListNodeTest {
	private static class Node<Item>{
		Item item;
		Node next;
	}
	//移除Node节点之后的节点
	public void removeAfter(Node node){
		if(node==null||node.next==null){
			
		}
		else{
			while(node.next!=null){
				node.next=node.next.next;
			}
		}
	}
	//将第二个节点插入链表使之成为第一个节点的后续节点
	@SuppressWarnings("unchecked")
	public void insertAfter(Node exists,Node newafter){
		if(exists==null||newafter==null){
			//什么也不做
		}
		else{
			if(exists.next==null){
				exists.next=newafter;
				newafter.next=null;
			}
			else{
				newafter.next=exists.next;
				exists.next=newafter;
			}
		}
	}
	//返回链表中最大的节点的值。用递归方法（假设所有的键均为正整数）
	public Integer max(Node first){
		if(first==null){
			return 0;
		}
		if(first.next==null)
			return (Integer)first.item;
		else{
			if((Integer)first.item>(Integer)first.next.item){
				int temp;
				temp=(Integer)first.item;
				first.item=(Integer)first.next.item;
				first.next.item=temp;
			}
			first=first.next;
			return max(first);
		}
		
//		if(first==null){}
//		if(first.next==null)
//			return (Integer)first.item;
//		else{
//			while(first.next!=null){
//				if((Integer)first.item>(Integer)first.next.item){
//					int temp;
//					temp=(Integer)first.item;
//					first.item=(Integer)first.next.item;
//					first.next.item=temp;
//				}
//				first=first.next;
//			}
//		}
//		return (Integer)first.item;
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void main(String[] args) {
		Node first=new Node();
		Node second=new Node();
		Node third=new Node();
		Node forth=new 	Node();
		Node fifth=new Node();
//		first=null;
		first.item=2;
		first.next=second;
		second.item=1;
		second.next=third;
		third.item=1;
		third.next=forth;
		forth.item=1;
		forth.next=fifth;
		fifth.item=1;
		fifth.next=null;
		LinkListNodeTest l=new LinkListNodeTest();
		System.out.println(l.max(first));
//		l.insertAfter(second, fifth);
//		System.out.println(third.next.item);
//		System.out.println(third.next.next.item);
//		Node test=third;
//		l.removeAfter(test);
//		System.out.println(first.item);
//		System.out.println(first.next.item);
//		System.out.println(first.next.next.item);
	}
}
