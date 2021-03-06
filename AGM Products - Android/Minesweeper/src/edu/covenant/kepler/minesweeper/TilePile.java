package edu.covenant.kepler.minesweeper;

import java.util.Random;
import java.util.Vector;

import coreAssets.CollisionException;
import coreAssets.GameOverException;
import coreAssets.GameSprite;
import coreAssets.Point;
import coreAssets.Rectangle;
import coreAssets.Size;
import coreAssets.SpriteDeletedException;
import coreAssets.StationarySprite;

public class TilePile extends StationarySprite {
	
	public static Tile curSelected;

	private Tile[][] pile;
	
	private MinesweeperBoard board;

	// The number of rows should be twice the number of columns
	//  so that it looks good on the phone
	

	// number of rows
	private int numRows = 10;

	private int numColumns = 5;
	
	// bricks in the pile
	private int numberOfTiles;

	// aggregated sprites that havent been hit
	private int numLeft;

	private double minePercentage;

	public TilePile(Rectangle r, MinesweeperBoard board, int tileColor) {
		super(r);
		this.board = board;
		minePercentage = 0.2;
		numberOfTiles = numRows * numColumns;
		name = "TilePile";
		

		Random rand = new Random();
		int mineCount = (int) (numberOfTiles * minePercentage);
		int[] mines = new int[mineCount];
		for(int i = 0; i < mines.length; i++) {
			boolean tryAgain = true;
			while(tryAgain) {
				tryAgain = false;
				int x = rand.nextInt(numberOfTiles);
				for(int j = 0; j < i; j++) {
					if(x == mines[j]) {
						tryAgain = true;
					}
				}
				if(!tryAgain) {
					mines[i] = x;
				}
			}
		}
		
		Point p = r.getLocation();
		Size s = r.getSize();
		pile = new Tile[numRows][numColumns];
		int initialX = p.getRealX();
		int initialY = p.getRealY();
		int x = initialX;
		int y = initialY;
		numLeft = numberOfTiles;
		for (int i = 0; i < numRows; i++) {
			x = initialX;
			for (int j = 0; j < numColumns; j++) {
				boolean isMine = false;
				int mineNum = numColumns * i + j;
				for(int k = 0; k < mines.length; k++) {
					if(mines[k] == mineNum) {
						isMine = true;
					}
				}
				pile[i][j] = new Tile(new Rectangle(new Point(x, y), new Size(
						s.getWidth() / numColumns, s.getHeight() / numRows)), isMine, pile, board, tileColor, i, j);
				x += s.getWidth() / numColumns;
			}
			y += s.getHeight() / numRows;
		}
		
		for(int i = 0; i < numRows; i++) {
			for(int j = 0; j < numColumns; j++) {
				Tile cur = pile[i][j];
				int num = 0;
				// Top left
				if(i != 0 && j != 0 && pile[i-1][j-1].isMine()) {
					num++;
				}
				// Top
				if(i != 0 && pile[i-1][j].isMine()) {
					num++;
				}
				// Top right
				if(i != 0 && j != numColumns - 1 && pile[i-1][j+1].isMine()) {
					num++;
				}
				// Left
				if(j != 0 && pile[i][j-1].isMine()) {
					num++;
				}
				// Right
				if(j != numColumns - 1 && pile[i][j+1].isMine()) {
					num++;
				}
				// Bottom left
				if(i != numRows - 1 && j != 0 && pile[i+1][j-1].isMine()) {
					num++;
				}
				// Bottom
				if(i != numRows - 1 && pile[i+1][j].isMine()) {
					num++;
				}
				// Bottom right
				if(i != numRows - 1 && j != numColumns - 1 && pile[i+1][j+1].isMine()) {
					num++;
				}
				cur.setNum(num);
			}
		}
		

	}

	public Tile[][] getPile() {
		return pile;
	}

	public void setPile(Tile[][] pile) {
		this.pile = pile;
	}
	
	// Checks the aggregated sprites for an overlap with the provided sprite
	public Rectangle getBoundingBox(GameSprite s) {
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numColumns; j++) {
				if (s.getR().intersects(pile[i][j].getR())) {
					return pile[i][j].getR();
				}
			}
		}
		return new Rectangle();
	}

	public void collideWith(GameSprite gs) throws GameOverException,
			CollisionException, SpriteDeletedException {
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numColumns; j++) {
				try {
					if (pile[i][j].getR().intersects(gs.getR()))
						pile[i][j].collideWith(gs);
				} catch (CollisionException e) {
					numLeft = numLeft - 1;
					if (numLeft == 0) {
						throw new GameOverException(true, "No more bricks");
					} else {
						throw e;
					}
				}
			}
		}
	}

	public void buildSpriteDesc(Vector sdv) {
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numColumns; j++) {
				pile[i][j].buildSpriteDesc(sdv);
			}
		}
	}

	public String getSaveData() {
		Point p = r.getLocation();
		Size s = r.getSize();
		String result = numLeft + "," + p.getFixedX() + "," + p.getFixedY()
				+ "," + s.getWidth() + "," + s.getHeight();
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numColumns; j++) {
				result += ";" + pile[i][j].getSaveData();
			}
		}
		return result;
	}

	public void setSaveData(String data) {
		String datum = data.substring(0, data.indexOf(","));
		data = data.substring(data.indexOf(",") + 1);
		numLeft = Integer.parseInt(datum);
		datum = data.substring(0, data.indexOf(","));
		data = data.substring(data.indexOf(",") + 1);
		int x = Integer.parseInt(datum);
		datum = data.substring(0, data.indexOf(","));
		data = data.substring(data.indexOf(",") + 1);
		int y = Integer.parseInt(datum);
		datum = data.substring(0, data.indexOf(","));
		data = data.substring(data.indexOf(",") + 1);
		int width = Integer.parseInt(datum);
		datum = data.substring(0, data.indexOf(";"));
		data = data.substring(data.indexOf(";") + 1);
		int height = Integer.parseInt(datum);

		r.getLocation().setFixedX(x);
		r.getLocation().setFixedY(y);
		r.getSize().setWidth(width);
		r.getSize().setHeight(height);
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numColumns; j++) {
				pile[i][j].setSaveData(data);
				if (j < numColumns - 1 || i < numRows - 1)
					data = data.substring(data.indexOf(";") + 1);
			}
		}
	}
	
	public int getNumRows() {
		return numRows;
	}

	public void setNumRows(int numRows) {
		this.numRows = numRows;
	}

	public int getNumberOfBricksPerRow() {
		return numColumns;
	}

	public void setNumberOfBricksPerRow(int numberOfBricksPerRow) {
		this.numColumns = numberOfBricksPerRow;
	}


	public int getColor() {
		return -1;
	}
}
