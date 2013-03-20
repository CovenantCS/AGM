package coreAssets;

public class PracticePuckSupply extends CountedPuckSupply {
	CountedPuckSupply gameSupply;

	PuckSupply practiceSupply;

	public PracticePuckSupply() {
		practiceSupply = new PuckSupply();
		gameSupply = new CountedPuckSupply();
	}

	public PracticePuckSupply(int maxNumber) {
		practiceSupply = new PuckSupply();
		gameSupply = new CountedPuckSupply(maxNumber);
	}

	public Puck getPuck(Point p) throws GameOverException {
		if (ActivationManager.getInstance().isPracticeMode()) {
			return practiceSupply.getPuck(p);
		} else {
			return gameSupply.getPuck(p);
		}
	}

}
