package minesweeper;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import coreAssets.AndroidProduct;


public class MinesweeperProduct extends AndroidProduct {
	
	protected void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
    }

    public void newGame( View view )
    {
        Intent intent = new Intent( this, msBoardActivity.class );
        startActivity( intent );
    }
    
    public void resumeGame( View view )
    {
        Intent intent = new Intent( this, msBoardActivity.class );
        intent.setAction( LOAD_GAME );
        startActivity( intent );
    }
}

//	public MinesweeperProduct() {
//		super();
//		services = new AndroidServices(this, "minesweeper");
//
//		// Regular Brickles
//		// game = new GenericGame(this, newBoard(), MainMenuTypes.Basic);
//
//		// Practice Brickles
//		game = new GenericGame(this, newBoard(), boardViewer,
//				MainMenuTypes.Practice);
//		try {
//			startApp();
//		} catch (MIDletStateChangeException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
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
//		MinesweeperBoard board = new MinesweeperBoard(boardViewer.getWidth(),
//				boardViewer.getHeight());
//		
//		boardViewer.setBoard(board);
//		boardViewer.setScoreBoard(sb);
//		return board;
//	}
//}

