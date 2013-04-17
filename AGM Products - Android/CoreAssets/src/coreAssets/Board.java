package coreAssets;

import java.util.Vector;

import android.content.Context;

public interface Board {
	void tick() throws GameOverException, UserInteruptException;

	int getWidth();

	void setWidth( int width );
	
	int getHeight();
	
	void setHeight( int height );
	
	void buildGameBoard();
	
	boolean gameOver();

	String getSaveData();
	
	void loadGame( Context context );
	
	void saveGame( Context context );

	void setSaveData(String data);

	void startMovement();

	void stopMovement();

	Score getScore();

	Vector<SpriteDesc> getSpriteDesc();
	
	Vector<TextSprite> getTextSprites();

	void ptrPressed(int x, int y);

	void ptrReleased(int x, int y);

	void ptrDragged(int x, int y);

	void keyLeft(boolean down);

	void keyRight(boolean down);

	void keyUp(boolean down);
}
