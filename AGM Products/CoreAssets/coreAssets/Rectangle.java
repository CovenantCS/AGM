package coreAssets;

public class Rectangle {
	private Point p;

	private Size s;

	public Rectangle(Point p, Size s) {
		this.p = p;
		this.s = s;
	}

	public Rectangle() {
		p = null;
		s = null;
	}

	public boolean intersects(Rectangle r) {
		int top, bottom, left, right;
		int rtop, rbottom, rleft, rright;
		top = p.getRealY();
		bottom = top + s.getHeight() - 1;
		left = p.getRealX();
		right = left + s.getWidth() - 1;
		rtop = r.p.getRealY();
		rbottom = rtop + r.s.getHeight() - 1;
		rleft = r.p.getRealX();
		rright = rleft + r.s.getWidth() - 1;

		int area = (bottom - top + 1) * (right - left + 1);
		int rarea = (rbottom - rtop + 1) * (rright - rleft + 1);
		if (area < rarea) {
			// slow, but it works
			for (int i = top; i <= bottom; i++) {
				for (int j = left; j <= right; j++) {
					if (i >= rtop && i <= rbottom && j >= rleft && j <= rright) {
						return true;
					}
				}
			}
		} else {
			// slow, but it works
			for (int i = rtop; i <= rbottom; i++) {
				for (int j = rleft; j <= rright; j++) {
					if (i >= top && i <= bottom && j >= left && j <= right) {
						return true;
					}
				}
			}
		}

		return false;
	}

	public Point getLocation() {
		return p;
	}

	public Size getSize() {
		return s;
	}

	public void setLocation(Point p) {
		this.p = p;
	}

	public void setSize(Size s) {
		this.s = s;
	}
}
