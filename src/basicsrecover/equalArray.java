package basicsrecover;
import java.util.*;
public class equalArray
{
	/*
	 * 题目：判断某个数组是否可以等分，其中分割数组的某个元素不算在内。
	 * 比如：{2，5，1，1，1，1，4，7，5，2，1，7}
	 * 可以四等分成{2,5}{1,1,1,4}{5,2}{7}
	 */
	public static boolean ifexplain(int [] input)
	{
		int sum = 0;
		int later = 0;
		boolean res = true;
		if(input.length < 3)
		{
			res = false;
		}
		else
		{
			for(int i = 0; i < input.length-2; i ++ )
			{
				sum = sum + input[i];//从第一个数据到第i个数据的和
				for(int j = i + 2;j < input.length; )
				{
					later = later + input[j];
					if(later < sum)
					{
						if(j == input.length-1)
						{
							res = false;
						}
						else
							j++;
					}
					else if(later == sum)
					{
						later = 0;
						res = true;
						j=j+2;//隔离下一个数据，从下下个数据开始计算
						}
					else if(later > sum)
					{
						res = false;
						later=0;
						break;
					}
				}
				if(res)
					break;
			}
		}
		System.out.println(res);
		return res;
	}
	public static void main(String []args)
	{
		/*Scanner sc = new Scanner(System.in 

);
		int size = 0;
		size = sc.nextInt();
		int [] input = new int [size];
		for( int i = 0; i < input.length ; i++)
		{
			input [i] = sc.nextInt();
		}*/
		int input[] = {2,5,1,1,1,1,4,7,5,2,1,7};
		boolean res = ifexplain(input);
		if(res)
		{
			System.out.println("This array can be explained!");
		}
		else
			System.out.println("This array can not be explaned!");
	}
}

