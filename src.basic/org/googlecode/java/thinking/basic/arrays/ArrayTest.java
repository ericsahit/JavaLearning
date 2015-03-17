package org.googlecode.java.thinking.basic.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import org.googlecode.java.thinking.basic.util.CountingGenerator;
import org.googlecode.java.thinking.basic.util.Generator;
import org.googlecode.java.thinking.basic.util.Print;
import org.junit.Test;

public class ArrayTest {
	
	@Test
	public void testArray() {
		//构成矩阵的每个向量都可以具有任意的长度
		int[][] a={
				{1,2,3},
				{4,5},
				{6}
		};
		//deepToString可以将多维数组转换为多个String
		Print.print(Arrays.deepToString(a));
		//基本类型的值在不进行初始化时，会被自动初始化。对象数组会被初始化为null
		int[][][] b=new int[2][2][4];
		Print.print(Arrays.deepToString(b));
		
		//自动包装机制对数组初始化也起作用
	}
	@Test
	public void testParameterizedArrayType() {
		//测试数组与泛型，通常不能实例化具有参数化类型的数组
		//List<String>[] lists=new ArrayList<String>[10];
		//但是，可以参数化数组本身的类型：
		Integer[] ints={1, 2, 3, 4, 5};
		Double[] doubles={1.1, 2.2, 3.3, 4.4, 5.5};
		Integer[] ints2=new ClassParameter<Integer>().f(ints);
		//使用参数化方法的方便之处：你不必为需要应用的每种不同的类型都使用一个参数而去实例化这个类
		ints2=MethodParameter.f(ints);
		
		//但是允许建立对这种数组的引用
		List<String>[] ls;
		List[] la=new List[10];
		ls=(List<String>[])la;
		ls[0]=new ArrayList<String>();
		//ls[1]=new ArrayList<Integer>();//不能赋值，一旦拥有了强类型引用，就会得到某些编译器检查
		
		Object[] objects=ls;
		//数组是协变类型的，因此List<String>[]也是一个Object[]
		objects[1]=new ArrayList<Integer>();//这样就可以赋值,不会有任何编译或运行时的错误
		//{泛型容器总是比泛型数组更好的选择}
	}
	@Test
	public void testGenerators() {
		//测试创建测试数据
		boolean[] a1=new boolean[6];
		Arrays.fill(a1, true);
		String[] a2=new String[6];
		Arrays.fill(a2, 3, 5, "World");
		Print.print(Arrays.asList(a2));
		
		testGenerator(CountingGenerator.class);
	}
	@Test
	public void testArraysUtil() {
		int[] i=new int[7];
		int[] j=new int[10];
		Arrays.fill(i, 47);
		Arrays.fill(j, 99);
		Print.print(Arrays.toString(i));//打印数组
		System.arraycopy(i, 0, j, 0, i.length);//复制数组，要比for循环复制快很多
		Print.print(Arrays.toString(j));
		
		int[] a1=new int[10];
		int[] a2=new int[10];
		Arrays.fill(a1, 47);
		Arrays.fill(a2, 47);
		//基本类型做过重载，使用相当比较。Integer或者其他Object使用equals方法
		Print.print(Arrays.equals(a1, a2));//依此比较数组的元素
		
		//使用策略模式来实现数组元素的比较
		CompType[] a=new CompType[12];
		for (int k = 0; k < a.length; k++) {
			a[k]=CompType.generator().next();
		}
		Print.print(Arrays.toString(a));
		//如果没有实现Comparable接口，就会报ClassCastException
		//{排序算法是针对正排序的特殊类型做了优化--针对基本类型使用“快速排序”，针对对象设计的“稳定归并排序”}
		Arrays.sort(a);
		//使用不同的Comparator
		Arrays.sort(a, Collections.reverseOrder());
		Arrays.sort(a, CompType.comparator());
		Print.print(Arrays.toString(a));
		
		int[] a3=new int[]
				{
				424, 589, 322, 888
				};
		Arrays.sort(a3);
		//对数组进行快速查找，内部采用折半查找，找到目标则返回index，否则返回负数(-插入点-1)
		//如果含有重复元素，则无法保证是哪个
		Print.print(Arrays.binarySearch(a3, 888));
		Print.print(Arrays.binarySearch(a3, 887));
		//经过Comparator排序的对象数组，使用binarySearch也必须传入Comparator，才能正确进行查找
		//{出于性能的考虑，要设计基本类型，也同时设计数组}
		//有一些语言根本就没有尺寸固定的低级数组，它们只有尺寸可调的容器，例如Python
	}
	
	public static void testGenerator(Class<?> surroundingClass) {
		for (Class<?> type:surroundingClass.getClasses()) {
			Print.printnb(type.getSimpleName()+": ");
			try {
				Generator<?> g=(Generator<?>)type.newInstance();
				for (int i = 0; i < 10; i++) {
					Print.printnb(g.next()+" ");
				}
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			Print.print();
		}
	}
}
class ClassParameter<T> {
	public T[] f(T[] arg) {return arg;}
}
class MethodParameter {
	public static <T> T[] f(T[] arg) {return arg;}
}
//实现Comparable接口
class CompType implements Comparable<CompType> {
	int i;
	int j;
	private static int count=1;
	public CompType(int n1,int n2) {
		i=n1;
		j=n2;
	}
	public String toString() {
		String result="[i="+i+", j="+j+"]";
		if (count++%3==0)
			result+="\n";
		return result;
	}
	@Override
	public int compareTo(CompType o) {
		return (i<o.i?-1:(i==o.i?0:1));
	}
	private static Random rand=new Random(47);
	public static Generator<CompType> generator() {
		return new Generator<CompType>() {
			@Override
			public CompType next() {
				return new CompType(rand.nextInt(100), rand.nextInt(100));
			}
		};
	}
	public static CompTypeComparator comparator() {
		return new CompTypeComparator();
	}
	public static class CompTypeComparator implements Comparator<CompType> {
		@Override
		public int compare(CompType o1, CompType o2) {
			return (o1.j<o2.j?-1:(o1.j==o2.j?0:1));
		}
	}
}

