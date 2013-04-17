package edu.covenant.kepler.pong;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import coreAssets.AndroidProduct;


public class PongProduct extends AndroidProduct
{
	@Override
    protected void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
    }

    @Override
    public void newGame( View view )
    {
        Intent intent = new Intent( this, PongBoardActivity.class );
        startActivity( intent );
    }
    
    @Override
    public void resumeGame( View view )
    {
        Intent intent = new Intent( this, PongBoardActivity.class );
        intent.setAction( LOAD_GAME );
        startActivity( intent );
    }
}
