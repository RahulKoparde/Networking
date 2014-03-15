package com.martin.networking;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class StreamServer {

	public static void main(String[] args) {
		
		final int backlog = 10;
		final int port = 1234;
		
		try {
			@SuppressWarnings("resource")
			ServerSocket socket = new ServerSocket(port, backlog);
			for (;;) {
				System.out.println("Waiting for connection...");
				Socket sockConnected = socket.accept();
				System.out.println("Connected to " + sockConnected);
				PrintStream out = new PrintStream(sockConnected.getOutputStream(), true, "UTF-8");
				out.println("Well, that's me in some charset. Hope you're having fun! Even with Ümläutén");
				sockConnected.close();
			}
		}
		catch (IOException e) {
			System.err.println(e);
		}
	}

}
