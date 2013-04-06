package brickles;

import android.os.Bundle;
import coreAssets.AndroidBoardActivity;
import coreAssets.CountedPuckSupply;

public class BricklesBoardActivity extends AndroidBoardActivity
{
    public BricklesBoardActivity()
    {
        // TODO Auto-generated constructor stub
    }

    public void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        this.boardViewer.setBoard( new BricklesBoard( new CountedPuckSupply( 3 ) ) );
    }
}
