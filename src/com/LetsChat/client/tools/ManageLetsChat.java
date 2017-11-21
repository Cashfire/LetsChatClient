/**
 * Manage the LetsChat windows with a HashMap.
 */
package com.LetsChat.client.tools;

import java.util.HashMap;

import com.LetsChat.client.view.*;

public class ManageLetsChat {
	private static HashMap hm = new HashMap<String, LetsChat>();
	
	public static void addLetsChat(String senderIdAndRecieverId, LetsChat letsChat){
		System.out.println("ManageLetsChat add"+senderIdAndRecieverId+"success");
		hm.put(senderIdAndRecieverId, letsChat);
	}
	
	public static LetsChat getLetsChat(String senderIdAndRecieverId){
		return (LetsChat) hm.get(senderIdAndRecieverId);
	}
	
	public static void removeLetsChat(String senderIdAndRecieverId){
		System.out.println("ManageLetsChat: "+senderIdAndRecieverId+" window is removed from HashMap.");
		hm.remove(senderIdAndRecieverId);
	}
}

