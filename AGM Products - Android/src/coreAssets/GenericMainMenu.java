package coreAssets;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;

public abstract class GenericMainMenu extends Form implements CommandListener,
		Menu {
	protected Platform platform;

	protected Services services;

	public GenericMainMenu(Platform platform, Services services) {
		super("Main Menu");
		this.platform = platform;
		this.services = services;
	}

	public abstract void commandAction(Command c, Displayable disp);

	public abstract void setMessage(String message);
}
