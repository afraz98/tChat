package tChat;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.security.SecureRandom;
import java.util.ArrayList;

public class Server implements Runnable {
	private int port;
	private DatagramSocket socket;
	private Thread run, manage, send, receive;
	private boolean running = false;
	private ArrayList<ServerClient> clients = new ArrayList<ServerClient>();

	public Server(int port) {
		this.port = port;
		try {
			socket = new DatagramSocket(port);
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		run = new Thread(this, "Server");
		run.start();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		running = true;
		manageClients();
		receive();
	}

	private void manageClients() {
		manage = new Thread("Manage") {
			public void run() {
				while (running) {
					/* Manage clients */
				}
			}
		};
		manage.start();
	}

	private void receive() {
		receive = new Thread("Receive") {
			public void run() {
				while (running) {
					byte[] data = new byte[1024];
					DatagramPacket packet = new DatagramPacket(data,
							data.length);
					try {
						socket.receive(packet);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					process(packet);
				}
			}
		};
		receive.start();
	}

	private void process(DatagramPacket p) {
		String s = new String(p.getData());
		int id = new SecureRandom().nextInt(128);
		if (s.startsWith("/c/")) { /* Connection packet */
			clients.add(new ServerClient(s.substring(3, s.length()),
					p.getAddress(), p.getPort(), id));
			id = new SecureRandom().nextInt(128);
			System.out.println(clients.get(0).toString());
		} else if (s.startsWith("/m/")) { /* Message packet */
			sendToAll(s);
			System.out.println(s);
		} else {

		}
	}

	private void sendToAll(String message) {
		for (int i = 0; i < clients.size(); i++) {
			ServerClient client = clients.get(i);
			send(message.getBytes(), client.address, client.port);
		}
	}

	private void send(final byte[] data, final InetAddress address,
			final int port) {
		send = new Thread("Send Server") {
			public void run() {
				DatagramPacket packet = new DatagramPacket(data, data.length,
						address, port);
				try {
					socket.send(packet);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		send.start();
	}
}
