package coreAssets;

/*
 * The ActivationManager keeps track of which features are enabled/disabled
 * as the result of feature interactions.
 * It is a singleton that currently supports practiceMode
 * See Lee & Kang Feature Dependency Analysis for Product Line Component Design
 * ICSR 2004 P. 69-85
 */
public class ActivationManager {
	private static final ActivationManager actMan = new ActivationManager();

	boolean practiceMode = false;

	private ActivationManager() {
	}

	public static ActivationManager getInstance() {
		return actMan;
	}

	public boolean isPracticeMode() {
		return practiceMode;
	}

	public void setPracticeMode(boolean setting) {
		practiceMode = setting;
	}

}
