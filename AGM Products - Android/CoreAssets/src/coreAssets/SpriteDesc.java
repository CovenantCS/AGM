package coreAssets;

import edu.covenant.kepler.coreassets.R;
import android.R.string;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/*
 * This struct passes information from the model to the viewer of the MVC architecture
 */
public class SpriteDesc {
	public int color;

	public int x;

	public int y;

	public int width;

	public int height;
	
	public string path;
	
	public Bitmap bitmap;

	public SpriteDesc(int color, int x, int y, int width, int height) {
		this.color = color;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public SpriteDesc(string path, Context context, int x, int y, int width, int height) {
		this.path = path;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		//This will probably have to be changed, but for the time being
		this.bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.logo);
	}

	public String toString() {
		return "color=" + color + " pos=" + x + "," + y + "size=" + width + ","
				+ height;
	}
}
