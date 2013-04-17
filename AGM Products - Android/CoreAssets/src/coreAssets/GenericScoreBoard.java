package coreAssets;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import coreAssets.Rectangle;
import coreAssets.ScoreBoard;
import coreAssets.SimpleScore;
import edu.covenant.kepler.coreassets.R;

public abstract class GenericScoreBoard extends View implements ScoreBoard 
{
	protected SimpleScore score;
	protected int numScoresToKeep = 10;
	protected String label = "";

	public GenericScoreBoard( Context context )
	{
	    super( context );
	}

	public GenericScoreBoard( Context context, AttributeSet attrs )
    {
	    super( context, attrs );
    }
	
	public GenericScoreBoard( Context context, AttributeSet attrs, int defStyle )
    {
	    super( context, attrs, defStyle );
    }

    public void setScore(SimpleScore score) 
	{
		this.score = score;
	}

	public void setLabel(String label) 
	{
		this.label = label;
	}
}
