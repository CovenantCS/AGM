package coreAssets;

import temporarilyDisabled.Puck;
import temporarilyDisabled.PuckSupply;

public class CountedPuckSupply extends PuckSupply {

	// the allowed number of pucks in this version of the game
	protected int maxPucks;

	// the number of pucks that have been deleted so far
	protected int usedPucks;

	// initializes to default values
	public CountedPuckSupply() {
		super();
		maxPucks = 3;
		usedPucks = 0;
	}

	// maximum number of pucks
	public CountedPuckSupply(int maxNumber) {
		super();
		maxPucks = maxNumber;
		usedPucks = 0;
	}

	// access number of pucks remaining
	public int numberLeft() {
		if ((maxPucks - usedPucks) >= 0) {
			return maxPucks - usedPucks;
		} else
			return 0;
	}

	// method allows the game to request a new puck
	// pre:true
	// post: self.numberLeft()=self.numberLeft@pre-1 or OutOfPucksException has
	// been thrown
	public Puck getPuck(Point p) throws GameOverException {
		if (usedPucks < maxPucks) {
			usedPucks = usedPucks + 1;
			return super.getPuck(p);
		} else {
			throw new GameOverException(false, "Out of Pucks");
		}
	}

	public String getSaveData() {
		return maxPucks + "," + usedPucks;
	}

	public void setSaveData(String data) {
		String datum = data.substring(0, data.indexOf(","));
		data = data.substring(data.indexOf(",") + 1);
		maxPucks = Integer.parseInt(datum);
		datum = data.substring(0, data.indexOf(":"));
		usedPucks = Integer.parseInt(datum);
	}
}
