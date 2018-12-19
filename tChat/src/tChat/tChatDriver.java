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
	private static int frameSize = 500;
	private static void createAndShowGUI() throws IOException {
       JFrame frame = new JFrame("tChat");
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame.setLayout(null);

       JButton connect = new JButton(new AbstractAction("") {
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
       }); 
       connect.setBounds(((frameSize / 2) - 50),((int) (frameSize * (2.0/3.0))),100,50);
       connect.setText("Connect");
       
       frame.add(connect); 
       frame.setSize(frameSize,frameSize);
       frame.setVisible(true);
   }

	 public static void main(String[] args) throws IOException {
   	createAndShowGUI(); 
   }
}
