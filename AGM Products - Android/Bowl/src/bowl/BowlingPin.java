package bowl;

/*
 * Pin.java
 *
 * Created on October 9, 2004, 1:52 PM
 */

import java.util.Vector;

import coreAssets.CollisionException;
import coreAssets.GameSprite;
import coreAssets.MovableSprite;
import coreAssets.Point;
import coreAssets.Rectangle;
import coreAssets.Size;
import coreAssets.SpriteDesc;

public class BowlingPin extends MovableSprite {
	private int color = 255 << 24 | 255 << 16 | 255 << 8 | 255;

	// the Pin has been hit
	private boolean isBroken;

	public BowlingPin(Rectangle r) {
		super(r, 0);
		v.setSpeed(0);
		stopMoving();
		isBroken = false;
		name = "Pin";
	}
	
	public BowlingPin( Rectangle r, int color )
	{
	    super(r, 0);
        v.setSpeed(0);
        stopMoving();
        isBroken = false;
        name = "Pin";
        this.color = color;
	}

	public void buildSpriteDesc(Vector sdv) {

		if (!isBroken || moving()) {

			sdv.addElement(new SpriteDesc(getColor(), r.getLocation()
					.getRealX() + 1, r.getLocation().getRealY() + 1, r
					.getSize().getWidth() - 1, r.getSize().getHeight() - 1));
		}
	}

	public void collideWith(GameSprite sprite) throws CollisionException {
		if (!isBroken) {
			isBroken = true;
			v.setSpeed(((BowlingBall) sprite).getVelocity().getSpeed());
			v.setDirection(((BowlingBall) sprite).getVelocity().getDirection());
			startMoving();
			((BowlingBall) sprite).reverseX();
			throw new CollisionException(sprite, this);
		}
	}

	public String getSaveData() {
		Point p = r.getLocation();
		Size s = r.getSize();
		return p.getFixedX() + "," + p.getFixedY() + "," + s.getWidth() + ","
				+ s.getHeight() + "," + (isBroken ? 1 : 0);
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
		int index = data.indexOf(";");
		if (index != -1) {
			datum = data.substring(0, index);
			data = data.substring(index + 1);
		} else if (data.indexOf(":") != -1) {
			index = data.indexOf(":");
			datum = data.substring(0, index);
			data = data.substring(index + 1);			
		} else {
			datum = data;
		}
		isBroken = (Integer.parseInt(datum) == 1 ? true : false);
		r.getLocation().setFixedX(x);
		r.getLocation().setFixedY(y);
		r.getSize().setWidth(width);
		r.getSize().setHeight(height);
	}

	public int getColor() {
		return color;
	}
}
