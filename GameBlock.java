import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameBlock extends JButton implements ActionListener{
	private int blockID = -1;
	public GameBlock(int id){
		this.blockID = id;
		setBackground(new Color((int)(216*0.6 + 216*0.4*(9-id)/9),47,47));
		addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
    	TicTacToe.GameBlockClicked(blockID);
  	} 
}