package com.samsung.sdsc.training.java.wanghaihua;

public class Producer implements Runnable {
	
	private Factory factory;
	
	public Producer(Factory factory) {
		
		if (factory == null) throw new NullPointerException();
		this.factory = factory;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			Product product = new Product("produnction01");
			factory.push(product);
			System.out.println("push product " + product.toString());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
