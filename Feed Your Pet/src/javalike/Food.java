package javalike;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class Food {

	private int x;
	private int y;

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

	public int getYa() {
		return ya;
	}

	public void setYa(int ya) {
		this.ya = ya;
	}

	public BufferedImage getfoodimage() {
		return foodimage;
	}

	public void setfoodimage(BufferedImage foodimage) {
		this.foodimage = foodimage;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	private int ya = 2;
	private BufferedImage foodimage;

	private Game game;

	public void move() {

		if (game.playerObject.getScore() >= 30
				&& game.playerObject.getScore() <=80){
			game.setLevel(2);
			ya=3;
		}
			else if(game.playerObject.getScore()>80){
				game.setLevel(3);
				ya=4;
			}
		
			y = y + ya;
		if (y + ya > 400)
			game.setGameOver(true);

	}

	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		g2.drawImage(foodimage, x, y, null);

	}

	public void Xrandom() {
		Random rand = new Random();
		x = rand.nextInt(600);
		if (x > 520) {
			x = 520;
		} else if (x < 50) {
			x = 50;
		}
	}

	public Food(Game game) {
		this.game = game;
		try {
			foodimage = ImageIO.read(new File("food.png"));
		} catch (IOException ex) {
			System.err.println("not found file!!!");
		}
	}

	public Rectangle getBounds() {

		return new Rectangle(x, y, foodimage.getWidth(), foodimage.getHeight());
	}

}
