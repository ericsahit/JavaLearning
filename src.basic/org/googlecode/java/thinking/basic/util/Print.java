package org.googlecode.java.thinking.basic.util;

import java.io.PrintStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Print {
	
	private static final Logger logger = LoggerFactory.getLogger(Print.class);
	
	public static void print() {
		System.out.println();
	}
	
	public static void print(Object obj) {
		System.out.println(obj);
	}
	
	public static void printnb(Object obj) {
		System.out.print(obj);
	}
	
	public static PrintStream printf(String format, Object...args) {
		return System.out.printf(format, args);
	}
	
	public static void info(Object obj) {
		logger.info("{}", obj);
	}
	
	public static void info(String format, Object...args) {
		logger.info(format, args);
	}
}
