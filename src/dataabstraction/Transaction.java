package dataabstraction;

import edu.princeton.cs.algs4.*;

/*用我们对Date的实现作为模板实现Transaction类型*/
public class Transaction {
	private String who;
	private SmartData when;
	private double amount;
	public Transaction(String who,SmartData when,double amount){
		this.who=who;
		this.when=new SmartData(when.year(),when.month(),when.day());
		this.amount=amount;
	}
	public Transaction(String tranction){
		String[] s=tranction.split("\\s+");
		if(s.length!=3)
			throw new IllegalArgumentException("Illeagl tranction");
		who=s[0];
		when=new SmartData(s[1]);
		amount=Double.parseDouble(s[2]);
	}
	public String who(){
		return who;
	}
	public SmartData when(){
		return when;
	}
	public double amount(){
		return amount;
	}
	public String toString(){
		return "name:"+who+" "+"data:"+when+" "+"amount"+amount;
	}
	public boolean equals(Object x){
		if(this==x)
			return true;
		if(x==null)
			return false;
		if(this.getClass()!=x.getClass())
			return false;
		Transaction that=(Transaction) x;
		if (!this.when.equals(that.when))  return false;
        if (!this.who.equals(that.who)) return false;
        if (this.amount  != that.amount)  return false;
        
        return true;
	}
	public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
        SmartData date=new SmartData(1996,12,5);
        Transaction tran=new Transaction("Kyson 1996/12/5 1000");
        StdOut.println(tran.toString());
    }



}
