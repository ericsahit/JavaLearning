package com.samsung.sdsc.training.java.thread;

public class Vender implements Runnable {
	private String name;
	private String type;
	private boolean ready = true;// 是否可以继续生产

	public Vender(String name, String type) {
		this.name = name;
		this.type = type;
	}

	@Override
	public void run() {
		while (true) {
			if (this.ready) {
				if (needProduce()) {
					makeAnApple();
					this.ready = false;
				} else {
					waitForOrder();
				}
			} else {
				haveARest();
				this.ready = true;
			}
		}
	}

	private boolean needProduce() {
		synchronized (Market.PRODUCT_LIST) {
			return Market.PRODUCT_LIST.size() < 10;
		}
	}

	private void makeAnApple() {
		synchronized (Market.PRODUCT_LIST) {
			Apple apple = new Apple(type);
			if (Market.PRODUCT_LIST.size() < 10) {
				Market.PRODUCT_LIST.add(apple);
				System.err.println(this.name + " 制造了一个 " + this.type + " 并卖给了市场，当前市场上共有 " + Market.PRODUCT_LIST.size()
						+ " 个苹果");
			} else {
				System.err.println(this.name + " 制造了一个 " + this.type + "，但是市场已经饱满了，只好自个把苹果吃了，当前市场上共有 "
						+ Market.PRODUCT_LIST.size() + " 个苹果");
			}
			Market.PRODUCT_LIST.notifyAll();
		}
	}

	private void waitForOrder() {
		synchronized (Market.PRODUCT_LIST) {
			try {
				Market.PRODUCT_LIST.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private void haveARest() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
