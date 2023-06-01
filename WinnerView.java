package views;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

public class WinnerView extends JFrame{

	public WinnerView(String w) {
		setTitle("Marvel: Ultimate Wars");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(925, 650);
		setLocationRelativeTo(null);
		setVisible(false);
		
		
		
		ImageIcon logo = new ImageIcon("marvellogo.png");
		ImageIcon bg = new ImageIcon("winnerbg.png");
		this.setIconImage(logo.getImage());
		JLabel background = new JLabel(bg);
		add(background);
		background.setLayout(null);
		background.setSize(925,650);
		background.setHorizontalAlignment(background.CENTER);
		
		JLabel winner = new JLabel(w);
		winner.setFont(new Font("Marvel", Font.PLAIN, 70));
		winner.setForeground(Color.white);
		winner.setBackground(Color.red);
		winner.setOpaque(true);
		winner.setBounds(300, 300, 300, 80);
		background.add(winner);
		
		
		
	}
	
	public static void main(String [] args) {
		
//		WinnerView r = new WinnerView("SJDHGFLSKDJHKEJDH");
//		r.setVisible(true);
//		r.validate();
//		
		
	}
	
	
}
