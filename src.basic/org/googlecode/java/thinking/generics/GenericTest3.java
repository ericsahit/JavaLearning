package org.googlecode.java.thinking.generics;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


import org.googlecode.java.thinking.basic.util.Print;
import org.junit.Test;

import sun.misc.Compare;

public class GenericTest3 {
	
	@Test
	public void testSelfBounded() {
		//测试自限定类型
		Subtype st1=new Subtype(), st2=new Subtype();
		st1.set(st2);
		Subtype st3=st1.get();
		st1.f();
		
		A a=new A();
		a.set(new A());
		a=a.set(new A()).get();
		a=a.get();
		C c=new C();
		c=c.setAndGet(new C());
		
		DerivedGetter dg=null;
		//Derived d=dg.get(); //可以
		//{P407-409的解释****}
		
		//exercise34
		ConcreteProcessor cp=new ConcreteProcessor();
		Print.print(cp == cp.test());
	}
	//自限定方法
	static <T extends SelfBounded<T>> T f(T arg) {
		return arg.set(arg).get();
	}
	void test(Getter g) {
		Getter result=g.get();
		GenericGetter<Getter> gg=g.get();//也是基类型
		//Arrays.asList(a)
	}
	
	//动态类型安全
	@Test
	public void testCheckedList() {
		List<Dog> dogList1=new ArrayList<Dog>();
		//应用decorator设计模式，包装List返回的是CheckedList类型，会在add时候强制检查类型，不对会有ClassCast异常
		List<Dog> dogList2=Collections.checkedList(new ArrayList<Dog>(), Dog.class);
		oldStyleMethod(dogList1);//可以强制添加进去Cat
		Print.print(dogList1);
		//oldStyleMethod(dogList2);//会报错，
	}
	@SuppressWarnings("unchecked")
	public void oldStyleMethod(List probablyDogs) {
		probablyDogs.add(new Cat());
	}
	
	//动态类型安全
	@Test
	public void testGenericException() {
		ProcessRunner<String, Failure1> runner=new ProcessRunner<String, Failure1>();
		for (int i = 0; i < 3; i++) {
			runner.add(new Processor1());
		}
		try {
			Print.print(runner.processAll());
		} catch (Failure1 e) {
			e.printStackTrace();
		}
		
		ProcessRunner<Integer, Failure2> runner2=new ProcessRunner<Integer, Failure2>();
		for (int i = 0; i < 3; i++) {
			runner2.add(new Processor2());
		}
		try {
			Print.print(runner2.processAll());
		} catch (Failure2 e) {
			e.printStackTrace();
		}
	}
	//测试混型
	@Test
	public void testMixin() throws Exception {
		//用接口实现混型
		//使用代理来实现Mixin类，
		//****代理模式只是原来对象的一个替身，装饰模式是对原对象的功能增强，适配器模式是要改变原对象的接口
		//装饰模式{扩展}既有对象的状态和功能，适配器模式实现不同接口的转换，代理模式某种意义上是通过第三方来进行对象的访问控制、监管。
		//代理用于：缓存代理，远程代理，权限控制
		//****代理模式：控制访问，关注于控制对对象的访问
		//****装饰模式：扩展功能，新增行为，关注于一个对象上动态的添加方法
		//用代理模式，代理类（proxy class）可以对它的客户隐藏一个对象的具体信息。因此，当使用代理模式的时候，我们常常在一个代理类中创建一个对象的实例
		//而当我们使用装饰器模式的时候，我们通常的做法是将原始对象作为一个参数传给装饰者的构造器。
		//使用代理模式，代理和真实对象之间的的关系通常在编译时就已经确定了，而装饰者能够在运行时递归地被构造。    
		//****装饰类只能新增行为，不能跳过其他的任何一个行为
		//****装饰模式动态地给一个对象添加了一些额外的职责，就增加功能来说，装饰模式比生成子类更为灵活
		//对使用者来说，装饰模式用具体类来调用，而代理模式用接口来调用
		Mixin mixin1=new Mixin(), mixin2=new Mixin();
		mixin1.set("test String 1");
		mixin2.set("test String 2");
		Print.print(mixin1.get()+" "+mixin1.getStamp()+ " "+mixin1.getSerialNumber());
		Print.print(mixin2.get()+" "+mixin2.getStamp()+ " "+mixin2.getSerialNumber());
		
		//使用Decorator模式来实现混型
		//{P414}装饰器模式经常用于满足各种可能的组合，而直接子类化会产生过多的类，不实际
		//Decorator使用分层对象来动态透明地向单个对象中添加责任
		TimeStampedDecorator t=new TimeStampedDecorator(new BasicImpl());
		TimeStampedDecorator t2=new TimeStampedDecorator(new SerialNumberedDecorator(new BasicImpl()));
		t2.getStamp();
		//尽管可以添加多个层，但最后一层才是实际的类型，因此只有最后一层方法才是可视的，是一种有局限的混型解决方法
		//t2.getSerialNumber();//不行，只能调用TimeStampedDecorator的新增方法
		
		//{P416}潜在类型机制的解释
		//Java当要在泛型类型上执行操作时，就会产生问题，因为擦除要求要求指定可能会用到的泛型类型的边界，以安全地调用代码中的泛型对象上的具体方法
		//所以必须限制泛型类型，使它们继承自特定的类，或实现特定的接口
		//而某些语言可以提供一种解决方案：潜在类型机制，只要求实现某个方法子集而不是特定的接口或类，从而方法了限制，产生更泛化的代码
		//我不关心你是什么类型，只要可以speak和sit就可以，而不要求具体类型
		//对缺乏潜在类型机制的补偿：反射
		perform(new BasicImpl());
		perform(new TimeStampedImpl());//可以调用，但是没有实际的get和set方法
		
		List<Basic> basics=new ArrayList<Basic>(10);
		for (int i = 0; i < 5; i++) {
			basics.add(new BasicImpl());
		}
		for (int i = 0; i < 5; i++) {
			basics.add(new Decorator(basics.get(i)));
		}
		apply(basics, Basic.class.getMethod("set", String.class), "set to this value");
		apply(basics, Basic.class.getMethod("get"));
		
		//使用适配器仿真潜在类型机制。潜在类型机制其实创建了一个隐式的接口，在Java里手动编写出这个接口来仿真
		//使用适配器模式来适配原有的接口，以产生想要的接口
		//{P424}Collection和SimpleQueue都有add方法，但是没有实现同一个接口。
		//fill如果被限制在Collection继承层次接口之内，即便SimpleQueue有一个add方法，它也不能工作。
		List<Pet> pets=new ArrayList<Pet>();
		//使用基类型来创建的适配器
		fill(new AddableCollectionAdapter<Pet>(pets), Pet.class, 3);
		fill(new AddableCollectionAdapter<Pet>(pets), Dog.class, 2);
		for (Pet p:pets)
			Print.print(p);
		//使用继承来创建适配器
		AddableSimpleQueue<Pet> petQueue=new AddableSimpleQueue<Pet>();
		fill(petQueue, Cat.class, 3);
		fill(petQueue, Pug.class, 1);
		for (Pet p:petQueue)
			Print.print(p);
		
		//将函数对象用作策略
		List<Integer> li=Arrays.asList(1, 2, 3, 4, 5, 6, 7);
		Integer result=Functional.reduce(li, new Functional.IntegerAdder());
		Print.print(result);
		result=Functional.reduce(li, new Functional.IntegerSubtracter());
		Print.print(result);
		//对每个li中的元素进行过滤操作
		Print.print(Functional.filter(li, new Functional.GreaterThan<Integer>(4)));
		//对每个li中的元素进行累计乘操作，返回最后的累积结果
		Print.print(Functional.forEach(li, new Functional.MultiplyingIntegerCollector()).result());
		Print.print(Functional.forEach(Functional.filter(li, new Functional.GreaterThan<Integer>(4)), 
				new Functional.MultiplyingIntegerCollector()).result());
		
		MathContext mc=new MathContext(7);//代表decimal保留的精度是几位小数
		List<BigDecimal> lbd=Arrays.asList(
				new BigDecimal(1.1, mc), new BigDecimal(2.2, mc),
				new BigDecimal(3.3, mc), new BigDecimal(4.4, mc)
				);
		BigDecimal bd=Functional.reduce(lbd, new Functional.BigDecimalAdder());
		Print.print(bd);
		Print.print(Functional.filter(lbd, new Functional.GreaterThan<BigDecimal>(BigDecimal.valueOf(3))));
		Print.print(Functional.transform(lbd, new Functional.BigDecimalUlp()));
	}
	public static void perform(Object basic) {
		Class<?> bas=basic.getClass();
		try {
			try {
				Method get=bas.getMethod("set",String.class);
				Print.print(get.invoke(basic, "set to this value"));
			} catch (NoSuchMethodException e) {
				Print.print("no set()");
			}
			try {
				Method get=bas.getMethod("get");
				Print.print(get.invoke(basic));
			} catch (NoSuchMethodException e) {
				Print.print("no get()");
			}

		} catch (Exception e) {
			throw new RuntimeException(basic.toString(), e);
		}
	}
	public static <T, S extends Iterable<? extends T>>
	void apply(S seq, Method f, Object... args) {
		try {
			for (T t:seq)
				Print.print(f.invoke(t, args));
		} catch (Exception e) {
			//由于程序员的错误
			throw new RuntimeException(e);
		}
	}
	public static <T> void fill(Addable<T> addable, Class<? extends T> classToken, int size) {
		for (int i = 0; i < size; i++) {
			try {
				addable.add(classToken.newInstance());
			} catch (Exception e) {
				throw new RuntimeException(e);
			} 
		}
	}
}

class GenericType<T> {}
//古怪的循环泛型
class CuriouslyRecurringGeneric extends GenericType<CuriouslyRecurringGeneric>{}
class BasicHolder<T> {
	T element;
	void set(T arg) {element=arg;}
	T get() {return element;}
	void f() {Print.print(element.getClass().getSimpleName());}
}
class Subtype extends BasicHolder<Subtype> {}
class Other{}
//BasicHolder可以使用任何类型作为其泛型参数，自限定则采取额外步骤，强制泛型当作其自己的边界参数来使用
class BasicOther extends BasicHolder<Other>{}

class SelfBounded<T extends SelfBounded<T>> {
	T element;
	SelfBounded<T> set(T arg) {element=arg;return this;}
	T get() {return element;}
	//void f() {Print.print(element.getClass().getSimpleName());}
}
class A extends SelfBounded<A>{}
class B extends SelfBounded<B>{}
//class f extends SelfBounded<B>{}
class C extends SelfBounded<C> {
	C setAndGet(C arg) {set(arg); return get();}
}
class D{}
//E不行，D的范围不对
//class E extends SelfBounded<D>{}
class F extends SelfBounded{}//可以不使用泛型

class Base{}
class Derived extends Base{}
interface OrdinaryGetter{
	Base get();
}
interface DerivedGetter extends OrdinaryGetter {
	Derived get();//覆盖OrdinaryGetter中的get方法
}
interface GenericGetter<T extends GenericGetter<T>> {T get();}
interface Getter extends GenericGetter<Getter>{}

abstract class GenericProcessor<T extends GenericProcessor<T>> {
	abstract T process(T arg);
	T test() {return process(null);}
}
class ConcreteProcessor extends GenericProcessor<ConcreteProcessor>{
	@Override
	ConcreteProcessor process(ConcreteProcessor arg) {
		if (arg==null)
			return this;
		return arg;
	}
}
//测试泛型异常
interface Processor<T, E extends Exception> {
	void process(List<T> resultCollector) throws E;
}
class ProcessRunner<T, E extends Exception> extends ArrayList<Processor<T, E>> {
	List<T> processAll() throws E {
		List<T> resultCollector=new ArrayList<T>();
		for (Processor<T, E> processor:this)
			processor.process(resultCollector);
		return resultCollector;
	}
}
class Failure1 extends Exception{}
class Processor1 implements Processor<String, Failure1> {
	static int count=3;
	@Override
	public void process(List<String> resultCollector) throws Failure1 {
		if (count-->1)
			resultCollector.add("Hep!");
		else
			resultCollector.add("Ho!");
		if (count<0)
			throw new Failure1();
	}
}
class Failure2 extends Exception{}
class Processor2 implements Processor<Integer, Failure2> {
	static int count=2;
	@Override
	public void process(List<Integer> resultCollector) throws Failure2 {
		if (count-->1)
			resultCollector.add(47);
		else
			resultCollector.add(11);
		if (count<0)
			throw new Failure2();
	}
}

//用接口来实现混型
interface TimeStamped {long getStamp();}
class TimeStampedImpl implements TimeStamped {
	private final long timeStamp;
	public TimeStampedImpl() {timeStamp=new Date().getTime();}
	@Override
	public long getStamp() {return timeStamp;}
}
interface SerialNumbered {long getSerialNumber();}
class SerialNumberedImpl implements SerialNumbered {
	private static long counter=1;
	private final long serialNumber=counter++;
	@Override
	public long getSerialNumber() {return serialNumber;}
}
interface Basic {
	void set(String val);
	String get();
}
class BasicImpl implements Basic {
	private String value;
	@Override
	public void set(String val) {value=val;}
	@Override
	public String get() {return value;}
}
//使用代理设计模式
class Mixin extends BasicImpl implements SerialNumbered, TimeStamped {
	private TimeStamped timeStamp=new TimeStampedImpl();
	private SerialNumbered serialNumber=new SerialNumberedImpl();
	@Override
	public long getStamp() {return timeStamp.getStamp();}
	@Override
	public long getSerialNumber() {return serialNumber.getSerialNumber();}
}
//用装饰器模式来实现混型
class Decorator implements Basic {
	protected Basic basic;
	public Decorator(Basic basic) {this.basic=basic;}
	public void set(String val) {Print.print("decorator call set");basic.set(val);}
	public String get() {Print.print("decorator call set");return basic.get();}
}
class TimeStampedDecorator extends Decorator {
	private final long timeStamp;
	public TimeStampedDecorator(Basic basic) {
		super(basic);
		timeStamp=new Date().getTime();
	}
	public long getStamp() {return timeStamp;}
}
class SerialNumberedDecorator extends Decorator {
	private static long counter=1;
	private final long serialNumber=counter++;
	public SerialNumberedDecorator(Basic basic) {
		super(basic);
	}
	public long getSerialNumber() {return serialNumber;}
}
//使用适配器仿真潜在类型机制。
interface Addable<T> {void add(T t);}
//将Collection适配成Addable类型
class AddableCollectionAdapter<T> implements Addable<T> {
	private Collection<T> c;
	public AddableCollectionAdapter(Collection<T> c) {
		this.c=c;
	}
	public void add(T item) {c.add(item);}
}
class SimpleQueue<T> implements Iterable<T> {
	private LinkedList<T> storage=new LinkedList<T>();
	public void add(T t) {storage.offer(t);}
	public T get(T t) {return storage.poll();}
	public Iterator<T> iterator() {return storage.iterator();}
}
//将SimpleQueue适配成Addable类型
class AddableSimpleQueue<T> extends SimpleQueue<T> implements Addable<T> {
	public void add(T t) {super.add(t);}
}

//将函数对象用作策略
//函数对象定义接口
//Combiner抽象掉了两个对象添加在一起的具体细节，并且只是声明它们在某种程序上被结合在一起。
interface Combiner<T> {T combine(T x, T y);}
//接受单一的参数，并产生一个结果；这个参数和结果不需要是相同的类型。
interface UnaryFunction<R, T> {R function(T x);}
//Collector被用作“收集参数”，并且当你完成时时，可以从中抽取结果
interface Collector<T> extends UnaryFunction<T, T> {
	T result();
}
//产生一个bool类型的结果
interface UnaryPredicate<T> {boolean test(T x);}

class Functional {
	//在每一个元素上调用Combiner，来合并为一个最终的元素
	public static <T> T reduce(Iterable<T> seq, Combiner<T> combiner) {
		Iterator<T> it=seq.iterator();
		while (it.hasNext()) {
			T result=it.next();
			while (it.hasNext()) {
				result=combiner.combine(result, it.next());
			}
			return result;
		}
		return null;
	}
	public static <T> Collector<T> forEach(Iterable<T> seq, Collector<T> func) {
		for(T t:seq) {
			func.function(t);
		}
		return func;
	}
	public static <T> List<T> filter(Iterable<T> seq, UnaryPredicate<T> pred) {
		List<T> result=new ArrayList<T>();
		for (T t:seq) {
			if (pred.test(t))
				result.add(t);
		}
		return result;
	}
	public static <R, T> List<R> transform(Iterable<T> seq, UnaryFunction<R, T> func) {
		List<R> result=new ArrayList<R>();
		for (T t:seq) {
			result.add(func.function(t));
		}
		return result;
	}
	static class IntegerAdder implements Combiner<Integer> {
		@Override
		public Integer combine(Integer x, Integer y) {
			return x+y;
		}
	}
	static class IntegerSubtracter implements Combiner<Integer> {
		@Override
		public Integer combine(Integer x, Integer y) {
			return x-y;
		}
	}
	static class BigDecimalAdder implements Combiner<BigDecimal> {
		@Override
		public BigDecimal combine(BigDecimal x, BigDecimal y) {
			return x.add(y);
		}
	}
	static class BigDecimalUlp implements UnaryFunction<BigDecimal, BigDecimal> {
		@Override
		public BigDecimal function(BigDecimal x) {
			return x.ulp();
		}
	}
	static class GreaterThan<T extends Comparable<T>> implements UnaryPredicate<T> {
		private T bound;
		public GreaterThan(T bound) {this.bound=bound;}
		public boolean test(T x) {
			return x.compareTo(bound)>0;
		}
	}
	static class MultiplyingIntegerCollector implements Collector<Integer> {
		private Integer val=1;
		@Override
		public Integer function(Integer x) {
			val*=x;
			return val;
		}
		@Override
		public Integer result() {
			return val;
		}
	}
	
}