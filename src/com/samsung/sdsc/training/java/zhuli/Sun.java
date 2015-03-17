package com.samsung.sdsc.training.java.zhuli;

public class Sun extends Parent{
	
	private String sunHouse="小草房";
	private String sunCar="自行车";
     public String getSunCar() {
		return sunCar;
	}

	public void setSunCar(String sunCar) {
		this.sunCar = sunCar;
	}

	
	 public String getSunHouse() {
		return sunHouse;
	}

	public void setSunHouse(String sunHouse) 
	{
		this.sunHouse = sunHouse;
	}

	
	public Sun()
	{
		
	}
	public Sun(String house)
	{
	 this.sunHouse=house;
		 
	}

	public Sun(String house,String car)
	{
	//this();
	// this(house);//注释  this.sunCar=car; and     this.sunHouse=house;
	// super();
	 //super(house,car);//如果子类的构造方法中没有用super语句显示调用父类的构造方法，那么通过这个构造方法创建子类对象时，
					   //java虚拟机会自动先调用父类的默认构造方法，而此时若父类没有默认构造方法时，就是编译出错
		this.sunCar=car;	
		this.sunHouse=house;
		
	}
	
	public void print()
	{   
		super.print(); 
	}
	
	
	 public void eat()
	 {
		 System.out.println("实现具体的有关吃的细节"); 
	 }
	 public void drink()
	 {
		 System.out.println("实现具体的有关喝的细节"); 
	 }
	 public void study()
	 {
		 System.out.println("实现具体的有关学习的细节"); 
	 }
	public static void main(String[] args) {
		
	//	Parent parent1 = new Parent("loft","别克");
		Parent  parent  =new Sun("loft","沃尔沃");//上溯造型
	//	Sun     sun  =new Sun("loft","大奔");
	//	Sun    sun1=new Sun("错层");
	//	System.out.println(parent1.getHouse()+"and"+parent1.getCar());
	  // System.out.println(parent1.getSunCar()+"and"+parent1.getSunHouse());
		System.out.println(parent.getHouse()+"and"+parent.getCar());
	//	System.out.println(sun.getSunHouse()+"and"+sun.getSunCar());
	//	System.out.println(sun1.getSunHouse()+"and"+sun1.getSunCar());
	 //   sun.setHouse("复式");
	 //   sun.setCar("本田");
	//	sun.print();
		parent.eat();
		parent.drink();
		parent.study();
		
		
		
		 
		
	
	 
	}

}
