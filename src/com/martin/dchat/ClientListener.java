package com.martin.dchat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.Socket;

public class ClientListener implements Runnable {

	private Socket clSocket;

	public ClientListener(Socket clSocket) {
		super();
		this.clSocket = clSocket;
	}

	@Override
	public void run() {
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(clSocket.getInputStream(), "UTF-8"));
			String text;
			while (true) {
				text = in.readLine();
				System.out.println(text);
			}
		} catch (UnsupportedEncodingException e) {
			System.err.println(e);
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println(e);
			e.printStackTrace();
		}
	}

}
