package bowl;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import coreAssets.AndroidProduct;
import coreAssets.Board;
import coreAssets.MainMenuTypes;
import coreAssets.Point;
import coreAssets.Rectangle;
import coreAssets.Size;

public class BowlingProduct extends AndroidProduct
{
    protected void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
    }

    public void newGame( View view )
    {
        Intent intent = new Intent( this, BowlingBoardActivity.class );
        startActivity( intent );
    }
    
    public void resumeGame( View view )
    {
        Intent intent = new Intent( this, BowlingBoardActivity.class );
        intent.setAction( LOAD_GAME );
        startActivity( intent );
    }

}
