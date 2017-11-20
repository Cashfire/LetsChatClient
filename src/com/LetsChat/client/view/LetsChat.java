package com.LetsChat.client.view;

import javax.swing.*;
import java.awt.*;
import javax.swing.*; 
/**
 * Function: the chat window.
 * @author Mao
 *
 */


public class LetsChat extends JFrame{
	JTextArea jta;
	JTextField jtf;
	JButton jb;
	JPanel jp;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LetsChat letsChat = new LetsChat("?");
	}

	public LetsChat(String friend){
		setIconImage(Toolkit.getDefaultToolkit().getImage(LetsChat.class.getResource("/images/modify.png")));
		jta = new JTextArea();
		jtf = new JTextField(15);
		jb = new JButton("Send");
		jp =new JPanel();
	
		
		jp.add(jtf);
		jp.add(jb);
		
		getContentPane().add(jta, "Center");
		getContentPane().add(jp, "South");
		//IconImage doesn't work on OS system
		this.setIconImage((new ImageIcon("/images/modify.png").getImage()));
		this.setTitle("You are chatting with"+ friend);
		this.setSize(300, 200);
		this.setVisible(true);
	}
}
