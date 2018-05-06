package bagstatckqueue;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

/*写一个可迭代的Stack用例，它含有一个静态的copy()方法，
 * 接受一个字符串的栈作为参数并返回该栈的一个副本。注意：
 * 这种能力是迭代器价值的一个重要体现，因为有了它我们无需改变基本 API 就能实现这种功能。*/
public class Stack2<Item> implements Iterable<Item>{
	private int N=0;
	private int pushNum=0;
	private int popNum=0;
	private Item[] a=(Item[])new Object[1];
	//调整数组大小
	private void resize(int N){
		Item[] it=(Item[])new Object[N];
		for(int i=0;i<a.length;i++){
			it[i]=a[i];
		}
		a=it;
	}
	public int size(){
		return N;
	}
	public boolean isEmpty(){
		return N==0;
	}
	public void push(Item it){
		if(N==a.length)
			resize(2*a.length);
		a[N++]=it;
		pushNum++;
	}
	public Item pop(){
		Item it=a[--N];
		a[N]=null;
		if(N>0&&N==a.length/4)
			resize(a.length/2);
		popNum++;
		return it;
		
	}
	public Iterator<Item> iterator() {
		return new ReverseArrayIterator();
	}
	private class ReverseArrayIterator implements Iterator<Item>{
		private int i=N;
		private int pushN=pushNum;
		private int popN=popNum;
		public boolean hasNext(){
			if(pushN!=pushNum||popN!=popNum)
				throw new ConcurrentModificationException();
			return i>0;
		}
		public Item next(){			
			return a[--i];
		}
		public void remove(){
			
		}
		
	}
	public static Stack2<String> copy(Stack2<String> sta){
		Stack2<String> st=new Stack2<String>();
		Stack2<String> reverse=new Stack2<String>();
		for(String str:sta){
			st.push(str);
		}
		for(String str:st){
			reverse.push(str);
		}
		return reverse;
	}
	public static void main(String[] args) {
		Stack2<String> stack = new Stack2<String>();
        stack.push("我");
        stack.push("的");
        stack.push("名字");
        stack.push("叫");
        stack.push("TaoYuHang");
        stack.push("微博:https://m.weibo.cn/p/100******");
        //打印
        System.out.println("原栈逆序输出:");
        for (String string : stack) {
            System.out.print(string);
        }
        System.out.println("");
        System.out.println("开始拷贝...");
        //拷贝
        Stack2<String> stack2 = Stack2.copy(stack);
        System.out.println("拷贝成功");
        System.out.println("开始打印拷贝后的栈");
        //创建迭代器
        for (String st : stack2) {
        	 System.out.print(st);
        	 stack2.pop();
        }


	}
	

}
