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
	static int length = 256;
	
	public static void main(String[] args) {
		String text = null;
		DatagramPacket packet;
		byte[] content = LOGON.getBytes();
		
		try {
			DatagramSocket socket = new DatagramSocket();
			InetAddress dest = InetAddress.getByName(servername);
			packet = new DatagramPacket(content, content.length, dest, port);
			
			System.out.println("Logging in...");
			
			socket.send(packet);
			
			new ReadThread(socket);
			
			System.out.println("Logged in. Ready to chat, type 'LOGOUT' to exit.");
			
			Scanner scanner = new Scanner(System.in);
			do {
				text = scanner.nextLine();
				content = text.getBytes();
				packet.setData(content);
				socket.send(packet);
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
