package bagstatckqueue;
/*这是一个桥牌的类，用于完成练习1.3.35题*/
import edu.princeton.cs.algs4.*;
public class Card {
	private String kind;
	private String num;
	public Card(){
		String[] kget={"方块","梅花","红桃","黑桃"};
		String[] nget={"2","3","4","5","6","7","8","9","10","J","Q","K","A"};
		kind=kget[StdRandom.uniform(4)];
		num=nget[StdRandom.uniform(13)];
	}
	public String toString(){
		return kind+num;
	}

}
