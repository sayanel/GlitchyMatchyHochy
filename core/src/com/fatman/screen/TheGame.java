package com.fatman.screen;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.assets.loaders.BitmapFontLoader;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fatman.controller.PlayerController;
import com.fatman.engine.Level;
import com.fatman.engine.LevelModule;
import com.fatman.engine.Pattern;
import com.fatman.engine.Player;
import com.fatman.graphics.LevelDrawer;
import com.fatman.graphics.LevelModuleDrawer;
import com.fatman.graphics.PlayerDrawer;
import com.fatman.graphics.TileSet;

import java.util.ArrayList;

public class TheGame extends ApplicationAdapter {

	private SpriteBatch m_batch;

	private Level m_level;
	private LevelDrawer m_level_drawer;


	private TileSet m_tile_set;
	private Texture m_texture;

	private double m_player_position;

	private double m_alpha;

	@Override
	public void create () {

		m_batch = new SpriteBatch();

		m_texture = new Texture(Gdx.files.internal("tileset/latile.png"));

		m_tile_set = new TileSet(m_texture, 64, 64);

		m_level_drawer = new LevelDrawer();
		m_level = new Level("patterns/", new ArrayList<LevelModule>(), m_level_drawer);

		m_level.getLevelModules().add(m_level.genLevelModule(new LevelModuleDrawer(m_tile_set, m_batch)));
		for(int i = 0; i < 4; ++i){
			double position = m_level.peek().getPosition() + m_level.peek().getWidth();
			m_level.addAtEnd(m_level.genLevelModule(position, new LevelModuleDrawer(m_tile_set, m_batch)));
		}

		m_level.notifyChanges();

		m_level.print();

		m_player_position = 0;

		m_alpha = 0.01;

	}

	@Override
	public void render () {


		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		BitmapFont bitmapFont = new BitmapFont();

		m_batch.begin();
		m_level_drawer.draw();
		bitmapFont.draw(m_batch, "PlayerPosition : " + Double.toString(m_player_position), 150, 350);
		m_batch.end();

		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
			m_player_position += m_alpha;
		}
		if(Gdx.input.isKeyPressed(Input.Keys.UP)){
			m_alpha *= 2;
		}

		m_level.checkPlayerPosition(m_player_position);

	}
}
