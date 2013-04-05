package coreAssets;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public abstract class AndroidGameActivity extends Activity
{
    protected Board board;
    
    public AndroidGameActivity()
    {
        // TODO Auto-generated constructor stub
    }
    
    public void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        Intent intent = getIntent();
        if ( AndroidProduct.LOAD_GAME.equals( intent.getAction() ) )
        {
            board.loadGame( this );
        }
    }
    
    public void onPause()
    {
        super.onPause();
        board.saveGame( this );
    }
}
