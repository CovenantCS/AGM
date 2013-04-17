package coreAssets;

import coreAssets.CollisionException;
import coreAssets.GameOverException;
import coreAssets.GameSprite;
import coreAssets.Point;
import coreAssets.Rectangle;
import coreAssets.Size;
import coreAssets.SpriteDeletedException;
import coreAssets.MovableSprite;

/*
 * Puck.java
 *
 * Created on October 9, 2004, 2:19 PM
 */


public class Puck extends MovableSprite {
	private int color = 255 << 24 | 255 << 16 | 255 << 8 | 0;

	// the height and width of the puck sprite
	private static final int defaultSize = 2;

	// the puck has been deleted or not
	protected boolean isDead;

	public Puck(Point p) {
		super(new Rectangle(p, new Size(defaultSize, defaultSize)), 315);
		v.setSpeed(2);
		name = "Puck";
	}
	
	public Puck(Point p, int puckSize) {
        super(new Rectangle(p, new Size(puckSize, puckSize)), 315);
        v.setSpeed(2);
        name = "Puck";
    }

	public Puck( Point p, int puckSize, int puckColor )
    {
	    super(new Rectangle(p, new Size(puckSize, puckSize)), 315);
        v.setSpeed(2);
        name = "Puck";
        this.color = puckColor;
    }

    public void collideWith(GameSprite gs) throws GameOverException,
			SpriteDeletedException, CollisionException {
		gs.collideWith(this);
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

	public String getSaveData() {
		Point p = r.getLocation();
		Size s = r.getSize();
		return p.getFixedX() + "," + p.getFixedY() + "," + s.getWidth() + ","
				+ s.getHeight() + "," + v.getDirection() + "," + v.getSpeed();
	}
	
	public void increaseSpeed( int increase )
	{
		v.setSpeed( v.getSpeed() + increase );
	}

	public void setSaveData(String data) {
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

	public int getColor() {
		return color;
	}
}
