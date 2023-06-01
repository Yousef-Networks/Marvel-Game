package views;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

import buttons.ChampButton;
import panels.*;

public class ChampSelectView extends JFrame implements  ActionListener{
	
	private ChampionsPanel champs;
	private int count =0;
	private ArrayList<String> chosen;

	public ChampSelectView() throws IOException {
		
		chosen = new ArrayList<String>();
		setTitle("Marvel: Ultimate Wars");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(925,650);
		setLocationRelativeTo(null);
		setVisible(false);
		
		champs = new ChampionsPanel();
		
//		for(int i = 0; i<3; i++) {
//			for(int j=0; j <5; j++) {
//				champs.getChampButtons()[i][j].addActionListener(this);
//			}
//		}
		
		add(champs, BorderLayout.SOUTH);
		
		
		
		

		ImageIcon logo = new ImageIcon("marvellogo.png");
		this.setIconImage(logo.getImage());
		
		JLabel title = new JLabel("SELECT YOUR CHAMPIONS");
		title.setFont(new Font("Marvel", Font.PLAIN, 30));
		title.setForeground(Color.black);
		title.setHorizontalAlignment(title.CENTER);

		

		add(title, BorderLayout.NORTH);
		pack();
		
		
	}
	
	public ChampionsPanel getChamps() {
		return champs;
	}

	public void actionPerformed(ActionEvent e) {
//		for(int i = 0; i<3; i++) {
//			for(int j=0; j <5; j++) {
//				if(e.getSource()== champs.getChampButtons()[i][j]) {
//						
//					String q = champs.getChampButtons()[i][j].getText();
//					 int ans = JOptionPane.showConfirmDialog(null, q + "\n Add to Team? ", "Select Team" , JOptionPane.YES_NO_OPTION);
//					if (ans ==0) {
//						champs.getChampButtons()[i][j].setEnabled(false);
//						chosen.add(q);
//						count++;	
//					}
//					onClose();
//				}
//
//			}
//		}
		
		
	}


//public void onClose() {
//	
//	if(count >=6) {
//		this.setVisible(false);
//	}
//}
	
	
	public static void main (String [] args) throws IOException {
		
		ChampSelectView c = new ChampSelectView();
		c.validate();
		
	}
	
	
	
	

}
