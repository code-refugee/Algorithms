package symbol_table;

import java.util.Scanner;

/*从标准输入读取一系列字母成绩，计算并打印（字母成绩对应的分数的平均值）*/
public class GPA {
	public static void main(String[] args) {
		SequentialSearchST<String,Double> st=new SequentialSearchST<>();
		
		//初始化，将字母成绩和数组分数的对应关系存入符号表中
		st.put("A+", 4.33);
		st.put("A", 4.00);
		st.put("A-", 3.67);
		st.put("B+", 3.33);
		st.put("B", 3.00);
		st.put("B-", 2.67);
		st.put("C+", 2.33);
		st.put("C", 2.00);
		st.put("C-", 1.67);
		st.put("D", 1.00);
		st.put("F", 0.00);
		
		int n=0;
		double total=0.0;
		System.out.println("请输入成绩");
		Scanner sc=new Scanner(System.in);
		for(n=0;!sc.hasNext("0");n++){
			String grade=sc.next();
			double value=st.get(grade);
			total+=value;
		}
		double gpa=total/n;
		System.out.printf("GPA is:%.2f\n",gpa);
	}
}
