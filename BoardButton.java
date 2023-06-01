package buttons;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;


public class BoardButton extends JButton implements ActionListener{

	private String info;
	
	public void setInfo(String info) {
		this.info = info;
	}

	public String getInfo() {
		return info;
	}

	public BoardButton() {
		super();
		this.setPreferredSize(new Dimension(125, 150));
		addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//JOptionPane.showMessageDialog(null,"You clicked on "+this.getText());
	}
	
}
