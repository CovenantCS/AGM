package bowl;

import android.os.Bundle;
import coreAssets.AndroidBoardActivity;
import coreAssets.SimpleScore;

public class BowlingBoardActivity extends AndroidBoardActivity
{
    public BowlingBoardActivity()
    {
        // TODO Auto-generated constructor stub
    }

    public void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        SimpleScore score = new SimpleScore();
        this.boardViewer.setBoard( new BowlingBoard( score ) );
        this.boardViewer.setActivity(this);
    }
}
