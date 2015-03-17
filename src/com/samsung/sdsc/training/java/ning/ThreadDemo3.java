package com.samsung.sdsc.training.java.ning;


class ThreadDemo3 extends Thread { 
private String name; 
private int delay; 

public ThreadDemo3(String sname, int i_delay) { 
name = sname; 
delay = i_delay; 

} 

public void run() { 
try { 
sleep(delay); 
} catch (InterruptedException e) { 

} 
System.out.println("多线程测试！\n" + name + "\n" + delay); 
} 

} 












