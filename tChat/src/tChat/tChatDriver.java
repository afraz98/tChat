package tChat;
import java.awt.Graphics;
import java.net.*;
import java.io.*;
import java.util.*;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.*;

public class tChatDriver {
	static String iP, port;
	private static int frameSize = 500;
	private static void createAndShowGUI() throws IOException {
       JFrame frame = new JFrame("tChat");
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame.setLayout(null);

       JLabel IP = new JLabel();
       JLabel Port = new JLabel();
       
       Port.setBounds((frameSize/2)-115, (frameSize / 3) - 10, 40, 40);
       IP.setText("IP: ");
       
       IP.setBounds((frameSize/2)-115, (frameSize / 4) - 10, 40, 40);
       Port.setText("Port: ");
       
       frame.add(IP);
       frame.add(Port);
       
		JTextArea area1 = new JTextArea();
		area1.setBounds((frameSize / 2) - 75, (frameSize / 3), 150, 20);
		
		JTextArea area2 = new JTextArea();
		area2.setBounds((frameSize / 2) - 75, ((int)(frameSize / 4)), 150, 20);
	
	   frame.add(area1);
	   frame.add(area2);
	   
       JButton connect = new JButton(new AbstractAction("") {
			@Override
			public void actionPerformed(ActionEvent e) {
				iP = area2.getText();
				port = area1.getText();
				tChat chat = new tChat(iP, port); 
				chat.start();
			}
       }); 
       connect.setBounds(((frameSize / 2) - 50),
    		   ((int) (frameSize * (3.0/5.0))),100,50);
       connect.setText("Connect");
	   
       frame.add(connect); 
       frame.setSize(frameSize,frameSize);
       frame.setVisible(true);
   }

public static void main(String[] args) throws IOException {
   	createAndShowGUI(); 
   }
}