package com.samsung.sdsc.training.java.ning;

import java.util.Calendar;


/*
 * 定义四个对象  太阳，蚂蚁，树，向日葵
 * 实现随着从日出到日落时蚂蚁，树，向日葵有规律的变化过程 ：
 * 随着时间的变化 蚂蚁数量增长，树长得越来越高，向日葵始终面向太阳
 * 
 */

public class Sun
{
	Tree tree;
	Ant ant;
	SunFlower sunflower;
	public Sun()
	{
		//Calendar calendar = Calendar.getInstance();

		int hour = 13;

		while (hour >= 7 && hour <= 17)
		{
			tree = new Tree();
			ant = new Ant();
			sunflower = new SunFlower();
			int count = 0;
			for (int i = 7; i <= hour; i=i+2)
			{
				count ++;
			}
			ant.setNum(1+count);
			tree.setHigh(1+count*0.1);
			sunflower.setFace(count * 30);

			System.out.println("蚂蚁数量:" + ant.getNum());
			System.out.println("树的高度:" + tree.getHigh());
			System.out.println("向日葵角度:" + sunflower.getFace());
			break;
		}
	}
	public static void main(String[] args)
	{
		new Sun();
	}
}