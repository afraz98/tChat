/*code obtained from geeks for geeks*/
package tChat;
import java.net.*;
import java.io.*;
import java.util.*;

public class tChat {
	private static final String TERMINATE = "Exit";
	static volatile boolean finished = false;
	
	private String IP, portNo;
	static String name;
	
	public tChat(String IP, String portNo, String name){
		this.IP = IP;
		this.portNo = portNo;
		this.name = name; 
	}

		void start() {
			try {
				InetAddress group = InetAddress.getByName(IP);
				int port = Integer.parseInt(portNo);
				Scanner sc = new Scanner(System.in);
				MulticastSocket socket = new MulticastSocket(port);

				socket.joinGroup(group);
				Thread t = new Thread(new ReadThread(socket, group, port));

				t.start();
				System.out.println("Start typing messages...\n");
				while (true) {
					String message;
					message = sc.nextLine();
					if (message.equalsIgnoreCase(tChat.TERMINATE)) {
						finished = true;
						socket.leaveGroup(group);
						socket.close();
						sc.close(); 
						break;
					}
					message = name + ": " + message;
					byte[] buffer = message.getBytes();
					DatagramPacket datagram = new DatagramPacket(buffer,
							buffer.length, group, port);
					socket.send(datagram);
				}
			}	catch(UnknownHostException h) {
				System.out.println("ERROR FINDING HOST. TERMINATING...");
				System.exit(0);
			} 	catch (Exception se) {
				System.out.println("UNKNOWN ERROR");
				System.exit(0);
		}
	}
}
			
			