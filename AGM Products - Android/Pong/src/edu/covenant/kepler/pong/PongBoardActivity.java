package edu.covenant.kepler.pong;

import android.os.Bundle;
import coreAssets.AndroidBoardActivity;
import coreAssets.CountedPuckSupply;

public class PongBoardActivity extends AndroidBoardActivity
{
    public PongBoardActivity()
    {
        // TODO Auto-generated constructor stub
    }

    public void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        this.boardViewer.setBoard( new PongBoard( new CountedPuckSupply( 3 ) ) );
        this.boardViewer.setActivity(this);
    }
}
