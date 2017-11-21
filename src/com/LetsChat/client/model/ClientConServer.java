/**
 * 1, new a socket to connect with server
 */
package com.LetsChat.client.model;
import java.net.*;
import java.io.*;
import java.util.*;

import com.LetsChat.client.tools.*;
import com.LetsChat.common.*;

public class ClientConServer {
	public Socket s;
	//send first-time info to server
	public boolean sendLoginInfoToServer(Object o){
		boolean b= false;
		try {
			s = new Socket("127.0.0.1", 9999);
			ObjectOutputStream oos= new ObjectOutputStream(s.getOutputStream());
			oos.writeObject(o);
			
			ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
			System.out.println("ClientConServer get the input from Server");
			Message m = (Message) ois.readObject();
			//verifying the Login userId and pwd.
			if(m.getMsgType().equals("1")){
				//use the socket to new an unique Thread(ccst) for this client
				ClientConSerThread ccst = new ClientConSerThread(s);
				//start this thread ccst
				ccst.start();
				//put the ccst to hashmap for the thread management
				String userId = ((User) o).getUserId();
				ManageClientConSerThread.addClientConSerThread(userId, ccst);
				b= true;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			System.out.println("ClientConServer's b: "+b);
			return b;
		}
	}
	
	//send message info to server
//	public void sentInfoToServer(){
//		
//	}
	

}
