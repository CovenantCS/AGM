package temporarilyDisabled;

import coreAssets.Board;
import coreAssets.Game;
import coreAssets.Services;
import OldUI.Platform;

public interface GameProduct {
	Game getGame();

	Platform getPlatform();

	Services getServices();

	Board newBoard();

	void exit();
}
