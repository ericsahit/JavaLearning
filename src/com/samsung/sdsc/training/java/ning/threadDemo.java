package com.samsung.sdsc.training.java.ning;


public class threadDemo { 
public static void main(String[] args) { 
Thread t = Thread.currentThread(); 
t.setName("你好吗？"); 
System.out.println("正在进行的Thread是：" + t); 
try { 
for (int i = 0; i < 5; i++) { 
System.out.println("我不叫穆继超" + i); 
Thread.sleep(3000); 
} 
} catch (Exception e) { 
// TODO: handle exception 
System.out.println("Thread has wrong" + e.getMessage()); 
} 

} 

} 

