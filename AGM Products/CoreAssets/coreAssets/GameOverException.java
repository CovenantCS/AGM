package coreAssets;

public class GameOverException extends Exception {
	private boolean won;

	public GameOverException(boolean won, String msg) {
		super(msg);
		this.won = won;
	}

	public boolean hasWon() {
		return won;
	}
}
