package coreAssets;

public class AndroidAnimation implements Runnable
{
    BoardViewer boardViewer;

    public AndroidAnimation( BoardViewer boardViewer )
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
