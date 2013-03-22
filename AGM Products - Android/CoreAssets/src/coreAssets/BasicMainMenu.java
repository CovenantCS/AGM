package coreAssets;

import edu.covenant.kepler.coreassets.R;
import android.os.Bundle;

public class BasicMainMenu extends GenericMainMenu
{

    public BasicMainMenu( Platform platform, Services services )
    {
        super( platform, services );
    }
    
    public BasicMainMenu()
    {
        
    }

    public void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.basic_main_menu );

    }

}
