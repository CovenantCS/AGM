package edu.covenant.kepler.minesweeper;


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

	public MinesweeperBoard(int width, int height) {
		super(width, height);
		init();
	}
	
	public MinesweeperBoard(Context context) {
		super();
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

		tilePile = new TilePile(new Rectangle(new Point(0, getHeight() / 10), new Size(getWidth(), getHeight() - (getHeight() / 10))), this);
		addStationaryPiece(tilePile);
		
		addText( new TextSprite( "Score: " + score, Color.BLACK, 10, (float)(getHeight() / 20), (float)(getWidth() / 2)) );
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
//				Point click = new Point(x,y);
//				Rectangle r = t.getRectangle();
//				Point point = r.getLocation();
//				Size size = r.getSize();
//				boolean found;
//				if(click.getRealX() > point.getRealX() - size.getWidth() / 2 && click.getRealX() < point.getRealX() + size.getWidth() / 2) {
//					found = true;
//				} else {
//					found = false;
//				}
//				if(click.getRealY() > point.getRealY() - size.getHeight() / 2 && click.getRealY() < point.getRealY() + size.getHeight() / 2) {
//					found = true;
//				} else {
//					found = false;
//				}
//				if(found) {
//					selected = t;
//					break;
//				}
			}
		}
		if(selected != null) {
			selected.reveal();
		}
	}
	
//	 private OnClickListener leftListener = new OnClickListener() {
//	        public void onClick(View v) {
//	        	if(TilePile.curSelected != null) {
//	    			TilePile.curSelected.flag();
//	    		}
//	     }
//	 };
//	 private OnClickListener rightListener = new OnClickListener() {
//	        public void onClick(View v) {
//	        	if(TilePile.curSelected != null) {
//	    			TilePile.curSelected.reveal();
//	    		}
//	     }
//	 };
	
	public void keyLeft(boolean down) {
		if(TilePile.curSelected != null) {
			TilePile.curSelected.flag();
		}
	}

	public void keyRight(boolean down) {
		if(TilePile.curSelected != null) {
			TilePile.curSelected.reveal();
		}
	}

	
	public void keyUp(boolean down) {
		userInterupt = true;	}

	@Override
	public void loadGame(Context context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveGame(Context context) {
		// TODO Auto-generated method stub
		
	}

}