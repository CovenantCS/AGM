package bowl;

import java.util.Random;

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
	protected int frame = 0; // for each game you get 10 frames
	protected int ballNum = 1; // for each frame you get two balls

	public BowlingBoard(int width, int height) {
		super(width, height);
		this.name = "bowl";
		rand = new Random();
		buildGameBoard();
		gameOver = false;
	}
	
	public BowlingBoard() {
        super();
        this.name = "bowl";
        rand = new Random();
        gameOver = false;
    }

	public void buildGameBoard() {
		EndWall endOfAlley;
		Lane lane;

		endOfAlley = new EndWall(new Rectangle(new Point(-5, -5), new Size(
				getWidth() + 10, 5)), true);
		lane = new Lane(new Rectangle(new Point(0, 0), new Size(
				getWidth() - 60, getHeight())));
		addStationaryPiece(endOfAlley);
		addStationaryPiece(lane);
		rackPins();
	}

	public void rackPins() {
		stationaryComponents.removeElement(rack);
		rack = new RackOfPins(new Rectangle(new Point(((getWidth() - 30) / 6),
				getHeight() / 20), new Size(((3 * getWidth() - 60) / 5)
				- getWidth() / 10, getHeight() / 5)));
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
		ball = new BowlingBall(new Point(x, getHeight() - (getHeight() / 20)));
		int i = rand.nextInt();
		i = (i >= 0 ? i : -i);
		ball.setDirection((i % 60) + 60);
		movableComponents.addElement(ball);
	}

	public void ptrReleased(int x, int y) {
		if (ball == null) {
			bowl(x, y);
		}
	}

	public void keyLeft(boolean down) {
		if (down) {
			// don't do anything when pressed down
		} else if (ball == null) {
			bowl(getWidth() / 2 - 10, 10);
			ball.startMoving();
		}
	}

	public void keyUp(boolean down) {
		userInterupt = true;
	}

	public String getSaveData() {
		return ball.getSaveData() + ":" + rack.getSaveData();
		// add scores
	}

	public void setSaveData(String data) {
		ball.setSaveData(data);
		data = data.substring(data.indexOf(":") + 1);
		data = data.substring(data.indexOf(":") + 1);
		rack.setSaveData(data);
	}

	protected void handleSpriteDeletedException() throws GameOverException {
		movableComponents.removeElement(ball);
		rack.stopPins();
		ball = null;
		ballNum++;
		if (ballNum > 2) {
			if (frame <= 10) {
				System.out.println("score: " + score + " frame: " + frame);
				rackPins();
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

}
