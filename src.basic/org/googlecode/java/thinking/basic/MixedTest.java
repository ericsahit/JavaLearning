package org.googlecode.java.thinking.basic;

import org.googlecode.java.thinking.basic.util.Print;
import org.junit.Test;

public class MixedTest {

	@Test
	public void testiplusplus() {
		int i=0;
		i=i++;
		//i=++i;
		Print.info(i);
		
		//i++ 在i所在的表达式中使用i的当前值后，让i加１
		//++i 预先在i基础上加1,然后在i所在的表达式中使用i的新值
	}
	
	@Test
	public void testplusplusi() {
		int i=0;
		i=++i;
		Print.info(i);
	}
	
}
