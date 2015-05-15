//======================================

// Company : GlitchyMatchyHochy
// Project : GlitchyMatchyHochy
// Author :  Mehdi-Antoine 
// Date :    23/04/2015.
//======================================

package com.fatman.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MainMenuScreen extends Game {



    public SpriteBatch batcher;


    @Override
    public void create () {
        batcher = new SpriteBatch();

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