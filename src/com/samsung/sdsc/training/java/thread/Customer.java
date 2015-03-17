package com.samsung.sdsc.training.java.thread;

public class Customer implements Runnable {
	private String name;

	private Apple apple;

	public Customer(String name) {
		this.name = name;
	}

	@Override
	public void run() {
		while (true) {
			if (haveApple()) {
				eatApple();
			} else {
				buyAnApple();
			}
		}
	}

	private boolean haveApple() {
		return this.apple != null;
	}

	private void eatApple() {
		try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(this.name + " 吃掉了一个 " + apple);
		this.apple = null;
	}

	private void buyAnApple() {
		while (true) {
			synchronized (Market.PRODUCT_LIST) {
				if (Market.PRODUCT_LIST.size() > 0) {
					this.apple = Market.PRODUCT_LIST.remove(0);
					System.out
							.println(this.name + " 买了一个 " + this.apple + "，当前市场上还有 " + Market.PRODUCT_LIST.size() + " 个苹果");
					Market.PRODUCT_LIST.notifyAll();
					break;
				} else {
					try {
						Market.PRODUCT_LIST.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
}
