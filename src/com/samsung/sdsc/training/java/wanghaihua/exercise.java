package com.samsung.sdsc.training.java.wanghaihua;


/**
 * 
 * @author haihua
 *
 * 实现Runnable接口比继承Thread类所具有的优势：
	1）：适合多个相同的程序代码的线程去处理同一个资源
	2）：可以避免java中的单继承的限制
	3）：增加程序的健壮性，代码可以被多个线程共享，代码和数据独立。
 *
 */
public class exercise {
	
	
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			//joinTest();
			//cpuTest();
			//mutexText();
			
			Factory factory = new Factory();
			
			new Thread(new Producer(factory)).start();
			new Thread(new Consumer(factory)).start();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 */
	private static void joinTest() throws Exception {
		ThreadDemo demo = new ThreadDemo();
		Thread th = new Thread(demo);
		Thread.sleep(1000); //在休眠时是否应该把cpu让出去？每个循环都要征用cpu时间片？
		//th.setDaemon(true);
		th.start();
		for (int i = 0; i < 50; i++) {
			if (i > 10) {
				th.join(); //等待th线程执行完毕，继续执行main线程
				
				//th.interrupt();
			}
			System.out.println("Main线程执行-->" + i);
		}
	}
	
	private static void cpuTest() throws Exception {
		ThreadDemo demo = new ThreadDemo();
		Thread th = new Thread(demo);
		Thread th2 = new Thread(demo);
		Thread th3 = new Thread(demo);
		th.start();
		th2.start();
		th3.start();
		
		Thread.sleep(1000 * 10);
		th.stop();
		th2.stop();
		th3.stop();
	}
	
	private static void mutexText() {
		ThreadDemo demo = new ThreadDemo();
		Thread th = new Thread(demo);
		Thread th2 = new Thread(demo);
		Thread th3 = new Thread(demo);
		th.start();
		th2.start();
		th3.start();
	}



	@Override
	public String toString() {
		return Thread.currentThread().getName();
	}
	
	

}
