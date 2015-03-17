package com.samsung.sdsc.training.java.io;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class FileSample {

	public static void readFile(String fileName) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(
				new FileInputStream(fileName), "GBK"));
		System.out.println("开始读取文件。。。");
		String line = null;
		while ((line = br.readLine()) != null) {
			System.out.println(line);
		}
		br.close();
		System.out.println("文件读取完毕。。。");
	}

	public static void copyFile(String src, String dest) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(
				new FileInputStream(src), "GBK"));
		PrintWriter pw = new PrintWriter(dest, "GBK");
		String line = null;
		while ((line = br.readLine()) != null) {
			pw.println(line);
		}
		System.out.println("文件复制成功。");
		pw.close();
		br.close();
	}

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		FileSample.readFile("test.txt");
		FileSample.copyFile("test.txt", "test.txt.copy");
	}
}
