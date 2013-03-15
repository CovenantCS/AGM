package coreAssets;

import java.util.Vector;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

/* 
 * this acts as the view portion of an mvc architechture 
 * board acts as the model
 * SpriteDesc communicates the parts of the model of needed by the view,
 * which is the current position of visable sprites on the board
 * J2ME requires view and controller to be combined, so this class 
 * implements both BoardViewer and BoardController
 */

public class J2MEBoardViewer extends Canvas implements BoardViewer,
		BoardController {
	private Board board;
	private Image image;
	private GenericScoreBoard sb;

	public J2MEBoardViewer() {
		image = Image.createImage(getWidth(), getHeight());
	}
	
	public void setScoreBoard(GenericScoreBoard sb){
		this.sb = sb;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	protected void paint(Graphics g) {
		Vector sprites = board.getSpriteDesc();
		Graphics g2 = image.getGraphics();
		g2.setColor(0, 0, 255);
		g2.fillRect(0, 0, getWidth(), getHeight());
		for (int i = 0; i < sprites.size(); i++) {
			SpriteDesc sd = (SpriteDesc) sprites.elementAt(i);
			g2.setColor(sd.color);
			g2.fillRect(sd.x, sd.y, sd.width, sd.height);
		}
		if(sb != null)
			sb.paint(g2);
		g.drawImage(image, 0, 0, Graphics.TOP | Graphics.LEFT);
	}

	public void pointerPressed(int x, int y) {
		board.ptrPressed(x, y);
	}

	public void pointerReleased(int x, int y) {
		board.ptrReleased(x, y);
	}

	public void pointerDragged(int x, int y) {
		board.ptrDragged(x, y);
	}

	public void keyPressed(int key) {
		if (key == -3) {
			board.keyLeft(true);
		} else if (key == -4) {
			board.keyRight(true);
		} else if (key == UP || key == -1) {
			board.keyUp(true);
		}
	}

	public void keyReleased(int key) {
		if (key == -3) {
			board.keyLeft(false);
		} else if (key == -4) {
			board.keyRight(false);
		}
	}

	public void tick() throws GameOverException, UserInteruptException {
		board.tick();
		repaint();
	}
}
