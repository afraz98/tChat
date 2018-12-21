package tChat;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.JScrollBar;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import javax.swing.JScrollPane;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date; 

public class mainClient extends JFrame {
	/*GUI Variables*/
	private JPanel contentPane;
	private JTextField textField;
	private JTextArea textArea;
	private String IP, name;
	private int port;
	private LocalTime time;
	private boolean connect;
	
	/*Networking Variables*/
	private DatagramSocket socket;
	private InetAddress address;
	private Thread send; 

	public mainClient(String IP, int port, String name) {
		this.IP = IP;
		this.port = port;
		this.name = name;
		time = time.now();
		
		connect = openConnection(IP);
		String conn = "/c/" + name;
		send(conn.getBytes());
		createWindow();
	}
	
	private void createWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 819, 484);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		textField = new JTextField();

		contentPane.add(textField, BorderLayout.SOUTH);
		textField.setColumns(10);
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		contentPane.add(textArea, BorderLayout.CENTER);
		
		
		textField.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					time = time.now();
					String msg = ("[" + time +"]" 
							+ name + ": " + textField.getText()+"\n");
					textArea.append(msg);
					textField.setText("");
					send(msg.getBytes());
				}
			}
		});
		
		JScrollPane s = new JScrollPane(textArea);
		contentPane.add(s);
		setVisible(true);
		setResizable(false);
	}

	private boolean openConnection(String addr) {
		try {
			socket = new DatagramSocket();
			address = InetAddress.getByName(addr);
		} catch (UnknownHostException e) {
			e.printStackTrace();
			return false;
		} catch (SocketException f) {
			f.printStackTrace();
			return false;
		}
		return true; 
	}

private void send(final byte[] b) {
	send = new Thread("Send") {
	public void run() {
		DatagramPacket p = new DatagramPacket(b, b.length,address,port); 
		try {
			socket.send(p);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}};
	send.start(); 
}
	
private String receive() {
	byte[] data = new byte[1024]; 
	DatagramPacket packet = new DatagramPacket(data, 1024);
	try {
		socket.receive(packet);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	String message = new String(packet.getData());
	return message; 
	}
}