/**
 * manage friends list, strangers list, blacklist 
 */
package com.LetsChat.client.tools;
import java.util.*;
import com.LetsChat.client.view.ChatList;

public class ManageChatList {
	private static HashMap hm= new HashMap<String, ChatList>();
	
	public static void addChatList(String userId, ChatList chatList){
		hm.put(userId, chatList);
	}
	
	public static ChatList getChatList(String userId){
		return (ChatList) hm.get(userId);
	}

}
