package analysisalgor;

import java.util.Arrays;

/*为 StaticSETofInts （请见表 1.2.15） 添加一个实例方法 howMany()，
找出给定键的出现次数且在最坏情况下所需的运行时间应该和 log(N) 成正比。*/
public class StaticSetofInts {
	private int[] a;
	public StaticSetofInts(int[] keys){
		a=new int[keys.length];
		for(int i=0;i<a.length;i++)
			a[i]=keys[i];
		Arrays.sort(a);
	}
//	public boolean contains(int key){
//		return rank(key)!=-1;
//	}
	 public int rank(int key, int lo, int hi)
     {
         while (lo <= hi)
         {
             int mid = (hi - lo) / 2 + lo;
             if (key < this.a[mid])
                 hi = mid - 1;
             else if (key > this.a[mid])
                 lo = mid + 1;
             else
                 return mid;
         }
         return -1;
     }
	public int howMany(int key){
		int hi=this.a.length-1;
		int lo=0;
		return howMany(key,lo,hi);
	}
	private int howMany(int key,int lo,int hi){
		int mid=rank(key,lo,hi);
		if(mid==-1)
			return 0;
		else{
			return 1+howMany(key,lo,mid-1)+howMany(key,mid+1,hi);
		}
		
	}
	
	public static void main(String[] args) {
		int[] a={1,4,5,5,2,1,4,2,3,12,2,12};
		StaticSetofInts sta=new StaticSetofInts(a);
		System.out.println(sta.howMany(1));
	}
	

}
