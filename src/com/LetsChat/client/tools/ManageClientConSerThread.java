/**
 * Function: manage those ccst threads with a HashMap.
 */
package com.LetsChat.client.tools;
import java.util.*;

public class ManageClientConSerThread {
	private static HashMap hm = new HashMap<String, ClientConSerThread>();
	//put the created ccst into the HashMap
	
	public static void addClientConSerThread(String userId, ClientConSerThread ccst){
		hm.put(userId, ccst);
	}
	
	//with userId, get its ccst from the hashMap
	public static ClientConSerThread getClientConSerThread(String userId){
		return (ClientConSerThread) hm.get(userId);
	}
}
