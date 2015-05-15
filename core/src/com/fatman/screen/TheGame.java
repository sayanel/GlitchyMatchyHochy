package com.fatman.screen;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
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
//bite

public class TheGame extends ApplicationAdapter {

	///////////////////////////CONSTANTS
	public static final int GAME_WIDTH = 800;
	public static final int GAME_HEIGHT = 480;


	///////////////////////////BATCH
	private SpriteBatch m_camera_batch;
	private SpriteBatch m_interface_batch;


	///////////////////////////LEVEL
	private Level m_level;
	private LevelDrawer m_level_drawer;

	private TileSet m_tile_set;
	private TileSet m_tile_set_object;
	private TileSet m_game_object_sprite;
	private Texture m_tile_set_texture;
	private Texture m_tile_set_texture_object;
	private Texture m_game_object_sprite_texture;

	///////////////////////////INTERFACE
	private TileSet m_stomach_sprite;
	private Texture m_stomach_texture;

	private TileSet m_pause_button_sprite;
	private Texture m_pause_button_texture;

	private TileSet m_pill_button_sprite;
	private Texture m_pill_button_texture;

	private TileSet m_pill_can_sprite;
	private Texture m_pill_can_texture;

	///////////////////////////BACKGROUND
	private ParallaxBackground m_background;


	///////////////////////////PLAYER
	private Player m_player;
	private PlayerController m_playerController;
	private PlayerDrawer m_playerDrawer;
	private Texture m_texturePlayerRun;
	private Texture m_texturePlayerJump;
	private PlayerInterfaceDrawer m_player_interface_drawer;


	////////////////////////CAMERA
	private static final float CAMERA_WIDTH = (float) GAME_WIDTH;
	private static final float CAMERA_HEIGHT = (float) GAME_HEIGHT;
	float m_move_camera_y;
	private OrthographicCamera m_camera;

	///////////////////////////COLLISION
	private Collision col;

	///////////////////////////AUDIO
	private Sound m_global_sound;



	@Override
	public void create () {

		///////////////////////////BATCH
		m_camera_batch = new SpriteBatch();
		m_interface_batch = new SpriteBatch();

		///////////////////////////LEVEL
		m_tile_set_texture = new Texture(Gdx.files.internal("tileset/street_tile.png"));
		m_tile_set_texture_object = new Texture(Gdx.files.internal("tileset/PiluleMoche.png"));
		m_game_object_sprite_texture = new Texture(Gdx.files.internal("tileset/food_sprite.png"));

		m_tile_set = new TileSet(m_tile_set_texture, 64, 64);
		m_tile_set_object = new TileSet(m_tile_set_texture_object, 64, 64);
		m_game_object_sprite = new TileSet(m_game_object_sprite_texture, 64, 64);

		m_level_drawer = new LevelDrawer();
		m_level = new Level("patterns/", new ArrayList<LevelModule>(), m_level_drawer);

		m_level.getLevelModules().add(m_level.genLevelModule(new LevelModuleDrawer(m_tile_set, m_tile_set_object, m_game_object_sprite, m_camera_batch)));
		for(int i = 0; i < 3; ++i){
			double position = m_level.peek().getPosition() + m_level.peek().getWidth();
			m_level.addAtEnd(m_level.genLevelModule(position, new LevelModuleDrawer(m_tile_set, m_tile_set_object, m_game_object_sprite, m_camera_batch)));
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

		///////////////////////////INTERFACE
		m_stomach_texture = new Texture(Gdx.files.internal("tileset/interface_sprite.png"));
		m_stomach_sprite = new TileSet(m_stomach_texture, 256, 256);

		m_pill_button_texture = new Texture(Gdx.files.internal("tileset/button-eat.png"));
		m_pill_button_sprite = new TileSet(m_pill_button_texture, 128, 128);

		m_pill_can_texture = new Texture(Gdx.files.internal("tileset/pill-can.png"));
		m_pill_can_sprite = new TileSet(m_pill_can_texture, 64, 64);

		m_pause_button_texture = new Texture(Gdx.files.internal("tileset/button-pause.png"));
		m_pause_button_sprite = new TileSet(m_pause_button_texture, 64, 64);

		m_player_interface_drawer = new PlayerInterfaceDrawer(m_stomach_sprite, m_pill_can_sprite, m_pill_button_sprite, m_pause_button_sprite, m_interface_batch);

		///////////////////////////PLAYER
		m_texturePlayerRun = new Texture(Gdx.files.internal("tileset/larry-run.png"));
		m_texturePlayerJump = new Texture(Gdx.files.internal("tileset/larry-jump.png"));

		m_playerDrawer = new PlayerDrawer(m_camera_batch, m_texturePlayerRun, m_texturePlayerJump, m_tile_set.getWidth(), m_tile_set.getHeight());

		m_player = new Player(m_playerDrawer, m_player_interface_drawer);
		m_playerController = new PlayerController(m_player);
		m_player.notifyChanges();

		///////////////////////////CAMERA
		this.m_camera = new OrthographicCamera(CAMERA_WIDTH, CAMERA_HEIGHT);
		this.m_camera.setToOrtho(false, CAMERA_WIDTH, CAMERA_HEIGHT);
		this.m_camera.position.set(CAMERA_WIDTH / 2f, CAMERA_HEIGHT / 2f, m_player.getPosition().x * 64);
		this.m_move_camera_y = CAMERA_HEIGHT / 2;


		///////////////////////////COLLISION
		col = new Collision();

		///////////////////////////SOUND
		m_global_sound = Gdx.audio.newSound(Gdx.files.internal("audio/wario01.mp3"));

		m_global_sound.play(1.0f);


	}

	@Override
	public void render () {

		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


		moveCamera(m_player.getPosition().x * 64, getCameraY());
		m_camera_batch.setProjectionMatrix(m_camera.combined);

		BitmapFont bitmapFont = new BitmapFont();
		m_playerController.eventHandler();
		m_player.update(m_playerController);

		col.collisionHandler(m_player, m_level.getModule(1));

		m_level.checkPlayerPosition(m_player.getPosition().x);

		///////////////////////////////////BACKGROUND
		if(m_player.getState() != Player.State.DEAD)
			m_background.draw(0.05f, m_player.getRun_delta()*5000);
		else
			m_background.draw(0.0f, 0);




		m_camera_batch.begin();
			///////////////////////////////LEVEL
			m_level_drawer.draw();

			//bitmapFont.draw(m_batch, "PlayerWorldPosition : " + Double.toString(m_player.getPosition().x), m_player.getPosition().x * 64, 350);
			//bitmapFont.draw(m_batch, "PlayerGraphicPosition : " + Double.toString(m_player.getPosition().x * 64), m_player.getPosition().x * 64, 380);

			bitmapFont.draw(m_camera_batch, "Weight : " + Double.toString(m_player.getWeight()), m_player.getPosition().x * 64, 380);
			bitmapFont.draw(m_camera_batch, "Pills : " + Double.toString(m_player.getPillsNumber()), m_player.getPosition().x * 64, 350);
			//bitmapFont.draw(m_batch, "Speed : " + Double.toString(m_player.getVelocity().x), m_player.getPosition().x * 64, 430);
			//bitmapFont.draw(m_batch, "bg speed : " + Double.toString(m_background.getSpeed().x), m_player.getPosition().x * 64, 410);
			//bitmapFont.draw(m_batch, "Pos en y : " + Double.toString(m_player.getPosition().y * 64), m_player.getPosition().x * 64 + 200, 330);
			//bitmapFont.draw(m_batch, "m_move_camera_y : " + Double.toString(m_move_camera_y), m_player.getPosition().x * 64 + 200, 310);
			//bitmapFont.draw(m_batch, "m_fat_state : " + Double.toString(m_player.getFatState()), m_player.getPosition().x * 64, 290);
			//Player.State state = m_player.getState();
			//bitmapFont.draw(m_batch, "State : " + state, m_player.getPosition().x * 64, 450);


			///////////////////////////////PLAYER
			m_playerDrawer.draw();
		m_camera_batch.end();

		m_interface_batch.begin();
//			bitmapFont.draw(m_interface_batch, "Weight : " + Double.toString(m_player.getWeight()), 300, 380);
//			bitmapFont.draw(m_interface_batch, "Pills : " + Double.toString(m_player.getPillsNumber()), 300, 350);

			m_player_interface_drawer.draw();

		m_interface_batch.end();


	}

	@Override
	public void dispose(){
		m_texturePlayerRun.dispose();
		m_texturePlayerJump.dispose();
	}


	//////////////////////////////CAMERA FUNCTION

	public float getCameraY(){
		if(m_player.getState() == Player.State.DOUBLEJUMP){
			float y_limit = CAMERA_HEIGHT - CAMERA_HEIGHT / 2;
			if(m_move_camera_y * 64 >= y_limit) m_move_camera_y = m_player.getPosition().y * 64;
			if(m_move_camera_y < CAMERA_HEIGHT / 2 ) m_move_camera_y = CAMERA_HEIGHT / 2;
		}
		else m_move_camera_y = CAMERA_HEIGHT / 2;

		return m_move_camera_y;
	}

	public void moveCamera(float x, float y){
		m_camera.position.set(x + CAMERA_WIDTH/2 - 200, y, 0);
		m_camera.update();
	}


}
