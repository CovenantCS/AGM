package coreAssets;

import android.graphics.Bitmap;

public abstract class StationarySprite extends ImageSprite {
	
	public StationarySprite(Rectangle r, Bitmap bm) {
		super(r, bm);
		name = "StationarySprite";
	}

	public StationarySprite(Rectangle r)
	{
		super(r);
	}
}
