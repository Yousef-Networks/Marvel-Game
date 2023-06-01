package panels;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import buttons.BoardButton;


public class BoardPanel extends JPanel{
	
	private BoardButton[][] boardButtons;
	
	public BoardPanel() {
		super();
		this.setPreferredSize(new Dimension(600, 300));
		this.setLayout(new GridLayout(5,5));
		 boardButtons= new BoardButton[5][5];
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				boardButtons[i][j]= new BoardButton();
				this.add(boardButtons[i][j]);
			}
		}
	}
	
	public BoardButton[][] getBoardButtons() {
		return boardButtons;
	}

	public static void main(String[] args) {
		JFrame frame= new JFrame();
		frame.setSize(925,650);
		frame.setVisible(true);
		BoardPanel panel= new BoardPanel();
		frame.add(panel);
		frame.validate();
	}
}
