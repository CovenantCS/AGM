package coreAssets;

/*
 * This struct passes information from the model to the viewer of the MVC architecture
 */
public class SpriteDesc {
	public int color;

	public int x;

	public int y;

	public int width;

	public int height;

	public SpriteDesc(int color, int x, int y, int width, int height) {
		this.color = color;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public String toString() {
		return "color=" + color + " pos=" + x + "," + y + "size=" + width + ","
				+ height;
	}
}
