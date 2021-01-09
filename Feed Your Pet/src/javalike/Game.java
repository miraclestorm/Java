package javalike;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Game extends JPanel implements Runnable, KeyListener {

	private static final long serialVersionUID = 1L;
	private Thread foodThread;
	private BufferedImage backImage;

	private boolean gameOver;

	private int level = 1;

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public BufferedImage getGameoverImage() {
		return gameoverImage;
	}

	public void setGameoverImage(BufferedImage gameoverImage) {
		this.gameoverImage = gameoverImage;
	}

	Food foodObject = new Food(this);
	private BufferedImage gameoverImage;
	Pet petObject = new Pet(this);
	Player playerObject = new Player(this);

	public Thread getfoodThread() {
		return foodThread;
	}

	public void setfoodThread(Thread foodThread) {
		this.foodThread = foodThread;
	}

	public BufferedImage getBackImage() {
		return backImage;
	}

	public void setBackImage(BufferedImage backImage) {
		this.backImage = backImage;
	}

	public boolean isGameOver() {
		return gameOver;
	}

	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}

	public Food getfoodObject() {
		return foodObject;
	}

	public void setfoodObject(Food foodObject) {
		this.foodObject = foodObject;
	}

	public Pet getpetObject() {
		return petObject;
	}

	public void setpetObject(Pet petObject) {
		this.petObject = petObject;
	}

	public Player getPlayerObject() {
		return playerObject;
	}

	public void setPlayerObject(Player playerObject) {
		this.playerObject = playerObject;
	}

	public void addNotify() {
		super.addNotify();
		foodThread = new Thread(this);
		foodThread.start();
	}

	public void paint(Graphics g) {
		requestFocus();
		super.paint(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		setBackground(Color.BLACK);

		g2.drawImage(backImage, 0, 0, null);

		foodObject.paint(g2);
		petObject.paint(g2);

		g2.setColor(Color.WHITE);
		Font font = new Font("arial", Font.BOLD, 30);
		g2.setFont(font);
		g2.drawString("Score:", 30, 480);
		g2.drawString("" + playerObject.getScore(), 130, 480);
		g2.drawString("level:", 480, 480);
		g2.drawString("" + level, 560, 480);

		if (gameOver)
			displayGameOver(g2);

	}

	public void move() {
		foodObject.move();
		petObject.move();
		playerObject.getfood();
	}

	public Game() {
		addKeyListener(this);
		try {
			backImage = ImageIO.read(new File("back.png"));
			gameoverImage = ImageIO.read(new File("gameover.png"));

		} catch (IOException ex) {
			System.err.println("not found image file!!!");
		}

	}

	public void displayGameOver(Graphics2D g) {
		g.drawImage(gameoverImage, 0, 0, null);
	}

	public void run() {

		while (!gameOver) {
			move();
			repaint();

			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
		}
		
	}
	
	public void keyPressed(KeyEvent e) {

		petObject.keyPressed(e);

	}

	public void keyReleased(KeyEvent e) {
		petObject.keyReleased(e);

	}

	public void keyTyped(KeyEvent arg0) {

	}

}
