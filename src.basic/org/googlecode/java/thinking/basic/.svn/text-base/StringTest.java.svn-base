package org.googlecode.java.thinking.basic;

import java.io.PrintStream;
import java.util.Formatter;

import org.junit.Test;

public class StringTest {
	
	@Test
	public void testPrintf() {
		int x=5;
		double y=5.332542;
		System.out.format("Row 1: [%d %f]\n", x, y);
		System.out.printf("Row 1: [%d %f]\n", x, y);
	}
	
	@Test
	public void testFormatter() {
		PrintStream outAlias=System.err;//System.out
		Turtle tommy=new Turtle("tommy", new Formatter(System.out));
		Turtle terry=new Turtle("terry", new Formatter(outAlias));
		
		tommy.move(0, 0);
		terry.move(4, 8);
		tommy.move(2, 5);
		terry.move(3, 4);
	}
	
	@Test
	public void testStringDotFormat() {
		try {
			throw new DatabaseException(3, 7, "Write failed");
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	@Test
	public void testHexFormatter() {
		//由于BinaryFile.read方法没有实现
		//HexFormatter.format(BinaryFile.read("Hex.class"));
	}
}

class Turtle {
	private String name;
	private Formatter f;
	public Turtle(String name, Formatter f) {
		this.name=name;
		this.f=f;
	}
	public void move(int x, int y) {
		f.format("%s The Turtle is at (%d, %d)\n", name, x, y);
	}
}

class DatabaseException extends Exception {

	public DatabaseException(int transactionID, int queryID, String message) {
		super(String.format("(t%d, q%d) &s", transactionID, queryID, message));
		
	}
	
}

class HexFormatter {
	public static String format(byte[] data) {
		StringBuilder result=new StringBuilder();
		int n=0;
		for (byte b : data) {
			if (n%16==0)
				result.append(String.format("%05X: ", n));
			result.append(String.format("%02X ", b));
			result.append("\n");
		}
		return result.toString();
	}
}
