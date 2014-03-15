package com.martin.dchat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Client {

	public static void main(String[] args) {
		
		final String server = "80.218.146.10";
		final int port = 1234;
		
		try {
			Socket clientSocket = new Socket(server, port);

			BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream(), "UTF-8"));
			String text;
			while ((text = in.readLine()) != null) {
				System.out.println(text);
			}
			
			in.close();
			clientSocket.close();
		} catch (IOException e) {
			System.err.println(e);
		}
	}

}
