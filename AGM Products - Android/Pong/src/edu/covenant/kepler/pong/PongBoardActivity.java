package edu.covenant.kepler.pong;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import coreAssets.AndroidBoardActivity;
import coreAssets.AndroidBoardViewer;
import coreAssets.CountedPuckSupply;
import coreAssets.GameOverException;
import coreAssets.PuckSupply;
import coreAssets.UserInteruptException;
import edu.covenant.kepler.pong.R;

@SuppressLint( "NewApi" )
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
        
        final AndroidBoardViewer boardViewer = ( AndroidBoardViewer ) findViewById( R.id.test );

        ViewTreeObserver viewTreeObserver = boardViewer.getViewTreeObserver();
        if ( viewTreeObserver.isAlive() )
        {
            viewTreeObserver.addOnGlobalLayoutListener( new OnGlobalLayoutListener()
                    {
                        @SuppressWarnings("deprecation")
						@Override
                        public void onGlobalLayout()
                        {

                            if ( android.os.Build.VERSION.SDK_INT >= 16 )
                            {
                                boardViewer.getViewTreeObserver().removeOnGlobalLayoutListener( this );
                            }
                            else
                            {
                                boardViewer.getViewTreeObserver().removeGlobalOnLayoutListener( this );
                            }

                            boardViewer.requestFocus();
                            boardViewer.setBackgroundColor( Color.GREEN );
                            PuckSupply ps = new CountedPuckSupply( 3 );

                            int width = boardViewer.getWidth();
                            int height = boardViewer.getHeight();
                            boardViewer.setBoard( new PongBoard( width, height,
                                    ps ) );
                            animationThread = new Thread( new PongAnimation(
                                    boardViewer ) );
                            animationThread.start();
                        }
                    } );
        }

    }

}
