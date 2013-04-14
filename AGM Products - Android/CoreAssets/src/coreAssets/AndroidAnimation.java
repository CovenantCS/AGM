package coreAssets;

import android.app.Activity;
import android.content.Intent;

public class AndroidAnimation implements Runnable
{
    BoardViewer boardViewer;
    Activity activity;

    public AndroidAnimation( BoardViewer boardViewer )
    {
        this.boardViewer = boardViewer;
        this.activity = boardViewer.getActivity();
    }

    // Ported over from GenericGame in J2ME version
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
				activity.finish();
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
