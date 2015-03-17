package org.googlecode.java.thinking.basic.exceptions;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Logger;

import org.googlecode.java.thinking.basic.util.Print;
import org.junit.Test;

import com.mysql.jdbc.BufferRow;

public class ExceptionTest {
	private static Logger logger=Logger.getLogger("ExceptionTest");

	//@Test
	public void testLoggingException() {
		try {
			throw new LoggingException();
		} catch (LoggingException e) {
			System.err.println("Caught "+e);
		}
		
		try {
			throw new LoggingException();
		} catch (LoggingException e) {
			System.err.println();
			//System.err.println("Caught "+e);
			StringWriter trace=new StringWriter();
			//printStackTrace打印Throwable和Throwable的调用栈轨迹。调用栈显示了“把你带到异常抛出点”的方法地用序列。
			e.printStackTrace(new PrintWriter(trace));
			logger.severe(trace.toString());
			//打印栈轨迹
			for (StackTraceElement ste:e.getStackTrace())
				Print.print(ste.getClassName()+ " " +ste.getMethodName());
		}
		//捕获异常时候，如果继续抛出本异常
		try {
			throwExp();
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	@Test
	public void testDynamicFieldsException() {
		DynamicFields df=new DynamicFields(3);
		Print.print(df);
		try {
			df.setField("d", "A value for d");
			df.setField("number", 47);
			df.setField("number2", 48);
			Print.print(df);
			df.setField("d", "A new value for d");
			df.setField("number3", 11);
			Print.print(df);
			Print.print(df.getField("d"));
			Object field=df.setField("d", null);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (DynamicFieldsException e) {
			e.printStackTrace();
		}
	}
	@Test
	public void test() {
		String str=null;
		//P263
		//多余的，对null进行调用会自动抛出NullPointerException异常
		//RuntimeException会自动被java虚拟机抛出，所以不必再异常说明(throws XXXX Exception)中把他们列出来
		//也会自动捕获，而不用自己来捕获
		//如果RuntimeException没有被捕获而直达main()，那么在程序退出前会调用异常的printStackTrace()
		//RuntimeException是“不检查的异常”，其他异常是“被检查的异常”
		if (str==null) throw new NullPointerException();
		
		//finally内return的话，当前异常会丢失，从finally返回
		try {
			throw new RuntimeException();
		} finally {
			//如果不return的话，会将异常抛出，等待上层程序来捕获
			//如果finally内又出现异常的话，会把try内捕获覆盖！P268
			return;
		}
	}
	
	public void throwExp() throws Exception {
		try {
			throw new LoggingException();
		} catch (LoggingException e) {
			//System.err.println("Caught "+e);
			//捕获异常时候，如果继续抛出本异常，则
			//throw e;
			throw (Exception)e.fillInStackTrace();
		}
	}
}

class LoggingException extends Exception {
	private static Logger logger=Logger.getLogger("LoggingException");
	public LoggingException() {
		StringWriter trace=new StringWriter();
		//讲输入的错误重定向到PrintWriter中
		printStackTrace(new PrintWriter(trace));
		logger.severe(trace.toString());
	}
}
class DynamicFieldsException extends Exception { }
class DynamicFields {
	private Object[][] fields;
	public DynamicFields(int initialSize) {
		fields=new Object[initialSize][2];
		for (int i = 0; i < initialSize; i++) {
			fields[i]=new Object[] { null, null };
		}
	}
	public String toString() {
		StringBuilder result=new StringBuilder();
		for (Object[] obj:fields) {
			result.append(obj[0]);
			result.append(": ");
			result.append(obj[1]);
			result.append("\n");
		}
		return result.toString();
	}
	private int hasFields(String id) {
		for (int i = 0; i < fields.length; i++) {
			if (id.equals(fields[i][0]))
				return i;
		}
		return -1;
	}
	private int getFieldNumber(String id) throws NoSuchFieldException {
		int fieldNum=hasFields(id);
		if (fieldNum==-1)
			throw new NoSuchFieldException();
		return fieldNum;
	}
	private int makeField(String id) {
		for (int i = 0; i < fields.length; i++) {
			if (fields[i][0]==null) {
				fields[i][0]=id;
				return i;
			}
		}
		Object[][] tmp=new Object[fields.length+1][2];
		for (int i = 0; i < fields.length; i++) {
			tmp[i]=fields[i];
		}
		tmp[fields.length]=new Object[] {null, null};
		fields=tmp;
		return makeField(id);
	}
	public Object getField(String id) throws NoSuchFieldException {
		return fields[getFieldNumber(id)][1];
	}
	public Object setField(String id, Object value) throws DynamicFieldsException {
		if (value==null) {
			//没有构造器，只能用initCause方法来填充cause
			DynamicFieldsException dfe=new DynamicFieldsException();
			dfe.initCause(new NullPointerException());
			throw dfe;
		}
		int fieldNum=hasFields(id);
		if (fieldNum==-1)
			fieldNum=makeField(id);
		Object result=null;
		try {
			result=getField(id);
		}
		catch (NoSuchFieldException e) {
			//使用构造器来填充原因
			throw new RuntimeException(e);
		}
		fields[fieldNum][1]=value;
		return result;
	}
}
//继承下的异常说明
class BaseballException extends Exception {}
class Foul extends BaseballException {}
class Strike extends BaseballException {}
abstract class Inning {
	public Inning() throws BaseballException {}
	public void event() throws BaseballException {
		
	}
	//抽象方法没有方法体，相当于c++里的纯虚函数
	public abstract void atBat() throws Strike, Foul;
	public void walk() {}
}
class StormException extends Exception {}
class RainedOut extends StormException {}
class PopFoul extends Foul {}
interface Storm {
	public void event() throws RainedOut;
	public void rainHard() throws RainedOut;
}
class StormyInning extends Inning implements Storm {
	//构造器可以抛出任何异常，而不必理会基类构造器所抛出的异常
	//但是因为基类的构造器必须被调用，所以异常说明中必须包含基类构造器的异常说明，例如BaseballException
	public StormyInning() throws RainedOut, BaseballException{}
	public StormyInning(String s) throws Foul, BaseballException{}
	//基类没有声明walk()的异常，那么派生类必须强制遵守基类方法的异常声明，使得对象的可替换性得到保证
	//! public void walk() throws PopFoul {}
	//既是基类又是接口中的方法，则接口的异常说明不能改变基类中的
	//！public void event() throws RainedOut{}
	//覆盖后的event方法，派生类方法可以不抛出任何异常，即使是基类定义的异常
	public void event(){}
	
	@Override
	public void rainHard() throws RainedOut {		
	}
	//抛出的异常可以比基类少，但不能比基类多，可以是继承的异常
	//在继承中，异常说明的接口不是变大了而是变小了
	@Override
	public void atBat() throws PopFoul {
		
	}
}
class InputFile {
	private BufferedReader in;
	public InputFile(String fname) throws Exception {
		try {
			//InputFile的好处就是能把BufferedReader和FileReader合二为一
			in=new BufferedReader(new FileReader(fname));
		} catch (FileNotFoundException e) {
			Print.print("Cannot open "+fname);
		} catch (Exception e) { //会捕获Exception以及所有从它派生的异常
			try {
				in.close();
			} catch (Exception e2) {
				Print.print("close unsuccessful");
			}
			throw e;
		} finally {
			//不能再这里进行关闭操作
		}
	}
	public String getLine() {
		String s;
		try {
			s=in.readLine();
		} catch (IOException e) {
			throw new RuntimeException("readLine() failed");
		}
		return s;
	}
	public void dispose() {
		try {
			in.close();
			Print.print("close unsuccessful");
		} catch (Exception e2) {
			throw new RuntimeException("in.close() failed");			
		}
	}
}


