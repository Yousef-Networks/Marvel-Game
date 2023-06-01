package buttons;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import model.world.*;

public class ChampButton extends JButton implements ActionListener{

	private int x, y, index;

	public int getIndex() {
		return index;
	}



	public void setIndex(int index) {
		this.index = index;
	}



	private String info;
	

	public String getInfo() {
		return info;
	}



	public void setInfo(String info) {
		this.info = info;
	}



	public int getX() {
		return x;
	}



	public void setX(int x) {
		this.x = x;
	}



	public int getY() {
		return y;
	}



	public void setY(int y) {
		this.y = y;
	}



	public ChampButton(String c) {
		super(c);
		this.setPreferredSize(new Dimension(125, 150));
		addActionListener(this);
	}
	


	@Override
	public void actionPerformed(ActionEvent e) {
//		String q = this.getText();
//		 ans = JOptionPane.showConfirmDialog(null, q, "Select" , JOptionPane.YES_NO_OPTION);
//		if (ans ==0) {
//			this.setEnabled(false);
//		}
		}
	
	
	
}
