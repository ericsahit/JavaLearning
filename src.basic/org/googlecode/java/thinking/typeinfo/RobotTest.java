package org.googlecode.java.thinking.typeinfo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.googlecode.java.thinking.basic.util.Print;
import org.junit.Test;

public class RobotTest {
	
	@Test
	public void test() {
		//Robot.Test.test(new SnowRemovalRobot("eric"));
		
		Robot[] bots={
			new SnowRemovalRobot("Eric Wong"),
			//对每一种类型的robot都产生一个空对象
			NullRobot.newNullRobot(SnowRemovalRobot.class)
		};
		for (Robot bot : bots)
			Robot.Test.test(bot);
	}
	
}

interface Operation {
	String description();
	void command();
}

interface Robot {
	String name();
	String model();
	List<Operation> operations();
	class Test {
		public static void test(Robot r) {
			if (r instanceof Null)
				Print.print("[Null Robot]");
			Print.print("Robot name: "+r.name());
			Print.print("Robot model: "+r.model());
			for (Operation operation : r.operations()) {
				Print.print(operation.description());
				operation.command();
			}
		}
	}
}

class SnowRemovalRobot implements Robot {
	
	private String name;
	public SnowRemovalRobot(String name) { this.name=name; }

	@Override
	public String name() {
		return name;
	}

	@Override
	public String model() {
		return "SnowBot Series 11";
	}

	@Override
	public List<Operation> operations() {
		return Arrays.asList(
			new Operation() {
				
				@Override
				public String description() {
					return name+" can shovel snow";
				}
				
				@Override
				public void command() {
					Print.print(name+" shoveling snow");
				}
			},
			new Operation() {
				
				@Override
				public String description() {
					return name+" can chip ice";
				}
				
				@Override
				public void command() {
					Print.print(name+" chipping ice");
				}
			}
		);
	}
	
}
//使用动态代理，可以让每种Robot类型都调用产生一个对应的空对象
class NullRobotProxyHandler implements InvocationHandler {
	private String nullName;
	private Robot proxied=new NRobot();
	public NullRobotProxyHandler(Class<? extends Robot> type) {
		nullName=type.getSimpleName()+" NullRobot";
	}
	
	private class NRobot implements Null, Robot {
		@Override
		public String name() { return nullName; }
		@Override
		public String model() { return nullName; }
		@Override
		public List<Operation> operations() { return Collections.emptyList(); }
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		return method.invoke(proxied, args);
	}
}

class NullRobot {
	public static Robot newNullRobot(Class<? extends Robot> type) {
		return (Robot) Proxy.newProxyInstance(
				NullRobot.class.getClassLoader(), 
				new Class[]{Null.class, Robot.class}, 
				new NullRobotProxyHandler(type));
	}
}
