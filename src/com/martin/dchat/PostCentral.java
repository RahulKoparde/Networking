package com.martin.dchat;

import java.util.ArrayList;

public class PostCentral {
	
	static ArrayList<ClientManager> clients = new ArrayList<ClientManager>();

	static void addClient(ClientManager clM) {
		clients.add(clM);
	}
	
	static void broadcast(String msg) {
		
		for (int i = 0; i < clients.size(); i++) {
			clients.get(i).sendMsg(msg);
		}
	}
}
