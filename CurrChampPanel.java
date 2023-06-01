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

public class CurrChampPanel extends JPanel{
	
	private BoardButton currChampion;
	private Champion champ;
	private JProgressBar currHP;
	private CastPanel cast;
	private BoardButton endTurn;
	
	public CastPanel getCast() {
		return cast;
	}


	public CurrChampPanel(Champion c) {
		setLayout(new FlowLayout());
		cast = new CastPanel(c.getAbilities());
		champ = c;
		currChampion = new BoardButton();
		currChampion.setText(champ.getName());
		currChampion.setActionCommand("currChamp");
		currChampion.setInfo(champ.toStringCurr());
		int d = champ.getCurrentHP();
		int m = champ.getMaxHP();
		currHP = new JProgressBar(0,m);
		currHP.setValue(d);
		currHP.setString(d + "/" + m + " HP");
		currHP.setPreferredSize(new Dimension(450,30));
		currHP.setStringPainted(true);
		currHP.setForeground(Color.red);
		currHP.setBackground(Color.black);
		

		endTurn = new BoardButton();
		endTurn.setPreferredSize(new Dimension(100,50));
		endTurn.setActionCommand("end turn");
		endTurn.setText("End Turn");
		add(endTurn, BorderLayout.CENTER);
		
		
		
		add(currChampion, BorderLayout.WEST);
		add(currHP, BorderLayout.CENTER);
		add(cast, BorderLayout.EAST);
		
	}
	
	public BoardButton getEndTurn() {
		return endTurn;
	}

	public Champion getChamp() {
		return champ;
	}

	public BoardButton getCurrChampion() {
		return currChampion;
	}
	public JProgressBar getCurrHP() {
		return currHP;
	}

	
	
	
	
	
	
		
}
