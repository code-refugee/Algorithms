package balanced_search_tree;

import java.util.NoSuchElementException;
import java.util.Scanner;

import bagstatckqueue.QueueDemo;

/*红黑二叉树（2-3平衡树） 定义：含有红黑链接并满足下列条件的二叉查找树：
 * 1)红连接均为左链接；（红色右链接和4- 节点也是可以存在的，但是我们这边规定只能存在
 * 红色左链接和3- 节点。因为只允许红色左链接的存在能减少可能出现的情况，因此实现所需的代码很少）
 * 2)没有任何一个节点同时和两条红链接相连（即规定只能存在3- 节点）
 * 3)该树是完美黑色平衡的，即任意空链接到根节点的路径上的黑链接数量相同
 * 
 * 红链接有什么作用：起到动态调整的作用。
 * 
 * 性能分析：在最坏情况下查找和插入操作的增长数量级均为2lgN，平均情况下为1.001lgN
 * 什么时候会出现最坏情况：
 * 它所对应的2-3树中构成最左边的路径节点全部都是3-节点而其余为2-节点。因为红黑树本质上还是
 * 一棵二叉树，所以最左边路径的长度是只包含2-节点路径长度（lgN）的两倍。
 * */
public class RedBlackBST<Key extends Comparable<Key>, Value> {
	private Node root; // 根节点
	private static final boolean RED = true;// 定义红链接为true
	private static final boolean BLACK = false;// 定义黑链接为false

	private class Node {
		Key key; // 键
		Value val; // 相关联的值
		Node left, right; // 左右子树
		int N; // 这棵树中的节点总数
		boolean color;// 指定父节点指向它的链接的颜色

		public Node(Key key, Value val, int N, boolean color) {
			this.key = key;
			this.val = val;
			this.N = N;
			this.color = color;
		}
	}
	
	//求树高
	public int height(){
		return height(root);
	}
	private int height(Node node){
		if(node==null)
			return -1;
		return 1+Math.max(height(node.left), height(node.right));
	}

	/*---------------------------------查询符号表的操作---------------------------------*/
	
	/*对比二叉树查找，这些操作代码无变化*/

	// 表是否为空
	public boolean isEmpty() {
		return root == null;
	}

	// 表中键值对的数量
	public int size() {
		return size(root);
	}
	
	//[lo..hi]之间键的数量
	public int size(Key lo,Key hi){
		if(lo==null)
			throw new IllegalArgumentException("first argument to size() is null");
		if(hi==null)
			throw new IllegalArgumentException("second argument to size() is null");
		return size(root,lo,hi);
	}
	
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
	
	private int size(Node node) {
		if (node == null)
			return 0;
		else
			return node.N;
	}

	// 键key是否存在于表中
	public boolean contains(Key key) {
		return get(key) != null;
	}

	// 获取键key对应的值（若键key不存在则返回空）
	public Value get(Key key) {
		if (key == null)
			throw new IllegalArgumentException("argument to get() is null");
		return get(root, key);
	}

	// 红黑树的get()与二叉查找树一样无变化
	private Value get(Node node, Key key) {
		if (node == null)
			return null;
		int cmp = key.compareTo(node.key);
		if (cmp > 0)
			return get(node.right, key);
		else if (cmp < 0)
			return get(node.left, key);
		else
			return node.val;
	}

	// 最小的键
	public Key min() {
		Node temp = min(root);
		if (temp == null)
			return null;
		return temp.key;
	}

	private Node min(Node node) {
		if (node == null)
			return null;
		if (node.left == null)
			return node;
		return min(node.left);
	}

	// 最大的键
	public Key max() {
		Node temp = max(root);
		if (temp == null)
			return null;
		return temp.key;
	}

	private Node max(Node node) {
		if (node == null)
			return null;
		if (node.right == null)
			return node;
		return max(node.right);
	}

	// 小于等于key的最大键
	public Key floor(Key key) {
		if (key == null)
			throw new IllegalArgumentException("argument to floor() is null");
		Node temp = floor(root, key);
		if (temp == null)
			return null;
		return temp.key;
	}

	/*
	 * 如果键key小于node节点的键，那么小于等于key的最大键一定在根节点的左子树
	 * 中；若大于node节点的键，那么只有当node节点的右子树中存在小于等于key的
	 * 节点时，小于等于key的最大键才会出现在右子树中，否则node节点就是小于等于 key的最大键
	 */
	private Node floor(Node node, Key key) {
		if (node == null)
			return null;
		int cmp = key.compareTo(node.key);
		if (cmp < 0)
			return floor(node.left, key);
		Node t = floor(node.right, key);
		if (t != null)
			return t;
		else
			return node;
	}

	// 大于等于key的最小键
	public Key ceiling(Key key) {
		if (key == null)
			throw new IllegalArgumentException("argument to ceiling() is null");
		Node temp = ceiling(root, key);
		if (temp == null)
			return null;
		return temp.key;
	}

	private Node ceiling(Node node, Key key) {
		if (node == null)
			return null;
		int cmp = key.compareTo(node.key);
		if (cmp > 0)
			return ceiling(node.right, key);
		Node t = ceiling(node.left, key);
		if (t != null)
			return t;
		else
			return node;
	}

	// 小于key的键的数量
	public int rank(Key key) {
		if (key == null)
			throw new IllegalArgumentException("argument to rank() is null");
		return rank(root, key);
	}

	/*
	 * 若key小于node节点中的键，则继续在左子树中寻找小于key的节点的键；若key
	 * 等于节点的键则返回左子树中节点的总数；若key大于节点的键，则返回该节点 左子树的总结点数+1加上key在右子树的排名
	 */
	private int rank(Node node, Key key) {
		if (node == null)
			return 0;
		int cmp = key.compareTo(node.key);
		if (cmp < 0)
			return rank(node.left, key);
		else if (cmp > 0)
			return 1 + size(node.left) + rank(node.right, key);
		else
			return size(node.left);
	}

	// 排名为k的键（注意排名是从0开始的!!）
	public Key select(int k) {
		if (k < 0)
			throw new IllegalArgumentException("argument to select() is");
		Node temp = select(root, k);
		if (temp == null)
			return null;
		return temp.key;
	}

	/*
	 * 如果左子树中的节点数t大于k，那么我们就继续递归地在左子树中查找排名为k的键；
	 * 如果t等于k，我们就返回根节点中的键；如果t小于k，我们就递归地在右子树中查 找排名为k-t-1的键
	 */
	private Node select(Node node, int k) {
		if (node == null)
			return null;
		int t = size(node.left);
		if (t > k)
			return select(node.left, k);
		else if (t < k)
			return select(node.right, k - t - 1);
		else
			return node;
	}
	
	//表中的所有键的集合，已排序
	public Iterable<Key> keys(){
		return keys(min(),max());
	}
	
	//[lo..hi]之间的所有键，已排序
	public Iterable<Key> keys(Key lo,Key hi){
		QueueDemo<Key> qe=new QueueDemo<>();
		keys(root,qe,lo,hi);
		return qe;
	}
	
	/*先递归的查找根节点的左子树，然后查找根节点，再递归的查找根节点的右子树*/
	private void keys(Node node,QueueDemo qe,Key lo,Key hi){
		if(node==null)
			return;
		int cmplo=lo.compareTo(node.key);
		int cmphi=hi.compareTo(node.key);
		if(cmplo<0)
			keys(node.left,qe,lo,hi);
		if(cmplo<=0&&cmphi>=0)
			qe.enqueue(node.key);
		if(cmphi>0)
			keys(node.right,qe,lo,hi);
	}
	
	/*---------------------------------更改符号表的操作---------------------------------*/

	// 将键值对存入表中（若值为空则将键key从表中删除）
	public void put(Key key, Value val) {
		if (key == null)
			throw new IllegalArgumentException("argument to put() is null");
		if (val == null) {
			delete(key);
			return;
		}
		root = put(root, key, val);

		/*
		 * 颜色转换会使根节点变为红色，红色的根节点（即root.color=RED）说明 根节点是一个3-
		 * 节点的一部分，但根节点已经是最上面的节点了，没有比它 更上面的节点与它相连构成3- 节点（与实际不符）。因此我们在每次插入后
		 * 都会将根节点设为黑色（注意：每当根节点由红变黑时数的黑链接高度就会加1）
		 */
		root.color = BLACK; // 根节点总是黑色的
		
		assert check();
	}

	/*
	 * 红黑树中实现插入算法的关键操作所需的步骤：要在一个3- 节点下插入新键，先创建一个临时的 4-
	 * 节点，将其分解并将红连接由中间键传递给它的父节点。重复这个过程，我们就能将红链接在 树中向上传递，直至遇到一个2- 节点或根节点
	 */
	private Node put(Node node, Key key, Value val) {
		if (node == null) {
			// 因为不知道树是否平衡，所以指向该节点的链接均为红
			return new Node(key, val, 1, RED);
		}
		int cmd = key.compareTo(node.key);
		if (cmd > 0)
			node.right = put(node.right, key, val);
		else if (cmd < 0)
			node.left = put(node.left, key, val);
		else
			node.val = val;

		// 这个if好理解，因为我们规定只能有红色左链接
		if (!isRed(node.left) && isRed(node.right))
			node=rotateLeft(node);

		/*
		 * 因为一个节点不能同时和两个红链接相连（变成了4- 节点）因此我们将它右旋转
		 * （右旋转的作用是将node.left作为中间键，好执行下一个if语句）
		 * 虽然还是有一个节点与两个红链接相连了，但是该节点却可以执行下一个if语句
		 */
		if (isRed(node.left) && isRed(node.left.left))
			node=rotateRight(node);

		// 处理4-节点（红黑树中只允许存在3-节点或2-节点）
		if (isRed(node.left) && isRed(node.right))
			flipColors(node);

		// 更新树中节点数目
		node.N =1+size(node.left)+size(node.right);
		return node;
	}

	// 删除最小的键
	/*
	 * 删除比较麻烦，我们先考虑删除最小值，当我们删除一个3节点中的元素的时候倒还好，
	 * 直接删除之后留下了一个2节点，树的平衡性没有发生变化。但是直接删除2节点会造成树
	 * 的高度的变化。所以，我们还是要处理一下，从上往下进行变换，最终的目标就是保证在 删除的时候当前节点不只是一个2节点。
	 */

	public void deleteMin() {
		// 注意红黑树为空的情况
		if (isEmpty())
			throw new NoSuchElementException("BST underflow");

		// ？？？（似乎这句话没必要）
		if (!isRed(root.left) && !isRed(root.right))
			root.color = RED;
		root = deleteMin(root);

		// 注意根节点都是黑色的，一定要将它变回来
		if (!isEmpty())
			root.color = BLACK;
		
		assert check();
	}

	/*
	 * 最小值在最左边，我们沿着左边下去的时候需要合并三个2节点形成一个4节点，
	 * 或者右边是三节点的话从右边节点“借”一个形成一个3节点或者4节点，这样就能保证当前节点大于2节点
	 * （注意这样做是不会打破平衡的：因为从第一个左子节点起它已经是3-或4-节点了，此时树平衡。沿着左边
	 * 下去，如果节点不是2-节点，不用变，继续往下走，如果是2-节点，那么它需要借节点，因为它的父节点或
	 * 兄弟节点一定有一个不是2-节点，所以借节点之后平衡同样不会打破）
	 */
	private Node deleteMin(Node node) {
		
		/*递归调用保证了，即使删除这个点也不会打破平衡性*/
		/*
		 * 因为是平衡二叉树（一个节点有左子节点必定有右子节点）所以不会像二叉查找树那样最最左边的
		 * 节点还有可能有一个它的右孩子节点。所以平衡二叉树找到最左边的节点直接返回null就行，不用返回 node.right
		 */
		if (node.left == null)
			return null;

		// 意味着node节点的左子节点是一个2-节点，要把它变为3-或4-节点
		if (!isRed(node.left) && !isRed(node.left.left))
			node = moveRedLeft(node);
		node.left = deleteMin(node.left);
		return balance(node);
	}

	// 删除最大键
	public void deleteMax() {
		if (isEmpty())
			throw new NoSuchElementException("BST underflow");
		if (!isRed(root.left) && !isRed(root.right))
			root.color = RED;
		root = deleteMax(root);
		if (!isEmpty())
			root.color = BLACK;
		
		assert check();
	}

	private Node deleteMax(Node node) {
		// 保持方向的一致性（因为在插入数据时我们规定红链接只能在左边）
		if (isRed(node.left))
			node = rotateRight(node);
		// 找到最右就删除
		if (node.right == null)
			return null;
		// 因为node.right节点还未做右旋操作所以&&之后的条件为!isRed(node.right.left)
		if (!isRed(node.right) && !isRed(node.right.left))
			node = moveRedRight(node);// 右子节点为2-节点就将其变为至少3-节点
		node.right = deleteMax(node.right);
		return balance(node);
	}

	//从表中删去键key（即其对应的值）
	public void delete(Key key){
		if(key==null)
			throw new IllegalArgumentException("argument to delete() is null");
		if(!isRed(root.left)&&!isRed(root.right))
			root.color=RED;
		root=delete(root,key);
		if(!isEmpty())
			root.color=BLACK;
		
		assert check();
	}
	
	private Node delete(Node node,Key key){
		int cmp=key.compareTo(node.key);
		
		/*若值比左子节点小，则继续在左子树中寻找，注意必须和deleteMin一样如果该节点是2-节点
		 * 必须将它变为3-或4-节点，这样删除节点时才不会打破平衡*/
		if(cmp<0){
			if(!isRed(node.left)&&!isRed(node.left.left))
				node=moveRedLeft(node);
			node.left=delete(node.left,key);
		}
		else{
			/*满足这个if判断，下一个if判断不会成立，因为node.right!==null*/
			if(isRed(node.left))
				rotateRight(node);
			
			/*上一句if保证了node没有红色的左子节点。如果有黑色的右链接,因为平衡一定会有
			 * 黑色左链接，所以当满足cmp=0且没有黑色右链接（黑色左链接也不存在）即可返回null*/
			if(cmp==0&&node.right==null)
				return null;
			
			/*上一个if不满足会出现2种情况*/
			
			// 意味着node节点的左子节点是一个2-节点，要把它变为3-或4-节点（防止删除后打破平衡）
			if(!isRed(node.right)&&!isRed(node.right.left))
				node=moveRedRight(node);
			
			//cmp==0,但存在左右子节点
			if(cmp==0){
				/*这几句很好理解，就是我们找到了目标键，但是这个键不是叶节点，
				 * 那么我们就把这个键的右子树的最小节点的键和值都赋给它，
				 * 然后删除右子树的最小值，这样就删除了目标键并且整个树还是有序的，平衡的。*/
				node.val=get(node.right,min(node.right).key);
				node.key=min(node.right).key;
				node.right=deleteMin(node.right);//执行完这句
			}
			
			//cmp>0,继续在右子树查找
			else
				node.right=delete(node.right,key);
		}
		
		return balance(node);
	}

	/*---------------------------------符号表的辅助操作---------------------------------*/

	// 指向该节点的颜色是否为红
	private boolean isRed(Node node) {
		if (node == null)
			return false;
		return node.color == RED;
	}

	/*
	 * 在插入新键时我们可以使用旋转操作帮助我们保证2-3树和红黑树之间的一一对应关系， 
	 * 因为旋转操作可以保证红黑树的两个重要性质：有序性和完美平衡性。
	 */

	/*
	 * 将链接左旋转(注意旋转之后节点颜色和子树中节点个数要变化)。若还不理解代码，请见 书本p277图
	 */
	private Node rotateLeft(Node node) {
		Node temp = node.right;
		node.right = temp.left;
		temp.left = node;
		temp.color = node.color;
		node.color = RED;
		temp.N = node.N;
		node.N = 1 + size(node.left) + size(node.right);

		// 注意返回的节点并不是形参节点
		return temp;
	}

	// 将链接右旋转
	private Node rotateRight(Node node) {
		Node temp = node.left;
		node.left = temp.right;
		temp.right = node;
		temp.color = node.color;
		node.color = RED;
		temp.N = node.N;
		node.N = 1 + size(node.left) + size(node.right);
		return temp;
	}

	/* 转换一个节点和它的两个子节点的颜色 */
	private void flipColors(Node node) {

		// 中节点的颜色必定和2个子节点的颜色对立
		assert (node != null) && (node.left != null) && (node.right != null);
		assert (!isRed(node) && isRed(node.left) && isRed(node.right))
				|| (isRed(node) && !isRed(node.left) && !isRed(node.right));

		node.color = !node.color;
		node.left.color = !node.left.color;
		node.right.color = !node.right.color;
	}

	// 将一个2-节点变为3-或4-节点
	private Node moveRedLeft(Node node) {

		// 将上面的节点拉下来形成一个大节点
		flipColors(node);

		// 看看是否能从兄弟节点中借一个（红链接只出现在左边）
		if (isRed(node.right.left)) {
			node.right = rotateRight(node.right);
			node = rotateLeft(node);

			/*
			 * 借了一个后再还一个给父节点，不然就变成了一个大节点 （不写这句也行，
			 * 因为大节点也是平衡的，写了更好）
			 */
			flipColors(node);
		}
		return node;
	}

	private Node moveRedRight(Node node) {
		// 将上面的节点拉下来形成一个大节点
		flipColors(node);
		if (isRed(node.left.left)) {
			node = rotateRight(node);
			flipColors(node);
		}
		return node;
	}

	// 将临时的4-节点解开使得树再次平衡，并更新节点的个数
	private Node balance(Node node) {
		if (!isRed(node.left) && isRed(node.right))
			node = rotateLeft(node);
		if (isRed(node.left) && isRed(node.left.left))
			node = rotateRight(node);
		if (isRed(node.left) && isRed(node.right))
			flipColors(node);
		node.N = size(node.left) + size(node.right) + 1;
		return node;
	}
	
	/*---------------------------------检查符号表是否是一棵真正的红黑树---------------------------------*/
	
	private boolean check(){
		if(!isBST())
			System.out.println("Not in symmetric order");
		if(!isSizeConsistent())
			System.out.println("Subtree counts not consistent");
		if(!isRankConsistent())
			System.out.println("Ranks not consistent");
		if(!is23())
			System.out.println("Not a 2-3 tree");
		if(!isBalanced())
			System.out.println("Not balanced");
		return isBST() && isSizeConsistent() && isRankConsistent() && is23() && isBalanced();
	}
	
	/*二叉树是否满足对称顺序。根据定义一颗二叉树满足，
	 * 根节点的键都大于它的左子树中的所有节点的键
	 * 都小于它的右子树中的所有节点的键
	 * 注意：此测试还确保数据结构是二叉树，因为顺序是严格的。*/
	private boolean isBST(){
		return isBST(root,null,null);
	}
	private boolean isBST(Node node,Key min,Key max){
		if(node==null)
			return true;
		if(min!=null&&min.compareTo(node.key)>=0)
			return false;
		if(max!=null&&max.compareTo(node.key)<=0)
			return false;
		
		/*因为是递归，我们可以从底部看起来理解这段代码，假设最底部有三个节点，一个左节点一个根节点
		 * 和一个右节点，第一个isBST()是用来判断左节点的值是否小于根节点的值，第二个isBST()用于判断
		 * 右节点的值是否大于根节点的值*/
		return isBST(node.left,min,node.key)&&isBST(node.right,node.key,max);
	}
	
	//检查子树中的节点个数对不对
	private boolean isSizeConsistent(){
		return isSizeConsistent(root);
	}
	private boolean isSizeConsistent(Node node){
		if(node==null)
			return true;
		if(node.N!=1+size(node.left)+size(node.right))
			return false;
		return isSizeConsistent(node.left)&&isSizeConsistent(node.right);
	}
	
	//选择/排名检查
	private boolean isRankConsistent(){
		for(int i=0;i<size();i++){
			if(i!=rank(select(i)))
				return false;
		}
		for(Key key:keys()){
			if(key.compareTo(select(rank(key)))!=0)
				return false;
		}
		return true;
	}
	
	//是否是一颗2-3树
	private boolean is23(){
		return is23(root);
	}
	private boolean is23(Node node){
		if(node==null)
			return true;
		//右链接不能为红
		if(isRed(node.right))
			return false;
		//不存在连续的2条红链接
		if(node!=root&&isRed(node)&&isRed(node.left))
			return false;
		//检查左右子树
		return is23(node.left)&&is23(node.right);
	}
	
	//检查该树是否平衡。即所有叶子节点到根节点的黑链接数相同
	private boolean isBalanced(){
		int black=0;
		Node x=root;
		//先求出一棵树中任意一个叶子节点到根节点的黑链接有多少。（这里我们求最左边的叶子节点到根节点）
		while(x!=null){
			if(!isRed(x))
				black++;
			x=x.left;
		}
		return isBalance(root,black);
	}
	
	/*思路：因为我们之前已经求出了从叶子节点到根节点的黑链接树，这对于每个叶子节点都适用（如果该树平衡）
	 * 因此我们只要分别沿左右子树查找黑链接，每找到一条black--，等到所有叶子节点都找完，比较balck是否
	 * 为0即可，如果树不平衡，是不会有black==0的*/
	private boolean isBalance(Node node,int black){
		if(node==null)
			return black==0;
		if(!isRed(node))
			black--;
		return isBalance(node.left,black)&&isBalance(node.right,black);
	}
	
	/*---------------------------------主方法---------------------------------*/
	
	public static void main(String[] args) {
		RedBlackBST<String,Integer> bst=new RedBlackBST<>();
		System.out.println("please write keys：");
		Scanner c=new Scanner(System.in);
		for(int i=0;!c.hasNext("0");i++){
			bst.put(c.next(), i);
		}
		for(String s:bst.keys()){
			System.out.println(s+" "+bst.get(s));
		}
	}
}
