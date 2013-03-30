package coreAssets;

import edu.covenant.kepler.coreassets.R;
import android.app.Activity;
import android.os.Bundle;

public abstract class AndroidBoardActivity extends Activity
{
    protected BoardViewer boardViewer;
    
    protected void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        //setContentView( R.layout.board_activity );
    }
}
