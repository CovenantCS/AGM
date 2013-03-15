package coreAssets;

// IMPORTANT NOTE!!!
// The coordinates stored in this structure are in
// fixed-point format. That means that the integer
// part is stored in the 1000000 to MAX_INT range,
// while the fractional part is in < 1000000. All
// values should be divided by 1000000 before they
// are used in an application. Fixed-point allows
// for greater precision on devices without
// floating-point capability.

public class Point {
	private int x;

	private int y;

	// input parameters are not fixed-point
	public Point(int x, int y) {
		this.x = x * 1000000;
		this.y = y * 1000000;
	}

	public int getFixedX() {
		return x;
	}

	public int getFixedY() {
		return y;
	}

	public int getRealX() {
		return x / 1000000;
	}

	public int getRealY() {
		return y / 1000000;
	}

	public void setFixedX(int x) {
		this.x = x;
	}

	public void setFixedY(int y) {
		this.y = y;
	}

	public void setRealX(int x) {
		this.x = x * 1000000;
	}

	public void setRealY(int y) {
		this.y = y * 1000000;
	}
}
