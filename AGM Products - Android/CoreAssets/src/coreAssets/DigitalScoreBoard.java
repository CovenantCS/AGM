package coreAssets;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.AttributeSet;
import coreAssets.Rectangle;
import coreAssets.SimpleScore;

public class DigitalScoreBoard extends GenericScoreBoard 
{
	private final int defaultTextColor = 255 << 24 | 0 << 16 | 255 << 8 | 0;
	private final int defaultBackgroundColor = 255 << 24 | 0 << 16 | 0 << 8 | 0;
	private Paint textPaint;
	private Paint backgroundPaint;
	private Rect bounds;
	private LinkedList<Integer> scores;

	public DigitalScoreBoard( Context context ) 
	{
		super( context );
		init( context );
	}
	
	public DigitalScoreBoard( Context context, AttributeSet attrs ) 
    {
        super( context, attrs );
        init( context );
    }
	
	public DigitalScoreBoard( Context context, AttributeSet attrs, int defStyle ) 
    {
        super( context, attrs, defStyle );
        init( context );
    }
	
	public void init( Context context )
	{
	  //need to be cast to activity to get the intent
        Intent intent =( (Activity) context ).getIntent();
        bounds = new Rect();
        this.textPaint = new Paint();
        this.backgroundPaint = new Paint();
        this.scores = new SimpleScore().getScores( context );
        this.textPaint.setColor( intent.getIntExtra( "text_color", this.defaultTextColor ) );
        this.textPaint.setTypeface( Typeface.SERIF );
        this.backgroundPaint.setColor( this.defaultBackgroundColor );
	}
	
	@SuppressLint( "NewApi" )
    public void onDraw( Canvas canvas )
	{   
	    canvas.drawRect( 0, 0, getWidth(), getHeight(), this.backgroundPaint );
	    //init value means nothing; just need spacer
	    String toDraw = "1.0";
	    int textSize = getHeight() / ( 2 * this.numScoresToKeep );
	    this.textPaint.setTextSize( textSize );
	    this.textPaint.getTextBounds( toDraw.toString(), 0, toDraw.toString().length(), this.bounds );
	    float x = ( getWidth() / 2 ) - ( ( ( this.bounds.width() ) ) / 2 );
	    float y = ( getHeight() / 2 ) - ( ( ( this.bounds.height() + 4 ) * this.numScoresToKeep ) / 2 );
	    for( int i = 1; i <= this.numScoresToKeep; i++ )
	    {
	        try
	        {
	            Integer scoreVal = this.scores.removeFirst();
	            toDraw = i + ". " + scoreVal.toString();
	        }
	        catch( NoSuchElementException e )
	        {
	            toDraw = i + ". ";
	        }
	        canvas.drawText( toDraw, x, y, this.textPaint );
	        this.textPaint.getTextBounds( toDraw, 0, toDraw.length(), this.bounds );
            y += this.bounds.height() + 4;
	    }
	}
}
