package coreAssets;

public class SpriteDeletedException extends Exception {
	GameSprite gs;

	public SpriteDeletedException(GameSprite gs) {
		this.gs = gs;
	}

}
