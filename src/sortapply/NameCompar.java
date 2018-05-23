package sortapply;

import java.io.File;
import java.util.Comparator;

public class NameCompar implements Comparator<File>{
	public int compare(File a,File b){
		String s1=a.getName();
		String s2=b.getName();
		for(int i=0;i<Math.min(s1.length(), s2.length());i++){
			if(s1.charAt(i)<s2.charAt(i))
				return -1;
			else if(s1.charAt(i)>s2.charAt(i))
				return +1;
		}
		return s1.length()-s2.length();
	}

	
}
