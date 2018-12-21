package tChat;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JToolBar;
import javax.swing.JTextArea;
import java.awt.Color;
import javax.swing.JLabel;

public class login extends JFrame {
	static String iP, port, name;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	public login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 378, 437);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(72, 193, 209, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblIp = new JLabel("IP:");
		lblIp.setBounds(22, 91, 46, 14);
		contentPane.add(lblIp);
		
		JLabel lblPort = new JLabel("Port:");
		lblPort.setBounds(16, 144, 46, 14);
		contentPane.add(lblPort);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(16, 196, 46, 14);
		contentPane.add(lblName);
		
		textField_1 = new JTextField();
		textField_1.setBounds(72, 88, 209, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(72, 141, 209, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnNewButton = new JButton("Connect");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				iP = textField_1.getText();
				port = textField_2.getText();
				name = textField.getText();
				setVisible(false);
				logOn(iP, port, name);
			}
		});
		btnNewButton.setBounds(129, 269, 89, 23);
		contentPane.add(btnNewButton);
		setVisible(true);
	}
	
	private static void logOn(String ip, String port,
			String name) {
		int p = Integer.parseInt(port);
		mainClient m = new mainClient(ip, p, name);
	}
	public static void main(String[] args) throws IOException {
		new login();
	}
}

