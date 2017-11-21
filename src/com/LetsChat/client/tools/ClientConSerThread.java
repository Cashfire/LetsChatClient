/**
 * whenever login one client, open a separate thread for the client.
 * Function: keep the Client connecting to the Server.
 */

package com.LetsChat.client.tools;
import java.io.*;
import java.net.*;

import com.LetsChat.common.*;
import com.LetsChat.client.view.*;

public class ClientConSerThread extends Thread{
	private Socket s;
	
	public Socket getS() {
		return s;
	}

	public void setS(Socket s) {
		this.s = s;
	}

	public ClientConSerThread(Socket s){
		this.s =s;
	}
	
	public void run(){
		while(true){
			//keep reading info from the server.
			try {
				InputStream i = s.getInputStream();
				ObjectInputStream ois = new ObjectInputStream(i);
				Message m = (Message) ois.readObject();
				//System.out.println(m.getSender()+"receive from server that "+ m.getReceiver()+" sent: "+m.getContent());
				//if m is common conversation
				if(m.getMsgType().equals(MessageType.msg_common)){
					// show the msg in correct LetsChat window
					LetsChat letsChat = ManageLetsChat.getLetsChat(m.getReceiver()+"To"+m.getSender());
					letsChat.showMessage(m);
				//else if client receives online Friend list 
				}else if(m.getMsgType().equals(MessageType.msg_return_onLineFriend)){
					System.out.println("Client get: "+m.getContent());
					String con = m.getContent();
					String OnlineFriends[] = con.split(" ");
					String receiver= m.getReceiver();
					//Modify the friendList
					ChatList chatList= ManageChatList.getChatList(receiver);
					//update friend list
					if(chatList != null){
						chatList.updateFriend(m);
					}
				}		
			} catch (IOException ex) {
                System.out.println("Server ShutDown");
                System.exit(0);
            } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
