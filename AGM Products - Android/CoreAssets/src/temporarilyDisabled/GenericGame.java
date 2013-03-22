package temporarilyDisabled;

import temporarilyDisabled.GameProduct;
import temporarilyDisabled.MainMenuFactory;
import coreAssets.Board;
import coreAssets.BoardViewer;
import coreAssets.Game;
import coreAssets.GameOverException;
import coreAssets.GenericMainMenu;
import coreAssets.MainMenuTypes;
import coreAssets.Score;
import coreAssets.ScoreBoard;
import coreAssets.UserInteruptException;

public class GenericGame implements Game, Runnable {

	protected GameProduct gameProd;

	protected Board board;

	protected ScoreBoard scoreBoard;

	protected MainMenuTypes mainMenuType;

	protected BoardViewer boardViewer;

	public GenericGame(GameProduct gameProd, Board board,
			BoardViewer boardViewer, MainMenuTypes mmt) {
		super();
		this.gameProd = gameProd;
		this.board = board;
		this.boardViewer = boardViewer;
		boardViewer.setBoard(board);
		this.mainMenuType = mmt;
	}

	public void run() {
		while (true) {
			try {
				boardViewer.tick();
			} catch (UserInteruptException e) {
				GenericMainMenu mainMenu = MainMenuFactory.createMainMenu(
						mainMenuType, gameProd.getPlatform(), gameProd
								.getServices());
				gameProd.getPlatform().getDisplay().setCurrent(mainMenu);
			} catch (GameOverException e) {
				GenericMainMenu mainMenu = MainMenuFactory.createMainMenu(
						mainMenuType, gameProd.getPlatform(), gameProd
								.getServices());
				mainMenu.setMessage(e.getMessage());
				gameProd.getPlatform().getDisplay().setCurrent(mainMenu);
			}
			try {
				Thread.sleep(20);
			} catch (Exception ex) {
			}
		}
	}

	public Board getBoard() {
		return board;
	}

	public BoardViewer getViewer() {
		return boardViewer;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public boolean gameOver() {
		return board.gameOver();
	}

	public Score getScore() {
		return board.getScore();
	}
}
