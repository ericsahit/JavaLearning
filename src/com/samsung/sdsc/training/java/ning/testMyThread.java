package com.samsung.sdsc.training.java.ning;

public class testMyThread { 


public static void main(String[] args) { 
ThreadDemo3 th1,th2,th3; 

th1 = new ThreadDemo3("线程1", (int) (Math.random() * 900)); 
th2 = new ThreadDemo3("线程2", (int) (Math.random() * 900)); 
th3 = new ThreadDemo3("线程3", (int) (Math.random() * 900)); 
th1.start(); 
th2.start(); 
th3.start(); 

} 
} 

