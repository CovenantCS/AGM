package OldUI;

import javax.microedition.lcdui.Graphics;

import coreAssets.ActivationManager;

public class PracticeScoreBoard extends GenericScoreBoard {
	private static int color = 255 << 16 | 255 << 8 | 0;

	GenericScoreBoard sb;

	public PracticeScoreBoard(GenericScoreBoard sb) {
		super(sb.r, sb.score);
		this.sb = sb;
	}

	public void paint(Graphics g) {
		if (ActivationManager.getInstance().isPracticeMode()) {
			g.setColor(color);
			String scoreStr = "Practice Mode";
			int offset = (g.getFont().stringWidth(scoreStr)) / 2;
			g.drawString(scoreStr, r.getLocation().getRealX() - offset, r
					.getLocation().getRealY(), Graphics.TOP | Graphics.LEFT);
		} else {
			sb.paint(g);
		}
	}
}
