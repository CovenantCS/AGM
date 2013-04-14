package coreAssets;

import android.app.Activity;

/*
 * Viewer interface for MVC architecture
 */
public interface BoardViewer {
	void setBoard(Board board);
	
	void setActivity(Activity activity);
	
	public Board getBoard();

	public Activity getActivity();

	public void tick() throws GameOverException, UserInteruptException;
}
