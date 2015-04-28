package com.fatman.screen;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fatman.engine.LevelModule;
import com.fatman.engine.Pattern;
import com.fatman.engine.Player;
import com.fatman.graphics.LevelModuleDrawer;
import com.fatman.graphics.PlayerDrawer;
import com.fatman.graphics.TileSet;

public class TheGame extends ApplicationAdapter {

	private SpriteBatch batch;
	private TileSet tileSet;
	private Texture texture;

	private Pattern scenePattern;
	private Pattern objectPattern;

	private LevelModule levelModule;

	private Player m_player;
	private PlayerDrawer m_playerDrawer;
	private TileSet m_tileSetPlayer;
	private Texture m_texturePlayer;

	private LevelModuleDrawer levelModuleDrawer;

	@Override
	public void create () {

//		Level level = new Level();
//		level.print();

		//background
		texture = new Texture(Gdx.files.internal("tileset/latile.png"));
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
		m_player.notifyChanges();


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

		levelModuleDrawer.draw();
		m_playerDrawer.draw();
	}
}
