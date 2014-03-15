package com.martin.networking;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ReadThread implements Runnable {
	static int length = 256;
	DatagramSocket socket;
	
	public ReadThread(DatagramSocket socket) {
		super();
		this.socket = socket;
		new Thread(this).start();
	}

	@Override
	public void run() {
		String text;
		DatagramPacket packet = new DatagramPacket(new byte[length], length);
		for (;;) {
			try {
				socket.receive(packet);
				text = new String(packet.getData(), 0, packet.getLength());
				System.out.println(text);
			}
			catch (IOException e) {
				System.err.println(e);
			}
		}
	}
}
