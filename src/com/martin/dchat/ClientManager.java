package com.martin.dchat;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class ClientManager implements Runnable {
	
	Socket clSocket;
	
	public ClientManager(Socket clSocket) {
		super();
		this.clSocket = clSocket;
	}

	@Override
	public void run() {
		try {
			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(clSocket.getOutputStream(), "UTF-8"));
			BufferedReader in = new BufferedReader(new InputStreamReader(clSocket.getInputStream(), "UTF-8"));
			
			out.write("Hi, I'm your handler. You can start typing, when you're done type 'END'.");
			out.newLine();
			out.flush();
			
			String msg;
			//while ((msg = in.readLine()) != null) {
			while (!(msg = in.readLine()).contentEquals("END")) {
				out.write(msg);
				out.newLine();
				out.flush();
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
