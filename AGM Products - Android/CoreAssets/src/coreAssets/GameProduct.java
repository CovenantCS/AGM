package coreAssets;

import coreAssets.Board;
import coreAssets.Game;
import coreAssets.Services;
import coreAssets.Platform;

public interface GameProduct {
	Game getGame();

	Platform getPlatform();

	Services getServices();

	Board newBoard();

	void exit();
}
