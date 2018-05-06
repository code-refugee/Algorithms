package test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
/*给定一个整数数列，找出其中和为特定值的那两个数。

你可以假设每个输入都只会有一种答案，同样的元素不能被重用。

示例:

给定 nums = [2, 7, 11, 15], target = 9

因为 nums[0] + nums[1] = 2 + 7 = 9
所以返回 [0, 1]*/
public class Test {
	public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
   for (int i = 0; i < nums.length; i++) {
       map.put(nums[i], i);
   }
   for (int i = 0; i < nums.length; i++) {
       int complement = target - nums[i];
       if (map.containsKey(complement) && map.get(complement) != i) {
           return new int[] { i, map.get(complement) };
       }
   }
   throw new IllegalArgumentException("No two sum solution");
   }
//	public int brinSearch(int[] nums,int key){
//		return brinSearch(nums,key,0,nums.length-1);
//	}
//	private int brinSearch(int[] nums,int key,int lo,int hi){
//		if(lo>hi)
//			return -1;
//		int mid=lo+(hi-lo)/2;
//		if(key>nums[mid])
//			return brinSearch(nums,key,mid+1,hi);
//		else if(key<nums[mid])
//			return brinSearch(nums,key,lo,mid-1);
//		else
//			return mid;
//	}
	public static void main(String[] args) {
//		Integer a=100;
//		Integer b=100;
//		if(a==b)
//			System.out.println("=");
//		else
//			System.out.println("!=");
//		
//	
//		}
		StringBuffer a=new StringBuffer();
		Scanner in=new Scanner(System.in);
		for(int i=0;i<10;i++){
			a.append(in.nextLine());
			}
		System.out.println(a.reverse());
		}
			
//		int y=6,x=1;
//		while(y--){
//			x--;}
//		}
	
	

}
