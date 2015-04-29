package com.fatman.screen;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fatman.controller.PlayerController;
import com.fatman.engine.LevelModule;
import com.fatman.engine.Pattern;
import com.fatman.engine.Player;
import com.fatman.graphics.LevelModuleDrawer;
import com.fatman.graphics.PlayerDrawer;
import com.fatman.graphics.TileSet;

public class TheGame extends ApplicationAdapter {

	////////////////////////////BATCH
	private SpriteBatch m_batch;

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


	////////////////////////CAMERA
	private static final float CAMERA_WIDTH = 800f;
	private static final float CAMERA_HEIGHT = 480f;

	private OrthographicCamera m_camera;



	@Override
	public void create () {

//		Level level = new Level();
//		level.print();

		m_batch = new SpriteBatch();


		//background
		texture = new Texture(Gdx.files.internal("tileset/latile.png"));
		tileSet = new TileSet(texture, 64, 64);
		levelModuleDrawer = new LevelModuleDrawer(tileSet, m_batch);
		scenePattern = new Pattern("patterns/scene_patterns/0.pt");
		objectPattern = new Pattern("patterns/object_patterns/0.pt");
		levelModule = new LevelModule(scenePattern, objectPattern, levelModuleDrawer);
		levelModule.notifyChanges();

		//player
		m_texturePlayer = new Texture(Gdx.files.internal("tileset/larry_run.png"));
		m_playerDrawer = new PlayerDrawer(m_batch, m_texturePlayer);
		m_player = new Player(m_playerDrawer);
		m_playerController = new PlayerController(m_player);
		m_player.notifyChanges();

		//camera
		this.m_camera = new OrthographicCamera(CAMERA_WIDTH, CAMERA_HEIGHT);
		this.m_camera.setToOrtho(false,CAMERA_WIDTH,CAMERA_HEIGHT);
		this.m_camera.position.set(CAMERA_WIDTH / 2f, CAMERA_HEIGHT / 2f, 0);


	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		moveCamera(m_player.getPosition().x, CAMERA_HEIGHT / 2);
		m_batch.setProjectionMatrix(m_camera.combined);

		m_batch.begin();

			///////////////////////////////LEVEL
			levelModuleDrawer.draw();

			///////////////////////////////PLAYER
			m_playerDrawer.draw();
			m_playerController.eventHandler();
			m_player.run();
			m_player.update(m_playerController);

		m_batch.end();


	}

	@Override
	public void dispose(){
		m_texturePlayer.dispose();
	}


	public void moveCamera(float x, float y){
		if ((m_player.getPosition().x > CAMERA_WIDTH / 2)) {
			m_camera.position.set(x, y, 0);
			m_camera.update();
		}
	}


}

