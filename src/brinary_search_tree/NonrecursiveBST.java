package brinary_search_tree;

/*用非递归实现put()和get()方法的二叉查找树*/
public class NonrecursiveBST<Key extends Comparable<Key>,Value> {
	private Node root;
	private class Node{
		Key key;
		Value val;
		Node letf,right;
		int N;
		public Node(Key key,Value val,int N){
			this.key=key;
			this.val=val;
			this.N=N;
		}
	}
	
	public Value get(Key key){
		if(key==null)
			throw new IllegalArgumentException("argument to grt() is null");
		Node temp=root;
		Value result=null;
		while(temp!=null){
			int cmp=key.compareTo(temp.key);
			if(cmp<0)
				temp=temp.letf;
			else if(cmp>0)
				temp=temp.right;
			else{
				result=temp.val;
				break;
			}
		}
		return result;
	}
	
	public void put(Key key,Value val){
		if(key==null)
			throw new IllegalArgumentException("argument to put() is null");
//		if(val==null){
//			delete(key);
//			return;
//		}
		Node temp=root;
		Node last=temp;
		while(temp!=null){
			last=temp;
			int cmp=key.compareTo(temp.key);
			if(cmp<0)
				temp=temp.letf;
			else if(cmp>0)
				temp=temp.right;
			else{
				temp.val=val;
				return;
			}
		}
		int cmp=key.compareTo(last.key);
		if(cmp<0)
			last.letf=new Node(key,val,1);
		else if(cmp>0)
			last.right=new Node(key,val,1);
	}

}
