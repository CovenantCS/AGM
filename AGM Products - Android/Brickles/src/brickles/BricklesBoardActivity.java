package brickles;

import android.os.Bundle;
import coreAssets.AndroidBoardActivity;
import coreAssets.CountedPuckSupply;
import edu.covenant.kepler.brickles.R;

public class BricklesBoardActivity extends AndroidBoardActivity
{
    public BricklesBoardActivity()
    {
        // TODO Auto-generated constructor stub
    }

    public void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        int paddleColor = getResources().getColor( R.color.paddle );
        int puckColor = getResources().getColor( R.color.puck );
        int brickColor = getResources().getColor( R.color.brick );
        this.boardViewer.setBoard( new BricklesBoard( new CountedPuckSupply( 3, puckColor ), paddleColor, brickColor ) );
        this.boardViewer.setActivity(this);
    }
}
