package com.samsung.sdsc.training.java.wanghaihua;

public class Consumer implements Runnable {
	
	private Factory factory;
	
	public Consumer(Factory factory) {
		if (factory == null) throw new NullPointerException();
		this.factory = factory;
	}

	@Override
	public void run() {
		while (true) {
			Product product = factory.pop();
			System.out.println("pop product " + product.toString());
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
