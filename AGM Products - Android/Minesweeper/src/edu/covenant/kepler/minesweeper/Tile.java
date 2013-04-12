package edu.covenant.kepler.minesweeper;

/*
 * Brick.java
 *
 * Created on October 9, 2004, 1:52 PM
 */

import java.util.Vector;

import coreAssets.CollisionException;
import coreAssets.GameOverException;
import coreAssets.GameSprite;
import coreAssets.Point;
import coreAssets.Puck;
import coreAssets.Rectangle;
import coreAssets.Size;
import coreAssets.SpriteDeletedException;
import coreAssets.SpriteDesc;
import coreAssets.StationarySprite;

public class Tile extends StationarySprite {

	// the tile is a mine
	private boolean isMine;
	private boolean isClicked;

//	private static final int color = 0 << 16 | 255 << 8 | 255;

	public Tile(Rectangle r) {
		super(r);
		isMine = false;
		isClicked = false;
		name = "Tile";
	}

	public int getColor() {
		if(isClicked) {
			return 0xDDDDDD;
		} else {
			return 0xAAAAFF;
		}
	}

	public void buildSpriteDesc(Vector sdv) {

		if (!isMine) {

			sdv.addElement(new SpriteDesc(getColor(), r.getLocation()
					.getRealX() + 1, r.getLocation().getRealY() + 1, r
					.getSize().getWidth() - 1, r.getSize().getHeight() - 1));
		}
	}

	public String getSaveData() {
		Point p = r.getLocation();
		Size s = r.getSize();
		return p.getFixedX() + "," + p.getFixedY() + "," + s.getWidth() + ","
				+ s.getHeight() + "," + (isMine ? 1 : 0);
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
		isMine = (Integer.parseInt(datum) == 1 ? true : false);
		r.getLocation().setFixedX(x);
		r.getLocation().setFixedY(y);
		r.getSize().setWidth(width);
		r.getSize().setHeight(height);
	}

	public void collideWith(GameSprite m) throws GameOverException, SpriteDeletedException, CollisionException {
		
	}
}
