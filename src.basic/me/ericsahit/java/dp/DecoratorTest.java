package me.ericsahit.java.dp;

import java.io.IOException;

public class DecoratorTest {
	public void test() {
		
		DInputStream origin = new TrueInpuStream();
		DInputStream decorator = new DConcreteInputStream(origin);
		decorator.read();
	}
}

interface DInputStream {
	void read();
}

class TrueInpuStream implements DInputStream {
	@Override
	public void read() {
		System.out.println("TrueInputStream read.");
	}
}

class DConcreteInputStream implements DInputStream {
	
	private DInputStream source;
	public DConcreteInputStream(DInputStream in) {
		source = in;
	}
	@Override
	public void read() {
		//java.io.BufferedInputStream
		System.out.println("Concrete input stream read");
		source.read();
	}
}


