package com.martin.dchat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class ClientListener implements Runnable {

	private Socket clSocket;

	public ClientListener(Socket clSocket) {
		super();
		this.clSocket = clSocket;
	}

	@Override
	public void run() {
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(clSocket.getInputStream(), "ISO-8859-1"));
			String text;
			while (clSocket != null) {
				text = in.readLine();
				System.out.println(text);
			}
		} 
		catch (SocketTimeoutException e) {
			System.out.println("Disconnected due to timeout");
		}
		catch (UnsupportedEncodingException e) {
			System.err.println(e);
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println(e);
			e.printStackTrace();
		}
	}

}
