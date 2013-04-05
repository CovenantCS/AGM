package coreAssets;

import edu.covenant.kepler.coreassets.R;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.TextView;

public abstract class AndroidProduct extends Activity
{
    public static final String LOAD_GAME = "LOAD_GAME";
    
    protected void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.basic_main_menu );
    }
    
    public void newGame( View view )
    {
        TextView message = (TextView) findViewById( R.id.message );
        message.setText( "newGame " + getResources().getString( R.string.not_implemented ) );
    }

    public void resumeGame( View view )
    {
        TextView message = (TextView) findViewById( R.id.message );
        message.setText( "resumeGame " + getResources().getString( R.string.not_implemented ) );
    }

    public void topScores( View view )
    {
        TextView message = (TextView) findViewById( R.id.message );
        message.setText( "topScores " + getResources().getString( R.string.not_implemented ) );
    }

    public void exitGame( View view )
    {
        finish();
    }

}
