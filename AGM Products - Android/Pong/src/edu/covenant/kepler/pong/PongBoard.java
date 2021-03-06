package edu.covenant.kepler.pong;

import android.graphics.Color;
import coreAssets.CollisionException;
import coreAssets.ContinuousActionBoard;
import coreAssets.EndWall;
import coreAssets.GameOverException;
import coreAssets.Paddle;
import coreAssets.Point;
import coreAssets.Puck;
import coreAssets.PuckSupply;
import coreAssets.Rectangle;
import coreAssets.SideWall;
import coreAssets.SimpleScore;
import coreAssets.Size;
import coreAssets.TextSprite;

public class PongBoard extends ContinuousActionBoard
{
    private Puck puck;
    private Paddle topPaddle;
    private Paddle bottomPaddle;
    private PuckSupply pucksupply;
    private boolean topHitLast;
    private int puckSize = 7;
    private int paddleColor;
    private int lineColor;

    public PongBoard( int width, int height, PuckSupply pucksupply, SimpleScore score )
    {
        super( width, height );
        this.name = "pong";
        this.pucksupply = pucksupply;
        this.score = score;
        topHitLast = false;
        init( pucksupply );
    }
    
    public PongBoard( PuckSupply pucksupply, SimpleScore score )
    {
        super();
        init( pucksupply );
    }
    
    public PongBoard( PuckSupply pucksupply, int paddleColor, int lineColor )
    {
        super();
        this.paddleColor = paddleColor;
        this.lineColor = lineColor;
        init( pucksupply );
    }
    
    private void init( PuckSupply pucksupply )
    {
        this.name = "pong";
        this.pucksupply = pucksupply;
        this.score = new SimpleScore();
        topHitLast = false;
        userInterupt = false;
    }

    public void buildGameBoard()
    {
        EndWall ceiling;
        EndWall floor;
        SideWall leftwall;
        SideWall rightwall;
        DividingLine dl;

        int paddleWidth = getWidth() / 8;
        int paddleHeight = getHeight() / 30;
        this.puckSize = getWidth() / 24;
        
        topPaddle = new Paddle( new Rectangle( new Point( ( getWidth() / 2 )
                - ( paddleWidth / 2 ) + 15, ( getHeight() / 10 ) ), new Size(
                paddleWidth, paddleHeight ) ), this.paddleColor );
        topPaddle.startMoving();
        addMovablePiece( topPaddle );

        bottomPaddle = new Paddle( new Rectangle(
                new Point( ( getWidth() / 2 ) - ( paddleWidth / 2 ) + 15,
                        getHeight() - ( getHeight() / 10 ) ), new Size(
                        paddleWidth, paddleHeight ) ), this.paddleColor );
        bottomPaddle.startMoving();
        addMovablePiece( bottomPaddle );

        dl = new DividingLine( new Rectangle( new Point( getWidth(),
                getHeight() ), new Size( getWidth(), getHeight() ) ), this.lineColor );
        addStationaryPiece( dl );

        try
        {
            puck = pucksupply.getPuck( new Point( ( getWidth() / 2 ),
                    ( 3 * getHeight() / 4 ) ), this.puckSize );
            addMovablePiece( puck );
        }
        catch ( Exception e )
        {
            // this is the first puck. It should always succeed
        }

        ceiling = new EndWall( new Rectangle( new Point( -5, -5 ), new Size(
                getWidth() + 10, 5 ) ), true );
        addStationaryPiece( ceiling );

        floor = new EndWall( new Rectangle( new Point( -5, getHeight() ),
                new Size( getWidth() + 10, 5 ) ), true );
        addStationaryPiece( floor );

        leftwall = new SideWall( new Rectangle( new Point( -5, -5 ), new Size(
                5, getHeight() + 10 ) ), false );
        addStationaryPiece( leftwall );

        rightwall = new SideWall( new Rectangle( new Point( getWidth(), -5 ),
                new Size( 5, getHeight() + 10 ) ), false );
        addStationaryPiece( rightwall );

		addText(new TextSprite( score.toString(), Color.BLACK, (getHeight() / 25 ), (float)getWidth() / 20, (float)getHeight() / 2 ));
    }

    public void ptrPressed( int x, int y )
    {
        if ( y < getHeight() / 2 )
            topPaddle.moveTo( x );
        else
            bottomPaddle.moveTo( x );
    }

    public void ptrDragged( int x, int y )
    {
        if ( y < getHeight() / 2 )
            topPaddle.moveTo( x );
        else
            bottomPaddle.moveTo( x );
    }

    public void keyLeft( boolean down )
    {
        if ( down )
        {
            topPaddle.moveLeft( true );
            bottomPaddle.moveLeft( true );
        }
        else
        {
            topPaddle.moveLeft( false );
            bottomPaddle.moveLeft( false );
        }
    }

    public void keyRight( boolean down )
    {
        if ( down )
        {
            topPaddle.moveRight( true );
            bottomPaddle.moveRight( true );
        }
        else
        {
            topPaddle.moveRight( false );
            bottomPaddle.moveRight( false );
        }
    }

    public void keyUp( boolean down )
    {
        userInterupt = true;
    }

    public String getSaveData()
    {
    	try {
    		return score + ":" + topHitLast + ":" + puck.getSaveData() + ":"
                + topPaddle.getSaveData() + ":" + bottomPaddle.getSaveData()
                + ":" + pucksupply.getSaveData() + ":";
    	}
    	catch (NullPointerException e) {
    		return "";
    	}
    }

    public void setSaveData( String data )
    {
        score = new SimpleScore( Integer.parseInt( data.substring( 0,
                data.indexOf( ":" ) ) ) );
		textComponents.elementAt(0).setValue(score.toString());
        data = data.substring( data.indexOf( ":" ) + 1 );
        if ( data.startsWith( "true" ) )
        {
            topHitLast = true;
        }
        else
        {
            topHitLast = false;
        }
        data = data.substring( data.indexOf( ":" ) + 1 );
        puck.setSaveData( data );
        data = data.substring( data.indexOf( ":" ) + 1 );
        topPaddle.setSaveData( data );
        data = data.substring( data.indexOf( ":" ) + 1 );
        bottomPaddle.setSaveData( data );
        data = data.substring( data.indexOf( ":" ) + 1 );
        pucksupply.setSaveData( data );
    }

    protected void handleSpriteDeletedException() throws GameOverException
    {
        try
        {
            movableComponents.removeElement( puck );
            puck = pucksupply.getPuck( new Point( ( getWidth() / 2 ),
                    ( getHeight() / 2 ) ), puckSize );
            movableComponents.addElement( puck );
        }
        catch ( GameOverException oope )
        {
            stopMovement();
            gameOver = true;
            throw new GameOverException( false, "Out of Pucks: " + score );
        }
    }

    protected void handleCollisionException( CollisionException ce )
    {
        if ( ce.getSprite1().name.equals( "Puck" ) )
        {
            if ( ce.getSprite2().name.equals( "Paddle" ) )
            {
                score.incScore( 1 );
    			textComponents.elementAt(0).setValue(score.toString());
            }
        }
    }
}
