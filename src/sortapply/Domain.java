package sortapply;

import java.util.Arrays;
import edu.princeton.cs.algs4.*;

/*逆域名排序*/
public class Domain implements Comparable<Domain>{
	private final String[] fields;
	private final int n;
	public Domain(String name){
		//将域名用点分为若干个部分
		fields=name.split("\\.");
		n=fields.length;
	}
	public String toString(){
		if(n==0)
			return "";
		String s=fields[0];
		for(int i=1;i<n;i++){
			s=fields[i]+"."+s;
		}
		return s;
	}
	public int compareTo(Domain that){
		for(int i=0;i<Math.min(this.n, that.n);i++){
			String s=fields[this.n-i-1];
			String t=fields[that.n-i-1];
			int c=s.compareTo(t);
			if(c>0)
				return +1;
			if(c<0)
				return -1;
		}
		return this.n-that.n;
	}
	
	 public static void main(String[] args) {

	        String[] names = StdIn.readAllStrings();
	        Domain[] domains = new Domain[names.length];
	        for (int i = 0; i < domains.length; i++) {
	            domains[i] = new Domain(names[i]);
	        }

	        Arrays.sort(domains);

	        for (int i = 0; i < domains.length; i++) {
	            StdOut.println(domains[i]);
	        }
	    }
}
