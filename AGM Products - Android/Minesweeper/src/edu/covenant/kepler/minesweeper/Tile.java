package edu.covenant.kepler.minesweeper;

/*
 * Brick.java
 *
 * Created on October 9, 2004, 1:52 PM
 */

import java.util.Vector;

import android.graphics.Color;

import coreAssets.CollisionException;
import coreAssets.GameOverException;
import coreAssets.GameSprite;
import coreAssets.Point;
import coreAssets.Puck;
import coreAssets.Rectangle;
import coreAssets.Size;
import coreAssets.SpriteDeletedException;
import coreAssets.SpriteDesc;
import coreAssets.StationarySprite;
import coreAssets.TextSprite;

public class Tile extends StationarySprite {
	
	public static final int BLUE = 175 << 24 | 0 << 16 | 0 << 8 | 255;
	public static final int RED  = 175 << 24 | 255 << 16 | 0 << 8 | 0;
	public static final int YELLOW = 175 << 24 | 0 << 16 | 0 << 8 | 255;
	public static final int LIGHT_BLUE = 175 << 24 | 109 << 16 | 176 << 8 | 243;
	public static final int GREEN = 175 << 24 | 80 << 16 | 250 << 8 | 92;
	
	// the tile is a mine
	private boolean isMine;
	private boolean isSelected;
	private boolean isRevealed;
	private boolean isFlagged;
	private int num = -1;
	
	private Rectangle rectangle;
	private Tile[][] pile;
	private int color;
	private MinesweeperBoard board;
	
	public Tile(Rectangle r, boolean isMine, Tile[][] pile, MinesweeperBoard board) {
		super(r);
		this.pile = pile;
		rectangle = r;
		isRevealed = false;
		name = "Tile";
		this.isMine = isMine;
		isFlagged = false;
		isSelected = false;
		color = BLUE;
		this.board = board;
	}

	public Rectangle getRectangle() {
		return rectangle;
	}

	public void setRectangle(Rectangle rectangle) {
		this.rectangle = rectangle;
	}
	
	public int getColor() {
		if(isRevealed) {
			return color;
		} else {
			return color;
		}
	}
	
	public void flag() {
		if(!isRevealed) {
			if(isFlagged) {
				isFlagged = false;
				color = GREEN;
			} else {
				isFlagged = true;
				color = YELLOW;
			}
		}
	}
	
	public void reveal() throws CollisionException {
		if(isMine) {
			//End Game
			color = RED;
			System.exit(0);
		} else if(!isRevealed) {
//			TilePile.curSelected.setSelected(false);
			TilePile.curSelected = null;
			color = LIGHT_BLUE;
			board.addText( new TextSprite( ""+num, Color.BLACK, 10, r.getLocation().getRealX()+10, r.getLocation().getRealY()+10 ) );
			throw new CollisionException(this);
		}
	}
	
	public void click() {
		if(!isSelected) {
			for(int i = 0; i < pile.length; i++) {
				for(int j = 0; j < pile[0].length; j++) {
					pile[i][j].setSelected(false);
				}
			}
			setSelected(true);
			TilePile.curSelected = this;
		} else {
			setSelected(false);
//			TilePile.curSelected.setSelected(false);
			TilePile.curSelected = null;
		}
	}
	
	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
		if(isSelected) {
			color = GREEN;
		} else if(isFlagged) {
			color = YELLOW;
		} else if(isRevealed) {
			color = LIGHT_BLUE;
		} else {
			color = BLUE;
		}
	}
	
	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public void buildSpriteDesc(Vector sdv) {

		
		SpriteDesc sd = new SpriteDesc(color,
				r.getLocation().getRealX() + 1, r.getLocation().getRealY() + 1,
				r.getSize().getWidth() - 1, r.getSize().getHeight() - 1);
		sdv.addElement(sd);
	}

	public String getSaveData() {
		Point p = r.getLocation();
		Size s = r.getSize();
		return p.getFixedX() + "," + p.getFixedY() + "," + s.getWidth() + ","
				+ s.getHeight() + "," + (isMine ? 1 : 0) + (isRevealed ? 1 : 0);
	}

	public void setSaveData(String data) {
		String datum = data.substring(0, data.indexOf(","));
		data = data.substring(data.indexOf(",") + 1);
		int x = Integer.parseInt(datum);
		datum = data.substring(0, data.indexOf(","));
		data = data.substring(data.indexOf(",") + 1);
		int y = Integer.parseInt(datum);
		datum = data.substring(0, data.indexOf(","));
		data = data.substring(data.indexOf(",") + 1);
		int width = Integer.parseInt(datum);
		datum = data.substring(0, data.indexOf(","));
		data = data.substring(data.indexOf(",") + 1);
		int height = Integer.parseInt(datum);
		int index = data.indexOf(";");
		if (index != -1) {
			datum = data.substring(0, index);
			data = data.substring(index + 1);
		} else {
			datum = data;
		}
//		isMine = (Integer.parseInt(datum) == 1 ? true : false);
		r.getLocation().setFixedX(x);
		r.getLocation().setFixedY(y);
		r.getSize().setWidth(width);
		r.getSize().setHeight(height);
	}

	public boolean isMine() {
		return isMine;
	}
	public void setMine(boolean isMine) {
		this.isMine = isMine;
	}
	public boolean isClicked() {
		return isRevealed;
	}
	public void setClicked(boolean isClicked) {
		this.isRevealed = isClicked;
	}
	public void setColor(int color) {
		this.color = color;
	}
	public void collideWith(GameSprite m) throws GameOverException, SpriteDeletedException, CollisionException {
		
	}
}
