package coreAssets;

import java.util.Vector;

public interface Board {
	void tick() throws GameOverException, UserInteruptException;

	boolean gameOver();

	String getSaveData();
	
	void loadGame();
	
	void saveGame();

	void setSaveData(String data);

	void startMovement();

	void stopMovement();

	Score getScore();

	Vector getSpriteDesc();

	void ptrPressed(int x, int y);

	void ptrReleased(int x, int y);

	void ptrDragged(int x, int y);

	void keyLeft(boolean down);

	void keyRight(boolean down);

	void keyUp(boolean down);
}
