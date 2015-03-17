package org.googlecode.java.thinking.basic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 
 * @author haihua
 *
 * Thinking In Java P107
 * 
 * 理解enum结构的用法 values()和ordinal()方法
 * plain = new Burrito(Spiciness.NOT)不能在静态方法中使用？
 */
public class Enum {
	
	public enum Spiciness {
		NOT, MILD, MEDIUM, HOT, FLAMING
	}

	private static final Logger logger = LoggerFactory.getLogger(Enum.class);
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		for (Spiciness s : Spiciness.values()) {
			logger.info("{}, ordinal {}", s, s.ordinal());
			//logger.info(s + ", ordinal " + s.ordinal());
		}
		logger.info(Spiciness.NOT.toString());
		
		new Enum().test();
	}

	private void test() {
		Burrito 
			plain = new Burrito(Spiciness.NOT),
			greenChile = new Burrito(Spiciness.MEDIUM),
			jalapeno = new Burrito(Spiciness.HOT);
		plain.describe();
		greenChile.describe();
		jalapeno.describe();
	}
	
	public class Burrito {
		Spiciness degree;
		public Burrito(Spiciness degree) {
			this.degree = degree;
		}
		public void describe() {
			logger.info("this burrito is ");
			switch (degree) {
				case NOT: logger.info("not spicy at all");
							break;
				case MEDIUM:
				case MILD: logger.info("a little hot.");
				case HOT:
				case FLAMING:
				default: logger.info("maybe too hot.");
			}
		}
	}

}
