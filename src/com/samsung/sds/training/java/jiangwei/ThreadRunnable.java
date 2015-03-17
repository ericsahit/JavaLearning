package com.samsung.sds.training.java.jiangwei;

public class ThreadRunnable implements Runnable {

	@Override
	public void run() 
	{
		// TODO Auto-generated method stub
		while(true)
		{
			System.out.println(Thread.currentThread().toString()+" Running");
		}

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
/*		ThreadRunnable T = new ThreadRunnable();
		Thread Barney = new Thread(T);
		Thread Fred = new Thread(T);
		Barney.start();
		Fred.start();	*/
//		Thread Jul = new Thread(T);
		ExtThread Fir = new ExtThread();
		ExtThread Sec = new ExtThread();
		Thread.currentThread().setPriority(10);
		Fir.start();
		Sec.start();
		
		try
		{
			Thread.sleep(1000 * 5);
		}
		catch(InterruptedException e)
		{		
			System.out.println(e.toString());
		}
		Fir.stop();
		Sec.stop();
		System.out.println(Fir.count + Sec.count + " Switches in 5 seconds.");	
	}

	public static void startNewThread()
	{
		ExtThread Jul = new ExtThread();
		Jul.start();
		Jul.setPriority(6);
	}
	

}

class ExtThread extends Thread{
	int count = 0;
	static ExtThread storage = null;
	public void run()
	{
		while(true)
		{
			//Get [name,, priority, group]
			//System.out.println(this.toString()+" Running");
			ExtThread.storage = this;
			count ++;
		}
	}
}
