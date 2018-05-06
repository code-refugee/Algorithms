package bagstatckqueue;

import java.io.File;

/*文件列表。文件夹就是一列文件和文件夹的列表。编写一个程序，从命令行接受一个文件夹名作为参数，
 * 打印出该文件夹下的所有文件并用递归的方式在所有子文件夹的名下（缩进）列出其下的所有文件。
 * 提示：用队列*/
import edu.princeton.cs.algs4.*;

public class FileList {
	public static void fileName(String path, Queue<String> q, int index) {
		File file = new File(path);
		File[] files = file.listFiles();// 此路径下的文件
		for(File F:files){
			int num=index;
			String s="";
			while(num-->0)
				s=s+" ";
			s=s+F.getName();
			q.enququ(s);
			if(F.isDirectory()){
				fileName(F.getAbsolutePath(),q,index+1);//注意如果变为index++或++index是错误的
			}
		}
			
	}
	public static void main(String[] args) {
		Queue<String> q = new Queue<String>();
		fileName("E:\\test", q, 0);
		int N = q.size();
		for (int i = 0; i < N; i++)
			System.out.println(q.dequeue());
	}

}
