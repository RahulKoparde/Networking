package com.martin.networking;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.util.ArrayList;

public class DatagramServer {

	static final String LOGON = "LOGIN";
	static final String LOGOUT = "LOGOUT";
	static int port = 1234;
	static int length = 256;
	
	public static void main(String[] args) {
		DatagramPacket packet = new DatagramPacket(new byte[length], length);
		ArrayList<InetSocketAddress> clients = new ArrayList<InetSocketAddress>();
		
		try {
			@SuppressWarnings("resource")
			DatagramSocket socket = new DatagramSocket(port);
			for (;;) {
				System.out.println("Waiting for connection...");
				socket.receive(packet);
				InetSocketAddress add = (InetSocketAddress) packet.getSocketAddress();
				String text = new String(packet.getData(), 0, packet.getLength());
				System.out.println(add + " > " + text);
				
				if (text.contentEquals(LOGON)) {
					clients.add(add);
					System.out.println("New client " + add + " connected (" + clients.size() + " connected)");
				}
				else if (text.contentEquals(LOGOUT)) {
					clients.remove(add);
					System.out.println("Client " + add + " disconnected (" + clients.size() + "connected)"); 
				}
				else {
					for (int i = 0; i < clients.size(); i++) {
						InetSocketAddress dest = (InetSocketAddress) clients.get(i);
						if (!dest.equals(add)) {
							packet.setSocketAddress(dest);
							socket.send(packet);
							System.out.println("Message sent to " + add);
						}
					}
				}
			}
		}
		catch (IOException e) {
			System.err.println(e);
		}
	}

}
