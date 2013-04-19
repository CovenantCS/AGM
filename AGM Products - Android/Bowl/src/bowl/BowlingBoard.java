package bowl;

import java.util.Random;

import android.content.Context;

import coreAssets.EndWall;

import coreAssets.CollisionException;
import coreAssets.GameOverException;
import coreAssets.Point;
import coreAssets.Rectangle;
import coreAssets.SimpleScore;
import coreAssets.Size;
import coreAssets.StimulasActionBoard;

public class BowlingBoard extends StimulasActionBoard {
	protected BowlingBall ball;
	protected RackOfPins rack;
	private Random rand;
	private int ballColor;
	private int pinColor;
	protected int frame = 0; // for each game you get 10 frames
	protected int ballNum = 1; // for each frame you get two balls
	Context context;

	//we're not using this currently
	//also conflicts with color-based constructor
	//public BowlingBoard(int width, int height) {
	//	super(width, height);
	//	init();
	//}
	
	public BowlingBoard(Context context) {
        super();
        init(context); 
    }
	
	public BowlingBoard( int ballColor, int pinColor )
	{
	    this.ballColor = ballColor;
	    this.pinColor = pinColor;
	}
	
	public void init(Context context)
	{
		this.context = context;
	    this.name = "bowl";
        rand = new Random();
        gameOver = false;
	}

	public void buildGameBoard(Context context) {
		EndWall endOfAlley;
		Lane lane;

		endOfAlley = new EndWall(new Rectangle(new Point(-5, -5), new Size(
				getWidth() + 10, 5)), true);
		lane = new Lane(new Rectangle(new Point(0, 0), new Size(
				getWidth() - 60, getHeight())));
		addStationaryPiece(endOfAlley);
		addStationaryPiece(lane);
		rackPins(context);
	}

	public void rackPins(Context context) {
		stationaryComponents.removeElement(rack);
		rack = new RackOfPins( context, new Rectangle(new Point(((getWidth() - 30) / 6),
				getHeight() / 20), new Size(((3 * getWidth() - 60) / 5)
				- getWidth() / 10, getHeight() / 5)), this.pinColor);
		addStationaryPiece(rack);
	}

	public int checkPointerX(int x) {

		if (x < 5 * (getWidth() / 16))
			x = (5 * (getWidth() / 16));
		else if (x > getWidth() - (getWidth() / 16))
			x = (getWidth() - (getWidth() / 16));

		return x;
	}

	public void bowl(int x, int y) {
		ball = new BowlingBall(new Point(x, getHeight() - (getHeight() / 20)), this.ballColor);
		int i = rand.nextInt();
		i = (i >= 0 ? i : -i);
		ball.setDirection((i % 60) + 60);
		movableComponents.addElement(ball);
	}

	public void ptrReleased(int x, int y) {
		if (ball == null) {
			bowl(getWidth() / 2 - 10, 10);
			ball.startMoving();
		}
	}

	public void keyLeft(boolean down) {
		if (ball == null) {
			bowl(getWidth() / 2 - 10, 10);
			ball.startMoving();
		}
	}

	public void keyUp(boolean down) {
		userInterupt = true;
	}

	public String getSaveData() {		
		if (ball == null) {
			return "null:" + rack.getSaveData();
			// add scores
		} else {
			return ball.getSaveData() + ":" + rack.getSaveData();
			// add scores
		}
	}

	public void setSaveData(String data) {
		if (!data.substring(0, 4).equals("null")) {
			if (ball == null) {
				bowl(getWidth() / 2 - 10, 10);
			}
			ball.setSaveData(data);
		}
		data = data.substring(data.indexOf(":") + 1);
		data = data.substring(data.indexOf(":") + 1);
		rack.setSaveData(data);
	}

	protected void handleSpriteDeletedException(Context context) throws GameOverException {
		movableComponents.removeElement(ball);
		rack.stopPins();
		ball = null;
		ballNum++;
		if (ballNum > 2) {
			if (frame <= 10) {
				System.out.println("score: " + score + " frame: " + frame);
				rackPins(context);
				ballNum = 1;
				frame++;
			} else {
				throw new GameOverException(true, "Game Over, score: " + score);
			}
		}
	}

	protected void handleCollisionException(CollisionException ce) {
		if (ce.getSprite2().name.equals("Pin")) {
			score.incScore(1);
		}
	}

	protected void handleTickAction() {
		rack.movePins();
		score.incScore(rack.checkForCollision());
	}

	@Override
	public void buildGameBoard()
	{
		// TODO Auto-generated method stub
		
	}

}
