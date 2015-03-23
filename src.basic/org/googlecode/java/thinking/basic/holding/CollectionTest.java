package org.googlecode.java.thinking.basic.holding;

import java.util.*;

import org.googlecode.java.thinking.basic.util.Print;
import org.junit.Test;


public class CollectionTest {

	//@Test
	public void testCollection() {
		
		//Collection是独立元素的序列，这些元素都服从一条或者多条规则。
		//List按照插入的顺序来保存元素
		//Set不能有重复的元素，add重新元素会自动去重
		//Queue按照排队规则来确定对象产生的顺序
		//Map关联数组
		Collection<Integer> c;
		printCollection(new ArrayList<Integer>());
		
		printCollection(new HashSet<Integer>());
		
		c=new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5));
		Integer[] moreInts={6, 7, 8, 9, 10};
		c.addAll(Arrays.asList(moreInts));
		
		Collections.addAll(c, 11, 12, 13, 14, 15);
		Collections.addAll(c, moreInts);
		
		//将Arrays.asList输出，将其当成List，但是底层仍表示的是数组，因此不能调整尺寸
		List<Integer> list=Arrays.asList(16, 17, 18, 19);
		list.set(1, 99);
		//不能执行add操作
		//list.add(20);
		
		//HashSet是最快获取元素的方式，存储的顺序是不关心的，只关心某object是否出现，内部实现使用HashMap
		//
		Print.print(fill(new HashSet<String>()));
		//TreeSet按照比较结果的升序来保存对象，内部实现使用TreeMap
		Print.print(fill(new TreeSet<String>()));
		//LinkedHashSet按照被添加的顺序来保存对象，内部实现使用LinkedHashMap
		Print.print(fill(new LinkedHashSet<String>()));
		
		new Hashtable<String, String>();
		
		Print.print(fill(new HashMap<String, String>()));
		Print.print(fill(new TreeMap<String, String>()));
		Print.print(fill(new LinkedHashMap<String, String>()));
	}
	//@Test
	public void testList() {
		//List比较Collection，可以在中间插入和删除元素，一种“可修改的序列”
		//ArrayList，基本的，长处是随机访问元素
		//LinkedList，随机访问较慢，提供了优化的顺序访问和中间的插入和删除操作
		//List的remove等方法依赖的都是object的equals方法，所以会根据equals方法的不同而表现不同
	}
	//@Test
	public void testIteration() {
		//迭代器是一个对象，它的工作是遍历并且选择序列中的对象，而客户端程序员不必知道或关心该序列底层的结构。
		//轻量级对象，创建代价小。Java的迭代器只能单向移动，可以将返回的元素删除。
		//迭代器统一了对容器的访问方式。
		List<Pet> pets=PetsTest.arrayList(12);
		Iterator<Pet> it=pets.iterator();
		while(it.hasNext()) {
			Pet p=it.next();
			//Print.print(p.getClass().getSimpleName()+" ");
		}
		for (Pet p:pets)
			Print.print(p.getClass().getSimpleName()+" ");

		it=pets.iterator();
		for (int i = 0; i < 6; i++) {
			it.next();
			it.remove();
		}
		Print.print(pets);
	}
	//@Test
	public void testLinkedList() {
		//ListIterator是一个更加强大的Iterator的子类型，只能用于各种List类的访问。允许双向移动
		LinkedList<Pet> pets=new LinkedList<Pet>(PetsTest.arrayList(5));
		Print.print(pets);
		
		//作用相同，都是返回列表的头
		Print.print(pets.getFirst());
		Print.print(pets.element());
		//getFirst和element在列表为空时候触发异常，peek则返回null
		Print.print(pets.peek());
		//作用相同，删除第一个元素
		Print.print(pets.remove());
		Print.print(pets.removeFirst());
		//与remove和removeFirst相同，在空时候不会异常，返回null
		Print.print(pets.poll());
		Print.print(pets);
		
		pets.addFirst(new Dog());
		Print.print(pets);
		//addFirst是插入到头部
		//offer,add和addLast相同，都是将某个元素插入到元素的尾（端）部
		pets.offer(new Dog());
		Print.print(pets);
		
		pets.add(new Dog());
		Print.print(pets);
		
		pets.addLast(new Dog());
		Print.print(pets);
		
		pets.removeLast();
		Print.print(pets);
	}
	//@Test
	public void testStack() {
		Stack<String> stack=new Stack<String>();
		for (String s:"My dog has fleas".split(" "))
			stack.push(s);
		while (!stack.isEmpty())
			Print.print(stack.pop());
		//java.util.Stack<E>
	}
	//@Test
	public void testSet() {
		//Set不保存重复的元素。最常使用的是测试归属行(使用Contains)，询问某个对象是否在某个Set中，查找是Set重要操作。
		//Set是基于对象的“值”来确定归属性的
		//HashSet的存储顺序没有规律，是因为出于速度原因，采用了散列存储
		//TreeSet是将元素存储到红-黑树数据结构中
		//LinkedHashSet也是用了散列，但是看起来是用了链表来维护元素的插入顺序
		printRandomSet(new HashSet<Integer>());
		printRandomSet(new TreeSet<Integer>());
		
		Set<String> words=new TreeSet<String>(String.CASE_INSENSITIVE_ORDER);
	}
	//@Test
	public void testQueue() {
		//LinkedList提供了方法以指出队列的行为，并且它实现了Queue接口，可以作为Queue的一种实现
		Queue<Integer> queue=new LinkedList<Integer>();
		Random rand=new Random(47);
		for (int i = 0; i < 10; i++) {
			//offer在允许的情况下将一个元素插入到队尾，或者返回false
			queue.offer(rand.nextInt(i+10));
		}
		//peek和element都在“不移除”情况下返回对头，区别同上
		//remove和poll都是移除并返回队头，但是poll(peek)为空时返回null，remove(element)抛出异常
		printQ(queue);
		
		//PriorityQueue优先级队列
		//PriorityQueue调用offer方法插入一个对象时，这个对象会在队列中被排序
		//默认的排序顺序是使用对象在队列中的自然顺序，可以通过提供自己的Comparator来修改这个顺序
		//PriorityQueue可以确保peek/poll/remove方法时，获取的元素将是队列中优先级最高的元素
		PriorityQueue<Integer> priorityQueue=
				new PriorityQueue<Integer>(10, Collections.reverseOrder());
		for (int i = 0; i < 10; i++) {
			//offer在允许的情况下将一个元素插入到队尾，或者返回false
			priorityQueue.offer(rand.nextInt(i+10));
		}
		printQ(priorityQueue);
		
		//List<Integer> ints=Arrays.asList(25, 22, 20, 18, 14, 9, 3, 1, 1, 2, 3, 9, 14, 18, 21, 23, 25);
		
	}
	@Test
	public void testCollectionInterface() {
		//CollectionSequence继承了AbstractCollection
		//如果实现Collection，就必须实现Iterator和size等很多方法
		CollectionSequence c=new CollectionSequence();
		for (Pet p:c) Print.printnb(p+" ");
		Print.print();
		//如果自定义的类已经实现了其他类，那么就不能再继承AbstractCollection。
		//这种情况下，要实现Collection，就必须实现接口的所有方法。所以不实现Collection，只实现Iterable迭代器会简单许多。
		//Iterable接口包含一个能够产生Iterator的Iterator()方法，并且被foreach用来在序列中移动
		//所有的Collection类(不包括各种Map)都是Iterable类型
		NonCollectionSequence nc=new NonCollectionSequence();
		for (Pet p:nc) Print.printnb(p+" ");
		Print.print();
		Iterator<Pet> it=nc.iterator();
		while (it.hasNext()) Print.printnb(it.next()+" ");
		//entrySet产生一个由Map.Entry的元素构成的Set
		for (Map.Entry<String, String> entry:System.getenv().entrySet()) {
			Print.print(entry.getKey()+": "+entry.getValue());
		}
		//数组不一定是一个Iterable，但是可以用于foreach语句
		//尝试把数组当作一个Iterable参数传递会导致失败。
		
		ReversibleArrayList<String> ral=new ReversibleArrayList<String>(
				Arrays.asList("To be or not to be".split(" ")));
		for (String s:ral)
			Print.printnb(s+" ");
		Print.print();
		for (String s:ral.reversed())
			Print.printnb(s+" ");
		
	}
	//适配器模式的应用，在默认的前向迭代器的基础上，增加反向迭代器的能力，因此不能使用覆盖，
	//而是添加了一个能够产生Iterable对象的方法,该对象可以用于foreach语句
	class ReversibleArrayList<T> extends ArrayList<T> {
		public ReversibleArrayList(Collection<T> c) { super(c); }
		public Iterable<T> reversed() {
			return new Iterable<T>() {
				@Override
				public Iterator<T> iterator() {
					return new Iterator<T>() {
						private int current=size()-1;
						@Override
						public boolean hasNext() {
							return current>-1;
						}

						@Override
						public T next() {
							return get(current--);
						}

						@Override
						public void remove() {
							throw new UnsupportedOperationException();
						}
					};
				}
			};
		}
	}
	
	class CollectionSequence extends AbstractCollection<Pet> {
		private Pet[] pets=PetsTest.createArray(8);
		@Override
		public Iterator<Pet> iterator() {
			return new Iterator<Pet>() {
				private int index=0;
				
				@Override
				public void remove() {
					throw new UnsupportedOperationException();
				}
				@Override
				public Pet next() {
					return pets[index++];
				}
				@Override
				public boolean hasNext() {
					return index<pets.length;
				}
			};
		}

		@Override
		public int size() {
			return pets.length;
		}
	}
	class PetSequence {
		protected Pet[] pets=PetsTest.createArray(8);
	}
	class NonCollectionSequence extends PetSequence implements Iterable<Pet> {

		@Override
		public Iterator<Pet> iterator() {
			return new Iterator<Pet>() {
				private int index=0;
				
				@Override
				public void remove() {
					throw new UnsupportedOperationException();
				}
				@Override
				public Pet next() {
					return pets[index++];
				}
				@Override
				public boolean hasNext() {
					return index<pets.length;
				}
			};
		}
		
	}
	
	
	class Stack<T> {
		private LinkedList<T> storage=new LinkedList<T>();
		public void push(T v) { storage.addFirst(v); }
		public T pop() { return storage.removeFirst(); }
		public T peek() { return storage.peek(); }
		public boolean isEmpty() { return storage.isEmpty(); }
		public String toString() { return storage.toString(); }
	}
	
	public Map<String, String> fill(Map<String, String> map) {
		
		map.put("rat", "Fuzzy");
		map.put("cat", "Rags");
		map.put("dog", "Bosco");
		map.put("dog", "Spot");
		return map;
	}
	
	public void printRandomSet(Set<Integer> set) {
		Random rand=new Random(47);
		for (int i = 0; i < 10000; i++) {
			set.add(rand.nextInt(30));
		}
		Print.print(set);
	}
	
	public Collection<String> fill(Collection<String> collection) {
		collection.add("rat");
		collection.add("cat");
		collection.add("dog");
		collection.add("dog");
		return collection;
	}
	
	public void printQ(Queue queue) {
		while (queue.peek()!=null)
			Print.print(queue.remove());
		Print.print();
	}
	
	public void printCollection(Collection<Integer> c) {
		for (int i = 0; i < 10; i++) {
			c.add(i);
		}
		for (Integer i:c)
			Print.print(i);
	}
}
