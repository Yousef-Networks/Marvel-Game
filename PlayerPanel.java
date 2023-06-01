package panels;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.*;

import javax.swing.AbstractButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.*;
import engine.*;
import model.world.*;
import model.abilities.*;

import buttons.*;



public class PlayerPanel extends JPanel implements ActionListener{
	
	private Player player;
	private BoardButton stats;
	
	public BoardButton getStats() {
		return stats;
	}

	public void setStats(BoardButton stats) {
		this.stats = stats;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public PlayerPanel(Player p) {
		
		player =p;
		stats = new BoardButton();
		stats.setText(p.getName());
		stats.setActionCommand(p.getName());
//		stats.setPreferredSize(new Dimension(200, 300));
//		stats.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
		stats.setInfo(player.toString());
		stats.addActionListener(this);
		add(stats, BorderLayout.EAST);
		
	}
	
	public static void main(String[] args) throws IOException {
		JFrame frame= new JFrame();
		frame.setSize(925,650);
		frame.setVisible(true);
		frame.setLayout(null);
		Player p = new Player("touny");
		Hero g = new Hero("h", 33, 4, 2, 7, 1, 0);
		Hero f = new Hero("h", 33, 4, 2, 7, 1, 0);
		p.getTeam().add(g);
		p.getTeam().add(f);
		p.setLeader(g);
		PlayerPanel panel= new PlayerPanel(p);
		panel.setBounds(100,100,125,150);
		frame.add(panel);
		frame.validate();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
//		String q = ((ChampButton)e.getSource()).getInfo();
//		JOptionPane.showMessageDialog(null,q);
		
	}
	
	
	

}
