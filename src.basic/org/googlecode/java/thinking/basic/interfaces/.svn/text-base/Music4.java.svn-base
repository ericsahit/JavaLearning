package org.googlecode.java.thinking.basic.interfaces;
import static org.googlecode.java.thinking.basic.util.Print.*;

import org.junit.Test;

class Note {
	
}

interface IPlayable {
	//自动是static&final，编译时常量
	int VALUE=5;
	
	//加不加public关键字都是public方法
	void play(Note n);
	void stop();
}

abstract class Instrument implements IPlayable {
	
	//调用子类构造器时，先调用基类构造器，但是what方法被覆盖为子类的对应方法
	public Instrument() { print(what()); }
	
	private int i;
	public abstract void play(Note n);
	//可以把what方法声明为abstract，以此强制继承者覆写该方法，并防止对无格式的Instrument实例化
	//public abstract String what(); 
	public String what() { return "Instrument"; }
	public abstract void adjust();
	//实现接口的方法必须是public的
	public void stop(){}
}

class Wind extends Instrument {

	@Override
	public void play(Note n) {
		info("Wind.play() " + n);
	}
	@Override
	public void adjust() { }
	
	public String what() { return "Wind"; }
}

class Percussion extends Instrument {

	@Override
	public void play(Note n) {
		info("Percussion.play() " + n);
	}

	@Override
	public void adjust() {
		
	}
	public String what() { return "Percussion"; }
}

class Stringed extends Instrument {

	@Override
	public void play(Note n) {
		info("Stringed.play()" + n);
	}

	@Override
	public void adjust() {
	}
	public String what() { return "Stringed"; }
}

class Brass extends Wind {
	public void play(Note n) {
		info("Brass.play() " + n);
	}
	public void adjust() { 
		info("Brass.adjust()"); 
	}
}

class Woodwind extends Wind {
	public String what() { return "Woodwind"; }
}

public class Music4 {
	
	public void tune(Instrument i) {
		i.play(new Note());
	}
	
	public void tuneAll(Instrument[] e) {
		for (Instrument i : e) {
			tune(i);
		}
	}
	@Test
	public void test() {
		Instrument[] orchestra = {
				new Wind(),
				new Percussion(),
				new Stringed(),
				new Brass(),
				new Woodwind()
		};
		tuneAll(orchestra);
	}
}
