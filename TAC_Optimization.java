
import java.util.*;
/*
sample input and output
 
Enter the maximum number of  expressions:5
enter 5 expressions as op op1 op2 res
+ 4 2 t1
+ a t1 t2
- b a t3
+ a 6 t4
+ t3 t4 t5
Optimised code is :
+ a 6 t2
- b a t3
+ t3 t2 t5

*/
public class TAC_Optimization
{
	public static int n=5;
	public static Expr a[];
	public TAC_Optimization() {
		a=new Expr[n];
		for(int i=0;i<n;i++) {
			a[i]=new Expr();
		}
	}
	public static void main(String args[]) throws Exception
	{
		TAC_Optimization p=new TAC_Optimization();
		p.input();
		p.constant();
		p.expression();
		p.output();
	}
	public void input()
	{
		Scanner sc=new Scanner(System.in);
		System.out.print("Enter the maximum number of  expressions:");
		n=sc.nextInt();
		System.out.println("enter "+n+" expressions as op op1 op2 res");
		for(int i=0;i<n;i++) {
			a[i].op=sc.next();
			a[i].op1=sc.next();
			a[i].op2=sc.next();
			a[i].res=sc.next();
			a[i].flag=0;
		}
	}
	public void constant()
	{
		int i,op1,op2,res=0;
		char op;
		for(i=0;i<n;i++)
		{
			if(Character.isDigit((a[i].op1).charAt(0)) && Character.isDigit((a[i].op2).charAt(0)))
			{
				op1=Integer.parseInt(a[i].op1);
				op2=Integer.parseInt(a[i].op2);
				op=a[i].op.charAt(0);
				if(op=='+')
				res=op1+op2;
				else if(op=='-')
				res=op1-op2;
				else if(op=='*')
				res=op1*op2;
				else if(op=='/')
				res=op1/op2;
				String res1=Integer.toString(res);
				a[i].flag=1;
				change(i,i,res1);
			}
		}
	}
	public void expression()
	{
		int i,j;
		for(i=0;i<n;i++)
		{
			for(j=i+1;j<n;j++)
			{
				if(a[i].op.equals(a[j].op))
				{
					if(a[i].op.equals("+") || a[i].op.equals("*"))
					{
						if(a[i].op1.equals(a[j].op1) && a[i].op2.equals(a[j].op2))
						{
							a[j].flag=1;
							change(i,j,"");
						}
					}
				}
				else
				{
					if(a[i].op1.equals(a[j].op1) && a[i].op2.equals(a[j].op2))
					{
					a[j].flag=1;
					change(i,j,"");
					}
				}
			}
		}
	}
	public  void output()
	{
		int i;
		System.out.println("Optimised code is :");
		for(i=0;i<n;i++)
		{
			if(a[i].flag==0)
				System.out.println(a[i].op+" "+a[i].op1+" "+a[i].op2+" "+a[i].res);
		}
	}
	public  void change(int p,int q,String res)
	{
		int i;
		for(i=q+1;i<n;i++)
		{
			if(a[q].res.equals(a[i].op1))
			{
				if(res.length()==0)
					a[i].op1=a[p].res;
				else
					a[i].op1=res;
			}
			else if(a[q].res.equals(a[i].op2))
			{
				if(res.length()==0)
					a[i].op2=a[p].res;
				else
					a[i].op2=res;
			}
		}
	}
}
class Expr
{
	public String op,op1,op2,res;
	public int flag=0;
	public Expr() {
		
	}
	public Expr(String op,String op1,String op2,String res,int flag)
	{
		this.op=op;
		this.op1=op1;
		this.op2=op2;
		this.res=res;
		this.flag=flag;
	}
}