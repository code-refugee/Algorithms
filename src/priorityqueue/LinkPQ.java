package priorityqueue;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

import basicsrecover.Card;

/**
 * 使用链表实现优先队列
 * 这是用链表结构代替了数组
 * 每个节点都有2个向下和1个向上的链接
 * 使用链表实现无需知道队列的大小
 * **/
public class LinkPQ<Key> implements Iterable<Key>{
	private Node root;//根节点
	private Dequeue<Node> queue;//双向队列
	private int n;//代表元素个数
	private Comparator<Key> comparator;//自定义排序
	
	private class Node{
		Key key;//节点内容
		Node parent;//父亲节点
		Node lchild;//左孩子
		Node rchild;//右孩子
	}
	
	public LinkPQ() {
		queue=new Dequeue<>();
		n=0;
	}
	
	public LinkPQ(Comparator<Key> comparator){
		this.comparator=comparator;
		queue=new Dequeue<>();
		n=0;
	}
	
	public boolean isEmpty(){
		return n==0;
	}
	
	public int size(){
		return n;
	}
	
	public Key max(){
		if(isEmpty())
			throw new NoSuchElementException("priority queue is underflow");
		return root.key;
	}
	
	public void insert(Key key){
		if(isEmpty()){
			root=new Node();
			queue.addAtLast(root);
			root.key=key;
			n++;
			return;
		}
		Node newnode=new Node();
		newnode.key=key;
		Node father=queue.peek();
		if(father.lchild==null){
			father.lchild=newnode;
			newnode.parent=father;
			queue.addAtLast(newnode);
			n++;
			swim(newnode);
			assert isMaxHead();
			return;
		}
		if(father.rchild==null){
			father.rchild=newnode;
			newnode.parent=father;
			queue.addAtLast(newnode);
			queue.removeFromFirst();//移除父节点
			n++;
			swim(newnode);
			assert isMaxHead();
			return;
		}
	}
	
	public Key delMax(){
		if(isEmpty())
			throw new NoSuchElementException("priority queue underflow");
		Key temp;
		
		if(size()==1){
			temp=root.key;
			queue.removeFromLast();
			root=null;
			n--;
			return temp;
		}
		
		temp=root.key;
		Node last=queue.removeFromLast();
		if(last.parent.rchild==last){
			queue.addAtFirst(last.parent);//如果是右孩子则将父节点恢复到队列中
		}
		exch(root,last);
		if(last.parent.lchild==last)
			last.parent.lchild=null;//防止对象游离
		else
			last.parent.rchild=null;
		n--;
		sink(root);
		assert isMaxHead();
		return temp;
	}
	
	private void swim(Node node){
		while(node.parent!=null&&less(node.parent,node)){
			exch(node.parent,node);
			node=node.parent;
		}
	}
	
	private void sink(Node node){
		while(node.lchild!=null){
			Node temp=node.lchild;
			if(node.rchild!=null&&less(temp,node.rchild)){
				temp=node.rchild;
			}
			if(!less(node,temp))
				break;
			exch(node,temp);
			node=temp;
		}
	}
	
	private boolean less(Node v,Node w){
		if(comparator==null)
			return ((Comparable<Key>)v.key).compareTo(w.key)<0;
		else
			return comparator.compare(v.key, w.key)<0;
	}
	
	private void exch(Node v,Node w){
		Key t=v.key;
		v.key=w.key;
		w.key=t;
	}
	
	private boolean isMaxHead(){
		Node temp=root;
		return isMaxHead(temp);
	}
	
	private boolean isMaxHead(Node node){
		if(node==null)
			return true;
		Node left=node.lchild;
		Node right=node.rchild;
		if(left!=null&&less(node,left)) return false;
		if(right!=null&&less(node,right)) return false;
		return isMaxHead(left)&&isMaxHead(right);
		
	}
	
	public Iterator<Key> iterator(){
		return new LinkIterator();
	}
	
	private class LinkIterator implements Iterator<Key>{
		private LinkPQ<Key> lp;
		private Dequeue<Node> dq;//使用了双向队列
		private Node temp;
		public LinkIterator(){
			if(comparator!=null)
				lp=new LinkPQ<>(comparator);
			else
				lp=new LinkPQ<>();
			dq=new Dequeue<>();
			temp=root;
			dq.addAtLast(temp);
			while(!dq.isEmpty()){
				temp=dq.removeFromFirst();
				lp.insert(temp.key);
				if(temp.lchild!=null){
					dq.addAtLast(temp.lchild);
				}
				if(temp.rchild!=null){
					dq.addAtLast(temp.rchild);
				}
			}
		}
		public boolean hasNext(){
			return !lp.isEmpty();
		}
		public Key next(){
			if(!hasNext())
				throw new NoSuchElementException();
			return lp.delMax();
		}
		
	}
	public static void main(String[] args) {
		LinkPQ<Card> lq=new LinkPQ<>();
		Card[] cd=new Card[100];
		for(int i=0;i<100;i++){
			cd[i]=new Card();
			lq.insert(cd[i]);
		}
		for(Card c:lq){
			System.out.println(c);
		}
	}

}
