package com.LetsChat.client.view;

import java.awt.*;
import java.awt.event.*;
import com.LetsChat.client.tools.*;
import com.LetsChat.common.Message;

import javax.swing.*;

public class ChatList extends JFrame implements ActionListener, MouseListener{
	private JPanel card1, jpFriendList, jp1, jpStrangerList, card2, jp2, jp3, jpBlackList, card3;
	private JButton jb11, jb12, jb13, jb21, jb22, jb23, jb31, jb32, jb33;
	private JScrollPane jsp1, jsp2, jsp3;
	CardLayout cl = new CardLayout();
	private String owner;
	private JLabel[] jlFriend;
	
	public static void main(String[] args) {
//		ChatList chatList= new ChatList();
	}

	public void updateFriend(Message m){
		String[] onlineFriend = m.getContent().split(" ");
		for(int i = 0; i< onlineFriend.length; i++){
		jlFriend[Integer.parseInt(onlineFriend[i])-1].setEnabled(true);
		}
	}

	public ChatList(String ownerId) {
		this.owner = ownerId;
		jb11 = new JButton("My Friends");
		jb12 = new JButton("Strangers");
		jb12.addActionListener(this);
		jb13 = new JButton("Blacklist");
		jb13.addActionListener(this);
		
		jb21 = new JButton("My Friends");
		jb21.addActionListener(this);
		jb22 = new JButton("Strangers");
		jb23 = new JButton("Blacklist");
		jb23.addActionListener(this);
		
		jb31 = new JButton("My Friends");
		jb31.addActionListener(this);
		jb32 = new JButton("Strangers");
		jb32.addActionListener(this);
		jb33 = new JButton("Blacklist");

		card1 = new JPanel(new BorderLayout());	
		card2 = new JPanel(new BorderLayout());	
		card3 = new JPanel(new BorderLayout());
		
		jpFriendList = new JPanel(new GridLayout(50,1,4,4));
		jpStrangerList = new JPanel(new GridLayout(20, 1, 4, 4));
		jpBlackList = new JPanel(new GridLayout(5,1, 4, 4));
		
		jp1 = new JPanel(new GridLayout(2,1));//has 2 buttons showing "Strangers" and "BlackList"
		jp2 = new JPanel(new GridLayout(2,1));//has 2 buttons showing "My Friends" and "Strangers" 
		jp3 = new JPanel(new GridLayout(3,1));//has all the 3 buttons 
		
		jsp1 = new JScrollPane(jpFriendList);
		jsp2 = new JScrollPane(jpStrangerList);
		jsp3 = new JScrollPane(jpBlackList);
		
		// suppose I have 50 friends in the friend list
		jlFriend= new JLabel[50];
		for(int i = 0; i< jlFriend.length; i++){
			jlFriend[i] = new JLabel(String.valueOf(i+1));
			jlFriend[i].setIcon(new ImageIcon(ChatList.class.getResource("/images/mm.gif")));
			jlFriend[i].addMouseListener(this);
			jpFriendList.add(jlFriend[i]);
			jlFriend[i].setEnabled(false);
			if(jlFriend[i].getText().equals(this.owner)){
				jlFriend[i].setEnabled(true);
			}
		}

		/*
		 * set card 1
		 */
		//add 2 buttons to the bottom panel jpfl3
		jp1.add(jb12);
		jp1.add(jb13);
		
		//The big panel jpfl1 contains 1 button, 1 scrollpane and 1 panel.
		card1.add(jb11, "North");
		card1.add(jsp1, "Center");
		card1.add(jp1, "South");
		//???
		getContentPane().add(card1, "Center");
		
		/*
		 * set card 2
		 */
		//First, add 2 buttons to jp2
		jp2.add(jb21);
		jp2.add(jb22);
		//Then, Suppose I have 20 strangers in the Stranger list
		JLabel jlStrangers[]= new JLabel[20];
		for(int i = 0; i< jlStrangers.length; i++){
			jlStrangers[i] = new JLabel(String.valueOf(i+1));
			jlStrangers[i].setIcon(new ImageIcon(ChatList.class.getResource("/images/strangers.png")));
			jpStrangerList.add(jlStrangers[i]);
		}
		//Finally, fill the card2 
		card2.add(jp2, "North");
		card2.add(jsp2, "Center");
		card2.add(jb23, "South");

		/*
		 * set card 3
		 */
		//First, add 3 buttons to jp3
		jp3.add(jb31);
		jp3.add(jb32);
		jp3.add(jb33);
		//Then, Suppose I have 5 persons in the blacklist
		JLabel jlBlack[]= new JLabel[5];
		for(int i = 0; i< jlBlack.length; i++){
			jlBlack[i] = new JLabel(String.valueOf(i+1));
			jlBlack[i].setIcon(new ImageIcon(ChatList.class.getResource("/images/blacklist.png")));
			jpBlackList.add(jlBlack[i]);
		}
		//Finally, fill the card3 
		card3.add(jp3, "North");
		card3.add(jsp3, "Center");
		
		//set the account name on title
		this.setTitle(ownerId);
		this.setLayout(cl);
		this.add(card1, "1");
		this.add(card2, "2");
		this.add(card3, "3");
		//this.add(card3, "3");
		this.setTitle(owner+"'s Chat List"); 
		this.setSize(140,500);
		this.setVisible(true);
		
		
	}


	/*
	 * 
	 */
	public void actionPerformed(ActionEvent evt) {
		//if clicking the "Strangers" card
		if(evt.getSource()==jb12||evt.getSource()==jb32){
			cl.show(this.getContentPane(), "2");
		}else if(evt.getSource()==jb21|| evt.getSource()==jb31){
			cl.show(this.getContentPane(), "1");
		}else if(evt.getSource()==jb13 ||evt.getSource()==jb23){
			cl.show(this.getContentPane(), "3");
		}
		
		
	}

	//choosing the friend you want to chat with
	public void mouseClicked(MouseEvent me) {
		// event handler of double-clicking
		if(me.getClickCount()==2){
			String friendNo = ((JLabel)me.getSource()).getText();
			//System.out.println("You want to chat with "+friendNo+".");
			//open a LetsChat window
			LetsChat letsChat= new LetsChat(this.owner, friendNo);
			//add the LetsChat to HashMap for easy management
			ManageLetsChat.addLetsChat(this.owner+"To"+friendNo, letsChat);
		}
	}


	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseEntered(MouseEvent me) {
		JLabel jb= (JLabel) me.getSource();
		jb.setForeground(Color.pink);
	}


	@Override
	public void mouseExited(MouseEvent me) {
		JLabel jb= (JLabel) me.getSource();
		jb.setForeground(Color.black);
		
	}

	
}
