package edu.covenant.kepler.pong;

import java.util.Vector;

import coreAssets.GameSprite;
import coreAssets.Rectangle;
import coreAssets.SpriteDesc;
import coreAssets.StationarySprite;

public class DividingLine extends StationarySprite {
	private static final int color = 255 << 16 | 255 << 8 | 255;

	public DividingLine(Rectangle r) {
		super(r);
	}

	public void collideWith(GameSprite m) {
	}

	public void buildSpriteDesc(Vector sdv) {
		sdv.addElement(new SpriteDesc(getColor(), 0,
				r.getLocation().getRealY() / 2, r.getSize().getWidth(), 2));
	}

	public int getColor() {
		return color;
	}

}
