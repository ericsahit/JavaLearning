package me.ericsahit.java.dp;

import java.util.concurrent.TimeUnit;

public final class SingletonTest {
	
	public void test() {
		Singleton instance = Singleton.getInstance();
	}
	
	public static void main(String[] args) throws Exception {
		
		byte[] arr = new byte[1024*1024*64];
		
		TimeUnit.SECONDS.sleep(30);
	}
}

class Singleton {
	
	private static volatile Singleton instance = null;
	
	private static Object lockObj = new Object();
	
	private Singleton() {
		
	}
	
	public static Singleton getInstance() {
		if (instance == null) {
			synchronized (lockObj) {
				if (instance == null) {
					instance = new Singleton();
				}
			}
		}
		return instance;
	}
	
}
