package com.martin.networking;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class StreamClient {
	final static String server = "80.218.146.10";
	final static int port = 1234;

	public static void main(String[] args) {
		try {
			Socket clientSocket = new Socket(server, port);
			InputStream is = clientSocket.getInputStream();
			byte[] buffer = new byte[1024];
			int read;
			while ((read = is.read(buffer)) != -1) {
				String output = new String(buffer, 0, read, "UTF-8");
				System.out.print(output);
				System.out.flush();
			}
			clientSocket.close();
		} catch (IOException e) {
			System.err.println(e);
		}
	}

}
