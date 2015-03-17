package org.googlecode.java.thinking.typeinfo;

import java.util.ArrayList;
import java.util.List;

import org.googlecode.java.thinking.basic.util.Print;

class CountedInteger {
	private static long counter;
	private final long id=counter++;
	
	public String toString() { return Long.toString(id); }
}

public class FilledList<T> {
	private Class<T> type;
	public FilledList(Class<T> classType) {
		this.type=classType;
	}
	public List<T> create(int nElements) {
		List<T> result = new ArrayList<T>(nElements);
		try {
			for (int i = 0; i < nElements; i++) {
				result.add(type.newInstance());
				Print.info(result.get(i));
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		return result;
	}
	
}
