package coreAssets;

public interface Services {
	void play();

	void exit();

	String save();

	String reload();

	void pause();

	void unpause();

	int getSpeed();

	void setSpeed(int speed);

	int getSoundLevel(int level);

	void setSoundLevel(int level);

	boolean gameOver();

	Score getCurrentScore();

	String saveScore();

	Score[] topScores();

	Game getGame();

	void newGame();
}
