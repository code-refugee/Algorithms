package sortapply;

import java.util.Comparator;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/*快速排序*/
public class QuickSort<Item>{
	private Comparator<Item> comparator;
	
	public QuickSort(){
		comparator=null;
	}
	
	public QuickSort(Comparator<Item> comparator){
		this.comparator=comparator;
	}
	
	//这是一个静态常量，用于指定何时该调用插入排序
	private final int INSERATION_NUM=8;
	
	/*这是一个公有的sort方法，该方法指定了一个形参,
	 * 方法体内调用了sort方法的重写。*/
	public  void sort(Item[] a){
		
		//这个排序算法使用了三取样切分，因此不需要随机打乱数组了
		sort(a,0,a.length-1);
		
		//使用断言判断数组是否已经排序
		assert isSorted(a);
	}
	
	/*这是一个私有的sort方法，该方法指定了三个形参。*/
	private  void sort(Item[] a,int lo,int hi){
		
		//若左边界大于右边界，则不需要操作，直接return
		if(hi<lo)
			return;
		
		//数组大小
		int n=hi-lo+1;
		
		/*数组大小若小于或等于指定的常数则调用插入排序，
		 * 因为小数组插入排序比较快*/
		if(n<=INSERATION_NUM){
			inserationSort(a,lo,hi);
			return;
		}
		
		/*k为切分元素，a[lo...k-1]小于等于a[k]，
		 * a[k+1...hi]大于等于a[k]*/
		int k=partition(a,lo,hi);
		
		//递归调用有三个形参的sort方法直到数组有序
		sort(a,lo,k-1);
		sort(a,k+1,hi);
	}
	
	//该方法用于计算切分的元素
	private  int partition(Item[] a,int lo,int hi){
		
		//数组中元素的个数
		int n=hi-lo+1;
		
		//利用三取样切分，不需要打乱数组
		int m=median3(a,lo,lo+n/2,hi);
		exch(a,lo,m);
		int i=lo,j=hi+1;
		Item t=a[lo];
		while(less(a[++i],t)){
			if(i==hi){
				exch(a,lo,hi);
				return hi;
			}
				
		}
		while(less(t,a[--j])){
			//只需要j=lo+1就可以返回值，因为自己与自己比肯定相等
			if(j==lo+1)
				return lo;
		}
		
		//因为上面两个while循环以经做过了++i和--j，且两个数进行了交换所以数组永远不会越界
		while(i<j){
			exch(a,i,j);
			while(less(a[++i],t));
			while(less(t,a[--j]));
		}
		exch(a,lo,j);
		return j;
	}
	
	/*这是一个插入排序inserationSort有3个形参*/
	private  void inserationSort(Item[] a,int lo,int hi){
		//该变量用来记录交换的元素的数量
		int exchange=0;
		
		/*这个for循环有两个作用。第一：将最小的元素放到第一位充当哨兵；
		 * 第二：用于检测数组是否有序，如果exchange=0，则表示数组有序*/
		for(int i=hi-1;i>=lo;i--){
			if(less(a[i+1],a[i])){
				exch(a,i+1,i);
				exchange++;
			}
		}
		
		//如果没有交换元素说明该数组本来就是有序的，不需要进一步排序
		if(exchange==0)
			return;
		
		/*如果数组无序则需要进一步做插入排序，该插入排序与原始插入排序不同
		 * 这里采用了数组平移的思想而不是数与数两两交换速度更快*/
		for(int i=lo+1;i<=hi;i++){
			Item v=a[i];
			int j=i;
			
			/*得益于上一个for循环，最小的数放在了a[1]充当了哨兵这里无需再做
			 * j>0的边界判断，加快了速度*/
			while(less(v,a[j-1])){
				a[j]=a[j-1];//此处实现的是数组的平移
				j--;
			}
			a[j]=v;
		}
		
	}
	
	//三取样切分，该方法用于在三个数中找出中位数
	private  int median3(Item[] a,int i,int j,int k){
		return less(a[i],a[j])?
				(less(a[j],a[k])?j:(less(a[i],a[k])?k:i)):
					(less(a[i],a[k])?i:(less(a[k],a[j])?j:k));
	}
	//该方法用于比较两个数的大小，返回一个boolean值
	private boolean less(Item v,Item w){
		if(comparator!=null)
			return comparator.compare(v, w)<0;
		else
			return ((Comparable)v).compareTo(w)<0;
	}
	
	//该方法用于交换数组内下标为i和j的值
	private void exch(Item[] a,int i,int j){
		Item t=a[i];
		a[i]=a[j];
		a[j]=t;
	}
	
	//该方法用于检测数组是否已经有序
	private boolean isSorted(Item[] a){
		for(int i=1;i<a.length;i++){
			if(less(a[i],a[i-1]))
				return false;
		}
		return true;
	}
	
	//用于输出数组中的元素
	public void show(Item[] a){
		for(int i=0;i<a.length;i++){
			StdOut.println(a[i]);
		}
	}
	
	public static void main(String[] args) {
		Integer[] a={3,2,1,50,45,26,11,32,59,211,114,256,52,16,18};
		QuickSort<Integer> q=new QuickSort<>();
		q.sort(a);
		q.show(a);
	}

}
