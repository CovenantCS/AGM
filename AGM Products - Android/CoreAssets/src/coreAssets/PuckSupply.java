package coreAssets;

import coreAssets.GameOverException;
import coreAssets.Point;

public class PuckSupply 
{
    protected int puckColor = 255 << 24 | 255 << 16 | 255 << 8 | 0;;
    
	public PuckSupply() 
	{
	}
	
	public PuckSupply( int color )
	{
	    this.puckColor = color;
	}

	public Puck getPuck(Point p) throws GameOverException {
	    Puck puck = new Puck(p, puckColor);
        puck.startMoving();
        return puck;
	}
	
	public Puck getPuck(Point p, int puckSize) throws GameOverException {
        Puck puck = new Puck(p, puckSize, puckColor);
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
