package javalike;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static Game game = new Game();
	Menu menu = new Menu();
	static JPanel centerPanel = new JPanel();

	static void showGamePanel(){
		 centerPanel.add(game);
	}
	
	
	public Main() {
		centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
		centerPanel.add(menu);
		

		add(centerPanel);

		setTitle("Feed Your Pet");
		setSize(626, 530);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	public static void main(String Args[]) {
		new Main();
	}
}
