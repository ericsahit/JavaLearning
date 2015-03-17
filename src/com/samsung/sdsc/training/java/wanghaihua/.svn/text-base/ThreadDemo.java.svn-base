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
public class ThreadDemo implements Runnable {
	
	private int count = 5;
	
	public void run() {
		//runTest();
		//cpuTest();
		mutexTest();
	}
	
	private void runTest() {
		for (int i = 0; i < 30; i++) {
			if (count > 0) {
				System.out.println(Thread.currentThread().getName() + "-->" + i);
				if (i == 20) {
					System.out.println("yeild");
					//Thread.yield();
				}
			}
		}
	}
	
	private void cpuTest()
	{
		while (true) {
			System.out.println(Thread.currentThread().getName() + "-->");
		}
	}
	
	private void mutexTest() {
		for (int i = 0; i < 10; i++) {
			if (count > 0) {
				try {
					Thread.sleep(1000);
				} catch (Exception e) {
					e.printStackTrace();
				}
				System.out.println("-->" + count--);
			}
		}
	}
}
