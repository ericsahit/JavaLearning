package com.samsung.sdsc.training.java.wangxinglong;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JTextArea;

public class GetMessage extends Thread{
	private int iport;
	String strMsg;
	private JTextArea getMsg;
	public GetMessage(int port,JTextArea strMsg) {

		iport=port;
		getMsg=strMsg;
	}

	public void run(){
		try {
			ServerSocket serverSo = new ServerSocket(iport);
			Socket s = serverSo.accept();
			//监听服务器端口，一旦有数据发送过来，那么就将数据封装成socket对象

			while(true){
				InputStreamReader i = new InputStreamReader(s.getInputStream());
				//从socket中得到读取流，该流中有客户端发送过来的数据

				BufferedReader b = new BufferedReader(i);
				strMsg= b.readLine();
				getMsg.append("对方说："+strMsg+"\n");
			}
		} catch (IOException e) {
			getMsg.append("IP地址错误或对方不在线！"+"\n");
		}
	}
}
