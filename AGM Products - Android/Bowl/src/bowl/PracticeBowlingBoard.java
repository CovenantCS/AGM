package bowl;

import coreAssets.ActivationManager;
import coreAssets.GameOverException;
import coreAssets.SimpleScore;

public class PracticeBowlingBoard extends BowlingBoard {

	public PracticeBowlingBoard(int width, int height, SimpleScore score) {
		super(width, height, score);
	}

	protected void handleSpriteDeletedException() throws GameOverException {
		movableComponents.removeElement(ball);
		rack.stopPins();
		ball = null;
		ballNum++;
		if (ballNum > 2) {
			if (ActivationManager.getInstance().isPracticeMode()) {
				System.out.println("in practice mode");
				rackPins();
				ballNum = 1;
			} else {
				if (frame <= 10) {
					rackPins();
					ballNum = 1;
					frame++;
				} else {
					throw new GameOverException(true, "Game Over, score: "
							+ score);
				}
			}
		}
	}
}
