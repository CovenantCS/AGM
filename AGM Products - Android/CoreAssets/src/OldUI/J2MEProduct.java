package OldUI;

import javax.microedition.lcdui.Display;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;

import coreAssets.Board;
import coreAssets.Game;
import coreAssets.GameProduct;
import coreAssets.Services;

public abstract class J2MEProduct extends MIDlet implements GameProduct {
	protected Platform platform;

	protected Services services;

	protected GenericGame game;

	protected J2MEBoardViewer boardViewer = new J2MEBoardViewer();

	public J2MEProduct() {
		super();
		platform = new J2MEPlatform(Display.getDisplay(this));
	}

	protected void pauseApp() {

	}

	public void exit() {
		try {
			destroyApp(false);
		} catch (MIDletStateChangeException e) {
		}

	}

	protected void destroyApp(boolean arg0) throws MIDletStateChangeException {
		notifyDestroyed();

	}

	public abstract Board newBoard();

	public Game getGame() {
		return game;
	}

	public Platform getPlatform() {
		return platform;
	}

	public Services getServices() {
		return services;
	}

}
