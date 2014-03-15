package com.martin.dchat;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	
	public static void main(String[] args) {
		
		final int backlog = 10;
		final  int port = 1234;
		
		try {
			@SuppressWarnings("resource")
			ServerSocket seSocket = new ServerSocket(port, backlog);
			for (;;) {
				System.out.println("Waiting for connection...");
				Socket clSocket = seSocket.accept();
				System.out.println("Connected to " + clSocket);
				
				BufferedWriter out = new BufferedWriter(new OutputStreamWriter(clSocket.getOutputStream(), "UTF-8"));
								
				out.write("Welcome to dchat. You're being connected to your handler...");
				out.newLine();
				out.flush();
				
				ClientManager clM = new ClientManager(clSocket);
				new Thread(clM).start();
				PostCentral.addClient(clM);
				
				System.out.println("Socket has been given to handler.");
			}
		}
		catch (IOException e) {
			System.err.println(e);
			e.printStackTrace();
		}
	}

}
