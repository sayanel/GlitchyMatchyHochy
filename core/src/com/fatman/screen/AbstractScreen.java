package com.fatman.screen;

import com.badlogic.gdx.Screen;

public abstract class AbstractScreen implements Screen {
    protected TheGame m_game;

    public AbstractScreen(final TheGame game){
        this.m_game = game;
    }

}
