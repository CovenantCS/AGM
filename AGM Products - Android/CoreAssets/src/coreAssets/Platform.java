package coreAssets;

import javax.microedition.lcdui.Display;

public interface Platform {
	Display getDisplay();

	void setDisplay(Display display);
}
