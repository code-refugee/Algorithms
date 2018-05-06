package bagstatckqueue;

import edu.princeton.cs.algs4.*;


/*编写一个Stack的用例Parentheses,从标准输入中读取一个文本流并使用栈判定其中的括号是否配对完整.
 * 例如[()]{}{[()]}为true,对于[(])程序则打印false.*/
public class Parentheses {
	private static final char LEFT_PAREN     = '(';  //paren---括弧
    private static final char RIGHT_PAREN    = ')';
    private static final char LEFT_BRACE     = '{';  //brace---大括号
    private static final char RIGHT_BRACE    = '}';
    private static final char LEFT_BRACKET   = '[';  //bracket---括号
    private static final char RIGHT_BRACKET  = ']';

    public static boolean isBalanced(String s) 
    {
    	if(s==null)
    		return false;
        Stack<Character> stack = new Stack<Character>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == LEFT_PAREN)   stack.push(LEFT_PAREN);
            if (s.charAt(i) == LEFT_BRACE)   stack.push(LEFT_BRACE);
            if (s.charAt(i) == LEFT_BRACKET) stack.push(LEFT_BRACKET);

            if (s.charAt(i) == RIGHT_PAREN) {
                if (stack.isEmpty())           return false;
                if (stack.pop() != LEFT_PAREN) return false;
            }

            else if (s.charAt(i) == RIGHT_BRACE) {
                if (stack.isEmpty())           return false;
                if (stack.pop() != LEFT_BRACE) return false;
            }

            else if (s.charAt(i) == RIGHT_BRACKET) {
                if (stack.isEmpty())             return false;
                if (stack.pop() != LEFT_BRACKET) return false;
            }
        }
        return stack.isEmpty();
    }


    public static void main(String[] args) 
    {
        String s = "[()]{}{[()]}";
        StdOut.println(isBalanced(s));
    }

}
