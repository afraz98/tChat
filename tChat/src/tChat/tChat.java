/*code obtained from geeks for geeks*/
package tChat;
import java.net.*;
import java.io.*;
import java.util.*;

public class tChat {
	private static final String TERMINATE = "Exit";
	static String name;
	static volatile boolean finished = false;
	private String IP, portNo;
	
	public tChat(String IP, String portNo){
		this.IP = IP;
		this.portNo = portNo;
	}

		void start() {
			try {
				InetAddress group = InetAddress.getByName(IP);
				int port = Integer.parseInt(portNo);
				Scanner sc = new Scanner(System.in);
				System.out.print("Enter your name: ");
				name = sc.nextLine();
				MulticastSocket socket = new MulticastSocket(port);

				// Since we are deploying
				socket.setTimeToLive(0);
				// this on localhost only (For a subnet set it as 1)

				socket.joinGroup(group);
				Thread t = new Thread(new ReadThread(socket, group, port));

				// Spawn a thread for reading messages
				t.start();

				// sent to the current group
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
			
			