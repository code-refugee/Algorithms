package basicsrecover;
import edu.princeton.cs.algs4.*;
public class Card implements Comparable<Card>{
	private String kind;
	private String num;
	public Card(){
		String[] rkid={"梅花","方块","红桃","黑桃"};
		String[] nget={"2","3","4","5","6","7","8","9","10","11","12","13","1"};
		int rk=StdRandom.uniform(4);
		int rn=StdRandom.uniform(13);
		kind=rkid[rk];
		num=nget[rn];
	}
	public String toString(){
		return kind+num;
	}
	public String getNum(){
		return num;
	}
	@Override
	public int compareTo(Card that) {
		if(Integer.parseInt(this.getNum())>Integer.parseInt(that.getNum()))
			return +1;
		if(Integer.parseInt(this.getNum())<Integer.parseInt(that.getNum()))
			return -1;
		return 0;
	}
}
