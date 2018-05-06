package basics;
import java.util.Arrays;

/*删除重复元素。修改BinarySearch类中的测试用例来删去排序之后白名单中的所有重复元素。*/
import edu.princeton.cs.algs4.*;
public class Exam1_1_28 {
	//该方法用于对数组进行排序
	public static int[] sort(int[] a){
		for(int i=0;i<a.length;i++){
			for(int k=i+1;k<a.length;k++){
				int ch;
				if(a[i]>a[k]){
					ch=a[i];
					a[i]=a[k];
					a[k]=ch;
				}
			}
		}
		return a;
	}
	
	//该方法用于去除重复元素
	public static int[] dislodge(int[] a){
		int[] temp=new int[a.length];
		int t=0;
		for(int i=0;i<a.length;i++){
			boolean flg=true;
			for(int k=i+1;k<a.length;k++){
				if(a[i]==a[k]){
					flg=false;
					break;
				}
			}
			if(flg){
				temp[t]=a[i];
				t++;
			}
		}
		int[] newarr=new int[t];
		//将temp的数组复制到newarr
		System.arraycopy(temp, 0, newarr,0, t);
		return newarr;
	}
	
	public static void main(String[] args){
		int[] a={2,1,1,3,3,4,4,4,5};
		System.out.println("排序后的数组");
		System.out.println(Arrays.toString(sort(a)));
		System.out.println("去重后的数组");
		System.out.println(Arrays.toString(dislodge(sort(a))));
	}

}
