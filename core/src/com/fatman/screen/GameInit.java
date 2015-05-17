//======================================
// Company : GlitchyMatchyHochy
// Project : GlitchyMatchyHochy
// Author :  Mehdi-Antoine 
// Date :    17/05/2015.
//======================================

package com.fatman.screen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

public class GameInit extends Game {

    public static int WINDOW_WIDTH;
    public static int WINDOW_HEIGHT;

    public static int GAME_WIDTH;
    public static int GAME_HEIGHT;

    public void create() {
        WINDOW_WIDTH = Gdx.app.getGraphics().getWidth();
        WINDOW_HEIGHT = Gdx.app.getGraphics().getHeight();

        GAME_WIDTH = 800;
        GAME_HEIGHT = 480;

        this.setScreen(new MainMenu(this));
    }

    public void render() {
        super.render(); // important!
    }

    public void dispose() {

    }
}
