package coreAssets;

import edu.covenant.kepler.coreassets.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
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
                Score score = this.boardViewer.getBoard().getScore();
                score.saveScore( activity );
                final AlertDialog.Builder builder = new AlertDialog.Builder( this.activity );
                String message = "Your score for this game is: " + score;
                String title = this.activity.getString( R.string.app_name ) + " Score";
                builder.setMessage( message );
                builder.setTitle( title );
                builder.setNegativeButton( "OK", new DialogInterface.OnClickListener()
                {

                    @Override
                    public void onClick( DialogInterface arg0, int arg1 )
                    {
                        //Drop us back to main menu when done.
                        activity.finish();
                    }
                });
                activity.runOnUiThread( new Runnable()
                {
                    public void run()
                    {
                        builder.show();
                    }
                });
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
