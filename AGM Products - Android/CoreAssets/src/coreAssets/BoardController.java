package coreAssets;

/*
 * Controller interface for MVC architecture
 */
public interface BoardController {
	void pointerPressed(int x, int y);

	void pointerDragged(int x, int y);

	void pointerReleased(int x, int y);

	void keyPressed(int key);

	void keyReleased(int key);
}
