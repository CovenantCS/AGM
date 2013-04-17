package coreAssets;

import edu.covenant.kepler.coreassets.R;
import android.app.Activity;
import android.os.Bundle;

public class BasicScoreboardActivity extends Activity
{
    protected DigitalScoreBoard scoreboard;
    
    protected void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.basic_scoreboard );
        
        this.scoreboard = ( DigitalScoreBoard ) findViewById( R.id.scoreboard );
        
        this.scoreboard.invalidate();
        
    }
}
