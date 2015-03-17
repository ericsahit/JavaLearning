package com.samsung.sds.training.java.jiangwei;

public  class ThreadTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
/*		String S="Jesse" + " do a test!";
		String SBuffer = new StringBuffer().append("Jesse").append(" do a test!").toString();
		System.out.print(S);
		System.out.print("\n");
		System.out.print(SBuffer);
*/		
		//当一个线程访问object的一个synchronized(this)同步代码块时，另一个线程仍然可以访问该object中的非synchronized(this)同步代码块。
		final ThreadTest testThread = new ThreadTest();  
        Thread t1 = new Thread(  new Runnable() {  public void run() {  testThread.m4t1();  }  }, "t1"  ); 
        Thread t2 = new Thread(  new Runnable() {  public void run() { testThread.m4t2();   }  }, "t2"  ); 
        t1.start(); 
        t2.start(); 
//        final ThreadNew newThread = new ThreadNew();
//        Thread t3 = new Thread(  new Runnable() {  public void run() { newThread.m4t3();   }  }, "t3"  ); 
//        t3.start();
		
	}
	
	  public void m4t1() 
	  { 
          synchronized(this)
          { 
               int i = 20; 
               while( i-- > 0)
               { 
                    System.out.println(Thread.currentThread().getName() + " : " + i); 
                    try
                    { 
 //                        Thread.sleep(1/100); 
                         Thread.sleep(500); 
                    } 
                    catch (InterruptedException ie) 
                    { 
                    	System.out.println(ie.toString());
                    } 
               } 
          } 
     } 
     public void m4t2()
     { 
          int i = 20; 
          while( i-- > 0)
          { 
               System.out.println(Thread.currentThread().getName() + " : " + i); 
               try 
               { 
            	  Thread.sleep(500);   //t1 : 4, t2 : 4, t2 : 3, t1 : 3,... ...t1 : 0,t2 : 0
                //    Thread.sleep(1000); 
               } 
               catch (InterruptedException ie)
               { 
            	   System.out.println(ie.toString());
               } 
          } 
     } 

}

class ThreadNew
{
    public synchronized void m4t3()
    { 
         int i = 20; 
         while( i-- > 0)
         { 
              System.out.println(Thread.currentThread().getName() + " : " + i); 
              try 
              { 
            	  Thread.sleep(500); 
              } 
              catch (InterruptedException ie)
              { 
            	  System.out.println(ie.toString());
              } 
         } 
    } 

}


