package coreAssets;

import java.util.Vector;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
//import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class AndroidBoardViewer extends View implements BoardViewer,
        BoardController
{
    Board board;
    int viewHeight;
    int viewWidth;

    public AndroidBoardViewer( Context context )
    {
        super( context );
        // TODO Auto-generated constructor stub
    }

    public AndroidBoardViewer( Context context, AttributeSet attrs )
    {
        super( context, attrs );
        // TODO Auto-generated constructor stub
    }

    public AndroidBoardViewer( Context context, AttributeSet attrs, int defStyle )
    {
        super( context, attrs, defStyle );
        // TODO Auto-generated constructor stub
    }

    protected void onDraw( Canvas canvas )
    {
        super.onDraw( canvas );
        Vector sprites = board.getSpriteDesc();
        Paint paint = new Paint();
        for ( int i = 1; i <= sprites.size(); i++ )
        {
            SpriteDesc sd = ( SpriteDesc ) sprites.elementAt( i - 1 );

            paint.setColor( Color.RED );

            int top = sd.y;
            int bottom = top + sd.height - 1;
            int left = sd.x;
            int right = left + sd.width - 1;

            canvas.drawRect( left, top, right, bottom, paint );
        }
    }

    @Override
    public void setBoard( Board board )
    {
        this.board = board;

    }

    protected void onSizeChanged( int xNew, int yNew, int xOld, int yOld )
    {
        super.onSizeChanged( xNew, yNew, xOld, yOld );

        this.viewWidth = xNew;
        this.viewHeight = yNew;
    }

    public int getViewWidth()
    {
        return this.viewWidth;
    }

    public int getViewHeight()
    {
        return this.viewHeight;
    }

    public void pointerPressed( int x, int y )
    {
        board.ptrPressed( x, y );
    }

    public void pointerReleased( int x, int y )
    {
        board.ptrReleased( x, y );
    }

    public void pointerDragged( int x, int y )
    {
        board.ptrDragged( x, y );
    }

    public void keyPressed( int key )
    {
        if ( key == -3 )
        {
            board.keyLeft( true );
        }
        else if ( key == -4 )
        {
            board.keyRight( true );
        }
        else if ( key == 1 || key == -1 )
        {
            board.keyUp( true );
        }
    }

    public void keyReleased( int key )
    {
        if ( key == -3 )
        {
            board.keyLeft( false );
        }
        else if ( key == -4 )
        {
            board.keyRight( false );
        }
    }

    @Override
    public void tick() throws GameOverException, UserInteruptException
    {
        board.tick();
        // causes onDraw to be called
        post( new Runnable()
        {
            public void run()
            {
                invalidate();
            }
        } );
    }

}