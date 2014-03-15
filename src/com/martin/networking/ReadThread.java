package com.martin.networking;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

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
		DatagramPacket packet = new DatagramPacket(new byte[length], length);
		for (;;) {
			try {
				socket.receive(packet);
				InetSocketAddress add = (InetSocketAddress) packet.getSocketAddress();
				String text = new String(packet.getData(), 0, packet.getLength());
				System.out.println(add + " > " + text);
			}
			catch (IOException e) {
				System.err.println(e);
			}
		}
	}
}
