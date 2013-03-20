package coreAssets;

public class MainMenuTypes {

	private final String name;

	private MainMenuTypes(String name) {
		this.name = name;
	};

	public String toString() {
		return name;
	}

	public static final MainMenuTypes Basic = new MainMenuTypes("BasicMainMenu");

	public static final MainMenuTypes Practice = new MainMenuTypes(
			"PracticeMainMenu");
}
