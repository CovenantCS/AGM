package coreAssets;

/*
 * Viewer interface for MVC architecture
 */
public interface BoardViewer {
	void setBoard(Board board);

	public void tick() throws GameOverException, UserInteruptException;
}
