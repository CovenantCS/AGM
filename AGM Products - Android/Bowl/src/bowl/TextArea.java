package bowl;

import java.util.Vector;

import android.graphics.Color;

import coreAssets.GameSprite;
import coreAssets.Point;
import coreAssets.Rectangle;
import coreAssets.Size;
import coreAssets.SpriteDesc;
import coreAssets.StationarySprite;

public class TextArea extends StationarySprite {
	private static final int color = Color.BLUE;
	
	public TextArea(Rectangle r) {
		super(r);
		name = "TextArea";
	}

	public void collideWith(GameSprite m) {
	}

	public int getColor() {
		return color;
	}

}
