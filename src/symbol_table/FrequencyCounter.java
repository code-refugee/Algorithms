package symbol_table;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/*该用例会从标准输入中得到一列字符串
 * 并记录每个长度达到指定阈值的字符串
 * 出现的次数，然后遍历所有键找出出现
 * 频率最高的键*/

public class FrequencyCounter {
	public static void main(String[] args){
		int minlen=Integer.parseInt(args[0]);
		SequentialSearchST<String, Integer> st=new SequentialSearchST<>();
		File file=new File(args[1]);
		try {
			FileInputStream in=new FileInputStream(file);
			BufferedInputStream bf=new BufferedInputStream(in);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
