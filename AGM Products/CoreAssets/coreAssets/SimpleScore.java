package coreAssets;

public class SimpleScore implements Score {
	private int score;

	public SimpleScore() {
		score = 0;
	}

	public SimpleScore(int score) {
		this.score = score;
	}

	public void incScore(int points) {
		score += points;
	}

	public int getScore() {
		return score;
	}

	public String toString() {
		return "" + score;
	}

	public int compareTo(Object o) {
		SimpleScore sc = (SimpleScore) o;
		return score - sc.score;
	}

}
