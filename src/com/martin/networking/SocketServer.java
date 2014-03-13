package com.martin.networking;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {

	public static void main(String[] args) {
		
		final int backlog = 10;
		final int port = 1234;
		
		try {
			ServerSocket socket = new ServerSocket(port, backlog);
			for (;;) {
				System.out.println("Waiting for connection...");
				Socket sockConnected = socket.accept();
				System.out.println("Connected to " + sockConnected);
				PrintStream ps = new PrintStream(sockConnected.getOutputStream());
				ps.println("A warm welcome from the server!");
				sockConnected.close();
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

}
