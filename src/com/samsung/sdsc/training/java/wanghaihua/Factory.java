package com.samsung.sdsc.training.java.wanghaihua;

/**
 * 
 * 工厂，共享数据区域
 * @author haihua
 *
 * wait()和notify()是根类Object的两个方法，也就意味着所有的JAVA类都会具有这个两个方法，为什么会被这样设计呢？\
 * 我们可以认为所有的对象默认都具有一个锁，虽然我们看不到，也没有办法直接操作，但它是存在的。
 * wait()方法表示：当缓冲区已满或空时，生产者或消费者线程停止自己的执行，放弃锁，使自己处于等待状态，让另一个线程开始执行；
 * notify()方法表示：当生产者或消费者对缓冲区放入或取出一个产品时，向另一个线程发出可执行通知，同时放弃锁，使自己处于等待状态。
 */
public class Factory {
	
	private Product[] productArr = new Product[10];
	private int index;
	
	public synchronized void push(Product product) {
		while (index == productArr.length) {
			try {
				System.out.println("仓库满，暂时停止生产");
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		this.notifyAll(); //唤醒在此对象监视器上等待的线程
		productArr[index] = product;
		index++;
		System.out.println("after push, total products " + index);
	}
	
	public synchronized Product pop() {
		while (index == 0) {
			try {
				System.out.println("仓库为空，不能取货物");
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		this.notifyAll();
		index--;
		System.out.println("after pop, total products " + index);
		return productArr[index];
	}
	
}
