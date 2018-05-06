package recover;
import edu.princeton.cs.algs4.*;
public class Card {
	private String kind;
	private String num;
	public Card(){
		String[] rkid={"梅花","方块","红桃","黑桃"};
		String[] nget={"2","3","4","5","6","7","8","9","10","J","Q","K","A"};
		int rk=StdRandom.uniform(4);
		int rn=StdRandom.uniform(13);
		kind=rkid[rk];
		num=nget[rn];
	}
	public String toString(){
		return kind+num;
	}
}
