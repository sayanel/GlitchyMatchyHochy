package com.fatman.screen;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.fatman.controller.PlayerController;
import com.fatman.engine.Collision;
import com.fatman.engine.Level;
import com.fatman.engine.LevelModule;
import com.fatman.engine.Player;
import com.fatman.graphics.*;

import java.util.ArrayList;

//merge master to graphic

public class TheGame extends ApplicationAdapter {

	///////////////////////////CONSTANTS
	public static final int GAME_WIDTH = 800;
	public static final int GAME_HEIGHT = 480;


	///////////////////////////BATCH
	private SpriteBatch m_batch;


	///////////////////////////LEVEL
	private Level m_level;
	private LevelDrawer m_level_drawer;

	private TileSet m_tile_set;
	private TileSet m_tile_set_object;
	private TileSet m_game_object_sprite;
	private Texture m_tile_set_texture;
	private Texture m_tile_set_texture_object;
	private Texture m_game_object_sprite_texture;

	///////////////////////////BACKGROUND
	private ParallaxBackground m_background;


	///////////////////////////PLAYER
	private Player m_player;
	private PlayerController m_playerController;
	private PlayerDrawer m_playerDrawer;
	private Texture m_texturePlayer;

	////////////////////////CAMERA
	private static final float CAMERA_WIDTH = (float) GAME_WIDTH;
	private static final float CAMERA_HEIGHT = (float) GAME_HEIGHT;

	private OrthographicCamera m_camera;

	///////////////////////////COLLISION
	Collision col;



	@Override
	public void create () {

		///////////////////////////BATCH
		m_batch = new SpriteBatch();

		///////////////////////////LEVEL
		m_tile_set_texture = new Texture(Gdx.files.internal("tileset/street_tile.png"));
		m_tile_set_texture_object = new Texture(Gdx.files.internal("tileset/PiluleMoche.png"));
		m_game_object_sprite_texture = new Texture(Gdx.files.internal("tileset/food_sprite.png"));

		m_tile_set = new TileSet(m_tile_set_texture, 64, 64);
		m_tile_set_object = new TileSet(m_tile_set_texture_object, 64, 64);
		m_game_object_sprite = new TileSet(m_game_object_sprite_texture, 64, 64);

		m_level_drawer = new LevelDrawer();
		m_level = new Level("patterns/", new ArrayList<LevelModule>(), m_level_drawer);

		m_level.getLevelModules().add(m_level.genLevelModule(new LevelModuleDrawer(m_tile_set, m_tile_set_object, m_game_object_sprite, m_batch)));
		for(int i = 0; i < 3; ++i){
			double position = m_level.peek().getPosition() + m_level.peek().getWidth();
			m_level.addAtEnd(m_level.genLevelModule(position, new LevelModuleDrawer(m_tile_set, m_tile_set_object, m_game_object_sprite, m_batch)));
		}

		m_level.notifyChanges();
		m_level.print();

		//////////////////////////BACKGROUND
		TextureRegion bg1 = new TextureRegion(new Texture(Gdx.files.internal("background/bg.jpg")));
		TextureRegion bg2 = new TextureRegion(new Texture(Gdx.files.internal("background/bg2.png")));
		m_background = new ParallaxBackground(new ParallaxLayer[]{
				new ParallaxLayer(bg1,new Vector2(0.05f,0.05f),new Vector2(0,100), new Vector2(0, 0)),
				new ParallaxLayer(bg2,new Vector2(0.1f,0.1f),new Vector2(0, 0)),
		}, GAME_WIDTH, GAME_HEIGHT, new Vector2(150,0));


		///////////////////////////PLAYER
		m_texturePlayer = new Texture(Gdx.files.internal("tileset/fatboy_sprite.png"));
		m_playerDrawer = new PlayerDrawer(m_batch, m_texturePlayer, m_tile_set.getWidth(), m_tile_set.getHeight());
		m_player = new Player(m_playerDrawer);
		m_playerController = new PlayerController(m_player);
		m_player.notifyChanges();

		///////////////////////////CAMERA
		this.m_camera = new OrthographicCamera(CAMERA_WIDTH, CAMERA_HEIGHT);
		this.m_camera.setToOrtho(false,CAMERA_WIDTH,CAMERA_HEIGHT);
		this.m_camera.position.set(CAMERA_WIDTH / 2f, CAMERA_HEIGHT / 2f, m_player.getPosition().x * 64);

		///////////////////////////COLLISION
		col = new Collision();

	}

	@Override
	public void render () {

		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		moveCamera(m_player.getPosition().x * 64, CAMERA_HEIGHT / 2);
		m_batch.setProjectionMatrix(m_camera.combined);

		//BitmapFont bitmapFont = new BitmapFont();
		m_playerController.eventHandler();
		m_player.update(m_playerController);

		col.collisionHandler(m_player, m_level.getModule(1));

		m_level.checkPlayerPosition(m_player.getPosition().x);

		///////////////////////////////////BACKGROUND
		m_background.draw(0.05f);

		m_batch.begin();

			///////////////////////////////LEVEL
			m_level_drawer.draw();
			//bitmapFont.draw(m_batch, "PlayerWorldPosition : " + Double.toString(m_player.getPosition().x), m_player.getPosition().x * 64, 350);
			//bitmapFont.draw(m_batch, "PlayerGraphicPosition : " + Double.toString(m_player.getPosition().x * 64), m_player.getPosition().x * 64, 380);

			///////////////////////////////PLAYER
			m_playerDrawer.draw();

		m_batch.end();




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
