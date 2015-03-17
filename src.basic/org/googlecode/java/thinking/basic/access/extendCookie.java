package org.googlecode.java.thinking.basic.access;

import org.googlecode.java.thinking.basic.access.superclass.Cookie;

public class extendCookie extends Cookie {
	
	public void testMethod() {
		protectedMethod();
		publicMethod();
		//internalMethod();
	}
}
