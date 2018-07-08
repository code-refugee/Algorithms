package balanced_search_tree;

public class TestRB {
	public static void main(String[] args) {
		String test="S E A R C H E X A M P L E";
		String[] keys=test.split("\\s+");
		RedBlackBST<String,Integer> st=new RedBlackBST<>();
		System.out.println("Now test is "+st.getClass().getName()+":");
		System.out.println();
		for(int i=0;i<keys.length;i++)
			st.put(keys[i], i);
		
		System.out.println("size= "+st.size());
		System.out.println("min= "+st.min());
		System.out.println("max= "+st.max());
		System.out.println();
		
		System.out.println("Testing keys()");
		System.out.println("--------------------------------");
		for(String s:st.keys())
			System.out.println(s+" "+st.get(s));
		System.out.println();
		
		System.out.println("Testing select");
		System.out.println("--------------------------------");
		for(int i=0;i<=st.size();i++)
			System.out.println(i+" "+st.select(i));
		System.out.println();
		
		System.out.println("key rank floor ceil");
		System.out.println("--------------------------------");
		for(char i='A';i<='Z';i++){
			String s=i+"";
			System.out.printf("%2s %4d %4s %4s\n",s,st.rank(s),st.floor(s),st.ceiling(s));	
		}
		System.out.println();
		
		String[] from={ "A", "Z", "X", "O", "B", "C" };
		String[] to={ "Z", "A", "X", "Z", "G", "L" };
		System.out.println("range search");
		System.out.println("--------------------------------");
		for(int i=0;i<from.length;i++){
			System.out.printf("%s-%s (%2d):",from[i],to[i],st.size(from[i], to[i]));
			for(String s:st.keys(from[i], to[i]))
				System.out.print(s+" "+st.get(s)+";");
			System.out.println();
		}
		System.out.println();
		
		for(int i=0;i<st.size()/2;i++){
			st.deleteMin();
		}
		System.out.println("After deleting the smallest " + st.size() / 2 + " keys");
		System.out.println("--------------------------------");
		for(String s:st.keys())
			System.out.println(s+" "+st.get(s));
		System.out.println();
		
		while(!st.isEmpty()){
			st.delete(st.select(st.size()/2));
		}
		System.out.println("After deleting the remaining keys");
		System.out.println("--------------------------------");
		for(String s:st.keys())
			System.out.println(s+" "+st.get(s));
		System.out.println();
		
		System.out.println("After adding back N keys");
		System.out.println("--------------------------------");
		for(int i=0;i<keys.length;i++)
			st.put(keys[i], i);
		for(String s:st.keys())
			System.out.println(s+" "+st.get(s));
		System.out.println();
		
	}
}
