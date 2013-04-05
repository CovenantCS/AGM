package coreAssets;

/*
 * Viewer interface for MVC architecture
 */
public interface BoardViewer {
	void setBoard(Board board);
	
	public Board getBoard();

	public void tick() throws GameOverException, UserInteruptException;
}
