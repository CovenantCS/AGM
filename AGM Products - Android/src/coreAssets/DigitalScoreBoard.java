package coreAssets;

import javax.microedition.lcdui.Graphics;

public class DigitalScoreBoard extends GenericScoreBoard {
	private static final int color = 255 << 16 | 255 << 8 | 0;

	public DigitalScoreBoard(Rectangle r, SimpleScore score) {
		super(r, score);
	}

	public void paint(Graphics g) {
		g.setColor(color);
		String scoreStr = label + score;
		int offset = (g.getFont().stringWidth(scoreStr)) / 2;
		g.drawString(scoreStr, r.getLocation().getRealX() - offset, r
				.getLocation().getRealY(), Graphics.TOP | Graphics.LEFT);
	}
}
