package bowl;

import java.util.Random;
import android.R.color;
import android.graphics.Color;
import android.content.Context;
import coreAssets.EndWall;
import coreAssets.CollisionException;
import coreAssets.GameOverException;
import coreAssets.Point;
import coreAssets.Rectangle;
import coreAssets.SimpleScore;
import coreAssets.Size;
import coreAssets.StimulasActionBoard;
import coreAssets.TextSprite;

public class BowlingBoard extends StimulasActionBoard {
	protected BowlingBall ball;
	protected RackOfPins rack;
	private Random rand;
	private int ballColor;
	private int ballSize;
	private int pinColor;
	protected int frame = 0; // for each game you get 10 frames
	protected int ballNum = 1; // for each frame you get two balls
	Context context;
	
	public BowlingBoard( Context context, SimpleScore score ) {
        super();
        init(context);
    }
	
	public BowlingBoard(Context context, int ballColor, int pinColor )
	{
	    init(context);
	    this.ballColor = ballColor;
	    this.pinColor = pinColor;
	}
	
	public void init(Context context)
	{
		this.context = context;
	    this.name = "bowl";
	    this.score = new SimpleScore();
        rand = new Random();
        gameOver = false;
	}

	public void buildGameBoard() {
		EndWall endOfAlley;
		Lane lane;
		this.ballSize = getWidth() / 24;
		TextArea textarea;

		endOfAlley = new EndWall(new Rectangle(new Point(-5, -5), new Size(
				getWidth() + 10, 5)), true);
		lane = new Lane(new Rectangle(new Point(0, 0), new Size(
				(int)(getWidth() * 0.75), getHeight())));
		textarea = new TextArea(new Rectangle(new Point((int)(getWidth() * 0.74), 0), new Size((int)(getWidth() * 0.3), getHeight())));
		addStationaryPiece(endOfAlley);
		addStationaryPiece(lane);
		addStationaryPiece(textarea);
		rackPins(this.context);

		addText(new TextSprite( "Score: "+score, Color.YELLOW, (getHeight() / 25 ), (float)(getWidth() * 0.75), (float)getHeight() / 10 ));
		/*addText(new TextSprite( "To start:", Color.YELLOW, (getHeight() / 25 ), (float)(getWidth() * 0.75), (float)getHeight() - (10*5) ));
		addText(new TextSprite( "Tap screen or", Color.YELLOW, (getHeight() / 25 ), (float)(getWidth() * 0.75), (float)getHeight() - (10*4) ));
		addText(new TextSprite( "press left", Color.YELLOW, (getHeight() / 25 ), (float)(getWidth() * 0.75), (float)getHeight() - (10*3) ));
		addText(new TextSprite( "arrow key.", Color.YELLOW, (getHeight() / 25 ), (float)(getWidth() * 0.75), (float)getHeight() - (10*2) ));*/
	}

	public void rackPins(Context context) {
		stationaryComponents.removeElement(rack);
		rack = new RackOfPins(context, new Rectangle(new Point(((getWidth() - 30) / 6),
				getHeight() / 20), new Size(((3 * getWidth() - 60) / 5)
				- getWidth() / 10, getHeight() / 5)), this.pinColor);
		addStationaryPiece(rack);
		System.err.println("rack created");
	}

	public int checkPointerX(int x) {

		if (x < 5 * (getWidth() / 16))
			x = (5 * (getWidth() / 16));
		else if (x > getWidth() - (getWidth() / 16))
			x = (getWidth() - (getWidth() / 16));

		return x;
	}

	public void bowl(int x, int y) {
		ball = new BowlingBall(new Point(x, getHeight() - (getHeight() / 20)), this.ballColor, this.ballSize);
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
    	try {
    		/*if (rack != null) System.err.println("rack");
    		if (ball != null) System.err.println("ball");
    		if (score != null) System.err.println("score");*/
    		if (ball == null) {
    			return "null:" + rack.getSaveData() + ":" + score;
    		} else {
    			return ball.getSaveData() + ":" + rack.getSaveData() + ":" + score;
    		}
    	}
    	catch (NullPointerException e) {
    		return "";
    	}
	}

	public void setSaveData(String data) {
System.err.println("set "+data);
		if (data.length() > 5) { 
			if (!data.substring(0, 4).equals("null")) {
				if (ball == null) {
					bowl(getWidth() / 2 - 10, 10);
				}
				ball.setSaveData(data);
				ball.startMoving();
			}
			data = data.substring(data.indexOf(":") + 1);
			rack.setSaveData(data);
			data = data.substring(data.indexOf(":") + 1);
			score = new SimpleScore(data);
		}
	}

	protected void handleSpriteDeletedException() throws GameOverException {
		movableComponents.removeElement(ball);
		rack.stopPins();
		ball = null;
		ballNum++;
		if (ballNum > 2) {
			if (frame <= 10) {
				System.out.println("score: " + score + " frame: " + frame);
				textComponents.elementAt(0).setValue("Score: "+score) ;
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
			textComponents.elementAt(0).setValue("Score: "+score);
		}
	}

	protected void handleTickAction() {
		rack.movePins();
		score.incScore(rack.checkForCollision());
		textComponents.elementAt(0).setValue("Score: "+score);
	}
}
