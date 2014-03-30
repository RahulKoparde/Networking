package com.martin.packetchat;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.util.ArrayList;

public class DatagramServer {

	private static int PORT = 1234;
	private static int LENGTH = 256;
	
	public static void main(String[] args) {
		DatagramPacket packet = new DatagramPacket(new byte[LENGTH], LENGTH);
		ArrayList<InetSocketAddress> clients = new ArrayList<InetSocketAddress>();
		// TODO: This way of storing the nicks is not working out, because an ArrayList is essentially unordered
		// A Map-type would be better
		ArrayList<String> nicks = new ArrayList<String>();
		
		String received;
		
		try {
			@SuppressWarnings("resource")
			DatagramSocket socket = new DatagramSocket(PORT);
			System.out.println("Ready for connection...");
			
			Protocol proto = new Protocol();
			
			for (;;) {
				socket.receive(packet);
				InetSocketAddress add = (InetSocketAddress) packet.getSocketAddress();
				received = new String(packet.getData(), 0, packet.getLength());
				
				System.out.println(add + " > " + received);
				
				switch (proto.getAction(received)) {
				case LOGIN:
					clients.add(add);
					nicks.add(proto.getContent(received));
					System.out.println("New client " + proto.getContent(received) + " connected (" + clients.size() + " connected)");
					break;
				case LOGOUT:
					clients.remove(add);
					nicks.remove(clients.indexOf(add));
					System.out.println("Client " + proto.getContent(received) + " disconnected (" + clients.size() + " connected)"); 
					break;
				case MESSAGE:
					for (int i = 0; i < clients.size(); i++) {
						InetSocketAddress dest = (InetSocketAddress) clients.get(i);
						if (!dest.equals(add)) {
							packet.setSocketAddress(dest);
							packet.setData((proto.encodeMessage(Action.MESSAGE, nicks.get(clients.indexOf(add)) + " > " + proto.getContent(received))).getBytes());
							socket.send(packet);
							//System.out.println("Message sent to " + clients.get(i));
						}
					}
					break;
				default:
					// Don't react if we were sent gibberish
					break;
				}
				
				/*text = received.substring(0, received.indexOf("<ex>"));
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
				*/
			}
		}
		catch (IOException e) {
			System.err.println(e);
		}
	}

}
