package com.LetsChat.client.model;

import com.LetsChat.common.*;

public class ClientUser {
	
	public boolean verifyUser(User u){
		return new ClientConServer().sendLoginInfoToServer(u);
	}
}
