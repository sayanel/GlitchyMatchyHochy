package com.fatman.screen;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fatman.engine.Level;
import com.fatman.engine.Pattern;
import com.fatman.graphics.TileSet;

import java.io.IOException;

public class TheGame extends ApplicationAdapter {

	private SpriteBatch batch;
	private TileSet tileSet;
	private Texture texture;

	@Override
	public void create () {

//		Level level = new Level();
//		level.print();

		texture = new Texture(Gdx.files.internal("tileset/latile.png"));

		tileSet = new TileSet(texture, 64, 64);

		batch = new SpriteBatch();
	}

	@Override
	public void render () {
		batch.begin();
			tileSet.getTile(0).draw(batch);
			tileSet.getTile(1).setPosition(64, 0);
		batch.end();

		batch.begin();
			tileSet.getTile(1).draw(batch);
		batch.end();
	}
}
