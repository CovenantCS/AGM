package bowl;

import android.os.Bundle;
import coreAssets.AndroidBoardActivity;

public class BowlingBoardActivity extends AndroidBoardActivity
{
    public BowlingBoardActivity()
    {
        // TODO Auto-generated constructor stub
    }

    public void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        this.boardViewer.setBoard( new BowlingBoard() );
        this.boardViewer.setActivity(this);
    }
}
