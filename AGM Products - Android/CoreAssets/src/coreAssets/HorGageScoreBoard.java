package coreAssets;

import javax.microedition.lcdui.Graphics;

public class HorGageScoreBoard extends GenericScoreBoard {
	private static int color = 0 << 16 | 255 << 8 | 255;
	private static final int size = 10;
	private static final int space = 2;

	public HorGageScoreBoard(Rectangle r, SimpleScore score) {
		super(r, score);
	}

	public void paint(Graphics g) {
		g.setColor(color);
		int x = size;
		int y = 1;

		for (int i = 0; i < score.getScore(); i++) {
			g.fillArc(x, y, size, size, 0, 360);
			x += size + space;
		}
	}
}
