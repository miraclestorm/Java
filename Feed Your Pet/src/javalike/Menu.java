package javalike;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Menu extends JPanel implements ActionListener, KeyListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JButton playButton;
	JButton helpButton;
	JButton exitButton;
	BufferedImage backgroundMenuImage;
	BufferedImage helpMenuImage;
	boolean flagHelp;

	public void createButton() {

		playButton = new JButton(new ImageIcon("play.png"));
		helpButton = new JButton(new ImageIcon("help.png"));
		exitButton = new JButton(new ImageIcon("exit.png"));
		playButton.setBounds(200, 100, 252, 82);
		helpButton.setBounds(200, 200, 252, 82);
		exitButton.setBounds(200, 300, 252, 82);
		playButton.addActionListener(this);
		helpButton.addActionListener(this);
		exitButton.addActionListener(this);
		add(playButton);
		this.add(helpButton);
		add(exitButton);
		setLayout(null);
		setVisible(true);

	}

	public void ShowButton(boolean bln) {
		playButton.setVisible(bln);
		helpButton.setVisible(bln);
		exitButton.setVisible(bln);

	}

	public Menu() {
		
		addKeyListener(this);
		createButton();

		try {
			backgroundMenuImage = ImageIO.read(new File("backgroundMenu.png"));
			helpMenuImage = ImageIO.read(new File("helpimage.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void paintComponent(Graphics g) {
		requestFocus();
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2.drawImage(backgroundMenuImage, 0, 0, null);
		if (flagHelp) {
			ShowButton(false);
			g2.drawImage(helpMenuImage, 0, 0, null);

		}

	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == playButton) {
			this.setVisible(false);
			Main.showGamePanel();
		}
		if (e.getSource() == helpButton) {
			flagHelp = true;
			repaint();
		}
		if(e.getSource()==exitButton){
			System.exit(0);
		}
	}

	public void keyPressed(KeyEvent e) {
		System.out.println("*");
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
			;
		flagHelp = false;
		
		repaint();
		ShowButton(true);
	}

	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

}
