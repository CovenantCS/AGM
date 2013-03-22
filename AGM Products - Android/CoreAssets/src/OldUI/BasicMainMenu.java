package OldUI;

import javax.microedition.lcdui.ChoiceGroup;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.StringItem;

import coreAssets.Score;
import coreAssets.Services;

public class BasicMainMenu extends GenericMainMenu {
	protected Command selectCmd;

	protected Command resumeCmd;

	protected ChoiceGroup cg;

	protected boolean resumeAppended;

	protected StringItem message;

	public BasicMainMenu(Platform platform, Services services) {
		super(platform, services);

		append(new StringItem(
				"Select a choice option and hit the Select button", null));
		cg = new ChoiceGroup("Choices:", ChoiceGroup.EXCLUSIVE);
		cg.append("Save Game", null);
		cg.append("Load Game", null);
		cg.append("Save Score", null);
		cg.append("New Game", null);
		cg.append("Top Scores", null);
		cg.append("Exit Game", null);
		append(cg);

		message = new StringItem(null, null);
		append(message);

		selectCmd = new Command("Select", Command.ITEM, 0);
		resumeCmd = new Command("Resume", Command.ITEM, 0);
		addCommand(selectCmd);
		resumeAppended = false;
		if (!services.gameOver()) {
			addCommand(resumeCmd);
			resumeAppended = true;
		}
		this.setCommandListener(this);
	}

	public void saveScore() {
		setMessage(services.saveScore());
	}

	public void saveGame() {
		setMessage(services.save());
	}

	public void commandAction(Command c, Displayable disp) {
		if (c == selectCmd) {
			String str = cg.getString(cg.getSelectedIndex());
			if (str.equals("Save Game")) {
				saveGame();
			} else if (str.equals("Load Game")) {
				services.reload();
			} else if (str.equals("New Game")) {
				services.newGame();
			} else if (str.equals("Save Score")) {
				saveScore();
			} else if (str.equals("Top Scores")) {
				viewTopScores();
			} else if (str.equals("Exit Game")) {
				services.exit();
			}
		} else if (c == resumeCmd) {
			resume();
		}
	}

	public void resume() {
		if (!services.gameOver()) {
			services.unpause();
		} else {
			setMessage("Sorry, you can't resume this game.");
		}
	}

	public void viewTopScores() {
		Score[] scores = services.topScores();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < scores.length; i++) {
			if (scores[i] != null) {
				sb.append(scores[i]);
				sb.append("\n");
			}
		}
		setMessage(sb.toString());
	}

	public void setMessage(String message) {
		this.message.setText(message);
	}
}
