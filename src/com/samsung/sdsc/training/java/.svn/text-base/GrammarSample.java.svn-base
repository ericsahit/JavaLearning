package com.samsung.sdsc.training.java;

import java.util.ArrayList;

public class GrammarSample {

	private static final int DEFAULT_ID = 10000;
	private int id;
	private String name;

	public GrammarSample() {
		this.id = DEFAULT_ID;
	}

	public GrammarSample(String name) {
		this.id = DEFAULT_ID;
		this.name = name;
	}

	public GrammarSample(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "[id=" + this.id + ", name=" + this.name + "]";
	}

	public static void main(String[] args) {
		GrammarSample gs = new GrammarSample();
		GrammarSample gs1 = new GrammarSample("朱丽");
		GrammarSample gs2 = new GrammarSample(1, "兴龙");

		System.out.println(gs);
		System.out.println(gs1);
		System.out.println(gs2);

		ArrayList<GrammarSample> list = new ArrayList<GrammarSample>();
		list.add(gs);
		list.add(gs1);
		list.add(gs2);

		for (int i = 0; i < list.size(); i++) {
			GrammarSample p = list.get(i);
//			if (p.getName().equals("朱丽")) {
			if ("朱丽".equals(p.getName())) {
				System.out.println("hi，大家好，我是" + p.getName() + "。");
				break;
			} else {
				System.out.println("共查询了" + i + 1 + "个对象。");
				System.out.println("共查询了" + (i + 1) + "个对象。");
			}
		}
	}
}
