package com.samsung.sdsc.training.java.ning;

public class threadDemo2 implements Runnable { 
public threadDemo2() { 
Thread t1 = Thread.currentThread(); 
t1.setName("第一个主进程"); 
System.out.println("正在运行" + t1); 
Thread t2 = new Thread(this, ""); 
System.out.println("在创建一个进程"); 
t2.start(); 
try { 
System.out.println("使他进入第一个睡眠状态"); 
Thread.sleep(2000); 
} catch (InterruptedException e) { 
System.out.println("Thread has wrong" + e.getMessage()); 
} 
System.out.println("退出第一个进程"); 
} 

public void run() { 
try { 
for (int i = 0; i < 5; i++) { 
System.out.println("进程" + i); 
Thread.sleep(3000); 
} 
} catch (InterruptedException e) { 
// TODO: handle exception 
System.out.println("Thread has wrong" + e.getMessage()); 
} 
System.out.println("退出第二个进程"); 

} 

public static void main(String[] args) { 
new threadDemo2(); 
} 
}