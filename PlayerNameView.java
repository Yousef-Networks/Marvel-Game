package views;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

public class PlayerNameView extends JFrame implements  ActionListener{

	private JButton done;
	private JTextField textp1;
	private JTextField textp2;
	
	


	public JTextField getTextp1() {
		return textp1;
	}


	public JTextField getTextp2() {
		return textp2;
	}


	public PlayerNameView() {
		
		setTitle("Marvel: Ultimate Wars");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(925, 650);
		setLocationRelativeTo(null);
		setLayout(null);
		setVisible(false);
		
		
		ImageIcon logo = new ImageIcon("marvellogo.png");
		ImageIcon bg = new ImageIcon("bgblur.png");
		this.setIconImage(logo.getImage());
		JLabel background = new JLabel(bg);
		add(background);
		background.setLayout(null);
		background.setSize(925,650);
		background.setHorizontalAlignment(background.CENTER);
		
		JLabel titlep1 = new JLabel("PLAYER ONE: ");
		JLabel titlep2 = new JLabel("PLAYER TWO: ");
		titlep1.setFont(new Font("Marvel", Font.PLAIN, 30));
		titlep2.setFont(new Font("Marvel", Font.PLAIN, 30));
		titlep1.setForeground(Color.black);
		titlep2.setForeground(Color.black);
		titlep1.setBounds(400, 150, 150, 50);
		titlep2.setBounds(400, 250, 150, 50);

		
		
		textp1 = new JTextField();
		textp2 = new JTextField();
		textp1.setText("NAME");
		textp2.setText("NAME");
		textp1.setPreferredSize(new Dimension(250, 40));
		textp2.setPreferredSize(new Dimension(250, 40));
		textp1.setFont(new Font("Marvel", Font.PLAIN, 30));
		textp2.setFont(new Font("Marvel", Font.PLAIN, 30));
		textp1.setForeground(Color.white);
		textp2.setForeground(Color.white);
		textp1.setBackground(Color.red);
		textp2.setBackground(Color.red);
		textp1.setBounds(325, 200, 250, 40);
		textp2.setBounds(325, 300, 250, 40);




		
		
		done = new JButton();
		done.setText("NEXT");
		done.setPreferredSize(new Dimension(150,50));
		done.setForeground(Color.white);
		done.setBackground(Color.red);
		done.setHorizontalTextPosition(JButton.CENTER);
		done.setVerticalTextPosition(JButton.BOTTOM);
		done.setBounds(375, 400, 150, 50);
		done.setFont(new Font("Marvel", Font.PLAIN, 40));
		
		
		background.add(titlep1);
		background.add(titlep2);
		background.add(textp1);
		background.add(textp2);
		background.add(done);
		
		
		


		
		
	}
	
	
	public JButton getDone() {
		return done;
	}


	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	
	
	
	

}
