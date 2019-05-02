package temp;

//REGISTER ALLOCATION - GRAPH COLORING
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;
public class GraphColoringAlgorithmForRegisterAllocation
{
	static Stack<Integer> s=new Stack<Integer>();
	static int m,n;
	public static void main(String args[])
	{
		long b=System.currentTimeMillis();
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter number of colours");
		m=sc.nextInt();
		System.out.println("Enter number of vertices");
		n=sc.nextInt();
		int g[][]=new int[n][n];
		int c[]=new int[n];
		int d[]=new int[n];
		for(int i=0;i<n-1;i++)
		{
			c[i]=0;
			g[i][i]=0;
			System.out.println("Enter 1 if they have edge else 0");
			for(int j=i+1;j<n;j++)
			{
				System.out.println(i+" and "+j);
				g[i][j]=sc.nextInt();
				g[j][i]=g[i][j];
			}
		}
		for(int i=0;i<n;i++)
		{
			d[i]=0;
			for(int j=0;j<n;j++)
				d[i]+=g[i][j];
		}
		RA(g,c,d);
		long e=System.currentTimeMillis();
		System.out.println((e-b)*0.001);
	}
	public static void RA(int g[][],int c[],int d[])
	{
		int v[]=new int[n];
		int ad[][]=new int[n][n];
		for(int i=0;i<n;i++)
		{
			v[i]=0;
			for(int j=0;j<n;j++)
			ad[i][j]=g[i][j];
		}
		for(int k=0;k<n-1;k++)
		{
			int mi=-1,mx=999;
			for(int i=0;i<n;i++)
			{
				if(d[i]<mx && d[i]>0)
				{
					mx=d[i];
					mi=i;
				}
			}
			s.push(mi);
			v[mi]=1;
			d[mi]=0;
			for(int j=0;j<n;j++)
			{
				ad[mi][j]=0;
				ad[j][mi]=0;
			}
			for(int i=0;i<n;i++)
			{
				d[i]=0;
				for(int j=0;j<n;j++)
					d[i]+=ad[i][j];
			}
		}
		for(int i=0;i<n;i++)
		{
			if(v[i]==0)
			{
				s.push(i);
				break;
			}
		}
		for(int i=0;i<n;i++)
		{
			int j=(int)s.pop();
			nV(j,c,g);
		}
		for(int i=0;i<n;i++)
			System.out.println(i+" "+c[i]);
	}
	public static void nV(int j,int c[],int g[][])
	{
		int i;
		c[j]=1;
		while(true)
		{
				for(i=0;i<n;i++)
				{
					if(g[i][j]==1  && c[j]==c[i])
					break;
				}
				if(i==n)
					return;
				c[j]=c[j]%m;
				c[j]++;
		}
	}
}
