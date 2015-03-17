package org.googlecode.java.thinking.typeinfo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.googlecode.java.thinking.basic.util.Print;
import org.googlecode.java.thinking.basic.util.TypeCounter;
import org.junit.Test;

public class PetsTest {
	static class PetCounter extends HashMap<String, Integer> {
		public void count(String type) {
			Integer quantity=get(type);
			if (quantity==null)
				put(type, 1);
			else
				put(type, quantity+1);
		}
	}
	static class PetCounter2 extends LinkedHashMap<Class<? extends Pet>, Integer> {
		public PetCounter2() {
			super();
		}
		public void count(Pet pet) {
			for (Map.Entry<Class<? extends Pet>, Integer> pair : entrySet())
				if (pair.getKey().isInstance(pet))
					put(pair.getKey(), pair.getValue()+1);
		}
		@Override
		public String toString() {
			StringBuilder result=new StringBuilder("{");
			for (Map.Entry<Class<? extends Pet>, Integer> pair : entrySet()) {
				result.append(pair.getKey().getSimpleName());
				result.append("=");
				result.append(pair.getValue());
				result.append(", ");
			}
			result.delete(result.length()-2, result.length());
			result.append("}");
			return result.toString();
		}
	}
	
	public static void countPets(PetCreator creator) {
		PetCounter counter=new PetCounter();
		for (Pet pet : creator.createArray(20)) {
			Print.printnb(pet.getClass().getSimpleName()+" ");
			if (pet instanceof Pet)
				counter.count("Pet");
			if (pet instanceof Dog)
				counter.count("Dog");
			if (pet instanceof Mutt)
				counter.count("Mutt");
			if (pet instanceof Pug)
				counter.count("Pug");
			if (pet instanceof Cat)
				counter.count("Cat");
			if (pet instanceof Manx)
				counter.count("Manx");
		}
		Print.print();
		Print.print(counter);
	}
	
	public static void countPets2() {
		PetCounter2 petCount=new PetCounter2();
		for (Pet pet : createArray(20)) {
			Print.printnb(pet.getClass().getSimpleName()+" ");
			petCount.count(pet);
		}
		Print.print();
		Print.print(petCount);
	}
	
	public static void countPets3() {
		TypeCounter petCount=new TypeCounter(Pet.class);
		for (Pet pet : createArray(20)) {
			Print.printnb(pet.getClass().getSimpleName()+" ");
			petCount.count(pet);
		}
		Print.print();
		Print.print(petCount);
	}
	
	public static final PetCreator creator=new LiteralPetCreator();
	public static Pet[] createArray(int size) {
		return creator.createArray(size);
	}
	public static Pet randomPet() {
		return creator.randomPet();
	}
	public static ArrayList<Pet> arrayList(int size) {
		return creator.arrayList(size);
	}
	
	@Test
	public void test() {
		//countPets(new ForNameCreator());
		//countPets(creator);
		//countPets2();
		countPets3();
	}
}

abstract class PetCreator {
	private Random rand=new Random(47);
	public abstract List<Class<? extends Pet>> types();
	public Pet randomPet() {
		int n=rand.nextInt(types().size());
		try {
			return types().get(n).newInstance();
		} catch (InstantiationException e) {
			throw new RuntimeException();
		} catch (IllegalAccessException e) {
			throw new RuntimeException();
		}
	}
	public Pet[] createArray(int size) {
		Pet[] result=new Pet[size];
		for (int i = 0; i < result.length; i++) {
			result[i]=randomPet();
		}
		return result;
	}
	public ArrayList<Pet> arrayList(int size) {
		ArrayList<Pet> result=new ArrayList<Pet>();
		Collections.addAll(result, createArray(size));
		return result;
	}
	
}
// 模板设计模式
class ForNameCreator extends PetCreator {
	private static List<Class<? extends Pet>> types=new ArrayList<Class<? extends Pet>>();
	private static String[] typeNames= {
		"org.googlecode.java.thinking.typeinfo.Dog",
		"org.googlecode.java.thinking.typeinfo.Mutt",
		"org.googlecode.java.thinking.typeinfo.Pug",
		"org.googlecode.java.thinking.typeinfo.Cat",
		"org.googlecode.java.thinking.typeinfo.Manx"
	};
	
	@SuppressWarnings("unchecked")
	private static void loader() {
		try {
			for (String name: typeNames)
				types.add((Class<? extends Pet>)Class.forName(name));
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
	static { loader(); }
	
	@Override
	public List<Class<? extends Pet>> types() {
		return types;
	}
	
}

class LiteralPetCreator extends PetCreator {
	
	@SuppressWarnings("unchecked")
	public static final List<Class<? extends Pet>> allTypes = 
			Collections.unmodifiableList(Arrays.asList(
					Pet.class, Dog.class, Mutt.class, Pug.class, Cat.class, Manx.class));
	private static final List<Class<? extends Pet>> types = 
			allTypes.subList(allTypes.indexOf(Cat.class), allTypes.size());
	

	@Override
	public List<Class<? extends Pet>> types() {
		//return types;
		return allTypes;
	}
	
}

class Individual {
	public String id;
	public String name;
	public Individual() { }
	public Individual(String name) { this.name=name; }
}

class Person extends Individual {
	public Person(String name) { super(name); }
}

class Pet extends Individual {
	Pet() { super(); }
	Pet(String name) { super(name); }
}

class Dog extends Pet {

	public Dog() {
		super();
	}

	public Dog(String name) {
		super(name);
	}
}
class Mutt extends Dog {

	public Mutt() {
		super();
	}

	public Mutt(String name) {
		super(name);
	}
}
class Pug extends Dog {

	public Pug() {
		super();
	}

	public Pug(String name) {
		super(name);
	}
}
class Cat extends Pet {

	public Cat() {
		super();
	}

	public Cat(String name) {
		super(name);
	}
}
class Manx extends Cat {

	public Manx() {
		super();
	}

	public Manx(String name) {
		super(name);
	}
}

