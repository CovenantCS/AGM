package edu.covenant.kepler.pong;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import coreAssets.AndroidBoardActivity;
import coreAssets.AndroidBoardViewer;
import coreAssets.AndroidProduct;
import coreAssets.BoardViewer;
import coreAssets.CountedPuckSupply;
import coreAssets.GameOverException;
import coreAssets.PuckSupply;
import coreAssets.UserInteruptException;
import edu.covenant.kepler.pong.R;

public class PongBoardActivity extends AndroidBoardActivity
{
    //private AndroidBoardViewer boardViewer;

    public PongBoardActivity()
    {
        // TODO Auto-generated constructor stub
    }

    public void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        this.boardViewer.setBoard( new PongBoard( new CountedPuckSupply( 3 ) ) );
    }
    
    public void onPause()
    {
        super.onPause();
        boardViewer.getBoard().saveGame( this );
    }

}
