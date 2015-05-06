//======================================

// Company : GlitchyMatchyHochy
// Project : GlitchyMatchyHochy
// Author :  Mehdi-Antoine 
// Date :    23/04/2015.
//======================================

package com.fatman.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.Viewport;


public class MainMenuScreen extends AbstractScreen{
    Sprite m_title;
    Sprite m_play;
    Sprite m_play_hover;
    Sprite m_arrow;
    boolean hovered = false;

    OrthographicCamera m_camera;
    private int width = 800;
    private int height = 480;

    float time = 0;

    public MainMenuScreen(final TheGame game) {
        super(game);
       // Gdx.input.setInputProcessor(this);
        m_title = new Sprite(new Texture(Gdx.files.internal("data/sprites/title.png")));
        m_play = new Sprite(new Texture(Gdx.files.internal("data/sprites/play.png")));
        m_play_hover = new Sprite(new Texture(Gdx.files.internal("data/sprites/play2.png")));
        m_arrow = new Sprite(new Texture(Gdx.files.internal("data/sprites/arrow.png")));
        m_game.setSpriteBatch(new SpriteBatch());
        m_game.getSpriteBatch().getProjectionMatrix().setToOrtho2D(0, 0, 800, 480);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.7f, 0.7f, 0.7f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);



        hovered = (Gdx.input.getX() >= 248 && Gdx.input.getX() <= 552 && Gdx.input.getY() >=  250 && Gdx.input.getY() <= 325);


        m_game.getSpriteBatch().begin();

        m_game.getSpriteBatch().draw(m_title, 100, 320, 0, 0, 600f, 90f, 1f, 1f, 0);
        m_game.getSpriteBatch().draw((hovered) ? m_play : m_play_hover, 248, 150, 0, 0, 304f, 75f, 1f, 1f, 0);

        if(hovered){
            m_game.getSpriteBatch().draw(m_arrow, 200, 165, 0, 0, 46f, 46f, 1f, 1f, 0);
        }

        m_game.getSpriteBatch().end();



        if (Gdx.input.isKeyPressed(Keys.ANY_KEY) || (Gdx.input.justTouched()&&hovered )) {
            System.out.println("GAME");
            m_game.setScreen(new GameScreen(m_game));
        }
    }

    @Override
    public void resize(int width, int height) {
        this.width = width;
        this.height = height;
        if (width == 480 && height == 320) {
            m_camera = new OrthographicCamera(700, 466);
            this.width = 700;
            this.height = 466;
        } else if (width == 320 && height == 240) {
            m_camera = new OrthographicCamera(700, 525);
            this.width = 700;
            this.height = 525;
        } else if (width == 400 && height == 240) {
            m_camera = new OrthographicCamera(800, 480);
            this.width = 800;
            this.height = 480;
        } else if (width == 432 && height == 240) {
            m_camera = new OrthographicCamera(700, 389);
            this.width = 700;
            this.height = 389;
        } else if (width == 960 && height == 640) {
            m_camera = new OrthographicCamera(800, 533);
            this.width = 800;
            this.height = 533;
        }  else if (width == 1366 && height == 768) {
            m_camera = new OrthographicCamera(1280, 720);
            this.width = 1280;
            this.height = 720;
        } else if (width == 1366 && height == 720) {
            m_camera = new OrthographicCamera(1280, 675);
            this.width = 1280;
            this.height = 675;
        } else if (width == 1536 && height == 1152) {
            m_camera = new OrthographicCamera(1366, 1024);
            this.width = 1366;
            this.height = 1024;
        } else if (width == 1920 && height == 1152) {
            m_camera = new OrthographicCamera(1366, 854);
            this.width = 1366;
            this.height = 854;
        } else if (width == 1920 && height == 1200) {
            m_camera = new OrthographicCamera(1366, 800);
            this.width = 1280;
            this.height = 800;
        } else if (width > 1280) {
            m_camera = new OrthographicCamera(1280, 768);
            this.width = 1280;
            this.height = 768;
        } else if (width < 800) {
            m_camera = new OrthographicCamera(800, 480);
            this.width = 800;
            this.height = 480;
        } else {
            m_camera = new OrthographicCamera(width, height);
        }

        m_camera.position.x = 400;
        m_camera.position.y = 240;
        m_camera.update();
        m_game.getSpriteBatch().getProjectionMatrix().set(m_camera.combined);

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {

    }
}

