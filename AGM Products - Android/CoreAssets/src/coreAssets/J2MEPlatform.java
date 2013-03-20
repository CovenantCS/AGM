package coreAssets;

import javax.microedition.lcdui.Display;

public class J2MEPlatform implements Platform {
	Display display;

	public J2MEPlatform(Display display) {
		this.display = display;
	}

	public Display getDisplay() {

		return display;
	}

	public void setDisplay(Display display) {
		this.display = display;
	}

}
