package brickles;

import android.os.Bundle;
import coreAssets.AndroidBoardActivity;
import coreAssets.CountedPuckSupply;
import coreAssets.SimpleScore;

public class BricklesBoardActivity extends AndroidBoardActivity
{
    public BricklesBoardActivity()
    {
        // TODO Auto-generated constructor stub
    }

    public void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        SimpleScore score = new SimpleScore();
        this.boardViewer.setBoard( new BricklesBoard( new CountedPuckSupply( 3 ), score ) );
        this.boardViewer.setActivity(this);
    }
}
