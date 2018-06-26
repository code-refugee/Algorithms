package brinary_search_tree;


import java.util.Scanner;

import bagstatckqueue.QueueDemo;

/*用二叉查找树实现的符号表（二叉查找树的性质：每个节点的键都大于其
 * 左子树中的任意节点的键而小于右子树的任意节点的键）。
 * 
 * 性能分析：
 * 在最坏情况下（即每个节点都只有左子树或只有右子树）查找的增长
 * 数量级为N，插入为N；平均情况下查找为1.39lgN，插入为1.39lgN。
 * 
 * 什么时候会出现最坏情况：用例将所有键按照顺序或者逆序插入符号表。*/
public class BST<Key extends Comparable<Key>,Value> {
	
	//根节点
	private Node root;
	
	//定义节点内部类
	private class Node{
		Key key; //键
		Value val; //值
		Node left,right; //指向子树的链接
		int N; //以该节点为根的子树中的节点总数
		public Node(Key key,Value val,int N){
			this.key=key;
			this.val=val;
			this.N=N;
		}
	}
	
	//表是否为空
	public boolean isEmpty(){
		return root==null;
	}
	
	//键值对的数量
	public int size(){
		return size(root);
	}
	
	/*重载size()方法*/
	private int size(Node node){
		if(node==null) return 0;
		else return node.N;
	}
	
	//返回[lo..hi]之间键的数量
	public int size(Key lo,Key hi){
		return size(root,lo,hi);
	}
	
	/*这里的做法与下面keys()方法类似*/
	private int size(Node node,Key lo,Key hi){
		if(node==null)
			return 0;
		int sum=0;
		int cmplo=lo.compareTo(node.key);
		int cmphi=hi.compareTo(node.key);
		if(cmplo<0)
			sum+=size(node.left,lo,hi);
		if(cmplo<=0&&cmphi>=0)
			sum+=1;
		if(cmphi>0)
			sum+=size(node.right,lo,hi);
		return sum;
	}
	
	//计算树的高度
	public int height(){
		return height(root);
	}
	private int height(Node node){
		if(node==null)
			return -1;
		
		//返回最长路径（将左子树的路径和右子树的路径相比较，返回长的那个）
		return 1+Math.max(height(node.left), height(node.right));
	}
	
	//键key是否存在于符号表中
	public boolean contains(Key key){
		return get(key)!=null;
	}
	
	//获取键key对应的值（若键key不存在则返回空）
	public Value get(Key key){
		if(key==null)
			throw new IllegalArgumentException("argument to get() is null");
		
		return get(root,key);
	}
	
	/*将key与节点的键进行比较，（由二叉查找树的性质得）若key比较小
	 * 则在该节点的左子树进行查找，若key比较大则在右子树进行查找*/
	private Value get(Node node,Key key){
		if(node==null)
			return null;
		int cmp=key.compareTo(node.key);
		if(cmp<0)
			return get(node.left,key);
		else if(cmp>0)
			return get(node.right,key);
		else
			return node.val;
	}
	
	//将键值对存入表中（若值为空则将键key从表中删除）
	public void put(Key key,Value val){
		if(key==null)
			throw new IllegalArgumentException("argument to put() is null");
		
		//因为当键不存在时我们会返回null，若允许值为空，在返回空值时会有歧义
		if(val==null){
			delete(key);
			return;
		}
		
		root=put(root,key,val);
		
	}
	private Node put(Node node,Key key,Value val){
		if(node==null)
			return new Node(key,val,1);
		int cmp=key.compareTo(node.key);
		if(cmp<0)
			node.left=put(node.left,key,val);
		else if(cmp>0)
			node.right=put(node.right,key,val);
		else
			node.val=val;
		node.N=size(node.left)+size(node.right)+1;
		return node;
	}
	
	//最小的键
	public Key min(){
		Node temp=min(root);
		if(temp==null)
			return null;
		return temp.key;
	}
	private Node min(Node node){
		if(node==null)
			return null;
		if(node.left==null)
			return node;
		return min(node.left);
	}
	
	//最大的键
	public Key max(){
		Node temp=max(root);
		if(temp==null)
			return null;
		return temp.key;
	}
	private Node max(Node node){
		if(node==null)
			return null;
		if(node.right==null)
			return node;
		return max(node.right);
	}
	
	//小于等于key的最大键
	public Key floor(Key key){
		if(key==null)
			throw new IllegalArgumentException("argument to floor() is null");
		Node temp=floor(root,key);
		if(temp==null)
			return null;
		return temp.key;
	}
	
	/*如果键key小于node节点的键，那么小于等于key的最大键一定在根节点的左子树
	 * 中；若大于node节点的键，那么只有当node节点的右子树中存在小于等于key的
	 * 节点时，小于等于key的最大键才会出现在右子树中，否则node节点就是小于等于
	 * key的最大键*/
	private Node floor(Node node,Key key){
		if(node==null)
			return null;
		int cmp=key.compareTo(node.key);
		if(cmp<0)
			return floor(node.left,key);
		Node t=floor(node.right,key);
		if(t!=null)
			return t;
		else
			return node;
	}
	
	//大于等于key的最小键
	public Key ceiling(Key key){
		Node temp=ceiling(root,key);
		if(temp==null)
			return null;
		return temp.key;
	}
	private Node ceiling(Node node,Key key){
		if(node==null)
			return null;
		int cmp=key.compareTo(node.key);
		if(cmp>0)
			return ceiling(node.right,key);
		Node t=ceiling(node.left,key);
		if(t!=null)
			return t;
		else
			return node;
	}
	
	//排名为k的键（注意排名是从0开始的!!）
	public Key select(int k){
		Node temp=select(root,k);
		if(temp==null)
			return null;
		return temp.key;
	}
	
	/*如果左子树中的节点数t大于k，那么我们就继续递归地在左子树中查找排名为k的键；
	 * 如果t等于k，我们就返回根节点中的键；如果t小于k，我们就递归地在右子树中查
	 * 找排名为k-t-1的键*/
	private Node select(Node node,int k){
		if(node==null)
			return null;
		int t=size(node.left);
		if(t==k)
			return node;
		else if(t>k)
			return select(node.left,k);
		else
			return select(node.right,k-t-1);
	}
	
	//小于key的键的数量
	public int rank(Key key){
		if(key==null)
			throw new IllegalArgumentException("argument to rank() is null");
		return rank(root,key);
		
	}
	
	/*若key小于node节点中的键，则继续在左子树中寻找小于key的节点的键；若key
	 * 等于节点的键则返回左子树中节点的总数；若key大于节点的键，则返回该节点
	 * 左子树的总结点数+1加上key在右子树的排名*/
	private int rank(Node node,Key key){
		//方法一
		if(node==null)
			return 0;
		int cmp=key.compareTo(node.key);
		if(cmp<0)
			return rank(node.left,key);
		else if(cmp>0)
			return 1+size(node.left)+rank(node.right,key);
		else
			return size(node.left);
		
		//方法二
//		int sum=0;
//		if(cmp<0)
//			sum+=rank(node.left,key);
//		else if(cmp>0){
//			sum+=size(node.left)+1;
//			sum+=rank(node.right,key);
//		}
//		else
//			sum+=size(node.left);
//		return sum;
					
	}
	
	//删除最小的键
	public void deleteMin(){
		root=deleteMin(root);
	}
	
	/*因为最小节点一定是在左子树，所以只要不断检索左子树直至遇见含有空的左链接
	 * 的节点，返回该节点的右链接，并更新节点计数器*/
	private Node deleteMin(Node node){
		if(node==null)
			return null;
		if(node.left==null)
			return node.right;
		node.left=deleteMin(node.left);
		node.N=size(node.left)+size(node.right)+1;
		return node;
	}
	
	//删除最大的键
	public void deleteMax(){
		root=deleteMax(root);
	}
	private Node deleteMax(Node node){
		if(node==null)
			return null;
		if(node.right==null)
			return node.left;
		node.right=deleteMax(node.right);
		node.N=size(node.left)+size(node.right)+1;
		return node;
	}
	
	//从表中删去键key（及其对应的值）
	public void delete(Key key){
		if(key==null)
			throw new IllegalArgumentException("argument to delete() is null");
		root=delete(root,key);
	}
	
	/*在删除节点node之后用它的后继节点填补它的位置。因为node有一个
	 * 右子节点，因此它的后继节点就是其右子树中的最小节点*/
	private Node delete(Node node,Key key){
		if(node==null)
			return null;
		int cmp=key.compareTo(node.key);
		if(cmp<0)
			node.left=delete(node.left,key);
		else if(cmp>0)
			node.right=delete(node.right,key);
		else{
			if(node.right==null)
				return node.left;
			if(node.left==null)
				return node.right;
			Node t=node;
			node=min(t.right);//获取右子节点的最小节点
			node.right=deleteMin(t.right);//删除右子树的最小节点
			node.left=t.left;
		}
		node.N=size(node.left)+size(node.right)+1;//更新节点计数器
		return node;
	}
	
	//表中所有键的集合，已排序
	public Iterable<Key> keys(){
		return keys(min(),max());
	}
	
	//[lo..hi]之间的所有键，已排序
	public Iterable<Key> keys(Key lo,Key hi){
		QueueDemo<Key> queue=new QueueDemo<Key>();
		keys(root,queue,lo,hi);
		return queue;
	}
	
	/*先递归的查找根节点的左子树，然后查找根节点，再递归的查找根节点的右子树*/
	private void keys(Node node,QueueDemo<Key> queue,Key lo,Key hi){
		if(node==null)
			return;
		int cmplo=lo.compareTo(node.key);
		int cmphi=hi.compareTo(node.key);
		if(cmplo<0)
			keys(node.left,queue,lo,hi);
		if(cmplo<=0&&cmphi>=0)
			queue.enqueue(node.key);
		if(cmphi>0)
			keys(node.right,queue,lo,hi);
	}
	
	public static void main(String[] args) {
		BST<String,Integer> bst=new BST<>();
		System.out.println("please write keys：");
		Scanner c=new Scanner(System.in);
		for(int i=0;!c.hasNext("0");i++){
			bst.put(c.next(), i);
		}
		for(String s:bst.keys()){
			System.out.println(s+" "+bst.get(s));
		}
//		System.out.println(bst.height());
//		System.out.println(bst.max());
//		System.out.println(bst.floor("b"));
//		System.out.println(bst.floor("g"));
	}
}
