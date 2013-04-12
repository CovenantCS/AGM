package minesweeper;

import android.content.Context;
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

public class MinesweeperBoard extends ContinuousActionBoard {
	
	private TilePile tilePile;

	public MinesweeperBoard(int width, int height) {
		super(width, height);
		buildGameBoard();
		this.name = "Minesweeper";
		userInterupt = false;
	}
	
	public MinesweeperBoard() {
		super();
		buildGameBoard();
		this.name = "Minesweeper";
		userInterupt = false;
	}

	public void buildGameBoard() {
		tilePile = new TilePile(new Rectangle(new Point(0, getHeight() / 10), new Size(getWidth(), getHeight() - (getHeight() / 10))));
		addStationaryPiece(tilePile);	
	}

	public String getSaveData() {
		/*return pucksupply.getSaveData() + ":" + puck.getSaveData() + ":"
				+ paddle.getSaveData() + ":" + brickpile.getSaveData() + ":"
				+ score;*/
		return "";
	}

	public void setSaveData(String data) {
		/*pucksupply.setSaveData(data);
		data = data.substring(data.indexOf(":") + 1);
		puck.setSaveData(data);
		data = data.substring(data.indexOf(":") + 1);
		paddle.setSaveData(data);
		data = data.substring(data.indexOf(":") + 1);
//		brickpile.setSaveData(data.substring(0, data.indexOf(":")));
		gameOver = false;
		data = data.substring(data.indexOf(":") + 1);
		score = new SimpleScore(Integer.parseInt(data));*/
		gameOver = false;
		data = data.substring(data.indexOf(":") + 1);
		score = new SimpleScore(Integer.parseInt(data));
	}

	protected void handleGameOverException(boolean won) {
		if (won) {
			score.incScore(1);
		}
	}

	protected void handleSpriteDeletedException() throws GameOverException {
		/*try {
			movableComponents.removeElement(puck);
			puck = pucksupply.getPuck(new Point((getWidth() / 2),
					(getHeight() / 2)));
			movableComponents.addElement(puck);
		} catch (GameOverException oope) {
			stopMovement();
			gameOver = true;
			throw new GameOverException(false, "You lost, score: " + score);
		}*/
	}

	protected void handleCollisionException(CollisionException ce) {
		if (ce.getSprite1().name.equals("Puck")
				&& ce.getSprite2().name.equals("Brick"))
			score.incScore(1);
	}
/*
	public void ptrPressed(int x, int y) {
		paddle.moveTo(x);
	}

	public void ptrDragged(int x, int y) {
		paddle.moveTo(x);
	}
*/
/*
	public void keyLeft(boolean down) {
		if (down)
			paddle.moveLeft(true);
		else
			paddle.moveLeft(false);
	}

	public void keyRight(boolean down) {
		if (down)
			paddle.moveRight(true);
		else
			paddle.moveRight(false);
	}
*/
	public void keyUp(boolean down) {
		userInterupt = true;
	}

	@Override
	public void loadGame(Context context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveGame(Context context) {
		// TODO Auto-generated method stub
		
	}

}