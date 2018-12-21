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

public class mainClient extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextArea textArea;
	public mainClient() {
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
		
		JScrollBar scrollBar = new JScrollBar();
		contentPane.add(scrollBar, BorderLayout.EAST);
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		contentPane.add(textArea, BorderLayout.CENTER);
		
		textField.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					textArea.append(textField.getText()+"\n");
					textField.setText(""); 
				}
			}
		});
		
		setVisible(true);
		setResizable(false);
	}
	
}
