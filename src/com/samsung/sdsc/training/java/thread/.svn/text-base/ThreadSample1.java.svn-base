package com.samsung.sdsc.training.java.thread;

public class ThreadSample1 extends Thread {
	public ThreadSample1(String name) {
		setName(name);
	}

	@Override
	public void run() {
		System.out.println("线程" + getName() + "运行成功。");
	}

	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			ThreadSample1 mt = new ThreadSample1("线程" + i);
			mt.start();
		}
	}
}
