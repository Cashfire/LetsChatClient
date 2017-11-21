package com.LetsChat.client.view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import com.LetsChat.client.model.ClientUser;
import com.LetsChat.common.*;
import java.io.*;
import com.LetsChat.client.tools.*;

public class ClientLogin extends JFrame implements ActionListener{

	private JLabel jlb1 ;
	JPanel jp1;
	JButton jp1_jb1, jp1_jb2, jp1_jb3, jp1_jb4;
	JTabbedPane jtp;
	JPanel jp2, jp3;
	JLabel jp2_jlb1, jp2_jlb2, jp2_jlb3, jp2_jlb4;
	JButton jp2_jb1;
	JTextField jp2_jtf;
	JPasswordField jp2_jpf;
	JCheckBox jp2_jcb1, jp2_jcb2;
	public static void main(String[] args) {
		ClientLogin clientLogin = new ClientLogin();
	}

	/**
	 * Create the frame.
	 */
	public ClientLogin() {		
		//north part
		jlb1 = new JLabel(new ImageIcon(ClientLogin.class.getResource("/images/titleLable.png")));
		
		//south part
		jp1 = new JPanel(); //center FlowLayout() by default;
		jp1_jb1 = new JButton("Login");
		jp1_jb1.addActionListener(this);
		jp1_jb1.setIcon(new ImageIcon(ClientLogin.class.getResource("/images/login.png")));
		jp1_jb2 = new JButton("Cancel");
		jp1_jb2.setIcon(new ImageIcon(ClientLogin.class.getResource("/images/delete.png")));
		jp1_jb3 = new JButton("Guide");
		jp1_jb3.setIcon(new ImageIcon(ClientLogin.class.getResource("/images/me.png")));
		jp1.add(jp1_jb1);
		jp1.add(jp1_jb2);
		jp1.add(jp1_jb3);
		
		//center part
		jp2 = new JPanel(new GridLayout(3,3));
		jp2_jlb1 = new JLabel("Acount No: ", JLabel.CENTER);
		jp2_jlb2 = new JLabel("Password: ", JLabel.CENTER);	
		jp2_jlb3 = new JLabel("Forget pwd?", JLabel.CENTER);
		jp2_jlb3.setForeground(Color.blue);
		jp2_jlb4 = new JLabel("Secure your pwd", JLabel.CENTER);
		jp2_jb1 = new JButton("Clear");
		jp2_jtf = new JTextField();
		jp2_jpf = new JPasswordField();
		jp2_jcb1= new JCheckBox("Invisi cloak");
		jp2_jcb2 = new JCheckBox("Remember me");
		
		jp2.add(jp2_jlb1);
		jp2.add(jp2_jtf);
		jp2.add(jp2_jb1);
		jp2.add(jp2_jlb2);
		jp2.add(jp2_jpf);
		jp2.add(jp2_jlb3);
		jp2.add(jp2_jcb1);
		jp2.add(jp2_jcb2);
		jp2.add(jp2_jlb4);
		
		jtp = new JTabbedPane();
		jtp.add("Lets Chat Account", jp2);
		jp3 = new JPanel(); 
		jtp.add("Phone Account", jp3);


		this.add(jlb1, "North");
		this.add(jp1, "South");
		this.add(jtp, "Center");
		
		this.setTitle("Client Login");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(360, 250);
		this.setVisible(true);
	}
	/*
	 * event handler of press the "Login" button
	 */
	public void actionPerformed(ActionEvent evt) {
		// if the client clicks "Login"
		if(evt.getSource()==jp1_jb1){  
			
			User u= new User();
			u.setUserId(jp2_jtf.getText().trim());
			u.setPwd(new String(jp2_jpf.getPassword()));
			
			ClientUser clientUser = new ClientUser();
			//verify the id&pwd in server
			if(clientUser.verifyUser(u)){
				//open the ChatList window
				ChatList chatList= new ChatList(u.getUserId());  
				ManageChatList.addChatList(u.getUserId(), chatList);
				//update the online friend list.
				
				try {
					//send a request for online friend list
					ClientConSerThread ccst = ManageClientConSerThread.getClientConSerThread(u.getUserId());
					ObjectOutputStream oos = new ObjectOutputStream(ccst.getS().getOutputStream());
					Message m = new Message();
					m.setSender(u.getUserId());//so server can know to whom it should reply
					m.setMsgType(MessageType.msg_get_onLineFriend);
					oos.writeObject(m);
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}		
				
				//close the Login window
				this.dispose();
			}else{
				JOptionPane.showMessageDialog(this, "Invalid Account or Password");
			}
			
		}		
	}
}
