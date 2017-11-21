/**
 *  The window for chatting 
 *  
 */

package com.LetsChat.client.view;

import javax.swing.*;

import com.LetsChat.common.*;
import com.LetsChat.client.tools.*;
import com.LetsChat.client.model.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;


/**
 * Function: the chat window.
 * @author Mao
 *
 */


public class LetsChat extends JFrame implements ActionListener{
	JTextArea jta;
	JTextField jtf;
	JButton jb;
	JPanel jp;
	private String ownerId;
	private String friendId;
	
	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		LetsChat letsChat = new LetsChat("?");
	}

	public LetsChat(String ownerId, String friend){
		this.ownerId = ownerId;
		this.friendId = friend;
		setIconImage(Toolkit.getDefaultToolkit().getImage(LetsChat.class.getResource("/images/modify.png")));
		jta = new JTextArea();
		jtf = new JTextField(15);
		jb = new JButton("Send");
		jb.addActionListener(this);
		jp =new JPanel();
	
		
		jp.add(jtf);
		jp.add(jb);
		
		getContentPane().add(jta, "Center");
		getContentPane().add(jp, "South");
		//IconImage doesn't work on OS system
		this.setIconImage((new ImageIcon("/images/modify.png").getImage()));
		this.setTitle(ownerId+" are chatting with"+ friend);
		this.setSize(300, 200);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		// If the client press the "Send" button 
		if(evt.getSource()==jb){
			Message m = new Message();
			m.setSender(this.ownerId);
			m.setReceiver(this.friendId);
			m.setContent(jtf.getText().trim());
			m.setSendTime(new java.util.Date().toString());
			//send m to server 
			try {	
				//use the ownerId to find its ccst from hashMap, then use the ccst's socket to get OutPutStream.
				OutputStream ops = ManageClientConSerThread.getClientConSerThread(ownerId).getS().getOutputStream();
				ObjectOutputStream oos= new ObjectOutputStream(ops);
				oos.writeObject(m);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

//	@Override
//	public void run() {
//		// TODO Auto-generated method stub
//		while(true){
//			//keep reading from server
//			try {
//				ObjectInputStream ois = new ObjectInputStream(ClientConServer.s.getInputStream());
//				Message m = (Message) ois.readObject();
//				//show the m in the window
//				String info = m.getSender()+" to "+m.getReceiver()+" : "+m.getContent()+"\n";
//				this.jta.append(info);
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}	
//	}
}
