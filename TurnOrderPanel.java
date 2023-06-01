package panels;


import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;
import engine.*;
import model.world.*;

import buttons.*;


public class TurnOrderPanel extends JPanel{
	
	private BoardButton[][] turns;
	private ArrayList<Champion> x;
	private PriorityQueue f;

	public BoardButton[][] getTurns() {
		return turns;
	}

	public TurnOrderPanel(PriorityQueue pq) {
		x = new ArrayList<Champion>();
		JLabel title = new JLabel("Turns:");
		title.setForeground(Color.black);
		title.setHorizontalAlignment(title.CENTER);
		JPanel turnOrder = new JPanel();
		 f = pq;
		int s = f.size();
		while(!f.isEmpty()) {
			x.add((Champion) f.remove());
		}
		if(s<6) {
			int d = 6-s;
			for(int j = 0; j<d; j++) {
				x.add(null);
			}
		}
			
		
		
		turnOrder.setLayout(new GridLayout(6,1));
		
		turns = new BoardButton[6][1];
		
		
		for(int h = 0; h<6; h++) {
			turns[h][0] = new BoardButton();
			turns[h][0].setPreferredSize(new Dimension(100,50));
			turnOrder.add(turns[h][0]);	
		}
		turns[0][0].setBackground(Color.green);
		reset();
		
		add(title, BorderLayout.NORTH);
		add(turnOrder, BorderLayout.SOUTH);
	}
	
	
	public void reset() {
		
		
			for(int i = 0; i<6; i++) {
				
				if(x.get(i)==null)
				turns[i][0].setText(null);
				else {
				Champion c = x.get(i);
				turns[i][0].setText(c.getName());	
				}
				
				
				}
				
			}
			
	public void update() {
		
		Champion q = x.remove(0);
		if(q.getCurrentHP()==0) 
			x.add(null);

		else
			x.add(q);
			
		reset();
	}
		
		
		
	
	
	public static void main(String[] args) {
		

		PriorityQueue x = new PriorityQueue(6);
		x.insert(new Hero("HH", 3, 4, 5, 7, 2, 9));
		x.insert(new Hero("DJ", 3, 4, 5, 6, 2, 9));
		x.insert(new Hero("FL", 3, 4, 5, 19, 2, 9));
		x.insert(new Hero("AN", 3, 4, 5, 38, 2, 9));
		x.insert(new Hero("E", 3, 4, 5, 5, 2, 9));
		x.insert(new Hero("D", 3, 4, 5, 20, 2, 9));
		x.remove();

		JFrame frame= new JFrame();
		frame.setSize(100,500);
		frame.setVisible(true);
		TurnOrderPanel panel= new TurnOrderPanel(x);
		//panel.update();
		System.out.println(x);
		frame.add(panel);
		frame.validate();
		
		
		
		
	}
	

}
