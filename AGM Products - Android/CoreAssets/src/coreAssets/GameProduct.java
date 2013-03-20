package coreAssets;

public interface GameProduct {
	Game getGame();

	Platform getPlatform();

	Services getServices();

	Board newBoard();

	void exit();
}
