package bagstatckqueue;
import java.util.NoSuchElementException;

/*编写一个过滤器InfixToPostfix，将算术表达式由中序表达式转为后序表达式。
 * 中序表达式转换为后序表达式以及后序表达式的求解---重点题 。 中序表达式，如：
 * 2*3/(2-1)+3*(4-1)转换为后序表达式则为23*21-/341-*+
 * */
import edu.princeton.cs.algs4.*;
public class InfixToPostFix
{
    public static void main(String[] args)
    {
        String str="2*3/(2-1)+3*(4-1)";
        String pfstr=transformExpression(str);
        StdOut.println(pfstr);
		 StdOut.println(caculateResult(pfstr));
    }
    
    //计算后序表达式的值
    public static String caculateResult(String s){
    	Stack<String> vals=new Stack<String>();
    	for(int i=0;i<s.length();i++){
    		char c=s.charAt(i);
    		if(c=='+'||c=='-'||c=='*'||c=='/'){
    			if(vals.size()<2)
    				throw new NoSuchElementException("Illegal express");
    			else{
    				String a=vals.pop();
    				double result = 0;
    				if(c=='+'){
    					result=Double.parseDouble(a)+Double.parseDouble(vals.pop());
    				}
    				else if(c=='-'){
    					result=Double.parseDouble(vals.pop())-Double.parseDouble(a);
    				}
    				else if(c=='*'){
    					result=Double.parseDouble(a)*Double.parseDouble(vals.pop());
    				}
    				else if(c=='/'){
    					result=Double.parseDouble(vals.pop())/Double.parseDouble(a);
    				}
    				String r=String.valueOf(result);
    				vals.push(r);
    			}
    		}
    		else
    			vals.push(String.valueOf(c));
    	}
    	return vals.pop();
    }
   
    
    //将前序表达式转换为后序表达式
    public static String transformExpression(String str)
    {
        Stack<Character> stack=new Stack<Character>();
        String express="";
        
        for(int i=0;i<str.length();i++)
        {
            char c=str.charAt(i);
            if(!isOperator(c))      //情形1:操作数情况
                express+=c;
            else                      
            {
                if(c=='(')          //情形2:左括号情况
                    stack.push(c);
                else if(c==')')     //情形3:右括号情况
                {
                    if(stack.isEmpty())
                        throw new NoSuchElementException("Stack underflow");
                    else
                    {
                        char temp=stack.pop();
                        while(temp!='(')
                        {
                            express+=temp;
                            temp=stack.pop();
                        }
                    }
                }
                else               //情形4:+-*/情况
                {
                    if(stack.isEmpty()||priority(stack.peek())<priority(c))
                        stack.push(c);
                    else    
                    {
                        while(!stack.isEmpty()&&stack.peek()!='('&&priority(stack.peek())>=priority(c))
                            express+=stack.pop();
                        stack.push(c);
                    }
                }
            }
        }
        if(!stack.isEmpty())     //栈中还有操作符情况
        {
            while(!stack.isEmpty())
            {
                if(stack.peek()=='(')
                    throw new NoSuchElementException("Illegal input");
                else
                    express+=stack.pop();
            }
        }         
        return express;
    }
    //判断字符是否为符号
    public static boolean isOperator(char c)
    {
        if(c=='+'||c=='-'||c=='*'||c=='/'||c=='('||c==')')
            return true;
        else
            return false;
    }
    //设定优先级
    public static int priority(char c)
    {
        if(c=='(')
            return 0;
        else if(c=='*'||c=='/')
            return 2;
        else if(c=='+'||c=='-')
            return 1;
        else
            return 3;
    }
}