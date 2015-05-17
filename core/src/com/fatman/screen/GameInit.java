package com.fatman.screen;//======================================

import com.badlogic.gdx.Game;

// Company : GlitchyMatchyHochy
// Project : GlitchyMatchyHochy
// Author :  Mehdi-Antoine 
// Date :    17/05/2015.
//======================================
public class GameInit extends Game {

    public void create() {
        this.setScreen(new MainMenu(this));
    }

    public void render() {
        super.render(); // important!
    }

    public void dispose() {

    }


}
