package coreAssets;

import javax.microedition.lcdui.Graphics;

public class RomanScoreBoard extends GenericScoreBoard {
	private static int color = 0 << 16 | 255 << 8 | 255;

	public RomanScoreBoard(Rectangle r, SimpleScore score) {
		super(r, score);
	}

	public void paint(Graphics g) {
		g.setColor(color);
		String strScore = "Nihil";
		switch (score.getScore()) {
		case 0:
			strScore = "Nihil";
			break;
		case 1:
			strScore = "I";
			break;
		case 2:
			strScore = "II";
			break;
		case 3:
			strScore = "III";
			break;
		case 4:
			strScore = "IV";
			break;
		case 5:
			strScore = "V";
			break;
		case 6:
			strScore = "VI";
			break;
		case 7:
			strScore = "VII";
			break;
		case 8:
			strScore = "VIII";
			break;
		case 9:
			strScore = "IX";
			break;
		case 10:
			strScore = "X";
			break;
		case 11:
			strScore = "XI";
			break;
		case 12:
			strScore = "XII";
			break;
		default:
			strScore = "to be cont.";
			break;
		}
		int offset = (g.getFont().stringWidth(strScore)) / 2;
		g.drawString(strScore, r.getLocation().getRealX() - offset, r
				.getLocation().getRealY(), Graphics.TOP | Graphics.LEFT);
	}
}
