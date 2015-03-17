package org.googlecode.java.thinking.basic.util;

import java.util.Collection;
//添加新行并且缩排所有元素的工具
public class PPrint {
	public static String pformat(Collection<?> c) {
		if (c.size()==0) return "[]";
		StringBuilder result=new StringBuilder("[");
		for (Object elem:c) {
			if (c.size()!=1)
				result.append("\n ");
			result.append(elem);
		}
		if (c.size()!=1)
			result.append("\n");
		result.append("]");
		return result.toString();
	}
	public static void pprint(Collection<?> c) {
		Print.print(pformat(c));
	}
}
