package org.googlecode.java.thinking.generics;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.TreeSet;


import org.googlecode.java.thinking.basic.util.Print;
import org.googlecode.java.thinking.basic.util.Sets;
import org.junit.Test;


public class GenericTest {
	@Test
	public void testPetGenerator() {
		//使用泛型可以编写更通用的代码，要使代码能够应用于“某种不具体的类型”，而不是一个具体的接口或类
		//当你创建参数化类型的一个实例时，编译器会为你负责转型操作，并且保证类型的正确性
		//泛型的一个重要原因就是为了创造容器类
		
		Generator<?> gen=new PetGenerator();
		for (int i = 0; i < 5; i++) {
			Print.print(gen.next());
		}
		for (Pet pet:new PetGenerator(5)) {
			Print.print(pet);
		}
		gen=new Fibonacci();
		for (int i = 0; i < 18; i++) {
			Print.printnb(gen.next()+" ");
		}
		for (Integer it:new IterableFibonacci(18)) {
			Print.printnb(it+" ");
		}
		Map<String, List<String>> sls=map();
		//类型推断只对赋值操作有效，其他时候可能不起作用。
		//将泛型方法调用的结果作为参数传递给另一个方法，这时候编译器不会执行类型推断。
		//这时候编译器认为调用泛型方法后，其返回值被赋给了一个object类型的变量，此例中为Map<Object, Object>
		//! g(map());
		//使用显式的类型说明，可以解决上述问题
		g(this.<Person, List<Pet>>map());
		
		List<String> ls=makeList("A", "B", "C");
		ls=makeList("ASDFGHJKL".split(""));
		Print.print(ls);
		
		List<Pet> petList=fill(new LinkedList<Pet>(), new PetGenerator(), 5);
		for (Pet p:petList)
			Print.print(p);
		//不使用create方法则需要更多的类型声明
		Generator<Pet> gen3=new BasicGenerator<Pet>(Pet.class);
		Generator<Pet> gen2=BasicGenerator.create(Pet.class);
		for (int i = 0; i < 5; i++) {
			Print.print(gen2.next());
		}
	}
	
	@Test
	public void testSets() {
		//EnumSet用来从enum直接创建Set，向range()传入某个范围的第一个元素和最后一个元素，将返回一个set
		Set<Watercolors> set1=EnumSet.range(Watercolors.ZINC, Watercolors.BRILLIANT_RED);
		Set<Watercolors> set2=EnumSet.range(Watercolors.DEEP_YELLOW, Watercolors.VIOLET);
		Print.print(set1);
		Print.print(set2);
		Print.print(Sets.<Watercolors>union(set1, set2));
		Print.print(Sets.<Watercolors>intersection(set1, set2));
		Print.print(Sets.<Watercolors>difference(set1, set2));
		Print.print(Sets.<Watercolors>complement(set1, set2));
		
		//打印出各种Collection类和Map类之间的方法差异：
		Print.print(ContainerMethodDifferences.methodSet(Collection.class));
		ContainerMethodDifferences.interfaces(Collection.class);
		ContainerMethodDifferences.difference(Set.class, Collection.class);
		ContainerMethodDifferences.difference(HashSet.class, Set.class);
		ContainerMethodDifferences.difference(LinkedHashSet.class, HashSet.class);
		ContainerMethodDifferences.difference(TreeSet.class, Set.class);
		//List extends Collection, adds: [get, set, listIterator, lastIndexOf, indexOf, subList]
		ContainerMethodDifferences.difference(List.class, Collection.class);
		ContainerMethodDifferences.difference(ArrayList.class, List.class);
		ContainerMethodDifferences.difference(Queue.class, Collection.class);
		ContainerMethodDifferences.difference(PriorityQueue.class, Queue.class);
		
		Print.print(ContainerMethodDifferences.methodSet(Map.class));
		ContainerMethodDifferences.difference(HashMap.class, Map.class);
		ContainerMethodDifferences.difference(LinkedHashMap.class, HashMap.class);
		ContainerMethodDifferences.difference(SortedMap.class, Map.class);
		ContainerMethodDifferences.difference(TreeMap.class, Map.class);
	}
	
	@Test
	public void testStore() {
		//使用泛型来简单安全的创建复杂的模型
		//Print.print(new Store(14, 5, 10));
		Print.print(new Store(2, 3, 4));
	}
	
	@Test
	public void testErasedType() {
		//擦除
		//可以声明ArrayList.class而不能声明ArrayList<Integer>.class
		Class c1=new ArrayList<String>().getClass();
		Class c2=new ArrayList<Integer>().getClass();
		Print.print(c1==c2);//被认为是相同的类型
		//getTypeParameters返回一个对象数组，表示有泛型声明所声明的类型参数，但只能发现参数占位符的标志符
		//在泛型代码内部，无法获得任何有关泛型参数类型的信息
		Print.print(Arrays.toString(c1.getClass().getTypeParameters()));
		Print.print(Arrays.toString(c2.getClass().getTypeParameters()));
		//Java泛型是通过擦除来实现的，意味着使用泛型时，任何具体的类型信息都被擦除了，唯一知道的就是你在使用一个对象。
		//ArrayList<String>和ArrayList<Integer>在运行时事实上是相同的类型，这两种形式都被擦除成他们的“原声”类型，即List
		
		//擦除不是语言特性，它是Java的泛型实现中的一种折中，因为泛型不是Java语言出现时就有的组成部分，所以这种折中是必需的
		//泛型类型只有在{静态类型检查的期间}才出现，在此之后，程序中的所有泛型类型都被擦除，替换为他们的非泛型上界
		//P376
		//擦除主要的理由是从非泛化代码到泛化代码的转变过程，以及在不破坏现有类库的情况下，将泛型融入Java语言
		//擦除的代价也是显著的，泛型不能用于显示地引用运行时类型的操作之中
		//擦除和迁移性兼容也意味着，使用泛型并不是强制的，尽管你可能希望这样
		
		ArrayMaker<String> stringMaker=new ArrayMaker<String>(String.class);
		String[] stringArray=stringMaker.createArray(9);
		Print.print(Arrays.toString(stringArray));
		//在泛型中创建数组，使用Array.newInstance()是推荐的方式
		Object objArr=Array.newInstance(String.class, 9);
		Print.print(Arrays.toString((Object[])objArr));
		
		ListMaker<String> lm=new ListMaker<String>();
		List<String> list=lm.create();
		
		//因为擦除在方法中移除了类型信息，所以在运行时的问题就是边界：即对象进入和离开方法的地点。
		//这些正是编译器在编译期执行类型检查并插入转型代码的地点。
		//泛型的所有动作都发生在边界处--对传递进来的值进行额外的编译器检查，并插入对传递出去的值的转型。
		//使用泛型的get()对返回的值转型仍旧是需要的，只不过不需要自己来写这些代码，
		//将由编译器自动插入，因此客户端写入和读取代码的噪声更小。{****P380}
		
		//擦除丢失了在泛型代码中执行某些操作的能力，任何在运行时需要知道确切类型信息的操作都将无法工作。
		//偶尔可以绕过这些问题进行编程，有时必须通过引入类型标签来对擦除进行补偿，意味着你需要显式的传递类型的Class对象
		stringMaker.f(new Object());
		stringMaker.f("");
		TypeMaker<String> stringMaker2=new TypeMaker<String>(String.class);
		Print.print(stringMaker2.create());
		TypeMaker<Integer> intMaker=new TypeMaker<Integer>(Integer.class);
		//Print.print(intMaker.create()); //会报错，因为Integer没有无参构造器
		//使用反射的参数版本来创建某个类的对象
		Print.print(intMaker.create(47));
	}
	
	@Test
	public void testGenericsList() {
		ListMaker<Integer>[] gia;
		//****{数组将会跟踪它们的实际类型，而这个类型在数组被创建时就确定，Object[]或者是Integer[]}
		//运行时会报错，因为运行时仍然是object数组，转型会出现问题
		//gia=(ListMaker<Integer>[])new Object[10];
		//成功创建泛型数组的唯一方式就是创建一个被擦除类型的新数组，然后对其转型
		gia=(ListMaker<Integer>[])new ListMaker[10];
		gia[0]=new ListMaker<Integer>();
		//gia[0]=new Object();//
		//gia[2]=new ListMaker<Double>();
		Integer[] ia;
		//运行时会报错，因为arr实际上还是Object数组
		//Integer[] ia=gia[0].getArray();
		Object[] oa=gia[0].getArray();
		//因为有了擦除，数组的运行时类型只能是Object[]，如果立即将其转型为T[]，
		//那么编译器该数组的实际类型就会丢失，而编译器可能会错过某些潜在的错误检查
		//最好是在集合内部使用Object[]，然后当你使用数组元素时，添加一个对T的转型
		GenericArray<Integer> ga=new GenericArray<Integer>(10);
		ga.put(0, 1024*1024);
		Integer i=ga.get(0);//不会报错，因为Object[]中的实际类型是Integer
		Print.print(i);
		//ia=ga.getArray();//会报错，因为array仍然是Object[]而不是Integer[]
		ga=new GenericArray<Integer>(Integer.class, 10);
		ia=ga.getArray2();//不会报错，array在初始化的时候已经是Interger[]，运行时类型是确切类型T[]
	}
	
	//泛型方法，与所在的类是否泛型没有关系
	//static方法无法访问泛型类的类型参数，所以static方法需要使用泛型能力，就必须使其成为泛型方法
	public static <T, A, B> void f(T x, A a, B b) {
		Print.print(x.getClass().getSimpleName());
	}
	//使用泛型方法和类型参数推断来快速生成容器对象
	public <K, V> Map<K, V> map() {
		return new HashMap<K, V>();
	}
	public void g(Map<Person, List<Pet>> petPeople){}
	//泛型方法+可变参数，与标准类库中java.util.Arrays.asList()方法有相同功能
	public static <T> List<T> makeList(T... args) {
		List<T> result=new ArrayList<T>();
		for (T item:args)
			result.add(item);
		return result;
	}
	public static <T> List<T> fill(List<T> coll, Generator<T> gen, int n) {
		Print.print("call List<T> fill(List<T> coll, Generator<T> gen, int n)");
		for (int i = 0; i < n; i++) {
			coll.add(gen.next());
		}
		return coll;
	}
	//可以List和ArrayList分别重载
	public static <T> ArrayList<T> fill(ArrayList<T> coll, Generator<T> gen, int n) {
		Print.print("call ArrayList<T> fill(ArrayList<T> coll, Generator<T> gen, int n)");
		for (int i = 0; i < n; i++) {
			coll.add(gen.next());
		}
		return coll;
	}
}
//参数化的Generator接口
interface Generator<T> { T next(); }
//基本类型无法作为类型参数，不过从JavaSE5具备了打包和拆包的功能
class Fibonacci implements Generator<Integer> {
	private int count=0;
	
	@Override
	public Integer next() {
		return fib(count++);
	}
	private int fib(int n) {
		if (n<2) return 1;
		return fib(n-2)+fib(n-1);
	}
}
class IterableFibonacci extends Fibonacci implements Iterable<Integer> {
	private int count=0;
	public IterableFibonacci(int n) {count=n;}
	@Override
	public Iterator<Integer> iterator() {
		return new Iterator<Integer>() {
			private int idx=count;
			@Override
			public void remove() {
				throw new UnsupportedOperationException();
			}
			
			@Override
			public Integer next() {
				idx--;
				return IterableFibonacci.this.next();
			}
			
			@Override
			public boolean hasNext() {
				return idx>0;
			}
		};
	}
	
}

class PetGenerator implements Generator<Pet>, Iterable<Pet> {
	private Class[] types = {
			Pet.class, Dog.class, Mutt.class, Pug.class, Cat.class, Manx.class
	};
	private Random rand=new Random(47);
	public PetGenerator() {}
	
	private int size=0;
	public PetGenerator(int sz) {size=sz;}

	@Override
	public Iterator<Pet> iterator() {
		return new PetIterator(); 
	}

	@Override
	public Pet next() {
		try {
			return (Pet)types[rand.nextInt(types.length)].newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	private class PetIterator implements Iterator<Pet> {
		int count=size;
		@Override
		public boolean hasNext() {
			return count>0;
		}
		@Override
		public Pet next() {
			count--;
			return PetGenerator.this.next();
		}
		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}
}
//更通用的Generator对象，可以为任何类构造对象，只要该类是公共并且有默认的构造器
class BasicGenerator<T> implements Generator<T> {
	private Class<T> type;
	public BasicGenerator(Class<T> type){this.type=type;}

	@Override
	public T next() {
		try {
			return type.newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	//为了减少类型声明
	public static <T> Generator<T> create(Class<T> type) {
		return new BasicGenerator<T>(type);
	}
}
enum Watercolors {
	ZINC, LEMOM_YELLOW, MEDIUM_YELLOW, DEEP_YELLOW, ORANGE, BRILLIANT_RED, CRIMSON, 
	ROSE_MADDER, VIOLET
}
class ContainerMethodDifferences {
	//查看不同类之间的方法差异
	static Set<String> methodSet(Class<?> type) {
		Set<String> result=new TreeSet<String>();
		for (Method method:type.getMethods()) {
			result.add(method.getName());
		}
		return result;
	}
	static void interfaces(Class<?> type) {
		Print.printnb("interfaces in "+type.getSimpleName()+": ");
		List<String> result=new ArrayList<String>();
		for (Class<?> c:type.getInterfaces()) {
			result.add(c.getSimpleName());
		}
		Print.print(result);
	}
	static Set<String> object=methodSet(Object.class);
	static {object.add("clone");}
	static void difference(Class<?> superset, Class<?> subset) {
		Print.printnb(superset.getSimpleName()+ " extends "+subset.getSimpleName()+", adds: ");
		Set<String> comp=Sets.<String>difference(methodSet(superset), methodSet(subset));
		comp.removeAll(object);
		Print.print(comp);
		interfaces(superset);
	}
}

class Product {
	private final int id;
	private String description;
	private double price;
	public Product(int IDnumber, String descr, double price) {
		id=IDnumber;
		description=descr;
		this.price=price;
		Print.print(toString());
	}
	public String toString() {
		return id+": "+description+", price: "+price;
	}
	public void priceChange(double change) {
		price+=change;
	}
	public static Generator<Product> generator = new Generator<Product>() {
		private Random rand=new Random(47);
		@Override
		public Product next() {
			return new Product(rand.nextInt(1000), "Test", Math.round(rand.nextDouble()*1000.0)+0.99);
		}
	};
}
class Shelf extends ArrayList<Product> {
	public Shelf(int nProducts) {
		GenericTest.fill(this, Product.generator, nProducts);
	}
}
class Aisle extends ArrayList<Shelf> {
	public Aisle(int nShelves, int nProdects) {
		for (int i = 0; i < nShelves; i++) {
			this.add(new Shelf(nProdects));
		}
	}
}
class CheckoutStand{}
class Office{}
class Store extends ArrayList<Aisle> {
	private ArrayList<CheckoutStand> checkouts=new ArrayList<CheckoutStand>();
	private Office office=new Office();
	public Store(int nAisle, int nShelves, int nProducts) {
		for (int i = 0; i < nAisle; i++) {
			this.add(new Aisle(nShelves, nProducts));
		}
	}
	public String toString() {
		StringBuilder result=new StringBuilder();
		for (Aisle a:this)
			for (Shelf s:a)
				for (Product p:s) {
					result.append(p);
					result.append("\n");
				}
		return result.toString();
	}
}
//泛型的工厂对象
class ArrayMaker<T> {
	//即使kind被存储为Class<T>，擦除也意味着它实际将被存储为Class，没有任何参数
	//因此在使用它创建数组时，Array.newInstance实际上并未拥有kind所蕴含的类型信息，因此这不会产生具体结果，必须手动转型，会产生一条警告
	//在泛型中创建数组，使用Array.newInstance()是推荐的方式
	private Class<T> kind;
	public ArrayMaker(Class<T> kind){this.kind=kind;}
	@SuppressWarnings("unchecked")
	T[] createArray(int size) {
		//产生的是String数组，不过需要显式的转型
		Object obj=Array.newInstance(kind, size);
		return (T[])obj;
	}
	public void f(Object arg) {
		//if (arg instanceof T){}
		//T var=new T();
		try {
			T var=kind.newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//可以使用传递一个工厂对象类创建新的实例，这个类必须有无参的构造器
		//T[] array=new T[10];
		//T[] array=(T)new Object[10];
		Print.print(kind.isInstance(arg));
	}

}
class ListMaker<T> {
	//编译器不会给出警告
	List<T> create() {return new ArrayList<T>();} 
	
	private T[] arr=(T[])new Object[3];
	public T[] getArray() {
		return arr;
	}
}
interface FactoryI<T> {
	//T create();
	T create(int arg);
}
class TypeMaker<T> {
	//传递一个工厂对象类创建新的实例，可以是Class<T>内建的工厂对象，
	//也可以是实现FactoryI来创建一个显式的工厂对象，可以获得编译器检查。建议方式，可以限制其类型，使只能接受实现了这个工厂的类
	//还可以使用模板设计模式，在具体子类中实现create方法
	private T x;
	
	//第一种
	private Class<T> kind;
	public TypeMaker(Class<T> kind){
		this.kind=kind;
		try {
			//如果是Integer则会报错，因为没有无参的构造器
			//x=kind.newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public <F extends FactoryI<T>> TypeMaker(F factory) {//第二种
		x=factory.create(0);
	}
	
	//使用Class对象的方法来创建对象，必须具有无参构造器
	public T create() {
		try {
			//
			return kind.newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		//return null;
	}
	
	//使用有Class对象中带参数的构造器来创建对象
	public T create(int arg) {
		try {
			for (Constructor<?> ctor:kind.getConstructors()) {
				Class<?>[] params=ctor.getParameterTypes();
				if (params.length==1 && params[0]==int.class) {
					return kind.cast(ctor.newInstance(arg));
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return null;
	}
	
	private Map<String, Class<?>> types=new HashMap<String, Class<?>>();
	public void addType(String typename, Class<?> kind) {
		types.put(typename, kind);
	}
	public Object createNew(String typename) {
		Class<?> clazz=types.get(typename);
		try {
			return clazz.newInstance();
		} catch (NullPointerException e) {
			return null;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	private Map<String, FactoryI<?>> factories=new HashMap<String, FactoryI<?>>();
	public Object createNew(String typename, int arg) {
		FactoryI<?> factory=factories.get(typename);
		try {
			return factory.create(arg);
		} catch (NullPointerException e) {
			return null;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
class GenericArray<T> {
	private Object[] array;
	public GenericArray(int sz) {
		array=new Object[sz];
	}
	public void put(int index, T item) {
		array[index]=item;
	}
	@SuppressWarnings("unchecked")
	public T get(int index) {
		return (T)array[index];
	}
	@SuppressWarnings("unchecked")
	public T[] getArray() {
		return (T[])array;
	}
	
	private T[] array2;
	@SuppressWarnings("unchecked")
	public GenericArray(Class<T> type, int sz) {
		array2=(T[])Array.newInstance(type, sz);
	}
	public T[] getArray2() {
		return array2;
	}
}

