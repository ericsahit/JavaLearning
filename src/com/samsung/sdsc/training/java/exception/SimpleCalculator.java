package com.samsung.sdsc.training.java.exception;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

public class SimpleCalculator {
	private int i;
	private int j;

	public void run() {
		while (true) {
			try {
				this.i = input("请输入被除数：");
				this.j = input("请输入除数：");
				int result = i / j;
				System.out.println(this.i + "/" + this.j + "的结果大概是：" + result);
			} catch (NumberFormatException e) {
				System.out.println("您输入的不是数字，请重新输入。");
			} catch (ArithmeticException e) {
				System.out.println("对不起，除数不能为0，请重新输入。");
			} catch (NumberOutofRangeException e) {
				System.out.println(e.getMessage());
			} catch (Exception e) {
				System.out.println("哎，这个破系统，怎么又出问题了，不过没关系，咱们再试一次吧。");
			} finally {
				reset();
			}
		}
	}

	private int input(String tipMsg) throws NumberOutofRangeException, NumberFormatException, IOException {
		LineNumberReader lr = new LineNumberReader(new InputStreamReader(System.in));
		System.out.println(tipMsg);
		if (Math.random() > 0.9) {
			throw new IOException("系统产生模拟异常。");
		}
		int result = Integer.parseInt(lr.readLine());
		if (result > 100 || result < 0) {
			throw new NumberOutofRangeException(0, 100);
		}
		return result;
	}

	private void reset() {
		this.i = 0;
		this.j = 0;
		System.out.println("系统已重置。请继续使用。");
	}

	public static void main(String[] args) {
		SimpleCalculator sc = new SimpleCalculator();
		sc.run();
	}
}
