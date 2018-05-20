package sortapply;

public class Job implements Comparable<Job>{
	private String name;
	private double time;
	public Job(String name,double time){
		this.name=name;
		this.time=time;
	}
	
	public int compareTo(Job that){
		if(this.time>that.time)
			return +1;
		if(this.time<that.time)
			return -1;
		return 0;
	}
	
	public String toString(){
		return "任务："+name+"需要的时间"+time;
	}
}
