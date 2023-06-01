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
import engine.*;
import model.world.*;
import model.abilities.*;

import buttons.*;


public class ChampionsPanel extends JPanel implements  ActionListener{
	
	private ArrayList<String> chosen;

	public ArrayList<String> getChosen() {
		return chosen;
	}

	private int count;
	private ChampButton[][] champButtons;
	
	
	public ChampionsPanel() throws IOException {
		super();
		this.setPreferredSize(new Dimension(925, 550));
		this.setLayout(new GridLayout(3,5));
		champButtons= new ChampButton[3][5];
		chosen = new ArrayList<String>();
		ArrayList<Champion> champs = new ArrayList<Champion>();
		ArrayList<Ability> abilities = new ArrayList<Ability>();
		Game game = new Game(new Player("hhh"),new Player("hhhh"));
		game.loadAbilities("Abilities.csv");
		game.loadChampions("Champions.csv");
		champs = game.getAvailableChampions();
		count = 0;

		String[][] champsLabels = {{champs.get(0).toString(),champs.get(1).toString(),champs.get(2).toString(),champs.get(3).toString(),champs.get(4).toString()},
									{champs.get(5).toString(),champs.get(6).toString(),champs.get(7).toString(),champs.get(8).toString(),champs.get(9).toString()},
									{champs.get(10).toString(),champs.get(11).toString(),champs.get(12).toString(),champs.get(13).toString(),champs.get(14).toString()}};
		
		
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 5; j++) {
				champButtons[i][j] = new ChampButton(champsLabels[i][j]);
				champButtons[i][j].addActionListener(this);
				champButtons[i][j].setX(i);
				champButtons[i][j].setY(j);
				champButtons[i][j].setInfo(champsLabels[i][j]);
				
				switch(champButtons[i][j].getY()) {
				
				case 0:
					
					switch(champButtons[i][j].getX()) {
					case 0:
						champButtons[i][j].setActionCommand("Captain America");
						champButtons[i][j].setIndex(0);
						break;
					
				case 1:
					
					champButtons[i][j].setActionCommand("Hela");
					champButtons[i][j].setIndex(5);
					break;
					
				case 2:
					champButtons[i][j].setActionCommand("Quicksilver");
					champButtons[i][j].setIndex(10);
					break;
					}
					
				
					break;
				case 1:
					
					switch(champButtons[i][j].getX()) {
					case 0:
						champButtons[i][j].setActionCommand("Deadpool");
						champButtons[i][j].setIndex(1);
						break;
					
				case 1:
					champButtons[i][j].setActionCommand("Hulk");	
					champButtons[i][j].setIndex(6);

					break;
					
				case 2:
					
					champButtons[i][j].setActionCommand("Spiderman");
					champButtons[i][j].setIndex(11);
					break;
					}
					
					break;
					
				case 2:
					switch(champButtons[i][j].getX()) {
					case 0:
						champButtons[i][j].setActionCommand("Dr Strange");
						champButtons[i][j].setIndex(2);
					
						break;
					
				case 1:
					
					champButtons[i][j].setActionCommand("Iceman");
					champButtons[i][j].setIndex(7);
					break;
					
				case 2:
					
					champButtons[i][j].setActionCommand("Thor");
					champButtons[i][j].setIndex(12);
					break;
					}
					
					break;
					
				case 3:
					switch(champButtons[i][j].getX()) {
					case 0:
						champButtons[i][j].setActionCommand("Electro");
						champButtons[i][j].setIndex(3);
						break;
					
				case 1:
					champButtons[i][j].setActionCommand("Ironman");
					champButtons[i][j].setIndex(8);
					
					break;
					
				case 2:
					champButtons[i][j].setActionCommand("Venom");
					champButtons[i][j].setIndex(13);
					
					break;
					}
					
					break;
					
				case 4:
					switch(champButtons[i][j].getX()) {
					case 0:
						champButtons[i][j].setActionCommand("Ghost Rider");
						champButtons[i][j].setIndex(4);
						break;
					
				case 1:
					champButtons[i][j].setActionCommand("Loki");
					champButtons[i][j].setIndex(9);
					
					break;
					
				case 2:
					
					champButtons[i][j].setActionCommand("Yellow Jacket");
					champButtons[i][j].setIndex(14);
					break;
					}
					
					break;
				
				
				
				}
				champButtons[i][j].setText(champButtons[i][j].getActionCommand());
				this.add(champButtons[i][j]);
			}
		}
		
		
		
		
	}
	
	
	public static void main(String[] args) throws IOException {
		JFrame frame= new JFrame();
		frame.setSize(925,650);
		frame.setVisible(true);
		ChampionsPanel panel= new ChampionsPanel();
		frame.add(panel);
		frame.validate();
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
//			for(int i = 0; i<3; i++) {
//				for(int j=0; j <5; j++) {
//					if(e.getSource()== champButtons[i][j]) {
//						String q = champButtons[i][j].getInfo();
//						 int ans = JOptionPane.showConfirmDialog(null, q + "\n Add to Team? ", "Select Team" , JOptionPane.YES_NO_OPTION);
//						if (ans ==0) {
//							champButtons[i][j].setEnabled(false);
//							chosen.add(champButtons[i][j].getActionCommand());
//							//System.out.println(chosen);
//							count++;	
//						}
//						onClose();
//					}
//	
//				}
//			}
			
			
		}
	

	public ChampButton[][] getChampButtons() {
		return champButtons;
	}


	public void onClose() {
		
		if(count >=6) {
			this.setVisible(false);
		}
	}
		
		
	
		
	
}
