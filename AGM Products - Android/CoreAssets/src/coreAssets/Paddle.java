package coreAssets;

import android.R.color;
import coreAssets.CollisionException;
import coreAssets.GameSprite;
import coreAssets.Point;
import coreAssets.Rectangle;
import coreAssets.Size;
import coreAssets.Velocity;
import coreAssets.MovableSprite;


public class Paddle extends MovableSprite {
	private static int color = 255 << 24 | 255 << 16 | 255 << 8 | 0;

	public Paddle(Rectangle r) {
		super(r, 0);
		v = new Velocity(0, 0);
		name = "Paddle";
	}

	// implementation of how to behave in a collision
	public void collideWith(GameSprite m) throws CollisionException {
		if (m instanceof Puck) {
			((Puck) m).reverseY();
			throw new CollisionException((MovableSprite) m, this);
		}
	}

	public void moveLeft(boolean go) {
		if (go) {
			v.setDirection(180);
			v.setSpeed(2);
		} else
			v.setSpeed(0);
	}

	public void moveRight(boolean go) {
		if (go) {
			v.setDirection(0);
			v.setSpeed(2);
		} else
			v.setSpeed(0);
	}

	public void moveTo(int x) {
		int width = r.getSize().getWidth();
		r.getLocation().setRealX(x - (width / 2));
	}

	public String getSaveData() {
		Point p = r.getLocation();
		Size s = r.getSize();
		return p.getFixedX() + "," + p.getFixedY() + "," + s.getWidth() + ","
				+ s.getHeight();
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
		datum = data.substring(0, data.indexOf(":"));
		data = data.substring(data.indexOf(":") + 1);
		int height = Integer.parseInt(datum);
		r.getLocation().setFixedX(x);
		r.getLocation().setFixedY(y);
		r.getSize().setWidth(width);
		r.getSize().setHeight(height);
	}

	public int getColor() {
		return color;
	}
}
