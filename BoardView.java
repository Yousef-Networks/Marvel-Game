package views;

import panels.*;
import model.world.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;
import engine.*;
import engine.PriorityQueue;


public class BoardView extends JFrame implements ActionListener, KeyListener{
	
	private BoardPanel board;
	private PlayerPanel pp1;
	private PlayerPanel pp2;
	private TurnOrderPanel turns;
	//private CurrChampPanel currChamp;
	

//	public CurrChampPanel getCurrChamp() {
//		return currChamp;
//	}

	public TurnOrderPanel getTurns() {
		return turns;
	}

	public PlayerPanel getPp1() {
		return pp1;
	}

	public PlayerPanel getPp2() {
		return pp2;
	}

	public BoardView( Player p1, Player p2) {
		setTitle("Marvel: Ultimate Wars");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(1500,650);
		setLocationRelativeTo(null);
		setVisible(false);
		
		//turns = new TurnOrderPanel(pq);
		
		pp1 = new PlayerPanel(p1);
		pp2 = new PlayerPanel(p2);
		JPanel players = new JPanel();
		players.setLayout(new GridLayout(2,1));
		players.add(pp1);
		players.add(pp2);
//		currChamp = new CurrChampPanel(c);
//		cast = new CastPanel(c.getAbilities());
		
		ImageIcon logo = new ImageIcon("marvellogo.png");
		this.setIconImage(logo.getImage());
		
		board = new BoardPanel();
		add(board, BorderLayout.CENTER);
		add(players, BorderLayout.EAST);
		//add(turns, BorderLayout.WEST);
//		add(pp1, BorderLayout.EAST);
//		add(pp2, BorderLayout.WEST);
//		add(currChamp, BorderLayout.SOUTH);
//		add(cast, BorderLayout.SOUTH);
		
	}

	

	public void setBoard(BoardPanel board) {
		this.board = board;
	}

	public BoardPanel getBoard() {
		return board;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	public static void main(String[] args) {
//		BoardView b = new BoardView();
//		b.setVisible(true);
//		b.validate();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	

}
