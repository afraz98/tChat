package tChat;
import java.net.DatagramSocket;
import java.net.SocketException;

public class Server implements Runnable{
	private int port;
	private DatagramSocket socket;
	private Thread run, manage, send, receive;
	private boolean running = false;
	
	public Server(int port) {
		this.port = port; 
		try {
			socket = new DatagramSocket(port);
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		run = new Thread(this, "Server");
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
				while(running) {
					/*Manage clients*/
				}
			}
		};
		manage.start(); 
	}
	
	private void receive() {
		receive = new Thread("Receive") {
			public void run() {
				while(running) {
					
				}
			}
		};
		receive.start();
	}
}
