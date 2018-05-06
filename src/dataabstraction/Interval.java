package dataabstraction;

/*编写一个Interval1D的用例,从命令行接受一个整数N.
 * 从标准输入中读取N个间隔(每个间隔由一对double值定义)
 * 并打印出所有相交的间隔对。*/

public class Interval {
	private double m;
	private double n;
	
	public Interval(double m,double n){
		if(m>n){
			double k;
			k=m;
			m=n;
			n=k;
		}
		this.m=m;
		this.n=n;  
		
	}
	
	public boolean isInterval(Interval that){
		if(this.n<that.m)
			return false;
		if(that.n<this.m)
			return false;
		return true;
	}
	
	public static void main(String[] args) {
		Interval[] intervals=new Interval[4];
		intervals[0] = new Interval(15.0, 33.0);
        intervals[1] = new Interval(45.0, 60.0);
        intervals[2] = new Interval(20.0, 70.0);
        intervals[3] = new Interval(46.0, 55.0);
        for(int i=0;i<intervals.length;i++){
        	for(int j=i+1;j<intervals.length;j++){
        		if(intervals[i].isInterval(intervals[j])){
        			System.out.println(intervals[i].m+" "+intervals[i].n+"\n"+
        		intervals[j].m+" "+intervals[j].n);
        			System.out.println();
        		}
        	}
        }
	}

}
