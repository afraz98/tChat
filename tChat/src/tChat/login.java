package tChat;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class login extends JFrame {
	static String iP, port, name;
	private JPanel contentPane;
	private JTextField textfield_3;
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
		setResizable(false);

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

		textfield_3 = new JTextField();
		textfield_3.setBounds(72, 193, 209, 20);
		contentPane.add(textfield_3);
		textfield_3.setColumns(10);

		textField_1.setText("10.0.0.8");
		textField_2.setText("8192");
		textfield_3.setText("Name");

		JButton btnNewButton = new JButton("Connect");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				iP = textField_1.getText();
				port = textField_2.getText();
				name = textfield_3.getText();
				setVisible(false);
				logOn(iP, port, name);
			}
		});
		btnNewButton.setBounds(129, 269, 89, 23);
		contentPane.add(btnNewButton);
		setVisible(true);
	}

	private static void logOn(String ip, String port, String name) {
		int p = Integer.parseInt(port);
		mainClient m = new mainClient(ip, p, name);
	}

	public static void main(String[] args) throws IOException {
		new login();
	}
}
