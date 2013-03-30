package edu.covenant.kepler.pong;

import coreAssets.BoardViewer;
import coreAssets.GameOverException;
import coreAssets.UserInteruptException;

public class PongAnimation implements Runnable
{
    BoardViewer boardViewer;

    public PongAnimation( BoardViewer boardViewer )
    {
        this.boardViewer = boardViewer;
    }

    @Override
    public void run()
    {
        while ( true )
        {
            try
            {
                boardViewer.tick();
            }
            catch ( UserInteruptException e )
            {

            }
            catch ( GameOverException e )
            {

            }
            try
            {
                Thread.sleep( 20 );
            }
            catch ( Exception ex )
            {
            }
        }
    }

}
