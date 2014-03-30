package com.martin.packetchat;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.util.ArrayList;

public class DatagramServer {

	private static final String LOGON = "LOGIN";
	private static final String LOGOUT = "LOGOUT";
	private static int PORT = 1234;
	private static int LENGTH = 256;
	
	public static void main(String[] args) {
		DatagramPacket packet = new DatagramPacket(new byte[LENGTH], LENGTH);
		ArrayList<InetSocketAddress> clients = new ArrayList<InetSocketAddress>();
		
		String received;
		String text;
		String name;
		
		try {
			@SuppressWarnings("resource")
			DatagramSocket socket = new DatagramSocket(PORT);
			System.out.println("Ready for connection...");
			for (;;) {
				socket.receive(packet);
				InetSocketAddress add = (InetSocketAddress) packet.getSocketAddress();
				received = new String(packet.getData(), 0, packet.getLength());
				
				System.out.println(add + " > " + received);
				
				text = received.substring(0, received.indexOf("<ex>"));
				name = received.substring(received.indexOf("<ex>") + 4, received.indexOf("</ex>"));
				
				if (text.contentEquals(LOGON)) {
					clients.add(add);
					System.out.println("New client " + name + " connected (" + clients.size() + " connected)");
				}
				else if (text.contentEquals(LOGOUT)) {
					clients.remove(add);
					System.out.println("Client " + name + " disconnected (" + clients.size() + " connected)"); 
				}
				else {
					for (int i = 0; i < clients.size(); i++) {
						InetSocketAddress dest = (InetSocketAddress) clients.get(i);
						if (!dest.equals(add)) {
							packet.setSocketAddress(dest);
							packet.setData((name + " > " + text).getBytes());
							socket.send(packet);
							System.out.println("Message sent to " + clients.get(i));
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
