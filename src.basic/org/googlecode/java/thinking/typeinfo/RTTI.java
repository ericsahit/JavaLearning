package org.googlecode.java.thinking.typeinfo;


class Useful {
	public void f() { }
	public void g() { }
}

class MoreUseful extends Useful {
	public void f() { }
	public void g() { }
	public void u() { }
	public void v() { }
}

public class RTTI {
	
	public void test() {
		Useful[] x = {
			new Useful(),
			new MoreUseful()
		};
		x[0].f();
		x[1].g();
		// 编译时类型识别
		//x[1].u();
		
		((MoreUseful)x[1]).u();
		
		// RTTI
		//((MoreUseful)x[0]).u();
	}
}
