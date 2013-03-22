package coreAssets;

import android.app.Activity;

public abstract class GenericMainMenu extends Activity
{
    Platform platform;
    Services services;

    public GenericMainMenu(Platform platform, Services services)
    {
        this.platform = platform;
        this.services = services;
    }
    
    public GenericMainMenu()
    {
        
    }

}
