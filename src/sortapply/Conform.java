package sortapply;

import java.util.Comparator;

/*排序得遵守的方法*/
public class Conform implements Comparator<String>{
	
	private final String ord="RWQOJMVAHBSGZXNTCIEKUPDYFL";
	
	public int compare(String v, String w) {
		for(int i=0;i<Math.min(v.length(), w.length());i++){
			int vindex=ord.indexOf(v.charAt(i));
			int windex=ord.indexOf(w.charAt(i));
			if(vindex<windex)
				return -1;
			else if(vindex>windex)
				return +1;
		}
		return v.length()-w.length();
	}		

}
