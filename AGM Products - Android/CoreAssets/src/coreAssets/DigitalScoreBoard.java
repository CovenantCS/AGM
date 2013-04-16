package coreAssets;

import java.util.Iterator;
import java.util.LinkedList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import coreAssets.Rectangle;
import coreAssets.SimpleScore;

public class DigitalScoreBoard extends GenericScoreBoard 
{
	private static final int color = 255 << 24 | 255 << 16 | 255 << 8 | 0;
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
        this.scores = new SimpleScore().getScores( getContext() );
        this.textPaint.setColor( Color.GREEN );
        this.backgroundPaint.setColor( Color.BLACK );
	}
	
	public void onDraw( Canvas canvas )
	{
	    String scoreString = label + score;
	    
	    int offset = this.bounds.width() / 2;
	    float x = getWidth() / 2 - offset;
	    float y = getHeight() / 2;
	    canvas.drawRect( 0, 0, getWidth(), getHeight(), this.backgroundPaint );
	    Iterator<Integer> iterator = scores.iterator();
	    while( iterator.hasNext() )
	    {
	        Integer score = iterator.next();
	        this.textPaint.getTextBounds( score.toString(), 0, score.toString().length(), this.bounds );
	        canvas.drawText( score.toString(), x, y, this.textPaint );
	        y += this.bounds.height();
	    }
	}
	

	/*public void paint(Graphics g) {
		g.setColor(color);
		String scoreStr = label + score;
		int offset = (g.getFont().stringWidth(scoreStr)) / 2;
		g.drawString(scoreStr, r.getLocation().getRealX() - offset, r
				.getLocation().getRealY(), Graphics.TOP | Graphics.LEFT);
	}*/
}
