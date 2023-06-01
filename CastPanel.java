package panels;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.IOException;

import model.abilities.*;
import model.world.Hero;

import java.util.*;
import javax.swing.*;

import buttons.BoardButton;
import engine.Player;


public class CastPanel extends JLayeredPane{

	private BoardButton[][] castButtons;
	
	public CastPanel(ArrayList<Ability> a) {
		super();
		this.setPreferredSize(new Dimension(200,150));
		this.setLayout(new GridLayout(3,2));
		castButtons= new BoardButton[3][2];
		
		for(int i = 0; i< a.size(); i++) {
			int g =i+1;
		castButtons[i][0] = new BoardButton();
		castButtons[i][0].setText(a.get(i).getName());
		castButtons[i][0].setInfo(a.get(i).toString());
		castButtons[i][0].setActionCommand("cast ability " + g);
		this.add(castButtons[i][0]);
		}
		
		castButtons[0][1] = new BoardButton();
		castButtons[0][1].setActionCommand("move");
		castButtons[0][1].setText("Move");
		this.add(castButtons[0][1]);
		
		castButtons[1][1] = new BoardButton();
		castButtons[1][1].setActionCommand("attack");
		castButtons[1][1].setText("Attack");
		this.add(castButtons[1][1]);
		
		castButtons[2][1] = new BoardButton();
		castButtons[2][1].setActionCommand("use leader ability");
		castButtons[2][1].setText("Use Leader Ability");
		this.add(castButtons[2][1]);
		
		
		
	}
	
	public BoardButton[][] getCastButtons() {
		return castButtons;
	}

	public static void main(String[] args) throws IOException {
		JFrame frame= new JFrame();
		frame.setSize(925,650);
		frame.setVisible(true);
		frame.setLayout(null);
		Player p = new Player("touny");
		Hero g = new Hero("h", 33, 4, 2, 7, 1, 0);
		Hero f = new Hero("h", 33, 4, 2, 7, 1, 0);
		f.getAbilities().add(new HealingAbility("skjdhv", 2, 3, 1, AreaOfEffect.SINGLETARGET, 5, 7));
		f.getAbilities().add(new HealingAbility("jfjfj", 2, 3, 1, AreaOfEffect.SINGLETARGET, 5, 7));
		f.getAbilities().add(new HealingAbility("ksjdh", 2, 3, 1, AreaOfEffect.SINGLETARGET, 5, 7));
		p.getTeam().add(g);
		p.getTeam().add(f);
		p.setLeader(g);
		CastPanel panel= new CastPanel(f.getAbilities());
		panel.setBounds(100,100,125,150);
		frame.add(panel);
		frame.validate();
	}

	
	
}
