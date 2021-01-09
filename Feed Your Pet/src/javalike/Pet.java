package javalike;

import java.awt.Graphics;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Pet implements KeyListener {

	private int x;
	private int y=350;
	private Game game;
	private int xa;
	private BufferedImage petImage;

	public Pet(Game game) {
		this.game=game;
		
		try {
			petImage = ImageIO.read(new File("pet.png"));
			} catch (IOException ex) {
			System.err.println("not found image file!!!");
			}
	}
	
	public void move(){
		
		if(x+xa>500)
			xa=0;
		if(x+xa<0)
			xa=0;
		x=x+xa;
	
	}
	
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
		RenderingHints.VALUE_ANTIALIAS_ON);
		
		g2.drawImage(petImage, x, y, null) ;
		
		}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT)
			xa=-6;
			if (e.getKeyCode() == KeyEvent.VK_RIGHT)
			xa=6;

		
	}
	@Override
	public void keyReleased(KeyEvent arg0) {
		xa=0;
		
	}
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public boolean collision() {
		return game.foodObject.getBounds().intersects(getBounds());
	}
	public Rectangle getBounds() {
		
		return new Rectangle(x, y, petImage.getWidth(),
				 petImage.getHeight());
		}
	
	
}
