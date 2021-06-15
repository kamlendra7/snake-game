package snake.game;

import javax.swing.*;

public class snake extends JFrame{
	
	snake(){
		Board b=new Board();
		add(b);
		pack();
		setLocationRelativeTo(null);
		setTitle("SNAKE GAME");
		setResizable(false);
	}
	
	public static void main(String args[]) {
		new snake().setVisible(true);
	}

}
