//======================================
// Company : GlitchyMatchyHochy
// Project : GlitchyMatchyHochy
// Author :  Mehdi-Antoine 
// Date :    23/04/2015.
//======================================

package com.fatman.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fatman.graphics.LevelModuleDrawer;
import com.fatman.graphics.TileSet;
import com.fatman.engine.LevelModule;
import com.fatman.engine.Pattern;
import com.fatman.engine.Player;
import com.fatman.controller.PlayerController;
import com.fatman.graphics.PlayerDrawer;

public class GameScreen extends AbstractScreen{

    /////////////////////////////LEVEL
    private TileSet tileSet;
    private Texture texture;
    private LevelModuleDrawer levelModuleDrawer;
    private Pattern scenePattern;
    private Pattern objectPattern;
    private LevelModule levelModule;

    ///////////////////////////PLAYER
    private Player m_player;
    private PlayerController m_playerController;
    private PlayerDrawer m_playerDrawer;
    private TileSet m_tileSetPlayer;
    private Texture m_texturePlayer;

    public GameScreen(final TheGame game) {
        super(game);
        // Gdx.input.setInputProcessor(this);
        m_game.setSpriteBatch(new SpriteBatch());
        m_game.getSpriteBatch().getProjectionMatrix().setToOrtho2D(0, 0, 800, 480);

        //background
		texture = new Texture(Gdx.files.internal("tileset/latile.png"));
		tileSet = new TileSet(texture, 64, 64);
		m_game.setSpriteBatch(new SpriteBatch());
		levelModuleDrawer = new LevelModuleDrawer(tileSet, m_game.getSpriteBatch());
		scenePattern = new Pattern("patterns/scene_patterns/0.pt");
		objectPattern = new Pattern("patterns/object_patterns/0.pt");
		levelModule = new LevelModule(scenePattern, objectPattern, levelModuleDrawer);
		levelModule.notifyChanges();

		//player
		m_texturePlayer = new Texture(Gdx.files.internal("tileset/larry_run.png"));
		m_tileSetPlayer = new TileSet(m_texturePlayer, 113, 113);
        m_game.setSpriteBatch(new SpriteBatch());
		m_playerDrawer = new PlayerDrawer(m_tileSetPlayer, m_game.getSpriteBatch());
		m_player = new Player(m_playerDrawer);
		m_playerController = new PlayerController(m_player);
		m_player.notifyChanges();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        levelModuleDrawer.draw();
        m_playerDrawer.draw();

        m_playerController.eventHandler();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
