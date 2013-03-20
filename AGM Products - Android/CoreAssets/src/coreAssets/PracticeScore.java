package coreAssets;

public class PracticeScore extends SimpleScore {

	public PracticeScore() {
		super();
	}

	public PracticeScore(int score) {
		super(score);
	}

	public void incScore(int points) {
		if (!ActivationManager.getInstance().isPracticeMode()) {
			super.incScore(points);
		}
	}

	public String toString() {
		if (!ActivationManager.getInstance().isPracticeMode()) {
			return super.toString();
		} else {
			return "N/A";
		}
	}
}
