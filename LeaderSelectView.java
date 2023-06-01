package views;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

import panels.*;

public class LeaderSelectView extends JFrame implements  ActionListener{
	
	
	private LeadersPanel leaders;

	public LeaderSelectView() throws IOException {
		
		
		setTitle("Marvel: Ultimate Wars");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(925,650);
		setLocationRelativeTo(null);
		setVisible(false);
		
//		leaders = new LeadersPanel(c);
//		add(leaders, BorderLayout.SOUTH);
		
		

		ImageIcon logo = new ImageIcon("marvellogo.png");
		this.setIconImage(logo.getImage());
		
		JLabel title = new JLabel("SELECT YOUR LEADERS");
		title.setFont(new Font("Marvel", Font.PLAIN, 30));
		title.setForeground(Color.black);
		title.setHorizontalAlignment(title.CENTER);

		

		add(title, BorderLayout.NORTH);
		//pack();
		
	}
	
	

	public void actionPerformed(ActionEvent e) {
		
		
	}

	
	public static void main (String [] args) throws IOException {
		
		//ChampSelectView c = new ChampSelectView();
		LeaderSelectView d = new LeaderSelectView();
		d.validate();
		
	}
	
	
	
	

}
