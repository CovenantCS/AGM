package bowl;

import android.os.Bundle;
import coreAssets.AndroidBoardActivity;
import edu.covenant.kepler.bowl.R;

public class BowlingBoardActivity extends AndroidBoardActivity
{
    public BowlingBoardActivity()
    {
        // TODO Auto-generated constructor stub
    }

    public void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        int ballColor = getResources().getColor( R.color.ball );
        int pinColor = getResources().getColor( R.color.pin );
        this.boardViewer.setBoard( new BowlingBoard( ballColor, pinColor ) );
        this.boardViewer.setActivity(this);
    }
}
