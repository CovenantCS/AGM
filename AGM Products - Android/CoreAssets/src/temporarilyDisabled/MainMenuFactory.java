package temporarilyDisabled;

import coreAssets.MainMenuTypes;
import coreAssets.Services;
import OldUI.BasicMainMenu;
import OldUI.GenericMainMenu;
import OldUI.Platform;
import OldUI.PracticeMainMenu;

public class MainMenuFactory {
	static public GenericMainMenu createMainMenu(MainMenuTypes type,
			Platform platform, Services service) {

		if (type == MainMenuTypes.Basic) {
			return new BasicMainMenu(platform, service);
		} else if (type == MainMenuTypes.Practice) {
			return new PracticeMainMenu(platform, service);
		} else { // make compiler happy - should never happen
			return new BasicMainMenu(platform, service);
		}
	}
}
