package com.samsung.sdsc.training.java.zhuli;

public class Parent {
	
	
 
	private String house="小木屋";
	
	private String car="拖拉机";
	
	public String getHouse() {
		return house;
	}
	public void setHouse(String house) {
		this.house = house;
	}
	public String getCar() {
		return car;
	}
	public void setCar(String car) {
		this.car = car;
	}
	
	
	public Parent()//默认的构造方法
	{
		
	}
	 public  Parent(String house,String car)//重载的构造方法
	{
		
		 this.house=house;
		 this.car=car;
	}
	 
	 public  void print()
	 {  
		 //System.out.println("house="+this.getHouse()+" car="+this.getCar()); 
		 System.out.println("house="+house+" car="+car);
	 }
	 
	 public void eat()
	 {
		 
	 }
	 public void drink()
	 {
		 
	 }
	 public void study()
	 {
		 
	 }
	

}
