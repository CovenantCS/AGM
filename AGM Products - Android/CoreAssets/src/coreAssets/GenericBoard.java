package coreAssets;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Vector;

import android.content.Context;

import coreAssets.Board;
import coreAssets.CollisionException;
import coreAssets.GameOverException;
import coreAssets.GameSprite;
import coreAssets.Score;
import coreAssets.SimpleScore;
import coreAssets.SpriteDeletedException;
import coreAssets.UserInteruptException;

//import OldUI.GenericScoreBoard;
import coreAssets.MovableSprite;

public abstract class GenericBoard implements Board {
	protected Vector<GameSprite> movableComponents;

	protected Vector<GameSprite> stationaryComponents;
	
	protected Vector<TextSprite> textComponents;

	protected int speed;
	
	protected String name;

	protected boolean moving;

	protected SimpleScore score;

	//protected GenericScoreBoard sb;

	protected boolean gameOver;

	int width;

	int height;

	public GenericBoard(int width, int height) {
		this.width = width;
		this.height = height;
		movableComponents = new Vector<GameSprite>();
		stationaryComponents = new Vector<GameSprite>();
		textComponents = new Vector<TextSprite>();
		speed = 1;
		moving = true;
		score = new SimpleScore();
		gameOver = false;
	}
	
	public GenericBoard() {
        movableComponents = new Vector<GameSprite>();
        stationaryComponents = new Vector<GameSprite>();
		textComponents = new Vector<TextSprite>();
        speed = 1;
        moving = true;
        score = new SimpleScore();
        gameOver = false;
    }

	public Score getScore() {
		return score;
	}

	public void startMovement() {
		moving = true;
	}

	public void stopMovement() {
		moving = false;
	}

	public void setSpeed(int newValue) {
		speed = newValue;
	}

	public int getSpeed() {
		return speed;
	}

	public boolean getGameOver() {
		return gameOver;
	}

	public void tick() throws GameOverException, UserInteruptException {
		if (moving) {
			handleTick();
		}
	}

	public void addMovablePiece(GameSprite sp) {
		if (sp != null) // skip optional pieces without comment
			movableComponents.addElement(sp);
	}

	public void removeMovablePiece(GameSprite sp) {
		movableComponents.removeElement(sp);
	}

	public void addStationaryPiece(GameSprite sp) {
		if (sp != null) // skip optional pieces without comment
			stationaryComponents.addElement(sp);
	}

	public void removeStationaryPiece(GameSprite sp) {
		stationaryComponents.removeElement(sp);
	}

	public void addText(TextSprite tsp) {
		if (tsp != null) // skip optional pieces without comment
			textComponents.addElement(tsp);
	}

	public void removeText(TextSprite tsp) {
		textComponents.removeElement(tsp);
	}

	public void resetList() {
		movableComponents.removeAllElements();
	}

	public void checkForCollision() throws GameOverException,
			CollisionException, SpriteDeletedException {
		for (int i = 0; i < movableComponents.size(); i++) {
			MovableSprite ms = (MovableSprite) (movableComponents.elementAt(i));
			for (int j = 0; j < stationaryComponents.size(); j++) {

				GameSprite ss = (GameSprite) (stationaryComponents.elementAt(j));
				if (ss.overLaps(ms)) {
					ss.collideWith(ms);
				}
			}
		}

		for (int i = 0; i < movableComponents.size(); i++) {
			MovableSprite ms = (MovableSprite) (movableComponents.elementAt(i));
			for (int j = i + 1; j < movableComponents.size(); j++) {
				GameSprite ss = (GameSprite) (movableComponents.elementAt(j));
				if (ss.overLaps(ms)) {
					ss.collideWith(ms);
				}
			}
		}
	}

	public abstract void handleTick() throws GameOverException,
			UserInteruptException;

	public abstract void buildGameBoard();

	public Vector<SpriteDesc> getSpriteDesc() {
		Vector<SpriteDesc> sprites = new Vector<SpriteDesc>();

		for (int i = 0; i < stationaryComponents.size(); i++) {
			GameSprite gs = ((GameSprite) stationaryComponents.elementAt(i));
			gs.buildSpriteDesc(sprites);
		}
		for (int i = 0; i < movableComponents.size(); i++) {
			GameSprite gs = ((GameSprite) movableComponents.elementAt(i));
			gs.buildSpriteDesc(sprites);
		}
		return sprites;
	}
	
	public Vector<TextSprite> getTextSprites() {
		return textComponents;
	}

	public boolean gameOver() {
		return gameOver;
	}

	public int getWidth() {
		return width;
	}
	
	public void setWidth( int width )
	{
	    this.width = width;
	}

	public int getHeight() {
		return height;
	}
	
	public void setHeight( int height )
	{
	    this.height = height;
	}

	/*
	 * These provide a default of no action To take an action over ride in a
	 * particular game board
	 */
	public void ptrPressed(int x, int y) {
	};

	public void ptrReleased(int x, int y) {
	};

	public void ptrDragged(int x, int y) {
	};

	public void keyLeft(boolean down) {
	};

	public void keyRight(boolean down) {
	};

	public void keyUp(boolean down) {
	};
	
	@Override
    public void loadGame( Context context )
    {
        String data = "";
        String fileName = this.name;

        try
        {
            InputStream inputStream = context.openFileInput( fileName );

            if ( inputStream != null )
            {
                InputStreamReader inputStreamReader = new InputStreamReader(
                        inputStream );
                BufferedReader bufferedReader = new BufferedReader(
                        inputStreamReader );
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ( ( receiveString = bufferedReader.readLine() ) != null )
                {
                    stringBuilder.append( receiveString );
                }

                inputStream.close();
                data = stringBuilder.toString();
                setSaveData( data );
            }
        }
        catch ( FileNotFoundException e )
        {
            e.printStackTrace();
        }
        catch ( IOException e )
        {
            e.printStackTrace();
        }

    }

    @Override
    public void saveGame( Context context )
    {
        String data = getSaveData();
        String fileName = this.name;
        FileOutputStream outputStream;
        try
        {
            context.deleteFile( fileName );
            outputStream = context.openFileOutput( fileName,
                    Context.MODE_PRIVATE );
            outputStream.write( data.getBytes() );
            outputStream.close();
        }
        catch ( Exception e )
        {
            e.printStackTrace();
        }
    }
}
