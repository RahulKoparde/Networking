package com.martin.dchat;

import java.util.ArrayList;

public class PostCentral {
	
	private static ArrayList<ClientManager> clients = new ArrayList<ClientManager>();

	static void addClient(ClientManager clM) {
		clients.add(clM);
	}
	
	static void removeClient(ClientManager clM) {
		clients.remove(clM);
	}
	
	static void broadcast(String msg) {
		for (int i = 0; i < clients.size(); i++) {
			clients.get(i).sendMsg(msg);
		}
	}
	
	static int getUsercount() {
		return clients.size();
	}
	
	static void notifyOthers(String msg, String name, ClientManager sender) {
		System.out.println(name + " (" + sender.getSocket().getInetAddress() + ") > " + msg);
		for (int i = 0; i < clients.size(); i++) {
			if (sender != clients.get(i)) {
				clients.get(i).sendMsg(name + " > " + msg);
			}
		}
	}
}
