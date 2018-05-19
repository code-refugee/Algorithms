package sortapply;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeMap;

import com.sun.prism.impl.Disposer.Record;

/*从标准输入读取一列字符串并按照字符串出现频率由高到低的顺序打印出每个字符串及其出现的次数,
 * 这题的解题思路为使用了TreeMap,TreeMap的特性是当你存进去一对键值对时，它会按照值的大小
 * 进行降序*/
public class Frequency {
	public QuickSort<String> qs;
	public TreeMap<String, Integer> freq;
	public Frequency() {
		qs=new QuickSort<>();
		freq=new TreeMap<>();
	}
	
	public void frequency(String[] s){
		qs.sort(s);
		int fre=1;
		for(int i=1;i<s.length;i++){
			if(!s[i].equals(s[i-1])){
				freq.put(s[i-1], fre);
				fre=1;
			}
			else
				fre++;
			if(i==s.length-1)
				freq.put(s[i], fre);
		}
		//注意此处对treemap的遍历，与数组数组遍历不同
		Iterator it=freq.entrySet().iterator();
		while(it.hasNext()){
			Map.Entry enty=(Map.Entry) it.next();
			System.out.println(enty.getKey()+"出现的次数为"+enty.getValue());
		}
	}
	
	public static void main(String[] args) {
		Scanner c=new Scanner(System.in);
		String N=c.nextLine();
		int len=Integer.parseInt(N);
		String[] s=new String[len];
		for(int i=0;i<len;i++){
			s[i]=c.nextLine();
		}
		Frequency f=new Frequency();
//		String[] s={"a","c","d","b","b","a","d","c","b","a"};
		f.frequency(s);
	}
}
