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

	public void buildSpriteDesc(Vector sdv) {
		sdv.addElement(new SpriteDesc(getColor(), r.getLocation().getRealX(), r
				.getLocation().getRealY(), r.getSize().getWidth(), r.getSize()
				.getHeight()));
	}

	public int getColor() {
		return color;
	}

}
