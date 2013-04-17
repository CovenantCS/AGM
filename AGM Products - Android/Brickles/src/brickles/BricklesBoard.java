package brickles;

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

public class BricklesBoard extends ContinuousActionBoard {
	private Paddle paddle;
	private Puck puck;
	private BrickPile brickpile;
	private PuckSupply pucksupply;
	private int paddleColor;
	private int brickColor;
	private Context context;

	public BricklesBoard(Context context, int width, int height, PuckSupply pucksupply) {
		super(width, height);
		init( context, pucksupply );
		buildGameBoard();
	}
	
	public BricklesBoard(Context context, PuckSupply pucksupply) {
        super();
        
        this.name = "brickles";
        init( context, pucksupply );
    }
	
	public BricklesBoard( Context context, PuckSupply pucksupply, int paddleColor, int brickColor )
	{
	    super();
	    init( context, pucksupply );
	    this.paddleColor = paddleColor;
	    this.brickColor = brickColor;
	}
	
	private void init( Context context, PuckSupply pucksupply )
	{
		this.context = context;
	    this.name = "brickles";
        this.pucksupply = pucksupply;
        userInterupt = false;
	}

	public void buildGameBoard() {
		EndWall ceiling;
		EndWall floor;
		SideWall leftwall;
		SideWall rightwall;

		int paddleWidth = getWidth() / 8;
		int paddleHeight = getHeight() /40;
		paddle = new Paddle(new Rectangle(new Point((getWidth() / 2)
				- (paddleWidth / 2) + 15, getHeight() - (getHeight() / 10)),
				new Size(paddleWidth, paddleHeight)), this.paddleColor);
		paddle.startMoving();
		addMovablePiece(paddle);

		try {
			puck = pucksupply.getPuck(new Point((getWidth() / 2),
					(getHeight() / 2)), 30);
			addMovablePiece(puck);
		} catch (GameOverException e) {
			// this is the first puck. It should always succeed
		}

		ceiling = new EndWall(new Rectangle(new Point(-5, -5), new Size(
				getWidth() + 10, 5)), false);
		addStationaryPiece(ceiling);

		floor = new EndWall(new Rectangle(new Point(-5, getHeight()), new Size(
				getWidth() + 10, 5)), true);
		addStationaryPiece(floor);

		leftwall = new SideWall(new Rectangle(new Point(-5, -5), new Size(5,
				getHeight() + 10)), false);
		addStationaryPiece(leftwall);

		rightwall = new SideWall(new Rectangle(new Point(getWidth(), -5),
				new Size(5, getHeight() + 10)), false);
		addStationaryPiece(rightwall);

		brickpile = new BrickPile(context, new Rectangle(new Point(getWidth() / 20,
				getHeight() / 20), new Size(getWidth() - (getWidth() / 10),
				getHeight() / 5)), brickColor);
		addStationaryPiece(brickpile);
	}

	public String getSaveData() {
		return pucksupply.getSaveData() + ":" + puck.getSaveData() + ":"
				+ paddle.getSaveData() + ":" + brickpile.getSaveData() + ":"
				+ score;
	}

	public void setSaveData(String data) {
		pucksupply.setSaveData(data);
		data = data.substring(data.indexOf(":") + 1);
		puck.setSaveData(data);
		data = data.substring(data.indexOf(":") + 1);
		paddle.setSaveData(data);
		data = data.substring(data.indexOf(":") + 1);
		brickpile.setSaveData(data.substring(0, data.indexOf(":")));
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
		try {
			movableComponents.removeElement(puck);
			puck = pucksupply.getPuck(new Point((getWidth() / 2),
					(getHeight() / 2)), 10);
			movableComponents.addElement(puck);
		} catch (GameOverException oope) {
			stopMovement();
			gameOver = true;
			throw new GameOverException(false, "You lost, score: " + score);
		}
	}

	protected void handleCollisionException(CollisionException ce) {
		if (ce.getSprite1().name.equals("Puck")
				&& ce.getSprite2().name.equals("Brick"))
			score.incScore(1);
	}

	public void ptrPressed(int x, int y) {
		paddle.moveTo(x);
	}

	public void ptrDragged(int x, int y) {
		paddle.moveTo(x);
	}

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

	public void keyUp(boolean down) {
		userInterupt = true;
	}

}
