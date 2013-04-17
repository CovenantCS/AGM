package coreAssets;

public class TextSprite {
	protected String label;
	protected int color;
	protected int size;
	protected float x;
	protected float y;
	
	public TextSprite(String value, int color, int size, float x, float y) {
		super();
		this.label = value;
		this.color = color;
		this.size = size;
		this.x = x;
		this.y = y;
	}

	public String getValue() {
		return label;
	}

	public void setValue(String value) {
		this.label = value;
	}

	public int getColor() {
		return color;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getSize() {
		return size;
	}

	public void setColor(int color) {
		this.color = color;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}
	
	
}
