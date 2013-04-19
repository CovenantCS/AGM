package coreAssets;

import java.util.Vector;

import android.graphics.Bitmap;

public class ImageSprite extends GameSprite
{
	protected Bitmap bm;
	public ImageSprite(Rectangle r, Bitmap bm)
	{
		super(r);
		this.bm = Bitmap.createScaledBitmap(bm, r.getSize().getWidth(), r.getSize().getHeight(), false);
	}

	public ImageSprite(Rectangle r)
	{
		super(r);
	}

	public Bitmap getBm()
	{
		return bm;
	}
	
	public void buildSpriteDesc(Vector<SpriteDesc> sdv) {
		if (getColor() != -1) {
			sdv.addElement(new SpriteDesc(getBm(), getColor(), r.getLocation()
					.getRealX(), r.getLocation().getRealY(), r.getSize()
					.getWidth(), r.getSize().getHeight()));
		}
	}

	@Override
	public void collideWith(GameSprite m) throws GameOverException,
			SpriteDeletedException, CollisionException
	{
		// TODO Auto-generated method stub
		
	}
}
