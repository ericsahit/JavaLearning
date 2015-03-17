package org.googlecode.java.thinking.basic;

import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.googlecode.java.thinking.basic.util.Print;
import org.junit.Test;

public class RegexTest {
	
	
	public static String knights="Then, when you have found the shrubbery, you must "+
			"cut down the mightiest tree in the forest... "+
			"with... a herring!";
	
	@Test
	public void test() {
		//+表示一个或者多个，\\+则表示将+转移，表示一个普通字符
		//?表示可能有也可能没有
		//|表示或者
		//括号表示将表达式分组
		String regex="";
		Print.print("-1234".matches("-?\\d+"));
		Print.print("5678".matches("-?\\d+"));
		Print.print("+911".matches("-?\\d+"));
		Print.print("+911".matches("(-|\\+)?\\d+"));
		
		split(" ");
		split("\\W+");//\\W表示非单词字符，\\w表示一个单词字符
		split("n\\W+");//表示n后面跟着非单词字符
		
		Print.print(knights.replaceFirst("f\\w+", "Located"));//表示f后面跟着一个或者多个字母
		Print.print(knights.replaceAll("shrubbery|tree|herring", "Banana"));
		
		regex="[A-Z].+!";
		Print.print(knights.matches(regex));
		Print.print("Then !".matches(regex));
		split("the|you");
		Print.print(knights.replaceAll("a|e|i|o|u", "_"));
		
		Print.print("abc".matches("[abc]"));//表示abc里的任意一个字符
		Print.print("abc".matches("\\S*"));//非空白符，也表示一个字符=[^\s]
		Print.print("abc".matches("\\w+"));//\w表示词字符，\w+表示一个或者多个词字符
		
	}
	
	@Test
	public void testPatternMatcher() {
		String regex="abc+";
		regex="(abc)+";//一次或多次的abc
		regex="(abc){2,}";//至少2次的abc
		String str="abcabcabcdefabc";
		Pattern p=Pattern.compile(regex);//Pattern对象表示编译后的正则表达式
		Matcher m=p.matcher(str);//matcher方法加上一个输入字符串共同构造了一个Matcher对象
		while (m.find()) {
			Print.print("Match \""+m.group()+"\" at positions "+m.start()+"-"+(m.end()-1));
		}
		//开始部分是否匹配模式
		Print.print(m.lookingAt());
		//全部是否匹配
		Print.print(m.matches());
		Print.print();
		str="\nJava now    has regular expressions";
		find("^Java", str);//一行的开始有Java字符.^在多行模式下匹配字符串和每行的开始，在默认模式下只匹配字符串的开始
		find("\\Breg.*", str);//\B表示非词的边界，整体表示非词含有reg以及后面跟0或多个字符
		find("n.w\\s+h(a|i)s", str);//\\s+表示一个或多个的空白字符
		find("s{0,3}", str);
		
		regex="(?i)((^[aeiou])|(\\s+[aeiou]))\\w+?[aeiou]\\b";//(?i)表示不区分大小写模式
		str="Arline ate eight apples and one orange while Anita hadn't any";
		p=Pattern.compile(regex);
		//m=p.matcher(str);
		//将现有的Matcher对象应用于一个新的字符序列
		//使用不带参数的reset方法可以将Match对象重新设置到当前字符序列的起始位置
		m.reset(str);
		while (m.find()) {
			//group(i)返回前一次匹配操作的第i组(整个匹配)
			//start(i)返回找到的组的起始索引
			Print.print("Match \""+m.group()+"\" at positions "+m.start()+"-"+(m.end()-1));
		}
		find(regex, str);
	}
	
	@Test
	public void testReplace() {
		String s="dasdadaddadadad//*!      Here's a block of text to use as input to the regular expression matcher. "
				+"\n     Note that we'll first extract the    block of text by looking for the special delimiters.!*//sddsadadadaad";
		Matcher mInput=Pattern.compile("/\\*!(.*)!\\*/", Pattern.DOTALL).matcher(s);
		if (mInput.find())
			s=mInput.group(1);
		s=s.replaceAll(" {2,}", " ");
		//过滤每行开始一个或者多个空白的字符
		s=s.replaceAll("(?m)^ +", "");
		Print.print(s);
		s=s.replaceFirst("[aeiou]", "(VOWEL1)");
		
		StringBuffer sbuf=new StringBuffer();
		Pattern p=Pattern.compile("[aeiou]");
		Matcher m=p.matcher(s);
		while (m.find()) {
			//将每次匹配之前到上次匹配之间的字符串替换并增加到sbuf中
			m.appendReplacement(sbuf, m.group().toUpperCase());
			Print.print(sbuf);
		}
		Print.print(sbuf);
		//将最后一次匹配后余下的部分复制到sbuf中
		m.appendTail(sbuf);
		Print.print(sbuf);
	}
	
	@Test
	public void testScanner() {
		//JavaSE5新增，可以接收任何类型的输入对象
		String threatData = 
			"58.27.82.161@02/10/2005\n"+
			"204.45.234.40@02/11/2008\n"+
			"[Next log section with different data format]"
			;
		Scanner scanner=new Scanner(threatData);
		String pattern="(\\d+[.]\\d+[.]\\d+[.]\\d+)@"+"(\\d{2}/\\d{2}/\\d{4})";
		while (scanner.hasNext(pattern)) {
			scanner.next(pattern);//将找到下一个匹配该模式的输入部分
			MatchResult result=scanner.match();//获得匹配的结果，但Scannner只针对下一分词进行匹配，所以正则表达式中不能含有定界符（跨一个分词了）
			String ip=result.group(1);
			String date=result.group(2);
			Print.printf("Threat on %s from %s\n", date, ip);
		}
	}
	@Test
	public void testStringTokenizer() {
		//不能实现太复杂的分割模式
		String input="But I'm not dead yet! I feel happy!";
		StringTokenizer stoke=new StringTokenizer(input);
		while (stoke.hasMoreElements())
			Print.printnb(stoke.nextToken() + " ");
		Print.print();
		Print.print(Arrays.toString(input.split(" ")));
		Scanner scanner=new Scanner(input);
		while (scanner.hasNext())
			Print.printnb(scanner.next()+" ");
	}
	
	//查看是否能找到其中一个匹配
	public void find(String regex, CharSequence input) {
        //Pattern p = Pattern.compile(regex);
        //第二个参数是编译的模式
        Pattern p = Pattern.compile(regex, Pattern.MULTILINE);
        Matcher m = p.matcher(input);
        Print.print(m.find());
	}
	
	public static void split(String regex) {
		Pattern p=Pattern.compile(regex);
		//Print.print(Arrays.toString(p.split(knights)));//两个一样效果
		Print.print(Arrays.toString(knights.split(regex)));
	}
}
