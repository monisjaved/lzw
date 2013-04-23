import java.io.*;
import java.util.*;

public class lzw
{
	static String[] dic = new String[1000];
	static int s;
	
	public static int check(String t,char a)
	{
		int i;
		int flag = 0;
		t = t+a;
		for(i=1;i<s;i++)
		{
			
			if(dic[i].equals(t))
			{
				flag = 1;
				break;
			}
		}
		return flag;
	}
	
	public static int find(String t)
	{
		int i;
		System.out.println("find string " + t);
		for(i=1;i<s;i++)
		{
			if(dic[i].equals(t))
			{
				System.out.println("found match at " + i);
				break;
			}
		}
		return i+1;
	}
	
	public static void compress1()
	{
		try
		{
			FileWriter fstream = new FileWriter("text.lzw");
			BufferedWriter outo = new BufferedWriter(fstream);
			FileWriter fstream1 = new FileWriter("dic.txt");
			BufferedWriter outd = new BufferedWriter(fstream1);
			Scanner in = new Scanner(System.in);
			char a;
			String t="";
			String x = new String();
			int l;
			int i=1;
			for(i=1;i<=256;i++)
			{
				a = (char)(i+1);
				dic[i] = Character.toString(a);
			}
			String test = in.nextLine();
			char[] te = test.toCharArray();
			i = 0;
			s = 256;
			t = Character.toString(te[i]);
			System.out.println(t);
			l = te.length - 1;
			while(i < l-1)
			{
				System.out.println("\nstring = "+t+" "+te[i+1]);
				if(check(t,te[i+1]) == 1)
				{
					t = t + te[++i];
					//System.out.println("t = " + t);
				}
				else
				{
					
					//System.out.println("a = " + t);
					x = String.valueOf(find(t));
					outo.write(x+ " ");
				}
				x = t + te[i+1];
				dic[s] = x;
				System.out.println("Added to dictionary = " + dic[s]+"\n");
				t = Character.toString(te[i+1]);
				i++;
				s++;
			}
			System.out.println("last = " + (find(dic[s-1])-1));
			for(i=1;i<s;i++)
			{
				outd.write(dic[i]);
				outd.newLine();
			}
			outo.close();
			outd.close();
			in.close();
		}
		catch(Exception e)
		{
			System.err.println(e.getMessage());
		}
	}
	
	public static void compress2(String file)
	{
		try
		{
			FileWriter fstream = new FileWriter("text.lzw");
			BufferedWriter outo = new BufferedWriter(fstream);
			FileWriter fstream1 = new FileWriter("dic.txt");
			BufferedWriter outd = new BufferedWriter(fstream1);
			System.out.println(file);
			Scanner in = new Scanner(new File(file));
			String test;
			char a;
			String t="";
			String x = new String();
			int l;
			int i=1;
			int m;
			for(i=1;i<=256;i++)
			{
				a = (char)(i+1);
				dic[i] = Character.toString(a);
			}
			s = 256;
			do
			{
				
				test = in.nextLine();
				char[] te = test.toCharArray();
				i = 0;
				t = Character.toString(te[i]);
				System.out.println(t);
				l = te.length - 1;
				while(i < l-1)
				{
					System.out.println("\nstring = "+t+" "+te[i+1]);
					if(check(t,te[i+1]) == 1)
					{
						t = t + te[++i];
						//System.out.println("t = " + t);
					}
					else
					{
						
						//System.out.println("a = " + t);
						x = String.valueOf(find(t));
						outo.write(x+ " ");
					}
					x = t + te[i+1];
					dic[s] = x;
					System.out.println("Added to dictionary = " + dic[s]+"\n");
					t = Character.toString(te[i+1]);
					i++;
					s++;
				}
				outo.write(String.valueOf(find(t)));
				System.out.println("last = " + (find(dic[s-1])-1));
				outo.write(" 1000 ");
			}
			while(in.hasNextLine() != false);
			for(i=1;i<s;i++)
			{
				outd.write(dic[i]);
				outd.newLine();
			}		
			outo.write("-1");
			outo.close();
			outd.close();
			in.close();
		}
		catch(Exception e)
		{
			System.err.println(e.getMessage());
		}
	}
	
	public static void decompress()
	{
		try
		{
			String[] dic1 = new String[1000];
			String test;
			Scanner in = new Scanner(new File("dic.txt"));
			int i = 1;
			while((test = in.nextLine()) != null)
			{
				System.out.println(test);
				dic1[i] = test;
				i++;
			}
			in.close();
			Scanner in1 = new Scanner(new File("text.lzw"));
			do
			{
				i = in1.nextInt();
				if(i == 1000)
				{
					System.out.print("\n");
					System.out.println(i);
					System.out.print(dic[i]);
				}
			}
			while(in.hasNextInt() != false);
			in1.close();
		}
		catch(Exception e)
		{
			System.err.println(e);
		}
	}
			
	
	public static void main(String args[])
	{
		try
		{
			int a;
			String x;
			Scanner re = new Scanner(System.in);
			do
			{
				System.out.println("\n----------------------------------------------\n");
				System.out.println("----------LZW PROGRAM BY MOONIS JAVED---------\n\n\n");
				System.out.println("*******MENU********\n\n");
				System.out.println("1.Compress a setence \n");
				System.out.println("2.Compress a file \n");
				System.out.println("3.Decompress \n");
				System.out.println("4.Exit \n");
				System.out.println("\nEnter the response number \n");
				a = re.nextInt();
				switch(a)
				{
					case 1:
						compress1();
						break;
						
					case 2:
						System.out.println("enter the filename \n");
						re.nextLine();
						x = re.nextLine();
						compress2(x);
						break;
						
					case 3:
						decompress();
						break;
						
					case 4:
						System.exit(0);
						
					default:
						System.out.println("Invalid response \n");
						break;
				}
			}
			while(a != 4);
		}
		catch(Exception e)
		{
			System.err.println(e.getMessage());
		}
	}
}