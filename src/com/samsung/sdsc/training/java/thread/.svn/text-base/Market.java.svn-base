package com.samsung.sdsc.training.java.thread;

import java.util.ArrayList;
import java.util.List;

public class Market {
	public static final List<Apple> PRODUCT_LIST = new ArrayList<Apple>();

	private List<Vender> venders = new ArrayList<Vender>();
	private List<Customer> customers = new ArrayList<Customer>();

	public void addVender(Vender vender) {
		venders.add(vender);
	}

	public void addCustomer(Customer customer) {
		customers.add(customer);
	}

	public void work() {
		for (Vender v : this.venders) {
			new Thread(v).start();
		}
		for (Customer c : this.customers) {
			new Thread(c).start();
		}
	}
}
