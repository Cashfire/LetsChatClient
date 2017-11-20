/**
 * Client model connect to server
 */
package com.LetsChat.client.model;
import java.net.*;
import java.io.*;
import java.util.*;

import com.LetsChat.common.*;

public class ClientConServer {
	//send first-time info to server
	public boolean sendLoginInfoToServer(Object o){
		boolean b= false;
		try {
			Socket s = new Socket("127.0.0.1", 9999);
			ObjectOutputStream oos= new ObjectOutputStream(s.getOutputStream());
			oos.writeObject(o);
			
			ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
			System.out.println("ClientConServer get the input from Server");
			Message m = (Message) ois.readObject();
			
			if(m.getMsgType().equals("1")){
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
