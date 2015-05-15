//======================================

// Company : GlitchyMatchyHochy
// Project : GlitchyMatchyHochy
// Author :  Mehdi-Antoine 
// Date :    23/04/2015.
//======================================

package com.fatman.screen;

import com.badlogic.gdx.Game;

public class MainMenuScreen extends Game {

    ///////////////////////////CONSTANTS
    public static final int GAME_WIDTH = 800;
    public static final int GAME_HEIGHT = 480;




    @Override
    public void create () {
        setScreen(new GameScreen(this));
    }

    @Override
    public void render() {
        super.render();
    }


}

/*
 public void update () {
        if (Gdx.input.justTouched()) {


            if (playBounds.contains(touchPoint.x, touchPoint.y)) {
                Assets.playSound(Assets.clickSound);
                m_biglarry.setScreen(new GameScreen(m_biglarry));
                return;
            }

        }
    }
*/