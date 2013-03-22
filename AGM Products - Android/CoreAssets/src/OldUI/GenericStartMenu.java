package OldUI;

import javax.microedition.lcdui.Choice;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.List;
import javax.microedition.lcdui.StringItem;

import coreAssets.Menu;
import coreAssets.Services;

public class GenericStartMenu implements Menu, CommandListener {
	protected Services services;

	protected StringItem message;

	protected List menu;

	private Command playGame;

	private Command exitCmd;

	public GenericStartMenu(Platform platform, Services services) {
		super();
		this.services = services;
		menu = new List("Main Menu", Choice.EXCLUSIVE);
		playGame = new Command("Play", Command.ITEM, 0);
		exitCmd = new Command("Exit", Command.ITEM, 0);
		menu.addCommand(playGame);
		menu.addCommand(exitCmd);
		menu.setCommandListener(this);
	}

	public List getMenu() {
		return menu;
	}

	public void commandAction(Command c, Displayable s) {
		if (c == exitCmd) {
			services.exit();
		} else if (c == playGame) {
			services.play();
		} else {
			System.out.println("Something is wrong with the commandListener");
		}
	}

	public void setMessage(String message) {
		this.message.setText(message);
	}
}
