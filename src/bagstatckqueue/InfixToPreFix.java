package bagstatckqueue;
import java.util.NoSuchElementException;

/*编写一个过滤器InfixToPostfix，将算术表达式由中序表达式转为前序表达式。
 * 中序表达式转换为前序表达式以及前序表达式的求解---重点题 。 中序表达式，如：
 * 2*3/(2-1)+3*(4-1)转换为前序表达式则为+/*23-21*3-41
 * */
import edu.princeton.cs.algs4.*;
public class InfixToPreFix {
	public static void main(String[] args) {
		String str="2*3/(2-1)+3*(4-1)";
		System.out.println(str);
		System.out.println(transformExpress(str));
		System.out.println(str);
	}
	public static String transformExpress(String s){
		Stack<Character> original=new Stack<Character>();
		Stack<Character> reverse=new Stack<Character>();
		String trans="";
		String ne="";
		original=newStack(s,original);
		trans=newString(original);
		for(int i=0;i<trans.length();i++){
			char c=trans.charAt(i);
			if(!isOperator(c))
				ne+=c;
			else{
				//")"作为括号内运算符出栈结束标志
				if(c==')')
					reverse.push(c);
				//"("作为括号内运算符开始出栈标志
				else if(c=='('){
					if(reverse.isEmpty())
						throw new NoSuchElementException("stack upflow");
					else{
						char temp=reverse.pop();
						while(temp!=')'){
							ne+=temp;
							temp=reverse.pop();
						}
							
					}
				}
				else{
					if(reverse.isEmpty()||createPriority(reverse.peek())<createPriority(c))
						reverse.push(c);
					else{
						while(!reverse.isEmpty()&&reverse.peek()!=')'&&createPriority(reverse.peek())>=createPriority(c)){
							ne+=reverse.pop();
						}
						reverse.push(c);
					}
				}
			}
		}
		if(!reverse.isEmpty()){
			while(!reverse.isEmpty()){
				if(reverse.peek()==')')
					throw new NoSuchElementException("Illegal input");
				else
					ne+=reverse.pop();
			}
		}
		reverse=newStack(ne,reverse);
		ne=newString(reverse);
		return ne;
		
	}
	//压栈操作
	private static Stack<Character> newStack(String s,Stack<Character> sta){
		for(int i=0;i<s.length();i++){
			char c=s.charAt(i);
			sta.push(c);
		}
		return sta;
	}
	//出栈操作
	private static String newString(Stack<Character> sta){
		String ne="";
		for(char c:sta)
			ne+=c;
		return ne;
	}
	//判断是否为操作符，若不是则正常输出即可
	private static boolean isOperator(char c){
		if(c=='+'||c=='-'||c=='*'||c=='/'||c=='('||c==')')
			return true;
		return false;
	}
	
	//判断优先级
	private static int createPriority(char c){
		if(c==')')
			return 0;
		else if(c=='*'||c=='/')
			return 2;
		else if(c=='+'||c=='-')
			return 1;
		else
			return 3;
	}

}
