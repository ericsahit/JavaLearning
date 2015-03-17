package org.googlecode.java.thinking.basic.access.superclass;

public class internalAnotherClass {
	
	public void testAccess() {
		Cookie c = new Cookie();
		c.internalMethod();
		//protected也提供包访问权限
		c.protectedMethod();
		c.publicMethod();
	}
}
