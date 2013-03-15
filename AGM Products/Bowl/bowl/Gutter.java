package bowl;

import coreAssets.GameSprite;
import coreAssets.Point;
import coreAssets.Rectangle;
import coreAssets.Size;
import coreAssets.StationarySprite;

public class Gutter extends StationarySprite {
	private static final int color = 0 << 16 | 0 << 8 | 0;

	private Edge left;

	private Edge right;

	public Gutter(Rectangle r) {
		super(r);
		name = "Gutter";
		left = new Edge(new Rectangle(new Point(r.getLocation().getRealX() - 2,
				r.getLocation().getRealY()), new Size(2, r.getSize()
				.getHeight())), 0);
		right = new Edge(new Rectangle(new Point(r.getLocation().getRealX()
				+ r.getSize().getWidth(), r.getLocation().getRealY()),
				new Size(2, r.getSize().getHeight())), 180);
	}

	public void collideWith(GameSprite m) {
		if (left.getR().intersects(m.getR())) {
			left.collideWith(m);
		} else if (right.getR().intersects(m.getR())) {
			right.collideWith(m);
		}
	}

	public int getColor() {
		return color;
	}
}
