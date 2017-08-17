package com.mygdx.ww2.managers;

import com.mygdx.ww2.abstracts.ScreenType;
import com.mygdx.ww2.screens.MenuScreen;

/**
 * Created by Gagiu Filip on 8/17/2017.
 */
public class ScreenManager {
    private ScreenType curentScreen;

    public ScreenManager()
    {
        curentScreen = new MenuScreen();
    }

    public void setScreen(ScreenType sc)
    {
        curentScreen = sc;
    }

    public void drawScreen()
    {
        curentScreen.finalize();
    }


    public void dispose() {

    }
}
