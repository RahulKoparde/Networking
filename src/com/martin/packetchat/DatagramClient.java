package com.martin.packetchat;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class DatagramClient {

	private static final String SERVERNAME = "80.218.146.10";
	private static final int PORT = 1234;
	private static final int LENGTH = 256;
	
	public static void main(String[] args) {
		String text = null;
		DatagramPacket packet;
		Protocol proto = new Protocol();
		
		try {
			Scanner scanner = new Scanner(System.in);
			System.out.println("Your nickname:");
			String nick = scanner.nextLine();
			DatagramSocket socket = new DatagramSocket();
			InetAddress dest = InetAddress.getByName(SERVERNAME);
			byte[] content = (proto.encodeMessage(Action.LOGIN, nick)).getBytes();
			packet = new DatagramPacket(content, content.length, dest, PORT);
			
			System.out.println("Logging in...");
			
			socket.send(packet);
			
			new ReadThread(socket, LENGTH);
			
			System.out.println("Logged in. Ready to chat, type 'LOGOUT' to exit.");
			
			do {
				text = scanner.nextLine();
				if (text.toUpperCase().contentEquals("LOGOUT")) {
					packet.setData(proto.encodeMessage(Action.LOGOUT, nick).getBytes());
				}
				else {
					packet.setData((proto.encodeMessage(Action.MESSAGE, text)).getBytes());
				}
				socket.send(packet);
				//System.out.println("Me > " + text);
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
