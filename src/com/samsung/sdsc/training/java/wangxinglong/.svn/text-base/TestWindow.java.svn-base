package com.samsung.sdsc.training.java.wangxinglong;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
class TestWindow extends JFrame implements ActionListener
{       

	String strMessage;
	JTextField txtIP;

	JTextArea txtSend;
	JTextArea txtList;
	JButton btnSend,btnClose,btnCheckIP;
	SendMessage sendMsg;
	GetMessage getMsg;
	JLabel lable1,lable2;

	TestWindow() 
	{  

	lable1=new JLabel("对方ip");
	txtIP=new JTextField(20);
	txtIP.setText("109.52.33.216");
	txtSend=new JTextArea(6,18);
	txtList=new JTextArea(6,18);
	txtList.setEditable(false);
	btnSend=new JButton("发送");
	btnClose=new JButton("关闭");
	btnCheckIP=new JButton("确定ip");

	setBounds(100,100,450,300);  //设置窗大的弹出位及宽高
	setVisible(true);
	Container con=getContentPane(); 
	con.setLayout(new FlowLayout());
	con.add(lable1);
	con.add(txtIP);
	con.add(btnCheckIP);

	con.add(new JScrollPane(txtSend));
	con.add(new JScrollPane(txtList));
	con.add(btnSend);
	con.add(btnClose);

	btnSend.addActionListener(this);
	btnClose.addActionListener(this);
	btnCheckIP.addActionListener(this);
	con.validate();
	}

	//设至IP和消息收发端口
	public void runthread(String ip)
	{
		getMsg = new GetMessage(8123,txtList);
		getMsg.start();
		sendMsg=new SendMessage(ip,8123,txtList);
		sendMsg.start();
	}
	
	//按钮是事，产生一个ActionEvent类型的对象e，传递给监听器，执行actionPerformed(e),
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource()==btnClose)
		{
			System.exit(0);
		}
		if(e.getSource()==btnSend)
		{   					     		 				
			txtList.append(txtSend.getText()+"\n");
			sendMsg.send(txtSend.getText());

			txtSend.setText("");
		}
		if(e.getSource()==btnCheckIP)
		{   
 			runthread(txtIP.getText());
		}
	}	
}
