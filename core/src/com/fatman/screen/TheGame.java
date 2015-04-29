package com.fatman.screen;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.fatman.controller.PlayerController;
import com.fatman.engine.LevelModule;
import com.fatman.engine.Pattern;
import com.fatman.engine.Player;
import com.fatman.graphics.LevelModuleDrawer;
import com.fatman.graphics.PlayerDrawer;
import com.fatman.graphics.TileSet;

public class TheGame extends Game {

	////////////////////////////BITFONT
	private BitmapFont font;

	////////////////////////////BATCH
	private SpriteBatch batch;
/*
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
*/
	public SpriteBatch getSpriteBatch(){
		return batch;
	}

	public void setSpriteBatch(SpriteBatch b){
		batch = b;
	}


	@Override
	public void create () {

//		Level level = new Level();
//		level.print();

		font = new BitmapFont();

		//background
		/*texture = new Texture(Gdx.files.internal("tileset/latile.png"));
		tileSet = new TileSet(texture, 64, 64);
		batch = new SpriteBatch();
		levelModuleDrawer = new LevelModuleDrawer(tileSet, batch);
		scenePattern = new Pattern("patterns/scene_patterns/0.pt");
		objectPattern = new Pattern("patterns/object_patterns/0.pt");
		levelModule = new LevelModule(scenePattern, objectPattern, levelModuleDrawer);
		levelModule.notifyChanges();

		//player
		m_texturePlayer = new Texture(Gdx.files.internal("tileset/larry_run.png"));
		m_tileSetPlayer = new TileSet(m_texturePlayer, 113, 113);
		batch = new SpriteBatch();
		m_playerDrawer = new PlayerDrawer(m_tileSetPlayer, batch);
		m_player = new Player(m_playerDrawer);
		m_playerController = new PlayerController(m_player);
		m_player.notifyChanges();*/

		this.setScreen(new MainMenuScreen(this));
	}

	@Override
	public void render () {

//		batch.begin();
//			tileSet.getTile(0).draw(batch);
//			tileSet.getTile(1).setPosition(64, 0);
//		batch.end();
//
//		batch.begin();
//			tileSet.getTile(1).draw(batch);
//		batch.end();

		/*levelModuleDrawer.draw();
		m_playerDrawer.draw();

		m_playerController.eventHandler();*/

		//m_player.increaseX();

		super.render();
	}

	@Override
	public void dispose(){
		batch.dispose();
		font.dispose();
	}
}
