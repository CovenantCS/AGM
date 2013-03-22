package OldUI;

import javax.microedition.lcdui.Graphics;

import coreAssets.Rectangle;
import coreAssets.ScoreBoard;
import coreAssets.SimpleScore;

public abstract class GenericScoreBoard implements
		ScoreBoard {
	protected SimpleScore score;
	protected Rectangle r;
	protected String label = "";

	public GenericScoreBoard(Rectangle r, SimpleScore score) {
		this.r = r;
		this.score = score;
	}

	public void setScore(SimpleScore score) {
		this.score = score;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	abstract public void paint(Graphics g);
}
