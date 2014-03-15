package com.martin.networking;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class DatagramClient {

	static final String LOGON = "LOGIN";
	static final String LOGOUT = "LOGOUT";
	static final String servername = "80.218.146.10";
	static int port = 1234;
	
	public static void main(String[] args) {
		String text = null;
		DatagramPacket packet;
		
		try {
			Scanner scanner = new Scanner(System.in);
			System.out.println("Your nickname:");
			String nick = scanner.nextLine();
			DatagramSocket socket = new DatagramSocket();
			InetAddress dest = InetAddress.getByName(servername);
			byte[] content = (LOGON + "<ex>" + nick + "</ex>").getBytes();
			packet = new DatagramPacket(content, content.length, dest, port);
			
			System.out.println("Logging in...");
			
			socket.send(packet);
			
			new ReadThread(socket);
			
			System.out.println("Logged in. Ready to chat, type 'LOGOUT' to exit.");
			
			do {
				text = scanner.nextLine();
				packet.setData((text + "<ex>" + nick + "</ex>").getBytes());
				socket.send(packet);
				System.out.println("Me > " + text);
			}
			while (!text.contentEquals("LOGOUT"));
			
			scanner.close();
			System.exit(0);
		}
		catch (IOException e) {
			System.err.println(e);
		}
	}

}
