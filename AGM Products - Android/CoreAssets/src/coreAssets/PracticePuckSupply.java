package coreAssets;

import coreAssets.Puck;
import coreAssets.PuckSupply;

//TODO: Find a better way to handle this whole extending mess.
public class PracticePuckSupply extends CountedPuckSupply {
	CountedPuckSupply gameSupply;

	PuckSupply practiceSupply;

	public PracticePuckSupply() 
	{
		practiceSupply = new PuckSupply();
		gameSupply = new CountedPuckSupply();
	}

	public PracticePuckSupply(int maxNumber ) 
	{
	    super( maxNumber );
		practiceSupply = new PuckSupply();
		gameSupply = new CountedPuckSupply(maxNumber );
	}

	public Puck getPuck(Point p) throws GameOverException {
		if (ActivationManager.getInstance().isPracticeMode()) {
			return practiceSupply.getPuck(p);
		} else {
			return gameSupply.getPuck(p);
		}
	}

}
