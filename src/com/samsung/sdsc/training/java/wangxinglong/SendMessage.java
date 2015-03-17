package com.samsung.sdsc.training.java.wangxinglong;

import java.io.PrintStream;
import java.net.Socket;

import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class SendMessage extends Thread {
	private String strIP;
	private int iPort;
	Socket s = null;
	JLabel label=null;
	JTextField txtIP;
	JTextArea txtList;
	public SendMessage(String ip,int port,JTextArea strMsg) {
		                       //服务器的IP地址和端口号 和发送的信息
		strIP=ip;
		iPort=port;
		txtList=strMsg;

	}


	public void run(){

		while(true){
			try {
				s = new Socket(strIP,iPort);
				txtList.setText("连接成功"+"\n");
				break;
			} catch (Exception e) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					System.out.println("出错了。。。。");
				}
			} 
		}

	}                        

	public  void send(String message)
	{
		try {
			PrintStream p = new PrintStream(s.getOutputStream());
			p.println(message);

		} catch (Exception e1) {
			System.out.println("连接异常:"+e1.getMessage());
		}
	}
}
