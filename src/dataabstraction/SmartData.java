package dataabstraction;
/*根据Date的API实现一个SmartDate类型,在日期非法时抛出一个异常,
 * 并为SmartDate添加一个方法dayOfTheWeek(),为日期中每周的日返回Monday到Sunday中的适当值.
 * (可以假定时间是21世纪).此题可了解构造函数直接如何调用,闰年的判断,
 * 蔡勒公式判断星期以及抛出异常的使用~ 此题重要！*/
public class SmartData {
	private int day;
	private int month;
	private int year;
	
	public SmartData(int year,int month,int day){
		if(!isIllegal(year,month,day))
			throw new IllegalArgumentException("Illegal data!!");
		else{
			this.year=year;
			this.month=month;
			this.day=day;
		}
	}
	
	public SmartData(String date){
		String[] fields=date.split("/");//使用”/“分隔符切分字符串
		if(fields.length!=3)
			throw new IllegalArgumentException("Illegal data!!please split by\"/\"");
		int m=Integer.parseInt(fields[1]);
        int d=Integer.parseInt(fields[2]);
        int y=Integer.parseInt(fields[0]);
//        this(y,m,d);不可行,必须要写在第一行,因此此处不可用
        if(!isIllegal(y,m,d))
            throw new IllegalArgumentException("illegal date!!");
        else
        {
            year=y;
            month=m;
            day=d;
        }    
	}
	
	//判断日期是否合法
	private  static boolean isIllegal(int y,int m,int d){
		if(y<1||m<1||m>12||d<1||d>31)
			return false;
		int[] monthofday={0,31,-1,31,30,31,30,31,31,30,31,30,31};
		if(isLeapYear(y))
			monthofday[2]=29;
		else
			monthofday[2]=28;
		if(monthofday[m]<d)
			return false;
		return true;
	}
	
	//判断是否为闰年
	private static boolean isLeapYear(int year){
		if(year%100!=0&&year%4==0)
			return true;
		else if(year%100==0&&year%400==0)
			return true;
		else
			return false;
	}
	
	//判断改日期是星期几用蔡勒公式计算
	public String dayOfTheWeek(){
		if(month==1||month==2){
			month+=12;
			year-=1;
		}
		int w=(year%100+(year%100/4)+year/400-2*(year/100)+(26*(month+1)/10)+day-1);
		if(w>=0)
			w=w%7;
		//注意对负数的取模运算
		else
			w=(w%7+7)%7;
		switch(w){
		case 1:
			return "Monday";
		case 2:
			return "Tuesday";
		case 3:
			return "Wednesday";
		case 4:
			return "Thursday";
		case 5:
			return "Friday";
		case 6:
			return "Saturday";
		default:
			return "Sunday";
		}
	}
	public int year(){
		return year;
	} 
	public int month(){
		return month;
	} 
	public int day(){
		return day;
	}
	public String toString(){
		return year()+"/"+month()+"/"+day();
	}
	public static void main(String[] args) {
		SmartData sd=new SmartData(2018,3,5);
		System.out.println(sd.dayOfTheWeek());;
	}

}
