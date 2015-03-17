package com.samsung.sdsc.training.java.zhuli;

public class GrammerTest {
	
	public static  void main(String[] args)
	
	{
		
		for(int i=1;i<=5;i++)
		{
			if (i==2)
			{
				continue;
				//break;
			
			}
			
			//System.out.println(i);
			
			
			if(i==3)
			{
				switch(i)
				{
				case 1:
					System.out.println(i);
					break;
				case 2:
					System.out.println(i);	
					break;
				case 3:
					System.out.println(i);
					break;
				default:
					System.out.println(i);
					break;
				}
				return;
			}
			 
			
			 
		}
		
	}

}
