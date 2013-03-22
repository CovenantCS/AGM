package OldUI;

import javax.microedition.lcdui.Displayable;
import javax.microedition.rms.RecordStore;

import coreAssets.Game;
import coreAssets.GameProduct;
import coreAssets.Score;
import coreAssets.Services;
import coreAssets.SimpleScore;

public class J2MEServices implements Services {
	protected GameProduct gameProd;

	protected String storageName;

	static final int NUM_SCORES = 5;

	public J2MEServices(GameProduct gameProd, String storageName) {
		this.gameProd = gameProd;
		this.storageName = storageName;
	}

	public void play() {
		gameProd.getPlatform().getDisplay().setCurrent(
				(Displayable) gameProd.getGame().getViewer());
		try {
			Thread thread = new Thread(gameProd.getGame());
			thread.start();
		} catch (Exception e) {
			System.out.println("Cannot create thread");
		}
	}

	public String save() {
		String msg = "";
		if (gameOver()) {
			msg = "Sorry, you can't save this game.";
		} else {
			String byteString = null;
			try {
				RecordStore rs = RecordStore.openRecordStore(storageName
						+ "Game", true);
				int numRecords = rs.getNumRecords();
				byteString = "G" + gameProd.getGame().getBoard().getSaveData();
				if (numRecords == 0) {
					rs.addRecord(byteString.getBytes(), 0, byteString.length());
					msg = "Game saved!";
				} else {
					rs.setRecord(1, byteString.getBytes(), 0, byteString
							.length());
					msg = "Game saved!";
				}
				rs.closeRecordStore();
			} catch (Exception ex) {
				System.out.println(ex.toString());
				msg = "Cannot save game!";
			}
		}
		return msg;
	}

	public String reload() {
		String msg = "";
		String byteString = null;
		try {
			RecordStore rs = RecordStore.openRecordStore(storageName + "Game",
					true);
			int numRecords = rs.getNumRecords();
			if (numRecords == 0) {
				msg = "No saved games were found!";
			} else {
				byteString = new String(rs.getRecord(1));
				if (byteString.substring(0, 1).equals("G")) {
					gameProd.getGame().getBoard().setSaveData(
							byteString.substring(1));
					unpause();
				} else {
					msg = "Cannot recover saved game!";
				}
			}
			rs.closeRecordStore();
		} catch (Exception ex) {
			System.out.println(ex.toString());
			msg = "Cannot recover saved game!";
		}
		return msg;
	}

	public void pause() {
		gameProd.getGame().getBoard().stopMovement();
	}

	public void unpause() {
		gameProd.getPlatform().getDisplay().setCurrent(
				(Displayable) gameProd.getGame().getViewer());
		gameProd.getGame().getBoard().startMovement();
	}

	public int getSpeed() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void setSpeed(int speed) {
		// TODO Auto-generated method stub

	}

	public int getSoundLevel(int level) {
		// TODO Auto-generated method stub
		return 0;
	}

	public void setSoundLevel(int level) {
		// TODO Auto-generated method stub

	}

	public void exit() {
		gameProd.exit();

	}

	public boolean gameOver() {
		return gameProd.getGame().gameOver();
	}

	public Score getCurrentScore() {
		return gameProd.getGame().getScore();
	}

	private boolean insertScore(Score[] scores, Score cur) {
		for (int i = 0; i < NUM_SCORES; i++) {
			if (scores[i] == null) {
				scores[i] = cur;
				return true;
			} else if (scores[i].compareTo(cur) == 0) {
				// if already in just say it was inserted but don't duplicate
				return true;
			} else if (scores[i].compareTo(cur) < 0) {
				for (int j = NUM_SCORES - 1; j > i; j--) {
					scores[j] = scores[j - 1];
				}
				scores[i] = cur;
				return true;
			}
		}
		return false;
	}

	public String saveScore() {
		String msg = "";
		try {
			RecordStore rs = RecordStore.openRecordStore(storageName + "Score",
					true);
			Score score = getCurrentScore();
			int numRecords = rs.getNumRecords();
			if (numRecords == 0) {
				for (int i = 0; i < NUM_SCORES; i++) {
					String str = "H" + 0;
					byte[] bytes = str.getBytes();
					rs.addRecord(bytes, 0, bytes.length);
				}
			}
			Score[] scores = new Score[NUM_SCORES];
			for (int i = 0; i < NUM_SCORES; i++)
				scores[i] = new SimpleScore();

			for (int i = 0; i < NUM_SCORES; i++) {
				byte[] bytes = rs.getRecord(i + 1); // records start at 1
				if (bytes[0] == (byte) 'H') {
					insertScore(scores, new SimpleScore(Integer
							.parseInt(new String(bytes).substring(1))));
				} else {
					msg = "can not save score";
					System.out.println("corrupt record store " + storageName
							+ "Score non-H record found");
				}
			}
			if (insertScore(scores, score)) {
				for (int i = 0; i < NUM_SCORES; i++) {
					String str = "H" + scores[i];
					byte[] bytes = str.getBytes();
					rs.setRecord(i + 1, bytes, 0, bytes.length);
				}
				msg = "Score saved";
			} else {
				msg = "Sorry, you don't have a high score";
			}
			rs.closeRecordStore();
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
		return msg;
	}

	public Score[] topScores() {
		Score[] scores = new Score[NUM_SCORES];
		try {
			RecordStore rs = RecordStore.openRecordStore(storageName + "Score",
					true);
			for (int i = 0; i < NUM_SCORES; i++) {
				byte[] bytes = rs.getRecord(i + 1);

				if (bytes[0] == (byte) 'H') {
					insertScore(scores, new SimpleScore(Integer
							.parseInt(new String(bytes).substring(1))));
				} else {
					System.out.println("corrupt record store " + storageName
							+ "Score non-H record found");
				}
			}
			rs.closeRecordStore();
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
		return scores;
	}

	public Game getGame() {
		return gameProd.getGame();
	}

	public void newGame() {
		gameProd.getGame().setBoard(gameProd.newBoard());
		gameProd.getPlatform().getDisplay().setCurrent(
				(Displayable) gameProd.getGame().getViewer());

	}
}
