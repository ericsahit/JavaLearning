package com.samsung.sds.training.java.jiangwei;

public class ThreadSyn implements Runnable {

	@Override
	public void run()
	{
		// TODO Auto-generated method stub
		synchronized(this) 
		{
			 for (int i = 0; i < 5; i++)
	            { 
	                 System.out.println(Thread.currentThread().getName() + " synchronized loop " + i);
	            }
		}
           
	}

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		ThreadSyn tsy = new ThreadSyn(); 
        Thread ta = new Thread(tsy, "A"); 
        Thread tb = new Thread(tsy, "B"); 
        ta.start(); 
        tb.start(); 
	}

}
