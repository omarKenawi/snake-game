package snakeGM;

import java.awt.Color;

import javax.swing.JFrame;

public class main {

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		gameplay gameplay = new gameplay();
		frame.setBounds(10,10,905,700);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.add(gameplay);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}

}
