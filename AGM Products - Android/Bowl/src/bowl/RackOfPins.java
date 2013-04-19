package bowl;

import java.util.Vector;

import android.content.Context;
import coreAssets.CollisionException;
import coreAssets.GameSprite;
import coreAssets.Point;
import coreAssets.Rectangle;
import coreAssets.Size;
import coreAssets.SpriteDesc;
import coreAssets.StationarySprite;

public class RackOfPins extends StationarySprite {

	// array of pins
	private BowlingPin[][] rack;

	// number of rows
	private static final int numRows = 4;

	// pins per row
	private static int numberOfPinsPerRow = 4;

	// pins in the rack
	private static int numberOfPins = 10;

	// aggregated sprites that havent been hit
	private int numLeft;


	public RackOfPins(Context context, Rectangle r, int pinColor) {
		super(r);
		Point p = r.getLocation();
		Size s = r.getSize();
		int initialX = p.getRealX();
		int initialY = p.getRealY();
		int x = initialX;
		int y = initialY;
		numLeft = numberOfPins;
		rack = new BowlingPin[4][]; // rows are of uneven length

		// a triangular array of pins
		for (int row = 0; row < rack.length; row++) {
			rack[row] = new BowlingPin[rack.length - row];
		}

		for (int row = 0; row < rack.length; row++) {
			x = initialX + (4 - rack[row].length) * (s.getWidth() / 8);
			for (int col = 0; col < rack[row].length; col++) {
				rack[row][col] = new BowlingPin(context, new Rectangle(new Point(x, y),
						new Size((s.getWidth() / 7), s.getHeight() / numRows)), pinColor);
				x += s.getWidth() / 4;
			}
			y += s.getHeight() / 4;
		}
		name = "RackOfPins";
	}
	public void buildSpriteDesc(Vector<SpriteDesc> sdv) {
		for (int i = 0; i < rack.length; i++) {
			for (int j = 0; j < rack[i].length; j++) {
				rack[i][j].buildSpriteDesc(sdv);
			}
		}
	}

	// Checks the aggregated sprites for an overlap with the provided sprite
	public Rectangle getBoundingBox(GameSprite s) {
		for (int i = 0; i < rack.length; i++) {
			for (int j = 0; j < rack[i].length; j++) {
				if (s.getR().intersects(rack[i][j].getR())) {
					return rack[i][j].getR();
				}
			}
		}
		return new Rectangle();
	}

	public void collideWith(GameSprite ms) throws CollisionException {
		for (int i = 0; i < rack.length; i++) {
			for (int j = 0; j < rack[i].length; j++) {
				try {
					if (rack[i][j].getR().intersects(ms.getR()))
						rack[i][j].collideWith(ms);
				} catch (CollisionException e) {
					numLeft = numLeft - 1;
					throw e;
				}
			}
		}
	}

	public void movePins() {
		for (int i = 0; i < rack.length; i++) {
			for (int j = 0; j < rack[i].length; j++) {
				if (rack[i][j].moving()) {
					rack[i][j].move();
				}
			}
		}
	}

	public void stopPins() {
		for (int i = 0; i < rack.length; i++) {
			for (int j = 0; j < rack[i].length; j++) {
				if (rack[i][j].moving()) {
					rack[i][j].stopMoving();
				}
			}
		}
	}

	public int checkForCollision() {
		int oldNumLeft = numLeft;
		for (int i = 0; i < rack.length; i++) {
			for (int j = 0; j < rack[i].length; j++) {
				if (rack[i][j].moving()) {
					for (int k = 0; k < rack.length; k++) {
						for (int l = 0; l < rack[k].length; l++) {
							if (!rack[k][l].moving()
									&& rack[i][j].getR().intersects(
											rack[k][l].getR())) {
								try {
									rack[k][l].collideWith(rack[i][j]);
								} catch (Exception ex) {
									numLeft--;
								}
							}
						}
					}
				}
			}
		}
		return oldNumLeft - numLeft;
	}

	public String getSaveData() {
		Point p = r.getLocation();
		Size s = r.getSize();
		String result = numLeft + "," + p.getFixedX() + "," + p.getFixedY()
				+ "," + s.getWidth() + "," + s.getHeight();
		for (int i = 0; i < rack.length; i++) {
			for (int j = 0; j < rack[i].length; j++) {
				result += ";" + rack[i][j].getSaveData();
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
		for (int i = 0; i < rack.length; i++) {
			for (int j = 0; j < rack[i].length; j++) {
				rack[i][j].setSaveData(data);
				if (j < numberOfPinsPerRow - 1 || i < numRows - 1)
					data = data.substring(data.indexOf(";") + 1);
			}
		}
	}

	public int getColor() {
		return -1;
	}
}
