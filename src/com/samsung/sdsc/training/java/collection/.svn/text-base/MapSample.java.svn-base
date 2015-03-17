package com.samsung.sdsc.training.java.collection;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MapSample {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Map<Integer, Employe> map = new HashMap<Integer, Employe>();
		List<Employe> c = new LinkedList<Employe>();
		c.add(new Employe(1, "兴龙"));
		c.add(new Employe(2, "蒋伟"));
		c.add(new Employe(3, "海华"));
		c.add(new Employe(4, "朱丽"));
		c.add(new Employe(5, "段宁"));
		for (Employe e : c) {
			map.put(e.getId(), e);
		}

		System.out.println(map.containsKey(3));
		System.out.println(map.containsKey(8));
		System.out.println(map.size());
		System.out.println(map.get(4));
		System.out.println(map.get(39));
		System.out.println(map.keySet());
		System.out.println(map.values());
	}
}
