package com.martin.networking;

import java.io.*;
import java.net.*;

public class Echo3 {
	@SuppressWarnings("deprecation")
	public static void main(String args[]) {

		ServerSocket echoServer = null;
		String line;
		DataInputStream is;
		PrintStream os;
		Socket clientSocket = null;

		try {
			echoServer = new ServerSocket(9999);
		} catch (IOException e) {
			System.out.println(e);
		}

		try {
			clientSocket = echoServer.accept();
			is = new DataInputStream(clientSocket.getInputStream());
			os = new PrintStream(clientSocket.getOutputStream());

			while (true) {
				line = is.readLine();
				System.out.println(line);
				os.println(line);
			}
		} catch (IOException e) {
			System.out.println(e);
		}
	}
}
