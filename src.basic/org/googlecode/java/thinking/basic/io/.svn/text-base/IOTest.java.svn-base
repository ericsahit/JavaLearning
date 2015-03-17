package org.googlecode.java.thinking.basic.io;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.Arrays;
import java.util.Iterator;
import java.util.regex.Pattern;

import org.googlecode.java.thinking.basic.util.Directory;
import org.googlecode.java.thinking.basic.util.PPrint;
import org.googlecode.java.thinking.basic.util.Print;
import org.googlecode.java.thinking.basic.util.ProcessFiles;
import org.junit.Test;

public class IOTest {
	
	@Test
	public void testBufferedInputFile() throws IOException {
		//Reader和Writer提供兼容Unicode与面向字符的IO功能
		//InputStream和OutputStream
		//InputStreamReader是将InputStream转换为Reader的适配器
		
		//对于InputStream和OutputStream来说，会使用FilterInputStream的装饰器子类来修改“流”来满足特殊需要
		//测试缓冲输入文件
		
		//InputStreamReader：传入InputStream，将InputStream适配为Reader
		//BufferedReader：执行read()时候先将Reader中的字符缓冲到Buffer再读取出来
		//StringReader：继承与Reader，传入一个字符串，然后可以读取
		//FileReader：继承于InputStreamReader，传入文件名调用super(new FileInputStream(fileName))将其包装为FileReader
		
		Print.print(read(".\\src.basic\\org\\googlecode\\java\\thinking\\basic\\io\\IOTest.java", "String"));
	}
	public String read(String filename, String keyword) throws IOException {
		//缓冲输入文件
		BufferedReader in=new BufferedReader(new FileReader(filename));
		
		String s;
		StringBuilder sb=new StringBuilder();
		//while ((s=in.readLine())!=null && ()) {//****????不起作用
		while ((s=in.readLine())!=null) {
			if(keyword != null && s.contains(keyword))
				sb.append(s+"\n");
		}
		in.close();
		return sb.toString();
	}
	
	@Test
	public void testMemoryInput() throws IOException {
		StringReader in=new StringReader(read(".\\src.basic\\org\\googlecode\\java\\thinking\\basic\\io\\IOTest.java", "String"));
		int c;
		while ((c=in.read())!=-1)//一个字节一个字节读
			Print.printnb((char)c);
		//要读取格式化的数据，可以使用DataInputStream，它是一个面向字节的IO类(不是面向字符的)
		DataInputStream din=new DataInputStream(
				new ByteArrayInputStream(
					read(".\\src.basic\\org\\googlecode\\java\\thinking\\basic\\io\\IOTest.java", "String").getBytes()
							));
		try {
			while (true)
				Print.printnb((char)din.readByte());//读取双字节会有问题
			//通过异常来检测输入的末尾，但是不能这么使用
		} catch (EOFException e) {
			Print.print("End of stream");
		}
		//可以用avaiable()方法来查看还剩下多少可供读取的字符
		//avaiable的工作方式对于所读取的媒介类型的不同而有所不同，“在没有阻塞的情况下所能读取的字节数”
		//对于文件，这意味着整个文件；但对于不同类型的流，可能就不是这样，因此要谨慎使用。
		din.reset();
		while (din.available()!=0)
			Print.printnb((char)din.readByte());
	}

	//@Test
	public void testFile() {//测试文件的读写类
		String regex=".*\\..*";
		File path=new File(".");
		String[] list=path.list();
		Print.print(Arrays.toString(list));
		
		//list=path.list(new DirFilter(regex));
		list=path.list(dirFilter(regex));
		Arrays.sort(list, String.CASE_INSENSITIVE_ORDER);
		Print.print(Arrays.toString(list));
		//计算所有选中文件的大小
		long total=0, len;
		for(String item:list) {
			len=new File(item).length();
			total+=len;
			Print.print(len);
		}
		Print.print(total);
		
		//实用工具类
		//Directory.local方法使用File.list()来产生File数组
		//Directory.walk方法将开始目录的名字转换为File对象，然后调用recurseDir()递归的遍历目录
		//Print.print(Directory.walk(".\\src.basic", ".*\\.java"));
		String dirBasic=".\\src.basic\\org\\googlecode\\java\\thinking\\basic\\util";
		PPrint.pprint(Directory.walk(".").dirs);
		for (File file:Directory.local(dirBasic, ".*"))
			Print.print(file);
		//使用策略模式来处理相应后缀名的文件
		new ProcessFiles(new ProcessFiles.Strategy() {
			@Override
			public void process(File file) {
				Print.print("s"+file);
			}
		}, "java").start(new String[]{"IOTests","IOTest"});
	}
	//DirFilter改为内部的匿名类来实现，传递的参数必须是final的，匿名内部类中是必需的，这样它才能够使用来自该类范围之外的对象
	public static FilenameFilter dirFilter(final String regex) {
		return new FilenameFilter() {
			private Pattern pattern=Pattern.compile(regex);
			@Override
			public boolean accept(File dir, String name) {
				return pattern.matcher(name).matches();
			}
		};
	}
	
	public static void handleFiles(String[] cmd) {
		if (cmd[0].equals("-r")) {
			File old=new File(cmd[1]);
			File rname=new File(cmd[2]);
			old.renameTo(rname);
			fileData(old);
			fileData(rname);
			return;
		}
		int count=0;
		boolean del=false;
		if (cmd[0].equals("-d")) {
			count++;
			del=true;
		}
		count--;
		while (++count<cmd.length) {
			File f=new File(cmd[count]);
			if (f.exists()) {
				Print.print(f+" exists");
				if (del) {
					Print.print("deleting..."+f);
					f.delete();
				}
			} else {
				if (!del) {
					f.mkdirs();//可以产生任意复杂的路径
					Print.print("created..."+f);
				}
			}
			fileData(f);
		}
	}
	public static void fileData(File f) {
		Object[] printArr=new Object[] {
				f.getAbsolutePath(),
				f.canRead(),
				f.canWrite(),
				f.getName(),
				f.getParent(),
				f.getPath(),
				f.length(),
				f.lastModified(),
				"directory: "+f.isDirectory()
		};
		PPrint.pprint(Arrays.asList(printArr));
	}
}
class DirFilter implements FilenameFilter {
	private Pattern pattern;
	public DirFilter(String regex) {
		pattern=Pattern.compile(regex);
	}
	@Override
	public boolean accept(File dir, String name) {
		return pattern.matcher(name).matches();
	}	
}

