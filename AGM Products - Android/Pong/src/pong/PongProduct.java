package pong;

import javax.microedition.midlet.MIDletStateChangeException;

import coreAssets.Board;
import coreAssets.GenericGame;
import coreAssets.GenericScoreBoard;
import coreAssets.GenericStartMenu;
import coreAssets.J2MEProduct;
import coreAssets.J2MEServices;
import coreAssets.MainMenuTypes;
import coreAssets.PracticePuckSupply;
import coreAssets.PracticeScore;
import coreAssets.PuckSupply;
import coreAssets.SimpleScore;

public class PongProduct extends J2MEProduct {

	public PongProduct() {
		super();
		services = new J2MEServices(this, "pong");

		// Regular Pong
		// game = new GenericGame(this, newBoard(), MainMenuTypes.Basic);

		// Practice Pong
		game = new GenericGame(this, newBoard(), boardViewer,
				MainMenuTypes.Practice);
	}

	protected void startApp() throws MIDletStateChangeException {
		GenericStartMenu menu = new GenericStartMenu(platform, services);
		platform.getDisplay().setCurrent(menu.getMenu());
	}

	public Board newBoard() {
		// Regular Pong
		// PuckSupply ps = new CountedPuckSupply(3);
		// SimpleScore score = new SimpleScore();

		// Practice Pong
		PuckSupply ps = new PracticePuckSupply();
		SimpleScore score = new PracticeScore();
		GenericScoreBoard sb = null;

		PongBoard board = new PongBoard(boardViewer.getWidth(), boardViewer
				.getHeight(), ps, score);
		
		boardViewer.setBoard(board);
		boardViewer.setScoreBoard(sb);
		return board;
	}
}
