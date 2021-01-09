package javalike;

public class Player {

	private Game game;

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	private int score;

	public Player(Game game) {

		this.game = game;
	}

	public void getfood() {

		if (game.petObject.collision()) {
			game.foodObject.setY(1);
			game.foodObject.Xrandom();
			score += 10;
			System.out.println(score);

		}

	}
}
