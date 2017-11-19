package com.LetsChat.client.view;

import java.awt.BorderLayout;
import java.awt.*;
import javax.swing.*;


public class ClientLogin extends JFrame {

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
		JLabel jlb1 = new JLabel(new ImageIcon(ClientLogin.class.getResource("/images/titleLable.png")));
		
		//south part
		jp1 = new JPanel(); //center FlowLayout() by default;
		jp1_jb1 = new JButton("Login");
		jp1_jb2 = new JButton("Cancel");
		jp1_jb3 = new JButton("Guide");
		jp1.add(jp1_jb1);
		jp1.add(jp1_jb2);
		jp1.add(jp1_jb3);
		//center part
		jp2 = new JPanel(new GridLayout(3,3));
		jp2_jlb1 = new JLabel("Acount No: ", JLabel.CENTER);
		jp2_jlb2 = new JLabel("Password: ", JLabel.CENTER);	
		jp2_jlb3 = new JLabel("Forget password?", JLabel.CENTER);
		jp2_jlb4 = new JLabel("Secure your Password", JLabel.CENTER);
		jp2_jb1 = new JButton("Clear record");
		jp2_jtf = new JTextField();
		jp2_jpf = new JPasswordField();
		jp2_jcb1= new JCheckBox("Invisibility cloak");
		jp2_jcb2 = new JCheckBox("Remember my pwd");
		
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
		this.setSize(400, 300);
	}

}
