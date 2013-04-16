package brickles;

/*
 * Brick.java
 *
 * Created on October 9, 2004, 1:52 PM
 */

import java.util.Vector;

import coreAssets.CollisionException;
import coreAssets.GameSprite;
import coreAssets.Point;
import coreAssets.Puck;
import coreAssets.Rectangle;
import coreAssets.Size;
import coreAssets.SpriteDesc;
import coreAssets.StationarySprite;

public class Brick extends StationarySprite {

	// the brick has been hit
	private boolean isBroken;

	private int color = 255 << 24 | 0 << 16 | 0 << 8 | 255;

	public Brick(Rectangle r) {
		super(r);
		isBroken = false;
		name = "Brick";
	}
	
	public Brick(Rectangle r, int color) {
        super(r);
        isBroken = false;
        name = "Brick";
        this.color = color;
    }

	public int getColor() {
		return color;
	}

	public void buildSpriteDesc(Vector sdv) {

		if (!isBroken) {

			sdv.addElement(new SpriteDesc(getColor(), r.getLocation()
					.getRealX() + 1, r.getLocation().getRealY() + 1, r
					.getSize().getWidth() - 1, r.getSize().getHeight() - 1));
		}
	}

	public void collideWith(GameSprite gs) throws CollisionException {
		if (!isBroken && (gs instanceof Puck)) {
			isBroken = true;
			((Puck) gs).reverseY();
			throw new CollisionException(gs, this);
		}
	}

	public void hitByPuck(Puck puck) {
		puck.reverse();
	}

	// kills brick without a collision
	public void die() {
		isBroken = true;
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
		} else {
			datum = data;
		}
		isBroken = (Integer.parseInt(datum) == 1 ? true : false);
		r.getLocation().setFixedX(x);
		r.getLocation().setFixedY(y);
		r.getSize().setWidth(width);
		r.getSize().setHeight(height);
	}
}
