//======================================
// Company : GlitchyMatchyHochy
// Project : GlitchyMatchyHochy
// Author :  Mehdi-Antoine 
// Date :    17/05/2015.
//======================================

package com.fatman.screen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Timer;

public class MainMenu extends ScreenAdapter {

    final GameInit m_game_init;

    ////////////////////////OTHER
    private Vector3 m_touchPos;
    private Rectangle m_playBounds;
    private Texture m_texture_menu;
    private SpriteBatch m_batch_screen;

    private Sound m_title_sound;

    public MainMenu(GameInit game_init) {
        m_game_init = game_init;

        m_texture_menu = new Texture(Gdx.files.internal("screen/title-bg.png"));
        m_playBounds = new Rectangle(0, 0, GameInit.WINDOW_WIDTH, GameInit.WINDOW_HEIGHT);

        m_touchPos = new Vector3();
        m_batch_screen = new SpriteBatch();

        m_title_sound = Gdx.audio.newSound(Gdx.files.internal("audio/BIG_LARRY.wav"));

    }

    public void update () {
        if (Gdx.input.justTouched()) {
            m_touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            if (m_playBounds.contains(m_touchPos.x, m_touchPos.y)) {

                m_title_sound.play(1.0f);
                Timer.schedule(new Timer.Task() {
                    @Override
                    public void run() {
                        m_game_init.setScreen(new GameScreen(m_game_init));
                    }
                }, 0.7f);


            }
        }
    }


    public void draw () {
        GL20 gl = Gdx.gl;
        gl.glClearColor(0, 1, 0, 1);
        gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        m_batch_screen.begin();
            m_batch_screen.draw(m_texture_menu, 0, 0, GameInit.WINDOW_WIDTH, GameInit.WINDOW_HEIGHT);
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
