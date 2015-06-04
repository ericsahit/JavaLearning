package me.ericsahit.java.dp;

import java.util.List;

public class CompositTest {

}

abstract class Compoment {
	abstract void operation();
	
	abstract void add(Compoment c);
	
	abstract Compoment remove(Compoment c);
	
	abstract Compoment getChild(int i);
}

class Leaf extends Compoment {

	@Override
	void operation() {
		
	}

	@Override
	void add(Compoment c) {
	}

	@Override
	Compoment remove(Compoment c) {
		return null;
	}

	@Override
	Compoment getChild(int i) {
		return null;
	}
	
}

class Node extends Compoment {
	
	private List<Compoment> children;

	@Override
	void operation() {
		for (Compoment c: children) {
			c.operation();
		}
	}

	@Override
	void add(Compoment c) {
		children.add(c);
	}

	@Override
	Compoment remove(Compoment c) {
		children.remove(c);
		return null;
	}

	@Override
	Compoment getChild(int i) {
		return children.get(i);
	}
	
}

