package sort;

public class Stopwatch {
	private final long start;
	public Stopwatch(){
		start=System.currentTimeMillis();
	}
	public double elapseTime(){
		long now=System.currentTimeMillis();
		return (now-start)/1000.0;
	}

}
