package coreAssets;

import edu.covenant.kepler.coreassets.R;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;

public abstract class AndroidBoardActivity extends Activity
{
    protected AndroidBoardViewer boardViewer;
    protected Thread animationThread;
    protected boolean running = true;
    
    protected void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView( R.layout.board_activity );
        
        this.boardViewer = ( AndroidBoardViewer ) findViewById( R.id.test );

        ViewTreeObserver viewTreeObserver = boardViewer.getViewTreeObserver();
        if ( viewTreeObserver.isAlive() )
        {
            viewTreeObserver.addOnGlobalLayoutListener( new OnGlobalLayoutListener()
                    {
                        @SuppressLint( "NewApi" )
                        @SuppressWarnings("deprecation")
                        @Override
                        public void onGlobalLayout()
                        {
                            //remove the listener, so this code only ever gets called once
                            //They renamed it slightly across versions
                            if ( android.os.Build.VERSION.SDK_INT >= 16 )
                            {
                                boardViewer.getViewTreeObserver().removeOnGlobalLayoutListener( this );
                            }
                            else
                            {
                                boardViewer.getViewTreeObserver().removeGlobalOnLayoutListener( this );
                            }
                            boardViewer.setupBoard();
                            boardViewer.requestFocus();
                            Intent intent = getIntent();
                            if ( AndroidProduct.LOAD_GAME.equals( intent.getAction() ) )
                            {
                                boardViewer.getBoard().loadGame( AndroidBoardActivity.this );
                            }
                            animationThread = new Thread( new AndroidAnimation( boardViewer, AndroidBoardActivity.this ) );
                            animationThread.start();
                        }
                    } );
        }
    }
    
    public void onPause()
    {
        super.onPause();
        boardViewer.getBoard().saveGame( this );
        System.err.println("in onpause");
    }
    
    public void onDestroy()
    {
        super.onDestroy();
        this.running = false;
    }
}
