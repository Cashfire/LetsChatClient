/**
 * whenever login one client, open a separate thread for the client.
 * Function: keep the Client connecting to the Server.
 */

package com.LetsChat.client.tools;
import java.io.*;
import java.net.*;

import com.LetsChat.common.*;

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
			ObjectInputStream ois;
			try {
				ois = new ObjectInputStream(s.getInputStream());
				Message m = (Message) ois.readObject();
				System.out.println(m.getSender()+"receive from server that "+ m.getReceiver()+" sent: "+m.getContent());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
}
