package coreAssets;

import android.app.IntentService;
import android.content.Intent;

public class AndroidServices extends IntentService implements Services
{
    private GameProduct product;
    private String storageIdentifier;
    
    
    public AndroidServices( GameProduct product, String gameName )
    {
        super( gameName );
        this.product = product;
        this.storageIdentifier = gameName;
    }
    
    public AndroidServices()
    {
        super("");
    }

    @Override
    public void play()
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void exit()
    {
        // TODO Auto-generated method stub

    }

    @Override
    public String save()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String reload()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void pause()
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void unpause()
    {
        // TODO Auto-generated method stub

    }

    @Override
    public int getSpeed()
    {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void setSpeed( int speed )
    {
        // TODO Auto-generated method stub

    }

    @Override
    public int getSoundLevel( int level )
    {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void setSoundLevel( int level )
    {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean gameOver()
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Score getCurrentScore()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String saveScore()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Score[] topScores()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Game getGame()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void newGame()
    {
        // TODO Auto-generated method stub

    }

    @Override
    protected void onHandleIntent( Intent intent )
    {
        // TODO Auto-generated method stub
        
    }

}
