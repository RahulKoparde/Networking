package com.martin.dchat;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
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
				BufferedReader in = new BufferedReader(new InputStreamReader(clSocket.getInputStream(), "UTF-8"));
				
				out.write("Hi. What's your name?");
				out.newLine();
				out.flush();
				
				String msg = in.readLine();
				out.write("Hello " + msg);
				out.flush();
				
				out.close();
				in.close();
				clSocket.close();
			}
		}
		catch (IOException e) {
			System.err.println(e);
		}
	}

}
