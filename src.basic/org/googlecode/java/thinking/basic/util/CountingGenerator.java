package org.googlecode.java.thinking.basic.util;

//参数化的Generator接口
public class CountingGenerator {
	public static class Boolean implements Generator<java.lang.Boolean> {
		private boolean value=false;
		@Override
		public java.lang.Boolean next() {
			value=!value;
			return value;
		}
	}
	static char[] chars="abcdefghijklmnopqrstuvwxyz".toCharArray();
	public static class Character implements Generator<java.lang.Character> {
		int index=-1;
		@Override
		public java.lang.Character next() {
			index=(index+1)%chars.length;
			return chars[index];
		}
	}
	public static class String implements Generator<java.lang.String> {
		private int length=7;
		Generator<java.lang.Character> cg=new Character();
		@Override
		public java.lang.String next() {
			char[] buf=new char[length];
			for (int i = 0; i < buf.length; i++) {
				buf[i]=cg.next();
			}
			return new java.lang.String(buf);
		}
	}
}
