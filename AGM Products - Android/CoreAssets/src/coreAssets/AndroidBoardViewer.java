package coreAssets;

import java.util.Vector;

import edu.covenant.kepler.coreassets.R;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Picture;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
//import android.graphics.Color;

public class AndroidBoardViewer extends View implements BoardViewer,
        BoardController
{
    protected Board board;
    protected int viewHeight;
    protected int viewWidth;
    protected Bitmap bm;
    protected Paint paint;
    protected Activity activity;

    public AndroidBoardViewer( Context context )
    {
        super( context );
        init();
    }

    public AndroidBoardViewer( Context context, AttributeSet attrs )
    {
        super( context, attrs );
        init();
    }

    public AndroidBoardViewer( Context context, AttributeSet attrs, int defStyle )
    {
        super( context, attrs, defStyle );
        init();
    }
    
    private void init()
    {
        bm = BitmapFactory.decodeResource(getResources(), R.drawable.logo);
        paint = new Paint();
    }

    protected void onDraw( Canvas canvas )
    {
        super.onDraw( canvas );
        canvas.drawBitmap(bm, viewWidth/2 - bm.getWidth()/2, viewHeight/2 - bm.getHeight()/2, null);
        
        // Draw sprites
        Vector sprites = board.getSpriteDesc();
        
        for ( int i = 1; i <= sprites.size(); i++ )
        {
            SpriteDesc sd = ( SpriteDesc ) sprites.elementAt( i - 1 );

            //color.setRed
            paint.setColor( sd.color );

            int top = sd.y;
            int bottom = top + sd.height - 1;
            int left = sd.x;
            int right = left + sd.width - 1;

            canvas.drawRect( left, top, right, bottom, paint );      	
        }
        
        // Draw text
        Vector<TextSprite> textSprites = board.getTextSprites();
        for ( int i = 0; i < textSprites.size(); i++ )
        {
            TextSprite text = (TextSprite) textSprites.elementAt( i );

			paint.setStyle( Paint.Style.FILL );
            paint.setColor( text.color );
			paint.setAntiAlias( true );
			paint.setTextSize( text.size );
            canvas.drawText( text.label, text.x, text.y, paint );      	
        }
        paint.reset();
    }
    
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) 
    {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        this.viewWidth = MeasureSpec.getSize(widthMeasureSpec);
        this.viewHeight = MeasureSpec.getSize(heightMeasureSpec);
        this.setMeasuredDimension(
                this.viewWidth, this.viewHeight);
    }
    
    public boolean onTouchEvent( MotionEvent event )
    {
        if( event.getAction() == MotionEvent.ACTION_MOVE || event.getAction() == MotionEvent.ACTION_DOWN )
        {
            this.board.ptrPressed( (int) event.getX(), (int) event.getY() );
        }
        else if ( event.getAction() == MotionEvent.ACTION_UP ) {
        	this.board.ptrReleased( (int) event.getX(), (int) event.getY() );
        }
        
        return true;
    }
    
    public boolean onKeyDown( int keyCode, KeyEvent event )
    {
        switch( keyCode )
        {
            case KeyEvent.KEYCODE_DPAD_LEFT:
            {
                this.board.keyLeft( true );
                return true;
            }
            case KeyEvent.KEYCODE_DPAD_RIGHT:
            {
                this.board.keyRight( true );
                return true;
            }
        }
        
        return super.onKeyDown( keyCode, event );
    }

    @Override
    public void setBoard( Board board )
    {
        this.board = board;

    }

    @Override
    public void setActivity( Activity activity )
    {
        this.activity = activity;

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

    @Override
    public Board getBoard()
    {
        return this.board;
    }

    @Override
    public Activity getActivity()
    {
        return this.activity;
    }

    public void setupBoard()
    {
        if( this.board == null )
        {
            throw new NullPointerException( "Game board was not created before attempting to init." );
        }
        //TODO: Throw exception if height/width are 0
        //bitmap can be created here to take advantage of knowing viewHeight/width are set
        bm = Bitmap.createScaledBitmap(bm, (int) (viewWidth/1.4), viewHeight/2, false);//this scales to the screen size
        
        this.board.setWidth( this.viewWidth );
        this.board.setHeight( this.viewHeight );
        this.board.buildGameBoard();
    }

}
