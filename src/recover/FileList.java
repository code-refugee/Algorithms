package recover;
import java.io.File;

/*文件列表。文件夹就是一列文件和文件夹的列表。编写一个程序，从命令行接受一个文件夹名作为参数，
 * 打印出该文件夹下的所有文件并用递归的方式在所有子文件夹的名下（缩进）列出其下的所有文件。*/
import bagstatckqueue.*;
public class FileList {
	public static void fileName(String path,Queue<String> q,int index){
		File file=new File(path);
		File[] files=file.listFiles();
		for(File f:files){
			int num=index;
			String s="";
			while(num-->0)//注意--num是错误的，因为它是num先减一再与0比较与num--不同
				s=s+" ";//缩进
			s=s+f.getName();
			q.enququ(s);
			if(f.isDirectory())//判断f是否为目录，目录和文件是不同的概念。文件夹等于目录
				fileName(f.getAbsolutePath(),q,index+1);//不能index++或++index否则整个for循环的index都改变
		}
	}
	public static void main(String[] args) {
		Queue<String> q=new Queue<>();
		String path="E:\\test";
		fileName(path,q,0);
		int N=q.size();
		for(int i=0;i<N;i++)
			System.out.println(q.dequeue());
	}

}
