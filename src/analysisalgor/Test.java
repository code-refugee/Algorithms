package analysisalgor;

import java.util.Scanner;

/*测试类，只用于测试*/
public class Test {
	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
        long res;           
        int _from_size = 0;
        _from_size = Integer.parseInt(in.nextLine().trim());
        int[] _from = new int[_from_size];
        int _from_item;
        for(int _from_i = 0; _from_i < _from_size; _from_i++) {
            _from_item = Integer.parseInt(in.nextLine().trim());
            _from[_from_i] = _from_item;
        }
        
        int _to_size = 0;
        _to_size = Integer.parseInt(in.nextLine().trim());
        int[] _to = new int[_to_size];
        int _to_item;
        for(int _to_i = 0; _to_i < _to_size; _to_i++) {
            _to_item = Integer.parseInt(in.nextLine().trim());
            _to[_to_i] = _to_item;
        }
        for(int i=0;i<_from_size;i++)
        	System.out.println(_from[i]);
        for(int i=0;i<_to_size;i++)
        	System.out.println(_to[i]);
	}

}
