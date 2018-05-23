package sortapply;
/*在线型对数时间内计算两组排列之间的Kendall tau距离*/
import sort.Inversion;;
public class KendallTau {
	public static long caculateTau(int[] ori,int[] compar){
		if(ori.length!=compar.length)
			throw new IllegalArgumentException();
		
		//用于存放ori数组元素的位置,已经有序
		int[] a=new int[ori.length];
		for(int i=0;i<ori.length;i++){
			a[ori[i]]=i;
		}
		
		//这个做法相当于将原来有序的数组打乱了，需要重新排序
		Integer[] b=new Integer[ori.length];
		for(int i=0;i<ori.length;i++){
			b[i]=a[compar[i]];
		}
		return Inserations.count(b);
	}
	
	public static void main(String[] args) {
		int[] a={0,3,1,6,2,5,4};
		int[] b={1,0,3,6,4,2,5};
		//0-1,3-1,2-4,5-4这4对数在两组排列中的相对顺序不同，结果应为4
		System.out.println(caculateTau(a, b));
	}
}
