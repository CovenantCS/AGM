package edu.covenant.kepler.match3;

import android.os.Bundle;
import coreAssets.AndroidBoardActivity;


public class m3BoardActivity extends AndroidBoardActivity
{
    public m3BoardActivity()
    {
        // TODO Auto-generated constructor stub
    }

    public void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        this.boardViewer.setBoard( new Match3Board());
        this.boardViewer.setActivity(this);
    }
}
