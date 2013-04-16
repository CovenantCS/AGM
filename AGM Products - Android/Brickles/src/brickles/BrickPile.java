package brickles;

/*
 * BrickPile.java
 *
 * Created on October 9, 2004, 2:02 PM
 */

import java.util.Vector;

import android.content.Context;

import coreAssets.CollisionException;
import coreAssets.GameOverException;
import coreAssets.GameSprite;
import coreAssets.Point;
import coreAssets.Rectangle;
import coreAssets.Size;
import coreAssets.StationarySprite;

public class BrickPile extends StationarySprite {

	private Brick[][] pile;

	// number of rows
	private static final int numRows = 2;

	// bricks per row
	private static final int numberOfBricksPerRow = 6;

	// bricks in the pile
	private int numberOfBricks = numRows * numberOfBricksPerRow;

	// aggregated sprites that havent been hit
	private int numLeft;

	public BrickPile(Context context, Rectangle r) {
		super(r);
		Point p = r.getLocation();
		Size s = r.getSize();
		pile = new Brick[numRows][numberOfBricksPerRow];
		int initialX = p.getRealX();
		int initialY = p.getRealY();
		int x = initialX;
		int y = initialY;
		numLeft = numberOfBricks;
		for (int i = 0; i < numRows; i++) {
			x = initialX;
			for (int j = 0; j < numberOfBricksPerRow; j++) {
				pile[i][j] = new Brick(context, new Rectangle(new Point(x, y), new Size(
						s.getWidth() / numberOfBricksPerRow, s.getHeight()
								/ numRows)));
				x += s.getWidth() / numberOfBricksPerRow;
			}
			y += s.getHeight() / numRows;
		}

		name = "BrickPile";
	}

	// Checks the aggregated sprites for an overlap with the provided sprite
	public Rectangle getBoundingBox(GameSprite s) {
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numberOfBricksPerRow; j++) {
				if (s.getR().intersects(pile[i][j].getR())) {
					return pile[i][j].getR();
				}
			}
		}
		return new Rectangle();
	}

	public void collideWith(GameSprite gs) throws GameOverException,
			CollisionException {
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numberOfBricksPerRow; j++) {
				try {
					if (pile[i][j].getR().intersects(gs.getR()))
						pile[i][j].collideWith(gs);
				} catch (CollisionException e) {
					numLeft = numLeft - 1;
					if (numLeft == 0) {
						throw new GameOverException(true, "No more bricks");
					} else {
						throw e;
					}
				}
			}
		}
	}

	public void buildSpriteDesc(Vector sdv) {
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numberOfBricksPerRow; j++) {
				pile[i][j].buildSpriteDesc(sdv);
			}
		}
	}

	public String getSaveData() {
		Point p = r.getLocation();
		Size s = r.getSize();
		String result = numLeft + "," + p.getFixedX() + "," + p.getFixedY()
				+ "," + s.getWidth() + "," + s.getHeight();
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numberOfBricksPerRow; j++) {
				result += ";" + pile[i][j].getSaveData();
			}
		}
		return result;
	}

	public void setSaveData(String data) {
		String datum = data.substring(0, data.indexOf(","));
		data = data.substring(data.indexOf(",") + 1);
		numLeft = Integer.parseInt(datum);
		datum = data.substring(0, data.indexOf(","));
		data = data.substring(data.indexOf(",") + 1);
		int x = Integer.parseInt(datum);
		datum = data.substring(0, data.indexOf(","));
		data = data.substring(data.indexOf(",") + 1);
		int y = Integer.parseInt(datum);
		datum = data.substring(0, data.indexOf(","));
		data = data.substring(data.indexOf(",") + 1);
		int width = Integer.parseInt(datum);
		datum = data.substring(0, data.indexOf(";"));
		data = data.substring(data.indexOf(";") + 1);
		int height = Integer.parseInt(datum);

		r.getLocation().setFixedX(x);
		r.getLocation().setFixedY(y);
		r.getSize().setWidth(width);
		r.getSize().setHeight(height);
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numberOfBricksPerRow; j++) {
				pile[i][j].setSaveData(data);
				if (j < numberOfBricksPerRow - 1 || i < numRows - 1)
					data = data.substring(data.indexOf(";") + 1);
			}
		}
	}
}
