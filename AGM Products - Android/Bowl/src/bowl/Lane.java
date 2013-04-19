package bowl;

import java.util.Vector;

import coreAssets.GameSprite;
import coreAssets.Point;
import coreAssets.Rectangle;
import coreAssets.Size;
import coreAssets.SpriteDesc;
import coreAssets.StationarySprite;

public class Lane extends StationarySprite {
	private static final int color = 255 << 24 | 0 << 16 | 255 << 8 | 255;

	private Gutter left;

	private Gutter right;

	public Lane(Rectangle r) {
		super(r);
		name = "Lane";
		left = new Gutter(new Rectangle(new Point(r.getLocation().getRealX(), r
				.getLocation().getRealY()), new Size(
				r.getSize().getWidth() / 10, r.getSize().getHeight())));
		right = new Gutter(new Rectangle(new Point(r.getLocation().getRealX()
				+ r.getSize().getWidth() - (r.getSize().getWidth() / 10), r
				.getLocation().getRealY()), new Size(
				r.getSize().getWidth() / 10, r.getSize().getHeight())));
	}

	public void collideWith(GameSprite m) {
		if (left.getR().intersects(m.getR())) {
			left.collideWith(m);
		} else if (right.getR().intersects(m.getR())) {
			right.collideWith(m);
		}
	}

	public void buildSpriteDesc(Vector<SpriteDesc> sdv) {
		sdv.addElement(new SpriteDesc(getColor(), r.getLocation().getRealX(), r
				.getLocation().getRealY(), r.getSize().getWidth(), r.getSize()
				.getHeight()));
		left.buildSpriteDesc(sdv);
		right.buildSpriteDesc(sdv);
	}

	public int getColor() {
		return color;
	}

}
