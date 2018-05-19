package sortapply;
/*从标准输入读入一列单词并打印出其中所以由两个单词组成的组合词*/
public class IsCombinationWord {
	public static void isCombineWord(String[] a){
		QuickSort<String> qs=new QuickSort<>(new LenComparator());
		qs.sort(a);
		qs.show(a);
		System.out.println("----------------------------------------------");
		int last=a.length-1;
		int combine=-1;
		int compara=a.length;
		int cancombine=a[last].length()/2;
		int cancompara=a[0].length()*2;
		
		for(int i=0;i<last;i++){
			if(a[i].length()>cancombine)
				break;
			else
				combine++;
		}
		
		for(int i=last;i>=0;i--){
			if(a[i].length()<cancompara)
				break;
			else
				compara--;
		}
		
		
		
	}
	public static void main(String[] args){
		String[] a={"afterthought","thought","after"};
		isCombineWord(a);
	}

}
