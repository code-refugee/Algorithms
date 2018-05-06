package dataabstraction;
/*有理数.为有理数实现一个不可变数据类型Rational,支持加减乘除操作 。
 * 从中思考如何自己来写出一个API大纲然后实现之。并用断言来防止溢出*/
public class Rational {
	
	//用long类型控制溢出的可能性
	private long numerator;
	private long denominator;
	
	public Rational(int numerator,int denominator){
		if(denominator==0)
			throw new RuntimeException("denominator can not be zero");
		//为后续计算方便将分母变为正数
		if(denominator<0){
			numerator=-1*numerator;
			denominator=-1*denominator;
		}
		long nc=gcb(Math.abs(numerator),Math.abs(denominator));
		//化为分数最简形式
		this.numerator=numerator/nc;
		this.denominator=denominator/nc;
	}
	
	public long getNumerator(){
		return numerator;
	}
	
	public long geetDenominator(){
		return denominator;
	}
	
	//求和
	public Rational plus(Rational b){
		//添加断言防止溢出。要使用断言生效，需要指定-ea参数，在RunConfigurations中设置
		assert(this.denominator<Integer.MAX_VALUE&&this.denominator>Integer.MIN_VALUE) :"会溢出";
		assert(b.denominator<Integer.MAX_VALUE&&b.denominator>Integer.MIN_VALUE):"会溢出";
		long a=this.numerator*b.denominator+this.denominator*b.numerator;
		long c=this.denominator*b.denominator;
		this.numerator=a;
		this.denominator=c;
		return this;
	}
	
	//求差
	public Rational minus(Rational b){
		long a=this.numerator*b.denominator-this.denominator*b.numerator;
		long c=this.denominator*b.denominator;
		long nc=gcb(Math.abs(c),Math.abs(a));
		this.numerator=numerator/nc;
		this.denominator=denominator/nc;
		return this;
	}
	
	//求积
	public Rational times(Rational b){
		long a=this.numerator*b.numerator;
		long c=this.denominator*b.denominator;
		long nc=gcb(Math.abs(c),Math.abs(a));
		this.numerator=numerator/nc;
		this.denominator=denominator/nc;
		return this;
	}
	
	//求商
	public Rational divides(Rational b){
		long a=this.numerator*b.denominator;
		long c=this.denominator*b.numerator;
		long nc=gcb(Math.abs(c),Math.abs(a));
		this.numerator=numerator/nc;
		this.denominator=denominator/nc;
		return this;
	}
	
	//该数与that是否相等
	public boolean equals(Rational that){
		if(this==that)
			return true;
		if(that==null)
			return false;
		return this.numerator==that.numerator&&this.denominator==that.denominator;
	}
	
	//对象的字符串表示
	public String toString(){
		if(denominator==1)
			return ""+numerator;
		return numerator+"/"+denominator;
		
	}
	//利用欧几里得算法求出最大公约数
	private static long gcb(long p,long q){
		if(q==0)
			return p;
		long r=p%q;
		return gcb(q,r);
	}
	public static void main(String[] args) {
		Rational r1=new Rational(1,1000000000);
		Rational r2=new Rational(1,1000000001);
		Rational r=new Rational(1,1000000003);
		System.out.println(r1.plus(r2).plus(r));
	}

}
