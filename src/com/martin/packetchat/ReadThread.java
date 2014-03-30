package com.martin.packetchat;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ReadThread implements Runnable {
	private int length = 256;
	private DatagramSocket socket;
	
	public ReadThread(DatagramSocket socket, int length) {
		super();
		this.socket = socket;
		this.length = length;
		new Thread(this).start();
	}

	@Override
	public void run() {
		String text;
		DatagramPacket packet = new DatagramPacket(new byte[length], length);
		Protocol proto = new Protocol();
		
		for (;;) {
			try {
				socket.receive(packet);
				text = proto.getContent(new String(packet.getData(), 0, packet.getLength()));
				System.out.println(text);
			}
			catch (IOException e) {
				System.err.println(e);
			}
		}
	}
}
