package com.samsung.sdsc.training.java.collection;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class CollectionSample {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<Employe> c = new LinkedList<Employe>();
		c.add(new Employe(1, "兴龙"));
		c.add(new Employe(7, "蒋伟"));
		c.add(new Employe(3, "海华"));
		c.add(new Employe(4, "朱丽"));
		c.add(new Employe(5, "段宁"));
		System.out.println(c.size());
		System.out.println(c);
		System.out.println(c.contains(new Employe(3, "段宁")));
		Collections.sort(c, new Comparator<Employe>() {
			@Override
			public int compare(Employe o1, Employe o2) {
				return o2.getId() - o1.getId();
//				return o2.getName().compareTo(o1.getName());
			}
		});
		System.out.println(c);
	}
}
