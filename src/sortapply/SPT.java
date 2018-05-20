package sortapply;

import java.util.Arrays;
import java.util.Scanner;

/*调度。从标准输入中读取任务的名称和所需的运行时间，用最短处理时间优先的原则打印出一份调度计划
 * 使得任务完成的平均时间最小*/
public class SPT {
	public static void main(String[] args){
		Scanner in=new Scanner(System.in);
		String n=in.nextLine();
		int N=Integer.parseInt(n);
		Job[] jobs=new Job[N];
		for(int i=0;i<N;i++){
			String name=in.nextLine();
			double time=Double.parseDouble(in.nextLine());
			jobs[i]=new Job(name,time);
		}
		Arrays.sort(jobs);
		for(int i=0;i<N;i++){
			System.out.println(jobs[i]);
		}
	}

}
