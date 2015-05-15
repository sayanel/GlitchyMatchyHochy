//======================================
// Company : GlitchyMatchyHochy
// Project : GlitchyMatchyHochy
// Author :  Mehdi-Antoine 
// Date :    23/04/2015.
//======================================

package com.fatman.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class GameScreen extends ScreenAdapter  {

    private TheGame m_biglarry;
    private MainMenuScreen m_game;
    private Vector3 m_touchPos;
    private Rectangle m_playBounds;
    private Texture m_texture_menu;
    private SpriteBatch m_batch_screen;


    public GameScreen (MainMenuScreen game) {

        m_texture_menu = new Texture(Gdx.files.internal("screen/title-bg.png"));
        m_playBounds = new Rectangle(0, 0, MainMenuScreen.GAME_WIDTH, MainMenuScreen.GAME_HEIGHT);
        m_touchPos = new Vector3();
        m_batch_screen = new SpriteBatch();
        m_game = game;
    }



    public void update () {
        if (Gdx.input.justTouched()) {
            m_touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);


            if (m_playBounds.contains(m_touchPos.x, m_touchPos.y)) {

                System.out.println("OK");
                //m_game.setScreen(new TheGame());

                return;
            }
        }
    }


    public void draw () {
        GL20 gl = Gdx.gl;
        gl.glClearColor(0, 1, 0, 1);
        gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        m_batch_screen.begin();
            m_batch_screen.draw(m_texture_menu, 0, 0, MainMenuScreen.GAME_WIDTH, MainMenuScreen.GAME_HEIGHT);
        m_batch_screen.end();

    }

    @Override
    public void render (float delta) {
        update();
        draw();
    }

    @Override
    public void pause () {

    }











}
