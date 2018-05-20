package sortapply;

import java.util.Scanner;

/*公正的选举。*/
public class California {
	public static void main(String[] args){
		Conform c=new Conform();
		QuickSort<String> qs=new QuickSort<>(c);
		Scanner in=new Scanner(System.in);
		int N=in.nextInt();
		String[] str=new String[N];
		for(int i=0;i<N;i++){
			str[i]=in.next().toUpperCase();
		}
		qs.sort(str);
		for(int i=0;i<N;i++){
			System.out.println(str[i]);
		}
	}
}
