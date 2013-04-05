package coreAssets;

import coreAssets.GameOverException;
import coreAssets.Point;

public class PuckSupply {
    
    
	public PuckSupply() {
	    
	}

	public Puck getPuck(Point p) throws GameOverException {
	    Puck puck = new Puck(p);
        puck.startMoving();
        return puck;
	}
	
	public Puck getPuck(Point p, int puckSize) throws GameOverException {
        Puck puck = new Puck(p, puckSize);
        puck.startMoving();
        return puck;
    }

	public String getSaveData() {
		return "";
	}

	public void setSaveData(String data) {
		// intentially left blank
	}
}
