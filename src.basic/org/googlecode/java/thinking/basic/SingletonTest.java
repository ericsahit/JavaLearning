package org.googlecode.java.thinking.basic;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.nio.channels.FileLock;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

public class SingletonTest {
	
	public static void main(String[] args) throws Exception {
		
		String fileName = "E:\\shm.lock";
		RandomAccessFile raFile = new RandomAccessFile(fileName, "rw");
		
		FileChannel lockfc = raFile.getChannel();
		
		FileLock flock = lockfc.tryLock();
		// 如果为空，则表明已经有应用占有该锁
		if(flock == null) {
		 // 不能执行写操作
		}
		else {
			// 可以执行写操作
			
			int gb = 1024*1024*1024;
			MappedByteBuffer mmap = lockfc.map(FileChannel.MapMode.READ_WRITE, 0, gb);
			
			//using POSIX syscalls for checking ulimit and 
			//locking pages of memory into the process's address space
			//使用系统调用mlock，mlock()页面在地址范围从 addr 和开始,长度的字节.所有页面包含一个指定的地址
			//范围当调用成功返回驻留在内存中以保证;页面是保证以后留在内存中,直到解锁
			NativeIO.POSIX.getCacheManipulator().mlock(fileName, mmap, gb);
			
			for (int i = 0; i < gb; i++) {
				buf.put((byte)255);
				System.out.println(i);
			}
			
		    if (mmap != null) {
		        NativeIO.POSIX.munmap(mmap);
		        mmap = null;
		      }
			
			TimeUnit.HOURS.sleep(1);
		}
	}
	
	public static void loadBlock(String blockFileName) throws Exception {
		
		MappedByteBuffer mmap;
		
	    try {
	    	
	    	RandomAccessFile blockFile = new RandomAccessFile(blockFileName, "r");
	    	long length = blockFile.length();
	    	
	        FileChannel blockChannel = blockFile.getChannel();
	        if (blockChannel == null) {
	          throw new IOException("Block InputStream has no FileChannel.");
	        }
	        mmap = blockChannel.map(MapMode.READ_ONLY, 0, length);
	        NativeIO.POSIX.getCacheManipulator().mlock(blockFileName, mmap, length);
	      } finally {
	          if (mmap != null) {
		            NativeIO.POSIX.munmap(mmap); // unmapping also unlocks
		          }
	      }
	}
	
}

class MySingleton {
	private static volatile MySingleton instance = null;
	
	private MySingleton() {
		
	}
	
	public static MySingleton getInstance() {
		if (instance == null) {
			synchronized(MySingleton.class) {
				instance = new MySingleton();
			}
		}
		return instance;
	}
	
	private static final MySingleton instance2 = new MySingleton();
	
	
}
