package bowl;

/*
 * BowlingBall.java
 *
 * Created on October 9, 2004, 2:19 PM
 */

import coreAssets.GameSprite;
import coreAssets.MovableSprite;
import coreAssets.Point;
import coreAssets.Rectangle;
import coreAssets.Size;
import coreAssets.SpriteDeletedException;
import coreAssets.Velocity;

public class BowlingBall extends MovableSprite {
	private int color = 255 << 24 | 255 << 16 | 255 << 8 | 0;

	// the height and width of the ball sprite
	private static final int ballDim = 5;

	// the ball has been deleted or not
	protected boolean isDead;

	public BowlingBall(Point p) {
		super(new Rectangle(p, new Size(ballDim, ballDim)), 90);
		v.setSpeed(2);
		name = "Ball";
	}
	
	public BowlingBall(Point p, int color) {
        super(new Rectangle(p, new Size(ballDim, ballDim)), 90);
        v.setSpeed(2);
        name = "Ball";
        this.color = color;
    }

	// delete the ball
	public void delete() throws SpriteDeletedException {
		throw new SpriteDeletedException(this);
	}

	// action that happens every tick of the game clock
	public void move() {
		if (!isDead) {
			super.move();
		}
	}

	public void setDirection(int direction) {
		v.setDirection(direction);
	}

	public void setSpeed(int speed) {
		v.setSpeed(speed);
	}

	public String getSaveData() {
		Point p = r.getLocation();
		Size s = r.getSize();
		return p.getFixedX() + "," + p.getFixedY() + "," + s.getWidth() + ","
				+ s.getHeight() + "," + v.getDirection() + "," + v.getSpeed();
	}

	public void setSaveData(String data) {
		// Check that a ball was indeed saved
		if (!data.substring(0, 4).equals("null")) {
			String datum = data.substring(0, data.indexOf(","));
			data = data.substring(data.indexOf(",") + 1);
			int x = Integer.parseInt(datum);
			datum = data.substring(0, data.indexOf(","));
			data = data.substring(data.indexOf(",") + 1);
			int y = Integer.parseInt(datum);
			datum = data.substring(0, data.indexOf(","));
			data = data.substring(data.indexOf(",") + 1);
			int width = Integer.parseInt(datum);
			datum = data.substring(0, data.indexOf(","));
			data = data.substring(data.indexOf(",") + 1);
			int height = Integer.parseInt(datum);
			datum = data.substring(0, data.indexOf(","));
			data = data.substring(data.indexOf(",") + 1);
			v.setDirection(Integer.parseInt(datum));
			datum = data.substring(0, data.indexOf(":"));
			data = data.substring(data.indexOf(":") + 1);
			v.setSpeed(Integer.parseInt(datum));
			r.getLocation().setFixedX(x);
			r.getLocation().setFixedY(y);
			r.getSize().setWidth(width);
			r.getSize().setHeight(height);
		}
	}

	public Velocity getVelocity() {
		return v;
	}

	public String toString() {
		return "BowlingBall";
	}

	public void collideWith(GameSprite m) {

	}

	public int getColor() {
		return color;
	}

}
