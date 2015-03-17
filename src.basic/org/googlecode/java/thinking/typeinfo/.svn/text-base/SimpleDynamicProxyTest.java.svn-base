package org.googlecode.java.thinking.typeinfo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.googlecode.java.thinking.basic.util.Print;
import org.junit.Test;

public class SimpleDynamicProxyTest {
	
	@Test
	public void test() {
		RealObject real=new RealObject();
		consumer(real);
		//Java的动态代理，因为它可以动态地创建代理并动态地处理对所代理方法的调用
		//动态代理上所作的所有调用都会被重定向到单一的调用处理器上
		Interface proxy=(Interface)Proxy.newProxyInstance(
				Interface.class.getClassLoader(), 
				new Class[]{Interface.class},
				new DynamicProxyHandler(real));
		consumer(proxy);
	}
	
	public static void consumer(Interface iface) {
		iface.doSomething();
		iface.doSomethingElse("bonobo");
	}	
}
//P339,动态代理的实现
class DynamicProxyHandler implements InvocationHandler {
	private Object proxied;
	public DynamicProxyHandler(Object proxied) {
		this.proxied=proxied;
	}
	
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		Print.print("****proxy: "+proxy.getClass()+".method: "+method+", args: "+args);
		
		if (method.getName().equals("doSomething"))
			Print.print("Proxy detected the doSomething method");
		
		if (args!=null)
			for (Object arg : args) {
				Print.print("	"+arg);
			}
		return method.invoke(proxied, args);
	}
}

interface Interface {
	void doSomething();
	void doSomethingElse(String arg);
}

class RealObject implements Interface {

	@Override
	public void doSomething() {
		Print.print("RealObject doSomething");
	}

	@Override
	public void doSomethingElse(String arg) {
		Print.print("RealObject doSomethingElse "+arg);
	}
	
}
