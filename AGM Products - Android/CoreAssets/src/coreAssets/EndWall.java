package coreAssets;

import coreAssets.GameSprite;
import coreAssets.Rectangle;
import coreAssets.SpriteDeletedException;
import coreAssets.StationarySprite;
import coreAssets.MovableSprite;


public class EndWall extends StationarySprite {
	// indicates whether this sprite absorbs sprites with which it collides
	private boolean absorb;

	// p: location of the upper left corner of the sprite
	// s: height and width of sprite
	public EndWall(Rectangle r, boolean newAbsorb) {
		super(r);
		absorb = newAbsorb;
		name = "EndWall";
	}

	// sprite with which self has collided
	public void collideWith(GameSprite gs) throws SpriteDeletedException {
		if (gs instanceof MovableSprite) {
			if (absorb) {
				((MovableSprite) gs).delete();
			} else {
				((MovableSprite) gs).reverseY();
			}
		}
	}
}
