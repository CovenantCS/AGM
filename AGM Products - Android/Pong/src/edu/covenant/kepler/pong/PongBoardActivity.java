package edu.covenant.kepler.pong;

import android.os.Bundle;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import coreAssets.AndroidBoardActivity;
import coreAssets.AndroidBoardViewer;
import coreAssets.CountedPuckSupply;
import coreAssets.GameOverException;
//import coreAssets.GenericMainMenu;
import coreAssets.PuckSupply;
import coreAssets.UserInteruptException;
import edu.covenant.kepler.pong.R;

public class PongBoardActivity extends AndroidBoardActivity
{
    Thread animationThread;

    public PongBoardActivity()
    {
        // TODO Auto-generated constructor stub
    }

    public void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        
        setContentView( R.layout.board_activity );
        
        AndroidBoardViewer boardViewer = ( AndroidBoardViewer ) findViewById( R.id.test );
        this.boardViewer = boardViewer;
        PuckSupply ps = new CountedPuckSupply( 3 );
        this.boardViewer.setBoard( new PongBoard( 100, 100, ps ) );
        this.animationThread = new Thread( new PongAnimation( this.boardViewer ) );
        this.animationThread.start();
    }
    
    

}
