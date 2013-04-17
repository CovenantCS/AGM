package edu.covenant.kepler.minesweeper;

import android.os.Bundle;
import coreAssets.AndroidBoardActivity;


public class msBoardActivity extends AndroidBoardActivity
{
    public msBoardActivity()
    {
        // TODO Auto-generated constructor stub
    }

    @Override
    public void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        this.boardViewer.setBoard( new MinesweeperBoard());
        this.boardViewer.setActivity(this);
    }
}
