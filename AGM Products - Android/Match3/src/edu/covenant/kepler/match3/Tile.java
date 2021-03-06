package edu.covenant.kepler.match3;

/*
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
	int num;
	public int getNum() {
		return num;
	}
	
	private int color = 255 << 24 | 0 << 16 | 0 << 8 | 255;

	public Tile(Rectangle r) {
		super(r);
		isMine = false;
		isClicked = false;
		name = "Tile";
	}
	public Tile(Rectangle r, int color, int num) {
        super(r);
        isMine = false;
        isClicked = false;
        name = "Tile";
        this.color = color;
    }
	public int getColor() {
		if(isClicked) {
			return color;
		} else {
			return color;
		}
	}
	public void setColor(int color){
		this.color=color;
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
		return color+","+p.getFixedX() + "," + p.getFixedY() + "," + s.getWidth() + ","
				+ s.getHeight() + "," + (isMine ? 1 : 0) + (isClicked ? 1 : 0);
	}

	public void setSaveData(String data) {
		String datum = data.substring(0, data.indexOf(","));
		data = data.substring(data.indexOf(",") + 1);
		color=Integer.parseInt(datum);
		datum = data.substring(0, data.indexOf(","));
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