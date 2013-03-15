package brickles;

import javax.microedition.midlet.MIDletStateChangeException;

import coreAssets.Board;
import coreAssets.DigitalScoreBoard;
import coreAssets.GenericGame;
import coreAssets.GenericScoreBoard;
import coreAssets.GenericStartMenu;
import coreAssets.J2MEProduct;
import coreAssets.J2MEServices;
import coreAssets.MainMenuTypes;
import coreAssets.Point;
import coreAssets.PracticePuckSupply;
import coreAssets.PracticeScore;
import coreAssets.PracticeScoreBoard;
import coreAssets.PuckSupply;
import coreAssets.Rectangle;
import coreAssets.SimpleScore;
import coreAssets.Size;

public class BricklesProduct extends J2MEProduct {

	public BricklesProduct() {
		super();
		services = new J2MEServices(this, "brickles");

		// Regular Brickles
		// game = new GenericGame(this, newBoard(), MainMenuTypes.Basic);

		// Practice Brickles
		game = new GenericGame(this, newBoard(), boardViewer,
				MainMenuTypes.Practice);
	}

	protected void startApp() throws MIDletStateChangeException {
		GenericStartMenu menu = new GenericStartMenu(platform, services);
		platform.getDisplay().setCurrent(menu.getMenu());
	}

	public Board newBoard() {
		// Regular Brickles
		//PuckSupply ps = new CountedPuckSupply(3);
		//SimpleScore score = new SimpleScore();
        //GenericScoreBoard sb = new DigitalScoreBoard(
        //		new Rectangle(new Point(boardViewer.getWidth() / 2, 0), new Size(0, 0)),
        //		score);
        
		// Practice Brickles
		PuckSupply ps = new PracticePuckSupply();
		SimpleScore score = new PracticeScore();
        GenericScoreBoard sb = new PracticeScoreBoard(new DigitalScoreBoard(
        		new Rectangle(new Point(boardViewer.getWidth() / 2, 0), new Size(0, 0)),
        		score));
        
		BricklesBoard board = new BricklesBoard(boardViewer.getWidth(),
				boardViewer.getHeight(), ps, score);
		
		boardViewer.setBoard(board);
		boardViewer.setScoreBoard(sb);
		return board;
	}
}
