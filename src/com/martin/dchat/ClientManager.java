package com.martin.dchat;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.Socket;

public class ClientManager implements Runnable {
	
	private Socket clSocket;
	private BufferedWriter out;
	
	public ClientManager(Socket clSocket) {
		super();
		this.clSocket = clSocket;
		try {
			out = new BufferedWriter(new OutputStreamWriter(clSocket.getOutputStream(), "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			System.err.println(e);
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println(e);
			e.printStackTrace();
		}
	}
	
	public Socket getSocket() {
		return clSocket;
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
			BufferedReader in = new BufferedReader(new InputStreamReader(clSocket.getInputStream(), "UTF-8"));
			
			sendMsg("Hi, I'm your handler. What's your name?");
			String name = in.readLine();
			System.out.println(clSocket.getInetAddress() + " logged in with name " + name + " (" + PostCentral.getUsercount() + " users)");
			sendMsg("Hi " + name + ". You can start typing now, when you want to disconnect type 'END'.");
			
			String msg;
			while ((msg = in.readLine()) != null) {
				if (msg.contentEquals("END")) {
					break;
				}
				PostCentral.notifyOthers(msg, name, this);
			}
			
			sendMsg("Bye " + name);
			PostCentral.removeClient(this);
			System.out.println(name + " (" + clSocket.getInetAddress() + ") disconnected (" + PostCentral.getUsercount() + " users)");
			clSocket.close();
		}
		catch (IOException e) {
			System.err.println(e);
			e.printStackTrace();
		}
		
	}

}
