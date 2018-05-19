package sortapply;

import java.util.Comparator;

/*该类自定义了字符串的一种比较方式，即按照长度来比较*/
public class LenComparator implements Comparator<String>{

	@Override
	public int compare(String o1, String o2) {
		if(o1.length()<o2.length())
			return -1;
		else if(o1.length()>o2.length())
			return 1;
		else
			return 0;
	}

	

}
