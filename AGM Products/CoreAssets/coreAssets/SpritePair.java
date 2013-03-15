package coreAssets;

public class SpritePair {
	private GameSprite first;

	private GameSprite second;

	public SpritePair(GameSprite s1, GameSprite s2) {
		first = s1;
		second = s2;
	}

	public GameSprite firstitem() {
		return first;
	}

	public GameSprite seconditem() {
		return second;
	}
}
