package coreAssets;

import edu.covenant.kepler.coreassets.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;

public class AndroidAnimation implements Runnable
{
    BoardViewer boardViewer;
    AndroidBoardActivity activity;

    public AndroidAnimation( BoardViewer boardViewer, AndroidBoardActivity activity )
    {
        this.boardViewer = boardViewer;
        this.activity = activity;
    }

    // Ported over from GenericGame in J2ME version
    @Override
    public void run()
    {
        boolean activityRunning = this.activity.running;
        while ( true  && activityRunning )
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
                        try
                        {
                            activity.finish();
                        }
                        catch( NullPointerException npe )
                        {
                            //was still running after game was closed
                            //exits gracefully
                        }
                    }
                });
                try
                {
                    activity.runOnUiThread( new Runnable()
                    {
                        public void run()
                        {
                            builder.show();
                        }
                    } );
                }
                catch( NullPointerException npe )
                {
                    //was still running after game was closed
                    //exits gracefully
                }
            }
            try
            {
                Thread.sleep( 20 );
            }
            catch ( Exception ex )
            {
            }
            try
            {
                activityRunning = this.activity.running;
            }
            catch( NullPointerException npe )
            {
                //see above
            }
        }
    }

}
