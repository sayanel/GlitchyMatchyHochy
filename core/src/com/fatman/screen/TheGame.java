package com.fatman.screen;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fatman.controller.PlayerController;
import com.fatman.engine.Level;
import com.fatman.engine.LevelModule;
import com.fatman.engine.Player;
import com.fatman.graphics.LevelDrawer;
import com.fatman.graphics.LevelModuleDrawer;
import com.fatman.graphics.PlayerDrawer;
import com.fatman.graphics.TileSet;

import java.util.ArrayList;

//collision

public class TheGame extends ApplicationAdapter {

	///////////////////////////BATCH
	private SpriteBatch m_batch;

	private Level m_level;
	private LevelDrawer m_level_drawer;


	private TileSet m_tile_set;
	private Texture m_texture;

	private double m_player_position;

	private double m_alpha;

	///////////////////////////PLAYER
	private Player m_player;
	private PlayerController m_playerController;
	private PlayerDrawer m_playerDrawer;
	private TileSet m_tileSetPlayer;
	private Texture m_texturePlayer;

	////////////////////////CAMERA
	private static final float CAMERA_WIDTH = 800f;
	private static final float CAMERA_HEIGHT = 480f;

	private OrthographicCamera m_camera;




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


		//player
		m_texturePlayer = new Texture(Gdx.files.internal("tileset/larry_run.png"));
		m_playerDrawer = new PlayerDrawer(m_batch, m_texturePlayer, m_tile_set.getWidth());
		m_player = new Player(m_playerDrawer);
		m_playerController = new PlayerController(m_player);
		m_player.notifyChanges();

		//camera
		this.m_camera = new OrthographicCamera(CAMERA_WIDTH, CAMERA_HEIGHT);
		this.m_camera.setToOrtho(false,CAMERA_WIDTH,CAMERA_HEIGHT);
		this.m_camera.position.set(CAMERA_WIDTH / 2f, CAMERA_HEIGHT / 2f, m_player.getPosition().x * 64);

	}

	@Override
	public void render () {


		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		moveCamera(m_player.getPosition().x * 64, CAMERA_HEIGHT / 2);
		m_batch.setProjectionMatrix(m_camera.combined);

		BitmapFont bitmapFont = new BitmapFont();

		m_batch.begin();

			///////////////////////////////LEVEL
			m_level_drawer.draw();
			bitmapFont.draw(m_batch, "PlayerWorldPosition : " + Double.toString(m_player.getPosition().x), m_player.getPosition().x * 64, 350);
			bitmapFont.draw(m_batch, "PlayerGraphicPosition : " + Double.toString(m_player.getPosition().x * 64), m_player.getPosition().x * 64, 380);

			///////////////////////////////PLAYER
			m_playerController.eventHandler();
			m_player.update(m_playerController);
			m_playerDrawer.draw();


		m_batch.end();


		m_level.checkPlayerPosition(m_player.getPosition().x);

	}

	@Override
	public void dispose(){
		m_texturePlayer.dispose();
	}

	public void moveCamera(float x, float y){
		m_camera.position.set(x + CAMERA_WIDTH/2 - 200, y, 0);
		m_camera.update();
	}


}
