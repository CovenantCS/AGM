package edu.covenant.kepler.pong;

import android.os.Bundle;
import coreAssets.AndroidBoardActivity;
import coreAssets.CountedPuckSupply;
import coreAssets.SimpleScore;

public class PongBoardActivity extends AndroidBoardActivity
{
    public PongBoardActivity()
    {
        // TODO Auto-generated constructor stub
    }

    public void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        int puckColor = getResources().getColor( R.color.puck );
        int paddleColor = getResources().getColor( R.color.paddle );
        int lineColor = getResources().getColor( R.color.line );
        this.boardViewer.setBoard( new PongBoard( new CountedPuckSupply( 3, puckColor ), paddleColor, lineColor ) );
        this.boardViewer.setActivity(this);
    }
}
