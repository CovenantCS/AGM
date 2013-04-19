package coreAssets;

/*
 * CollisionException.java
 *
 * Created on September 28, 2004, 10:25 AM
 */

public class CollisionException extends java.lang.Exception {
	public GameSprite gs1;

	public GameSprite gs2;

	public CollisionException(GameSprite gs1, GameSprite gs2) {
		this.gs1 = gs1;
		this.gs2 = gs2;
	}
	
	public CollisionException(GameSprite gs1) {
		this.gs1 = gs1;
		this.gs2 = gs1;
	}

	public GameSprite getSprite1() {
		return gs1;
	}

	public GameSprite getSprite2() {
		return gs2;
	}
}
