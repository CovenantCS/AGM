package coreAssets;

/*
 * This class is the root of the sprite hierarchy.  Sprites in tis MVC architecture
 * are part of model.  
 */
import java.util.Vector;

public abstract class GameSprite {
	public String name;

	protected Rectangle r;

	public GameSprite(Rectangle r) {
		this.r = r;
		name = "GameSprite";
	}

	public int getColor() {
		return -1;  // default no color, do not pass to viewer
	}

	public abstract void collideWith(GameSprite m) throws GameOverException,
			SpriteDeletedException, CollisionException;

	public Rectangle getR() {
		return r;
	}

	public void setR(Rectangle r) {
		this.r = r;
	}

	public boolean overLaps(GameSprite s) {
		return (r.intersects(s.getR()));
	}

	/*
	 * default code to build a sprite desc to send to the viewer
	 */
	public void buildSpriteDesc(Vector sdv) {
		if (getColor() != -1) {
			sdv.addElement(new SpriteDesc(getColor(), r.getLocation()
					.getRealX(), r.getLocation().getRealY(), r.getSize()
					.getWidth(), r.getSize().getHeight()));
		}
	}

}
