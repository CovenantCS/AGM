package edu.covenant.kepler.pong;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import coreAssets.AndroidProduct;
import coreAssets.Board;


public class PongProduct extends AndroidProduct
{
    protected void onCreate( Bundle savedInstanceState )
    {
        Intent intent = new Intent();
        intent.putExtra( "name", "Pong" );
        setIntent( intent );
        super.onCreate( savedInstanceState );
        //TextView message = (TextView) findViewById( R.id.message );
        //message.setText( message.getText() + " to " + this.productName  );
    }

    public void newGame( View view )
    {
        Intent intent = new Intent( this, PongBoardActivity.class );
        startActivity( intent );
    }
    
    public void resumeGame( View view )
    {
        Intent intent = new Intent( this, PongBoardActivity.class );
        intent.setAction( LOAD_GAME );
        startActivity( intent );
    }
}
