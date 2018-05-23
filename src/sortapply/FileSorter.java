package sortapply;

import java.io.File;
import java.util.Arrays;
import java.util.Scanner;

import bagstatckqueue.Queue;

/*按文件名排序。从命令行接受一个目录名并打印出按照文件名排序后的所有文件
 * 提示：使用File数据类型*/
public class FileSorter {
	public static void fileName(String path,Queue<String> q,int index){
		File file=new File(path);
		File[] files=file.listFiles();
		Arrays.sort(files,new NameCompar());
		for(File f:files){
			String str="";
			int num=index;
			while(num-->0)
				str+=" ";
			str+=f.getName();
			q.enququ(str);
			if(f.isDirectory())
				fileName(f.getAbsolutePath(),q,index+1);
		}
	}
	public static void main(String[] args){
		Queue<String> q=new Queue<>();
		Scanner in=new Scanner(System.in);
		String path=in.nextLine();
		fileName(path,q,0);
		int n=q.size();
		for(int i=0;i<n;i++){
			System.out.println(q.dequeue());
		}
	}
}
