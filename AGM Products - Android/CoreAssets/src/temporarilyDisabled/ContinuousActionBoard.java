package temporarilyDisabled;

import coreAssets.CollisionException;
import coreAssets.GameOverException;
import coreAssets.SpriteDeletedException;
import coreAssets.UserInteruptException;
import OldUI.MovableSprite;

/*
 * This class is a main loop implementation for the board if the 
 * continuousAction feature varation is chosen.  The choice of which feature 
 * variation is appropreate for the main loop is dependent on the characteristics 
 * of the game.  It is chosen by subclassing the product specific board off of the 
 * appropreate main loop class.
 */

public abstract class ContinuousActionBoard extends GenericBoard {
	// we would like to throw a UserInteruptException when the user presses the
	// up arrow, but the J2ME interface for keyPressed won't allow it.
	// so we set a flag and throw the exception, based on the flag, when we can
	protected boolean userInterupt;

	public ContinuousActionBoard(int width, int height) {
		super(width, height);
	}

	public void handleTick() throws GameOverException, UserInteruptException {
		if (userInterupt) {
			userInterupt = false;
			stopMovement();
			throw new UserInteruptException();
		}
		for (int i = 0; i < movableComponents.size(); i++) {
			MovableSprite ms = (MovableSprite) movableComponents.elementAt(i);
			if (ms.moving()) {
				ms.move();
			}
		}

		try {
			checkForCollision();
		} catch (GameOverException goe) {
			stopMovement();
			gameOver = true;
			handleGameOverException(true);
			throw new GameOverException(true, "You won, score: " + score);
		} catch (SpriteDeletedException pde) {
			handleSpriteDeletedException();
		} catch (CollisionException ce) {
			handleCollisionException(ce);
		}
	}

	protected void handleGameOverException(boolean won) {

	}

	protected void handleSpriteDeletedException() throws GameOverException {

	}

	protected void handleCollisionException(CollisionException ce) {

	}
}
