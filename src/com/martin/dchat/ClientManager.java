package com.martin.dchat;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class ClientManager implements Runnable {
	
	Socket clSocket;
	BufferedWriter out;
	
	public ClientManager(Socket clSocket) {
		super();
		this.clSocket = clSocket;
	}
	
	public void sendMsg(String msg) {
		try {
			out.write(msg);
			out.newLine();
			out.flush();
		} catch (IOException e) {
			System.err.println(e);
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		try {
			out = new BufferedWriter(new OutputStreamWriter(clSocket.getOutputStream(), "UTF-8"));
			BufferedReader in = new BufferedReader(new InputStreamReader(clSocket.getInputStream(), "UTF-8"));
			
			sendMsg("Hi, I'm your handler. You can start typing, when you're done type 'END'.");
			
			String msg;
			while ((msg = in.readLine()) != null) {
				if (msg.contentEquals("END")) {
					break;
				}
				if (msg.contentEquals("SPECIAL")) {
					PostCentral.broadcast("Yes");
				}
				sendMsg(msg);
			}
						
			out.close();
			in.close();
			clSocket.close();
		}
		catch (IOException e) {
			System.err.println(e);
			e.printStackTrace();
		}
		
	}

}
