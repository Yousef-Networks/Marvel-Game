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


public class LeadersPanel extends JPanel implements  ActionListener{
	
	private ArrayList<String> chosen;

	public ArrayList<String> getChosen() {
		return chosen;
	}

	private int count;
	private ChampButton[][] leaderButtons;
	
	public LeadersPanel(ArrayList<String> c) throws IOException {
		super();
		this.setPreferredSize(new Dimension(925, 550));
		this.setLayout(new GridLayout(1,3));
		leaderButtons= new ChampButton[1][3];
		count = 0;
		chosen = new ArrayList<String>();
		chosen = c;
		int f = 0;

//		String[][] leaderLabels = {{chosen.get(0),chosen.get(1),chosen.get(2)},
//									{chosen.get(3),chosen.get(4),chosen.get(5)}};
		
		
		
			for (int j = 0; j < 3; j++) {
				leaderButtons[0][j] = new ChampButton(chosen.get(f));
				leaderButtons[0][j].setActionCommand(chosen.get(f));
				f++;
				this.add(leaderButtons[0][j]);
			}
		
	}
	
	


	public static void main(String[] args) throws IOException {
		JFrame frame= new JFrame();
		frame.setSize(925,650);
		frame.setVisible(true);
//		ArrayList<ChampButton> c = [buttons.ChampButton[,364,0,182x204,disabled,alignmentX=0.0,alignmentY=0.5,border=javax.swing.plaf.BorderUIResource$CompoundBorderUIResource@35875750,flags=296,maximumSize=,minimumSize=,preferredSize=java.awt.Dimension[width=125,height=150],defaultIcon=,disabledIcon=,disabledSelectedIcon=,margin=javax.swing.plaf.InsetsUIResource[top=2,left=14,bottom=2,right=14],paintBorder=true,paintFocus=true,pressedIcon=,rolloverEnabled=true,rolloverIcon=,rolloverSelectedIcon=,selectedIcon=,text=Dr Strange,defaultCapable=true], buttons.ChampButton[,182,204,182x204,disabled,alignmentX=0.0,alignmentY=0.5,border=javax.swing.plaf.BorderUIResource$CompoundBorderUIResource@35875750,flags=296,maximumSize=,minimumSize=,preferredSize=java.awt.Dimension[width=125,height=150],defaultIcon=,disabledIcon=,disabledSelectedIcon=,margin=javax.swing.plaf.InsetsUIResource[top=2,left=14,bottom=2,right=14],paintBorder=true,paintFocus=true,pressedIcon=,rolloverEnabled=true,rolloverIcon=,rolloverSelectedIcon=,selectedIcon=,text=Hulk,defaultCapable=true], buttons.ChampButton[,546,0,182x204,disabled,alignmentX=0.0,alignmentY=0.5,border=javax.swing.plaf.BorderUIResource$CompoundBorderUIResource@35875750,flags=296,maximumSize=,minimumSize=,preferredSize=java.awt.Dimension[width=125,height=150],defaultIcon=,disabledIcon=,disabledSelectedIcon=,margin=javax.swing.plaf.InsetsUIResource[top=2,left=14,bottom=2,right=14],paintBorder=true,paintFocus=true,pressedIcon=,rolloverEnabled=true,rolloverIcon=,rolloverSelectedIcon=,selectedIcon=,text=Electro,defaultCapable=true], buttons.ChampButton[,0,408,182x204,disabled,alignmentX=0.0,alignmentY=0.5,border=javax.swing.plaf.BorderUIResource$CompoundBorderUIResource@35875750,flags=296,maximumSize=,minimumSize=,preferredSize=java.awt.Dimension[width=125,height=150],defaultIcon=,disabledIcon=,disabledSelectedIcon=,margin=javax.swing.plaf.InsetsUIResource[top=2,left=14,bottom=2,right=14],paintBorder=true,paintFocus=true,pressedIcon=,rolloverEnabled=true,rolloverIcon=,rolloverSelectedIcon=,selectedIcon=,text=Quicksilver,defaultCapable=true], buttons.ChampButton[,546,204,182x204,disabled,alignmentX=0.0,alignmentY=0.5,border=javax.swing.plaf.BorderUIResource$CompoundBorderUIResource@35875750,flags=296,maximumSize=,minimumSize=,preferredSize=java.awt.Dimension[width=125,height=150],defaultIcon=,disabledIcon=,disabledSelectedIcon=,margin=javax.swing.plaf.InsetsUIResource[top=2,left=14,bottom=2,right=14],paintBorder=true,paintFocus=true,pressedIcon=,rolloverEnabled=true,rolloverIcon=,rolloverSelectedIcon=,selectedIcon=,text=Ironman,defaultCapable=true], buttons.ChampButton[,364,408,182x204,disabled,alignmentX=0.0,alignmentY=0.5,border=javax.swing.plaf.BorderUIResource$CompoundBorderUIResource@35875750,flags=296,maximumSize=,minimumSize=,preferredSize=java.awt.Dimension[width=125,height=150],defaultIcon=,disabledIcon=,disabledSelectedIcon=,margin=javax.swing.plaf.InsetsUIResource[top=2,left=14,bottom=2,right=14],paintBorder=true,paintFocus=true,pressedIcon=,rolloverEnabled=true,rolloverIcon=,rolloverSelectedIcon=,selectedIcon=,text=Thor,defaultCapable=true]];
//		panel= new LeadersPanel(c);
//		frame.add(panel);
		frame.validate();
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
//			for(int i = 0; i<3; i++) {
//				for(int j=0; j <2; j++) {
//					if(e.getSource()== leaderButtons[i][j]) {
//							
//						String q = leaderButtons[i][j].getText();
//						 int ans = JOptionPane.showConfirmDialog(null, q + "\n Select as Leader? ", "Select Leader" , JOptionPane.YES_NO_OPTION);
//						if (ans ==0) {
//							for(int k = 0; k<3; k++) {
//								leaderButtons[i][k].setEnabled(false);
//							}
//							count++;	
//						}
//						onClose();
//					}
//	
//				}
//			}
			
			
		}
	

	public ChampButton[][] getLeaderButtons() {
		return leaderButtons;
	}


	public void onClose() {
		
		if(count >=2)
			this.setVisible(false);
	}
		
		
	
		
	
}
