package edu.covenant.kepler.pong;

//import javax.microedition.midlet.MIDletStateChangeException;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import coreAssets.AndroidProduct;
import coreAssets.Board;
//import coreAssets.GenericGame;
//import coreAssets.GenericScoreBoard;
//import coreAssets.GenericStartMenu;
//import coreAssets.J2MEProduct;
//import coreAssets.J2MEServices;
import coreAssets.MainMenuTypes;
import coreAssets.PracticePuckSupply;
import coreAssets.PracticeScore;
import coreAssets.PuckSupply;
import coreAssets.SimpleScore;
//import edu.covenant.kepler.testapp.TestGameScreen;


public class PongProduct extends AndroidProduct
{
    protected void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        //TextView message = (TextView) findViewById( R.id.message );
        //message.setText( message.getText() + " to " + this.productName  );
    }

    public void newGame( View view )
    {
        Intent intent = new Intent( this, PongBoardActivity.class );
        startActivity( intent );
    }
    
    public void resumeGame( View view )
    {
        Intent intent = new Intent( this, PongBoardActivity.class );
        intent.setAction( LOAD_GAME );
        startActivity( intent );
    }
}

/*public class PongProduct extends J2MEProduct {

	public PongProduct() {
		super();
		services = new J2MEServices(this, "pong");

		// Regular Pong
		// game = new GenericGame(this, newBoard(), MainMenuTypes.Basic);

		// Practice Pong
		game = new GenericGame(this, newBoard(), boardViewer,
				MainMenuTypes.Practice);
	}

	protected void startApp() throws MIDletStateChangeException {
		GenericStartMenu menu = new GenericStartMenu(platform, services);
		platform.getDisplay().setCurrent(menu.getMenu());
	}

	public Board newBoard() {
		// Regular Pong
		// PuckSupply ps = new CountedPuckSupply(3);
		// SimpleScore score = new SimpleScore();

		// Practice Pong
		PuckSupply ps = new PracticePuckSupply();
		SimpleScore score = new PracticeScore();
		GenericScoreBoard sb = null;

		PongBoard board = new PongBoard(boardViewer.getWidth(), boardViewer
				.getHeight(), ps, score);
		
		boardViewer.setBoard(board);
		boardViewer.setScoreBoard(sb);
		return board;
	}
}*/
