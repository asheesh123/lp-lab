import java.util.*;
public class BinToDecUsingSDT
{
	static String in="";
	public static void main(String args[]){
		long b=System.currentTimeMillis();
		System.out.println("Enter the binary data");
		Scanner sc=new Scanner(System.in);
		String in=sc.next();
		if(!(in.contains(".")))
			in+=".0";
		N n=new N(in);
		System.out.println("Value is "+n.val);
		System.out.println("Count is "+n.cnt);
		long e=System.currentTimeMillis();
		System.out.println("execution time in ms: "+(e-b)*0.001);
	}
}
class N{
	int cnt=0;
	double val=0.0;
	N(String s){
		String v1="",v2="";
		int i=0;
		while((s.charAt(i)+"").compareTo(".")!=0){
			v1+=s.charAt(i)+"";
			i++;
		}
		L l1=new L(v1);
		i++;
		while(i<s.length()){
			v2+=s.charAt(i)+"";
			i++;
		}
		L l2=new L(v2);
		cnt=l1.cnt+l2.cnt;
		val=l1.val+(l2.val*Math.pow(2,-1*l2.cnt));
	}
}
class L{
	int cnt=0;
	double val=0.0;
	L(String s){
		if(s.length()==1){
			B b=new B(s);
			val=b.val;
			cnt=b.cnt;
		}
		else{
			String s1=s.charAt(s.length()-1)+"";
			StringBuilder sb=new StringBuilder(s);
			sb.deleteCharAt(s.length()-1);
			s=sb.toString();
			L l1=new L(s);
			B b=new B(s1);
			val=2*l1.val+b.val;
			cnt=l1.cnt+b.cnt;
		}
	}
}
class B{
	int cnt=0;
	double val=0.0;
	B(String s){
		cnt=1;
		val=Double.parseDouble(s.charAt(0)+"");
	}
}