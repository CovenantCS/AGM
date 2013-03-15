package bowl;

import coreAssets.GameSprite;
import coreAssets.Rectangle;
import coreAssets.StationarySprite;

public class Edge extends StationarySprite {

	private int dir;

	public Edge(Rectangle r, int dir) {
		super(r);
		this.dir = dir;
		name = "Edge";
	}

	// sprite with which self has collided
	public void collideWith(GameSprite m) {
		if (m instanceof BowlingBall) {
			if (dir == 0) {
				if (((BowlingBall) m).getVelocity().getDirection() < 90
						|| ((BowlingBall) m).getVelocity().getDirection() > 270)
					((BowlingBall) m).reverseX();
			} else {
				if (((BowlingBall) m).getVelocity().getDirection() > 90
						&& ((BowlingBall) m).getVelocity().getDirection() < 270)
					((BowlingBall) m).reverseX();
			}
		}
	}
}
