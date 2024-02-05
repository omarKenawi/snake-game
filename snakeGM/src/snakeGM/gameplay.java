package snakeGM;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class gameplay extends JPanel implements KeyListener, ActionListener {
	private ImageIcon titileImage;
	private ImageIcon HeadDown;
	private ImageIcon HeadUp;
	private ImageIcon HeadLefh;
	private ImageIcon HeadRight;
	private ImageIcon fruit;
	private ImageIcon tail;
	private boolean LEFT = false;
	private boolean play = false;
	private boolean canPlay = true;
	private boolean RIGHT = true;
	private boolean UP = false;
	private boolean DOWN = false;
	private int delay = 100;
	private int moves = 0;
	private int scores = 0;
	private Timer timer;
	private int[] snakeXlenth = new int[700];
	private int[] snakeYlenth = new int[700];
	private int snakeLenth = 3;
	private int[] fruitXpos = { 25, 50, 75, 100, 125, 150, 175, 200, 225, 250, 275, 300, 325, 350, 375, 400, 425, 450,
			475, 500, 525, 550, 575, 600, 625, 650, 675, 700, 725, 750, 775, 800, 825, 850 };
	private int[] fruitYpos = { 75, 100, 125, 150, 175, 200, 225, 250, 275, 300, 325, 350, 375, 400, 425, 450, 475, 500,
			525, 550, 575, 600, 625 };
	Random rX = new Random();
	Random rY = new Random();
	private int randomX = rX.nextInt(fruitXpos.length);
	private int randomY = rY.nextInt(fruitYpos.length);

	public gameplay() {
		addKeyListener(this);
		setFocusable(true);
		setBackground(Color.DARK_GRAY);
		setFocusTraversalKeysEnabled(false);
		timer = new Timer(delay, this);
		timer.start();
		play=true;
		
	}

	public void paint(Graphics gr) {
		super.printComponent(gr);
		titileImage = new ImageIcon("title.png");
		titileImage.paintIcon(this, gr, 25, 5);
		gr.setColor(Color.DARK_GRAY);
		gr.drawRect(24, 74, 851, 577);
		gr.setColor(Color.black);
		gr.drawRect(25, 75, 850, 575);
		gr.setColor(Color.white);
		gr.setFont(new Font("areal", Font.PLAIN, 18));
		gr.drawString("Scores : " + scores, 750, 45);

		if (moves == 0) {
			snakeXlenth[0] = 100;
			snakeXlenth[1] = 75;
			snakeXlenth[2] = 50;

			snakeYlenth[0] = 100;
			snakeYlenth[1] = 100;
			snakeYlenth[2] = 100;
		}
		for (int i = 0; i < snakeLenth; i++) {
			if (i == 0 && RIGHT) {
				HeadRight = new ImageIcon("HeadRight.png");
				HeadRight.paintIcon(this, gr, snakeXlenth[0], snakeYlenth[0]);
			} else if (i == 0 && LEFT) {
				HeadLefh = new ImageIcon("HeadLeft.png");
				HeadLefh.paintIcon(this, gr, snakeXlenth[0], snakeYlenth[0]);
			} else if (i == 0 && UP) {
				HeadUp = new ImageIcon("HeadUp.png");
				HeadUp.paintIcon(this, gr, snakeXlenth[0], snakeYlenth[0]);
			} else if (i == 0 && DOWN) {
				HeadDown = new ImageIcon("HeadDown.png");
				HeadDown.paintIcon(this, gr, snakeXlenth[0], snakeYlenth[0]);
			} else {
				tail = new ImageIcon("tail.png");
				tail.paintIcon(this, gr, snakeXlenth[i], snakeYlenth[i]);

			}

		}

		for (int i = 1; i < snakeLenth; i++) {
			if (fruitXpos[randomX] == snakeXlenth[i] && fruitYpos[randomY] == snakeYlenth[i]) {
				i = 0;
				randomX = rX.nextInt(fruitXpos.length);
				randomY = rY.nextInt(fruitYpos.length);
			}

		}
		fruit = new ImageIcon("fruit.png");
		fruit.paintIcon(this, gr, fruitXpos[randomX], fruitYpos[randomY]);
		if ((fruitXpos[randomX] == snakeXlenth[0]) && fruitYpos[randomY] == snakeYlenth[0]) {
			scores += 5;
			randomX = rX.nextInt(fruitXpos.length);
			randomY = rY.nextInt(fruitYpos.length);
			snakeLenth++;

		}
		for (int i = 1; i < snakeLenth; i++) {
			if (snakeXlenth[i] == snakeXlenth[0] && snakeYlenth[i] == snakeYlenth[0]) {
				RIGHT = false;
				LEFT = false;
				UP = false;
				DOWN = false;
				play=false;
				gr.setColor(Color.RED);
				canPlay=false;
				gr.setFont(new Font("areal", Font.BOLD, 35));
				gr.drawString("GAME OVER YOUR SCOUR: " + scores, 200, 305);
				gr.setColor(Color.white);
				gr.setFont(new Font("areal", Font.BOLD, 20));
				gr.drawString("PRESS ENTER TO RESTART: " , 300, 345);
			}

		}
		gr.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		timer.restart();
		if (canPlay) {
			
		if (RIGHT) {
			for (int i = snakeLenth - 1; i >= 0; i--) {
				snakeYlenth[i + 1] = snakeYlenth[i];
			}
			for (int i = snakeLenth; i >= 0; i--) {
				if (i == 0)
					snakeXlenth[i] = snakeXlenth[i] + 25;
				else
					snakeXlenth[i] = snakeXlenth[i - 1];
				if (snakeXlenth[i] > 850) {
					snakeXlenth[i] = 25;
				}
			}

		}
		if (LEFT) {
			for (int i = snakeLenth - 1; i >= 0; i--) {
				snakeYlenth[i + 1] = snakeYlenth[i];
			}
			for (int i = snakeLenth; i >= 0; i--) {
				if (i == 0)
					snakeXlenth[i] = snakeXlenth[i] - 25;
				else
					snakeXlenth[i] = snakeXlenth[i - 1];
				if (snakeXlenth[i] < 25) {
					snakeXlenth[i] = 850;
				}
			}

		}
		if (UP) {
			for (int i = snakeLenth - 1; i >= 0; i--) {
				snakeXlenth[i + 1] = snakeXlenth[i];
			}
			for (int i = snakeLenth; i >= 0; i--) {
				if (i == 0)
					snakeYlenth[i] = snakeYlenth[i] - 25;
				else
					snakeYlenth[i] = snakeYlenth[i - 1];
				if (snakeYlenth[i] < 75) {
					snakeYlenth[i] = 625;
				}
			}

		}
		if (DOWN) {
			for (int i = snakeLenth - 1; i >= 0; i--) {
				snakeYlenth[i + 1] = snakeYlenth[i];
			}
			for (int i = snakeLenth; i >= 0; i--) {
				if (i == 0)
					snakeYlenth[i] = snakeYlenth[i] + 25;
				else
					snakeXlenth[i] = snakeXlenth[i - 1];
				if (snakeYlenth[i] > 625) {
					snakeYlenth[i] = 75;
				}
			}

		}
		repaint();

		}
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (!play) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				moves =0;
				scores=0;
				RIGHT=true;
				snakeLenth=3;
				play=true;
				canPlay=true;
				
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT||e.getKeyCode() == KeyEvent.VK_D) {
			if (canPlay) {
			if (!LEFT) {
				moves++;
				RIGHT = true;
			} else {
				RIGHT = false;
			}
			UP = false;
			DOWN = false;
		}

		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT||e.getKeyCode() == KeyEvent.VK_A) {
			if (canPlay) {
			if (!RIGHT) {
				moves++;
				LEFT = true;
			} else {
				LEFT = false;
			}
			UP = false;
			DOWN = false;
		}

		}
		if (e.getKeyCode() == KeyEvent.VK_UP||e.getKeyCode() == KeyEvent.VK_W) {
			if (canPlay) {
			if (!DOWN) {
				moves++;
				UP = true;
			} else {
				UP = false;
			}
			RIGHT = false;
			LEFT = false;

		}
	}
		if (e.getKeyCode() == KeyEvent.VK_DOWN||e.getKeyCode() == KeyEvent.VK_S) {
			if (canPlay) {
			if (!UP) {
				moves++;
				DOWN = true;
			} else {
				DOWN = false;
			}
			RIGHT = false;
			LEFT = false;

		}
	}

	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

}
