//======================================
// Company : GlitchyMatchyHochy
// Project : GlitchyMatchyHochy
// Author :  Mehdi-Antoine 
// Date :    17/05/2015.
//======================================

package com.fatman.screen;
import com.badlogic.gdx.Game;

public class GameInit extends Game {

    public static final int GAME_WIDTH = 800;
    public static final int GAME_HEIGHT = 480;

    public void create() {
        this.setScreen(new MainMenu(this));
    }

    public void render() {
        super.render(); // important!
    }

    public void dispose() {

    }
}
