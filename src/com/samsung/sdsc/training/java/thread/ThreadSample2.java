package com.samsung.sdsc.training.java.thread;

public class ThreadSample2 implements Runnable {

	@Override
	public void run() {
		System.out.println("线程" + Thread.currentThread().getName() + "运行成功。");
	}

	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			Thread mt = new Thread(new ThreadSample2());
			mt.start();
		}
	}
}
