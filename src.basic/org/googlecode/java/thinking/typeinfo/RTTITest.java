package org.googlecode.java.thinking.typeinfo;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Random;
import java.util.regex.Pattern;

import org.googlecode.java.thinking.basic.util.Print;
import org.junit.Test;


public class RTTITest {
	
	//@Test
	public void test() {
		Print.print("inside main");
		new FancyToy();
		Print.print("after creating FancyToy");
		try {
			Class.forName("Gum");
		} catch (ClassNotFoundException e) {
			Print.print("Could not find Gum");
		}
		Print.print("after Class.forName(\"Gum\")");
		new Cookie();
		Print.print("after creating Cookie");
	}
	
	//@Test
	public void testClassLoad() {
		//****详细了解Class对象的含义P314
		//类加载器用来加载.class文件中的Class对象，所有的类都是在对其第一次使用时，动态加载到JVM中去的
		//当程序创建第一个对类的静态成员的引用时，就会加载这个类。这个证明构造器也是类的静态方法
		Print.print("begin FancyToy.class");
		//比forName更安全，编译器检查，所以不需要置于try语句块中
		//使用.class来创建Class的引用时，并不会自动初始化该对象，因为：
		//1.加载，从字节码中创建一个Class对象
		//2.链接，验证字节码，为静态域分配存储空间
		//3.初始化，执行静态初始化器和静态初始化块
		//^^^^初始化会延迟到对静态方法(包括构造器)或者非常数静态域(不加final)首次引用时才执行
		//^^^^P319初始化的相关信息
		Class c=FancyToy.class;
		Print.print("end FancyToy.class");
		try {
			//会初始化该对象Loading Toy，Loading FancyToy
			Print.print("begin Class.forName");
			c=Class.forName("org.googlecode.java.thinking.typeinfo.FancyToy");
		} catch (ClassNotFoundException e) {
			Print.print("Cannot find FancyToy");
			System.exit(1);
		}
		//Simple name:　FancyToy
		//Canonical name:　org.googlecode.java.thinking.typeinfo.FancyToy
		printInfo(c);
		for (Class face:c.getInterfaces())
			printInfo(face);
		Class up=c.getSuperclass();
		Object obj=null;
		try {
			//obj必须带有默认的构造器
			obj=up.newInstance();
		} catch (InstantiationException e) {
			Print.print("Cannot Instantiate");
			System.exit(1);
		} catch (IllegalAccessException e) {
			Print.print("Cannot access");
			System.exit(1);
		}
		//对象应使用getClass()方法来获取该对象的实际类型的Class引用
		printInfo(obj.getClass());
	}
	
	//@Test
	public void testClassreference() throws InstantiationException, IllegalAccessException {
		//向Class引用添加泛型语法的原因仅仅是为了提供编译器类型检查
		
		Class<FancyToy> ftClass=FancyToy.class;
		Class intClass=int.class;
		//int.class和Integer.TYPE等价，基本类型的包装器类还有一个标准字段TYPE
		intClass=Integer.TYPE;
		//通配符？表示任何事物，是泛型的一部分。Class<?>要好于Class，
		//表明并非疏忽，而是用了一个非具体类的引用
		//提供了编译器类型检查
		Class<Integer> genericIntClass=int.class;
		genericIntClass=Integer.class;
		intClass=double.class;
		//限定Class引用所指向的Class对象的类型，不能更改为其他类型，否则会报错
		//! genericIntClass=double.class;

		Class<?> intClass2=int.class;
		intClass2=double.class;
		//?和extends配合，创建了一个限定的范围，表明是Number或者其子类都可以
		Class<? extends Number> bounded=int.class;
		bounded=double.class;
		bounded=Number.class;
		//?和super配合，创建了一个限定的范围，表明是FancyToy或者其父类
		Class<? super FancyToy> superClass=ftClass.getSuperclass();
		//不能直接写成Class<Toy>，只能表示为Class<? super FancyToy>(某个类，它是FancyToy的超类)
		//! Class<Toy> toyClass=ftClass.getSuperclass();
		
		//返回也是Object而不是Toy类的精确类型
		//! Toy toy=superClass.newInstance();
		Object obj=superClass.newInstance();
		if (obj instanceof Toy)
			((Toy)obj).play();
	}
	
	//@Test
	public void testFilledList() {
		//CountedIntegern必须有默认构造器，否则会得到一个运行时的异常
		FilledList<CountedInteger> fl=new FilledList<CountedInteger>(CountedInteger.class);
		Print.print(fl.create(15));
	}
	
	@Test
	public void testReflect() {
		//反射和RTTI的区别就在于RTTI在编译时打开和检查.class文件，反射机制.class文件在编译时不可获取，在运行时检查和打开.class文件
		try {
			Pattern p=Pattern.compile("\\w+\\.|final|native");
			String className="org.googlecode.java.thinking.typeinfo.RTTITest";
			Class<?> c=Class.forName(className);
			Method[] methods=c.getMethods();
			Constructor[] ctors=c.getConstructors();
			for (Method method:methods) {
				Print.print(method.toString());
				Print.print(p.matcher(method.toString()).replaceAll(""));
			}
			for (Constructor ctor:ctors)
				
				Print.print(p.matcher(ctor.toString()).replaceAll(""));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	static void printInfo(Class cc) {
		Print.print("Class name: " + cc.getName() +
			" is interface? [" + cc.isInterface() + "]");
		Print.print("Simple name:　" + cc.getSimpleName());
		Print.print("Canonical name:　" + cc.getCanonicalName());
	}
}

interface HasBatteries { }
interface Waterproof { }
interface Shoots { }

class Toy {
	//该语句在类被第一次加载时执行
	static { Print.info("Loading Toy"); }
	Toy() { }
	Toy(int i) { }
	void play() { }
}

class FancyToy extends Toy
implements HasBatteries, Waterproof, Shoots { 
	
	//编译器常量，不会引发初始化，不需要初始化就可以读取
	static final int staticFinalVar=47;
	//在被读取之前，要先进行链接(为这个域分配存储空间)和初始化(初始化该存储空间)，会引发类对象的初始化
	static int staticVar=147;
	//会引发初始化
	static int staticVar2=new Random().nextInt(1000);
	
	static { Print.info("Loading FancyToy"); }
	FancyToy() { super(1); }
}

class Gum {
	static { Print.info("Loading Gum"); }
}

class Cookie {
	static { Print.info("Loading Cookie"); }
}
