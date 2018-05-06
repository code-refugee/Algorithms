package analysisalgor;

import java.util.NoSuchElementException;

/*一个队列实现的栈。使用一个队列实现一个栈，使得每个栈操作所需的队列操作数量为线性级别。*/
public class StackUseQueue<Item> {
	private Queue<Item> qe;
	public StackUseQueue(){
		qe=new Queue<>();
	}
	public boolean isEmpty(){
		return qe.isEmpty();
	}
	public int size(){
		return qe.size();
	}
	public void push(Item item){
		qe.enqueue(item);
	}
	public Item pop(){
		if(qe.size()<1)
			throw new NoSuchElementException();
		int N=qe.size();
		Item temp=null;
		if(N==1)
			temp=qe.dequeue();
		if(N>=2){
			Item temp1=qe.dequeue();
			N=N-1;
			while(N!=0){
				qe.enqueue(temp1);
				temp1=qe.dequeue();
				N--;
			}
			temp=temp1;
		}
		return temp;
	}
	public static void main(String[] args) {
		StackUseQueue<String> squ=new StackUseQueue<>();
		squ.push("你的");
		squ.push("名字");
		squ.push("：");
		squ.push("三叶");
		squ.push("泷");
		System.out.println(squ.pop());
		System.out.println(squ.pop());
		System.out.println(squ.pop());
		System.out.println(squ.pop());
		squ.push("三叶");
		System.out.println(squ.pop());
	}

}
