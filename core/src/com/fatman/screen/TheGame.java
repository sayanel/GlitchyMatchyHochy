package com.fatman.screen;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fatman.engine.Level;
import com.fatman.engine.Player;

public class TheGame extends Game {
	//******************************* ATTRIBUTES ******************************
	private Level m_level;
	private Player m_player;
	public SpriteBatch m_batch;
	public BitmapFont m_font;


	//*******************************   METHODS  ******************************
	@Override
	public void create () {
		m_batch = new SpriteBatch();
		m_font = new BitmapFont();
		this.setScreen(new MainMenuScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}

	@Override
	public void dispose(){
		m_batch.dispose();
		m_font.dispose();
	}
}
