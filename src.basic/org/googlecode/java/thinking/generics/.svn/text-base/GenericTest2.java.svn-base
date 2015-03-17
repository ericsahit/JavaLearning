package org.googlecode.java.thinking.generics;

import java.awt.Color;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.googlecode.java.thinking.basic.util.Print.print;

import org.googlecode.java.thinking.basic.util.Print;
import org.junit.Test;

//从边界测试开始
public class GenericTest2<W, T> {
	@Test
	public void testBound() {
		Solid<Bounded> solid=new Solid<Bounded>(new Bounded());
		solid.color();
		solid.getX();
		solid.weight();
		
		Solid2<Bounded> solid2=new Solid2<Bounded>(new Bounded());
		solid2.color();
		solid2.getX();
		solid2.weight();
		
		//数组的实际类型是Solid2数组，所以不能赋值Colored2或ColoredDimension2
		//只能赋值Solid2或者子类型childSolid{P390}
		Colored2<Bounded>[] colored=new Solid2[2];
		colored[0]=solid2;
		ChildSolid childSolid=new ChildSolid(new Bounded());
		colored[1]=childSolid;
		//编译器允许你这么干，但是运行时通不过，向数组中放置异构类型时会产生异常java.lang.ArrayStoreException
		//colored[1]=new Colored2<Bounded>(new Bounded());
		//使用泛型容器来代替数组，会编译报错，泛型没有内建的协变类型
		//List<Colored2<Bounded>> colorList=new ArrayList<Solid2<Bounded>>();
		//通配符引用的是明确的类型，因此这个被赋值的List必须持有诸如Colored2或Solid2等某种制定类型
		List<? extends Colored2<Bounded>> colorList2=new ArrayList<Solid2<Bounded>>();
		//唯一的限制是这个List要持有某种具体的Colored2或者Colored2子类型，但是你实际上并不关心它是什么
		//不知道List持有什么类型，就不能安全的添加对象，只能添加null.
		//add的参数是(? extends Colored2<Bounded>，这意味着他可以是任何事物，而编译器无法验证类型安全性
		//不管它指向什么(例如ArrayList<Solid2<Bounded>>)，只要向上转型，就将丢失掉向其中传递任何对象的能力
		//colorList2.add(solid2);
		//colorList2.add(new Object());
		colorList2.add(null);
		//可以安全的得到这个List中的内容，类型是Colored2或者Colored2的基类型
		colored[0]=colorList2.get(0);
		List<? extends Number> intList=new ArrayList<Integer>();
		//intList.add(Integer.valueOf(1));
		//contains和indexOf接受的是Object参数，因此不涉及任何通配符
		colorList2.contains(solid2);
		colorList2.indexOf(solid2);
		//{编译器只会关注传递进来和要返回的对象类型}
		
		//{逆变}：使用超类型通配符，可以声明通配符是由某个特定类的任意基类来界定的，
		//使得你可以安全的传递一个类型对象到泛型类型中
		List<? super Solid2<Bounded>> sl = new ArrayList<Solid2<Bounded>>();;
		//sl是Solid2的某种基类型的List，那么向其中添加Solid2或Solid2的子类型ChildSolid是安全的
		//而添加Colored2是不安全的
		sl.add(solid2);
		sl.add(childSolid);
		Colored2<Bounded> colored2=new Colored2<Bounded>(new Bounded());
		
		//sl.add(colored2);
		
		List<Colored2<Bounded>> colorList3=new ArrayList<Colored2<Bounded>>();
		List<Solid2<Bounded>> solid2List=new ArrayList<Solid2<Bounded>>();
		writeExact(colorList3, colored2);//可以
		//****{书上说有错误，但实际上是可以编译和运行通过}，应该使用通配符的list
		writeExact(colorList3, solid2);
		writeWithWildCard(colorList3, solid2);
		
		List<Number> numList=new ArrayList<Number>();
		writeExact(numList, Integer.valueOf(1));//同上
		
		
		HoldItem<Bounded> holdItem=readExact(colorList2);
		holdItem=readExact(colorList3);
		
		solid2=readExact(solid2List);//可以
		colored2=readExact(solid2List);//也可以，返回之后会自动向上转型
		
		//Reader
		Reader<Colored2<Bounded>> reader=new Reader<Colored2<Bounded>>();
		colored2=reader.readExact(colorList3);//可以
		//colored2=reader.readExact(solid2List);//不行，因为是Colored2<Bounded>的泛型类
		CovariantReader<Colored2<Bounded>> covariantReader=new CovariantReader<Colored2<Bounded>>();
		colored2=covariantReader.readCovariant(colorList3);
		colored2=covariantReader.readCovariant(solid2List);//可以，
	}
	@Test
	public void testUnboundedWildcard() throws Exception {
		List list1=new ArrayList();
		//<?>可以被认为是一种装饰，它在声明：我是想用Java的泛型来编写这段代码，我在这里并不是想用原生类型，
		//但是当前情况下，泛型参数可以持有任何类型
		List<?> list2=new ArrayList();
		List<? extends Object> list3=new ArrayList(); 
		
		list1=new ArrayList<String>();
		list2=new ArrayList<String>();
		list3=new ArrayList<String>();
		
		List<?> wildList=new ArrayList();
		wildList=new ArrayList<String>();
		list1=wildList;
		list2=wildList;//可以
		list3=wildList;//可以
		//无界通配符的一个重要应用：多个泛型参数时，有时允许一个参数可以是任何类型，同时为其他参数确定某种特定类型的这种能力会很重要
		Map map1;
		Map<?, ?> map2;
		Map<String, ?> map3;
		Map map=new HashMap();
		map1=map;
		map2=map;//全都是无界通配符时，编译器看起来就无法将其与原生Map区分了，所以不警告
		map3=map;//会警告类型转换
		
		Map<String, Integer> mapV=new HashMap<String, Integer>();
		map1=mapV;
		map2=mapV;
		map3=mapV;
		
		//使用确切参数类型来替代通配符类型的好处是，可以用泛型参数来做更多的事，
		//但是使用通配符使得你必须接受范围更宽的参数化类型作为参数
		HoldItem raw=new HoldItem<Integer>(1);
		f1(raw);//有警告
		f2(raw);//没警告，参数类型在调用f2()过程中被捕获，因此它可以在f1()的调用中被使用
		HoldItem raw2=new HoldItem();
		raw2.setItem(new Double(0));
		f2(raw2);//没警告
		
		//ObjectInputStream in=new ObjectInputStream(new FileInputStream("GenericTest2.java"));
		//List<String> shapes=(List<String>)in.readObject();//会警告，转型方式不对
		//List<String> shapes=List.class.cast(in.readObject());
		
	}
	//不能编译，重载方法将产生相同的类型签名
	void f3(List<T> v) {}
	//void f3(List<W> v) {}

	public static <T> void writeExact(List<T> list, T item) {
		list.add(item);
	}
	public static <T> void writeWithWildCard(List<? super T> list, T item) {
		list.add(item);
	}
	public static <T> T readExact(List<T> list) {
		return list.get(0);
	}
	public static <T> void f1(HoldItem<T> holder) {
		T t=holder.getItem();
		Print.print(t.getClass().getSimpleName());
	}
	public static void f2(HoldItem<?> holder) {
		f1(holder);
	}
	
	static class Reader<T> {
		T readExact(List<T> list) {return list.get(0);}
	}
	static class CovariantReader<T> {
		T readCovariant(List<? extends T> list) {
			return list.get(0);
		}
	}
}
interface HasColor {Color getColor();}
class Colored<T extends HasColor> {
	T item;
	Colored(T item) {this.item=item;}
	T getItem() {return item;}
	Color color() {return item.getColor();}
}
class Dimension {public int x, y, z;}
//使用多个边界类需要在第一个，然后才是接口
//class ColoredDimension<T extends HasColor & Dimension>{}
class ColoredDimension<T extends Dimension & HasColor>{
	T item;
	public ColoredDimension(T item) {this.item=item;}
	T getItem() {return item;}
	Color color() {return item.getColor();}
	int getX() {return item.x;}
}
interface Weight{int weight();}
//使用extends边界，只能有一个具体类，可以有多个接口
class Solid<T extends Dimension & HasColor & Weight> {
	T item;
	public Solid(T item) {this.item=item;}
	T getItem() {return item;}
	Color color() {return item.getColor();}
	int getX() {return item.x;}
	int weight(){return item.weight();}
}
class Bounded extends Dimension implements HasColor, Weight {
	@Override
	public int weight() {
		return 0;
	}

	@Override
	public Color getColor() {
		return null;
	}
}
//将上面的类体系重构
class HoldItem<T> {
	T item;
	public HoldItem(T item) {this.item=item;}
	public HoldItem() {}
	T getItem(){return item;}
	void setItem(T item) {this.item=item;}
}
class Colored2<T extends HasColor> extends HoldItem<T> {
	//必须有这个构造器
	public Colored2(T item) {super(item);}
	Color color() {return item.getColor();}
}
//T必须有HasColor或者继承HasColor的边界，否则不能ColoredDimension2不能继承Colored2
//除了HasColor边界，T可以有更多的边界
class ColoredDimension2<T extends Dimension & HasColor> extends Colored2<T> {
	public ColoredDimension2(T item) {super(item);}
	int getX() {return item.x;}
}
class Solid2<T extends Dimension & HasColor & Weight> extends ColoredDimension2<T> {
	public Solid2(T item) {super(item);}
	int weight(){return item.weight();}
}
class ChildSolid extends Solid2<Bounded> {
	public ChildSolid(Bounded item) {super(item);}
}
