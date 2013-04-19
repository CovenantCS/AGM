package brickles;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import coreAssets.AndroidProduct;
import coreAssets.Board;
import coreAssets.MainMenuTypes;
import coreAssets.Point;
import coreAssets.PuckSupply;
import coreAssets.Rectangle;
import coreAssets.SimpleScore;
import coreAssets.Size;

public class BricklesProduct extends AndroidProduct 
{
	@Override
    protected void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
    }

	@Override
    public void newGame( View view )
    {
        Intent intent = new Intent( this, BricklesBoardActivity.class );
        startActivity( intent );
    }
    
	@Override
    public void resumeGame( View view )
    {
        Intent intent = new Intent( this, BricklesBoardActivity.class );
        intent.setAction( LOAD_GAME );
        startActivity( intent );
    }
}
