package brickles;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import coreAssets.AndroidProduct;
import coreAssets.Board;
import coreAssets.MainMenuTypes;
import coreAssets.Point;
import coreAssets.PuckSupply;
import coreAssets.Rectangle;
import coreAssets.SimpleScore;
import coreAssets.Size;

public class BricklesProduct extends AndroidProduct 
{
    protected void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
    }

    public void newGame( View view )
    {
        Intent intent = new Intent( this, BricklesBoardActivity.class );
        startActivity( intent );
    }
    
    public void resumeGame( View view )
    {
        Intent intent = new Intent( this, BricklesBoardActivity.class );
        intent.setAction( LOAD_GAME );
        startActivity( intent );
    }
    
//	public BricklesProduct() {
//		super();
//		services = new J2MEServices(this, "brickles");
//
//		// Regular Brickles
//		// game = new GenericGame(this, newBoard(), MainMenuTypes.Basic);
//
//		// Practice Brickles
//		game = new GenericGame(this, newBoard(), boardViewer,
//				MainMenuTypes.Practice);
//	}
//
//	protected void startApp() throws MIDletStateChangeException {
//		GenericStartMenu menu = new GenericStartMenu(platform, services);
//		platform.getDisplay().setCurrent(menu.getMenu());
//	}
//
//	public Board newBoard() {
//		// Regular Brickles
//		//PuckSupply ps = new CountedPuckSupply(3);
//		//SimpleScore score = new SimpleScore();
//        //GenericScoreBoard sb = new DigitalScoreBoard(
//        //		new Rectangle(new Point(boardViewer.getWidth() / 2, 0), new Size(0, 0)),
//        //		score);
//        
//		// Practice Brickles
//		PuckSupply ps = new PracticePuckSupply();
//		SimpleScore score = new PracticeScore();
//        GenericScoreBoard sb = new PracticeScoreBoard(new DigitalScoreBoard(
//        		new Rectangle(new Point(boardViewer.getWidth() / 2, 0), new Size(0, 0)),
//        		score));
//        
//		BricklesBoard board = new BricklesBoard(boardViewer.getWidth(),
//				boardViewer.getHeight(), ps, score);
//		
//		boardViewer.setBoard(board);
//		boardViewer.setScoreBoard(sb);
//		return board;
//	}
}
