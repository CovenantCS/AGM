package coreAssets;

public interface Game extends Runnable {
	Board getBoard();

	BoardViewer getViewer();

	boolean gameOver();

	Score getScore();

	void setBoard(Board board);
}
