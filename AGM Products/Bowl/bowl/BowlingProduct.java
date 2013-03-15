package bowl;

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
import coreAssets.PracticeScore;
import coreAssets.PracticeScoreBoard;
import coreAssets.Rectangle;
import coreAssets.SimpleScore;
import coreAssets.Size;

public class BowlingProduct extends J2MEProduct {

	public BowlingProduct() {
		super();
		services = new J2MEServices(this, "bowl");

		// Regular Pong
		// game = new GenericGame(this, newBoard(), boardViewer, MainMenuTypes.Basic);

		// Practice Pong
		game = new GenericGame(this, newBoard(), boardViewer,
				MainMenuTypes.Practice);
	}

	protected void startApp() throws MIDletStateChangeException {
		GenericStartMenu menu = new GenericStartMenu(platform, services);
		platform.getDisplay().setCurrent(menu.getMenu());
	}

	public Board newBoard() {
		// Regular Bowl
		// SimpleScore score = new SimpleScore();
		// GenericScoreBoard sb = new DigitalScoreBoard(new Rectangle(new Point(getWidth()-50, 3),new Size(0, 0)), score);
		// BowlingBoard board = new BowlingBoard(score,sbf);

		// Practice Bowl
		SimpleScore score = new PracticeScore();
		GenericScoreBoard sb = new PracticeScoreBoard(new DigitalScoreBoard(new Rectangle(new Point(boardViewer.getWidth()-50, 3),new Size(0, 0)), score));
		BowlingBoard board = new PracticeBowlingBoard(boardViewer.getWidth(),
				boardViewer.getHeight(), score);
		
		boardViewer.setBoard(board);
		boardViewer.setScoreBoard(sb);
		return board;
	}

}
