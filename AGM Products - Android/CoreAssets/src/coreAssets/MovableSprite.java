package coreAssets;

import android.graphics.Bitmap;
import coreAssets.GameSprite;
import coreAssets.Point;
import coreAssets.Rectangle;
import coreAssets.SpriteDeletedException;
import coreAssets.Velocity;

public abstract class MovableSprite extends ImageSprite {
	protected Velocity v;

	private boolean moving;

	public MovableSprite(Rectangle r, int d, Bitmap bm) {
		super(r, bm);
		v = new Velocity(1, d);
		name = "MovableSprite";
	}

	public MovableSprite(Rectangle r, int d)
	{
		super(r);
		v = new Velocity(1, d);
		name = "MovableSprite";
	}

	public boolean moving() {
		return moving;
	}

	public void move() {
		Point p = r.getLocation();
		p.setFixedX(p.getFixedX() + v.getSpeed() * v.getDx());
		p.setFixedY(p.getFixedY() + v.getSpeed() * v.getDy());
	}

	public void reverse() {
		v.reverse();
	}

	public void reverseX() {
		v.reverseX();
	}

	public void reverseY() {
		v.reverseY();
	}

	public void setDirection(int direction) {
		v.setDirection(direction);
	}

	public void startMoving() {
		moving = true;
	}

	public void stopMoving() {
		moving = false;
	}

	public void delete() throws SpriteDeletedException {
		throw new SpriteDeletedException(this);
	}
}
