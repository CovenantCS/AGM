package edu.covenant.kepler.minesweeper;


import java.util.Vector;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import coreAssets.CollisionException;
import coreAssets.ContinuousActionBoard;
import coreAssets.EndWall;
import coreAssets.GameOverException;
import coreAssets.Paddle;
import coreAssets.Point;
import coreAssets.Puck;
import coreAssets.PuckSupply;
import coreAssets.Rectangle;
import coreAssets.SideWall;
import coreAssets.SimpleScore;
import coreAssets.Size;
import coreAssets.TextSprite;

public class MinesweeperBoard extends ContinuousActionBoard {
	
	private TilePile tilePile;
	private Context context;
	private int tileColor;

	public MinesweeperBoard(int width, int height, int tileColor) {
		super(width, height);
		this.tileColor = tileColor;
		init();
	}
	
	public MinesweeperBoard(Context context, int tileColor) {
		super();
		this.tileColor = tileColor;
		init();
	}
	
	public void init() {
		this.name = "Minesweeper";
		userInterupt = false;
		this.score = new SimpleScore();
	}

	public void buildGameBoard() {
//		Button left = new Button(context);
//		left.setOnClickListener(leftListener);
//		Button right = new Button(context);
//		right.setOnClickListener(rightListener);

		tilePile = new TilePile(new Rectangle(new Point(0, getHeight() / 10), new Size(getWidth(), getHeight() - (getHeight() / 10))), this, tileColor);
		addStationaryPiece(tilePile);
		
		addText( new TextSprite( "Score: " + score, Color.BLACK, (getHeight() / 25 ), (float)(getWidth() / 10), (float)(getHeight() / 20) ) );
	}

	public String getSaveData() {
		/*return pucksupply.getSaveData() + ":" + puck.getSaveData() + ":"
				+ paddle.getSaveData() + ":" + brickpile.getSaveData() + ":"
				+ score;*/
    	try {
    		return tilePile.getSaveData() + ":" + score;
    	}
    	catch (NullPointerException e) {
    		return "";
    	}
	}

	public void setSaveData(String data) {
		//System.out.println(data);
		/*pucksupply.setSaveData(data);
		data = data.substring(data.indexOf(":") + 1);
		puck.setSaveData(data);
		data = data.substring(data.indexOf(":") + 1);
		paddle.setSaveData(data);
		data = data.substring(data.indexOf(":") + 1);
		brickpile.setSaveData(data.substring(0, data.indexOf(":")));*/
		gameOver = false;
		tilePile.setSaveData(data);
		data = data.substring(data.indexOf(":") + 1);
		System.out.println(data);
		score = new SimpleScore(Integer.parseInt(data));
		textComponents.elementAt(0).setValue("Score: "+score);
	}

	protected void handleGameOverException(boolean won) {
		if (won) {
			score.incScore(1);
		}
	}

	protected void handleSpriteDeletedException() throws GameOverException {
	//	try {
		//	movableComponents.removeElement(puck);
			//puck = pucksupply.getPuck(new Point((getWidth() / 2),
					//(getHeight() / 2)), 10);
			//movableComponents.addElement(puck);
		//} catch (GameOverException oope) {
			stopMovement();
			gameOver = true;
			throw new GameOverException(false, "You lost, score: " + score);
		//}
	
	}
	
	public void updateScore() {
		score.incScore(1);
		textComponents.elementAt(0).setValue("Score: "+score);
	}

	protected void handleCollisionException(CollisionException ce) {
		//if (ce.getSprite1().name.equals("Puck")
		//		&& ce.getSprite2().name.equals("Brick")) {
			score.incScore(1);
			textComponents.elementAt(0).setValue("Score: "+score);
		//}
	}
/*
	public void ptrPressed(int x, int y) {
		paddle.moveTo(x);
	}

	public void ptrDragged(int x, int y) {
		paddle.moveTo(x);
	}
*/
	
	public void ptrReleased(int x, int y) {
		Tile selected = null;
		Tile[][] pile = tilePile.getPile();
		for(int i = 0; i < pile.length; i++) {
			for(int j = 0; j < pile[0].length; j++) {
				Tile t = pile[i][j];
				Rectangle click = new Rectangle(new Point(x, y), new Size(1, 1));
				Rectangle r = t.getRectangle();
				if(click.intersects(r)) {
					selected = t;
					break;
				}
			}
		}
		if(selected != null) {
			try {
				if(selected.getNum() == 0) {
					selected.expand(selected);
				} else {
					selected.reveal();
				}
			} catch (CollisionException e) {
				handleCollisionException(e);
			}
            catch ( GameOverException e )
            {
                gameOver = true;
            }
		}
	}
	
	public void keyLeft(boolean down) {
		if(TilePile.curSelected != null) {
			TilePile.curSelected.flag();
		}
	}

	public void keyRight(boolean down) {
		if(TilePile.curSelected != null) {
			try {
				TilePile.curSelected.reveal();
			} catch (CollisionException e) {
				handleCollisionException(e);
			}
            catch ( GameOverException e )
            {
                gameOver = true;
            }
		}
	}

	
	public void keyUp(boolean down) {
		userInterupt = true;
	}

	public Vector getTextComponents() {
		// TODO Auto-generated method stub
		return textComponents;
	}

}
