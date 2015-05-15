package com.fatman.graphics;

/**
 * Created by maximilien on 28/04/2015.
 */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.fatman.engine.Player;

public class PlayerDrawer implements Drawer {

    //******************** * PARAMETERS * ********************//

    private SpriteBatch m_batch;

    private Vector2 m_position;
    private float m_height;
    private float m_width;
    private Player.State m_state;
    private int m_fat_state;

    private int m_tile_width;
    private int m_tile_height;

    private int m_isAlreadyDead;

    /////////ANIMATION & TEXTURE .
    private Animation m_playerRunAnimation1;
    private Animation m_playerRunAnimation2;
    private Animation m_playerRunAnimation3;
    private Animation m_playerJumpAnimation1;
    private Animation m_playerJumpAnimation1_prout;
    private Animation m_playerJumpAnimation2;
    private Animation m_playerJumpAnimation2_prout;
    private Animation m_playerJumpAnimation3;
    private Animation m_playerJumpAnimation3_prout;
    private Animation m_playerDie;

    /////////SOUND
    Sound m_prout_sound;
    Sound m_souffle_sound;



    private float m_elapsedTime = 0;



    //******************** * CONSTRUCTORS * ********************//
    public PlayerDrawer(SpriteBatch batch, Texture texturePlayerRun, Texture texturePlayerJump, Texture texturePlayerDie, int tileWidth, int tileHeight) {
        m_batch = batch;

        setTileWidth(tileWidth);
        setTileHeight(tileHeight);

        ///////RUN
        TextureRegion[] split = new TextureRegion(texturePlayerRun).split(128, 128)[0];
        m_playerRunAnimation1 = new Animation(0.06f, split);
        split = new TextureRegion(texturePlayerRun).split(128, 128)[1];
        m_playerRunAnimation2 = new Animation(0.06f, split);
        split = new TextureRegion(texturePlayerRun).split(128, 128)[2];
        m_playerRunAnimation3 = new Animation(0.06f, split);

        //////JUMP NORMAL
        split = new TextureRegion(texturePlayerJump).split(128, 128)[0];
        m_playerJumpAnimation1 = new Animation(0.06f, split);
        split = new TextureRegion(texturePlayerJump).split(128, 128)[2];
        m_playerJumpAnimation2 = new Animation(0.06f, split);
        split = new TextureRegion(texturePlayerJump).split(128, 128)[4];
        m_playerJumpAnimation3 = new Animation(0.06f, split);

        //////JUMP PROUT
        split = new TextureRegion(texturePlayerJump).split(128, 128)[1];
        m_playerJumpAnimation1_prout = new Animation(0.06f, split);
        split = new TextureRegion(texturePlayerJump).split(128, 128)[3];
        m_playerJumpAnimation2_prout = new Animation(0.06f, split);
        split = new TextureRegion(texturePlayerJump).split(128, 128)[5];
        m_playerJumpAnimation3_prout = new Animation(0.06f, split);

        //DIE
        split = new TextureRegion(texturePlayerDie).split(128, 128)[0];
        m_playerDie = new Animation(0.06f, split);
        m_isAlreadyDead = 0;


    }

    //******************** * GETTERS * ********************//

    public void setTileWidth(int tileWidth){
        m_tile_width = tileWidth;
    }
    public void setTileHeight(int tileHeight){
        m_tile_height = tileHeight;
    }

    //******************** * SETTERS * ********************//



    //******************** * FUNCTION * ********************//


    public void drawJump(){
        if(m_fat_state == 0) m_batch.draw(m_playerJumpAnimation1.getKeyFrame(m_elapsedTime, true), m_position.x * m_tile_width, m_position.y * m_tile_height);
        if(m_fat_state == 1) m_batch.draw(m_playerJumpAnimation2.getKeyFrame(m_elapsedTime, true), m_position.x * m_tile_width, m_position.y * m_tile_height);
        if(m_fat_state == 2) m_batch.draw(m_playerJumpAnimation3.getKeyFrame(m_elapsedTime, true), m_position.x * m_tile_width, m_position.y * m_tile_height);
    }

    public void drawDoubleJump(){
        if(m_fat_state == 0) m_batch.draw(m_playerJumpAnimation1_prout.getKeyFrame(m_elapsedTime, true), m_position.x * m_tile_width, m_position.y * m_tile_height);
        if(m_fat_state == 1) m_batch.draw(m_playerJumpAnimation2_prout.getKeyFrame(m_elapsedTime, true), m_position.x * m_tile_width, m_position.y * m_tile_height);
        if(m_fat_state == 2) m_batch.draw(m_playerJumpAnimation3_prout.getKeyFrame(m_elapsedTime, true), m_position.x * m_tile_width, m_position.y * m_tile_height);

    }

    public void drawPlayerRun(){
        if(m_fat_state == 0) m_batch.draw(m_playerRunAnimation1.getKeyFrame(m_elapsedTime, true), m_position.x * m_tile_width, m_position.y * m_tile_height);
        if(m_fat_state == 1) m_batch.draw(m_playerRunAnimation2.getKeyFrame(m_elapsedTime, true), m_position.x * m_tile_width, m_position.y * m_tile_height);
        if(m_fat_state == 2) m_batch.draw(m_playerRunAnimation3.getKeyFrame(m_elapsedTime, true), m_position.x * m_tile_width, m_position.y * m_tile_height);
    }

    public void drawPlayerDie(){
        m_batch.draw(m_playerDie.getKeyFrame(m_elapsedTime, true), m_position.x * m_tile_width, m_position.y * m_tile_height);
        m_isAlreadyDead ++;
    }

    public void drawDeadPlayer(){
        m_batch.draw(m_playerDie.getKeyFrame(9), m_position.x * m_tile_width, m_position.y * m_tile_height);
    }


    //DRAWER METHODS
    public void draw() {


        if(m_state == Player.State.RUNNING){
            m_elapsedTime += Gdx.graphics.getDeltaTime()/1;
            drawPlayerRun();
        }

        if(m_state == Player.State.DOUBLEJUMP) {
            m_elapsedTime += Gdx.graphics.getDeltaTime()/2;
            drawDoubleJump();
        }

        if(m_state == Player.State.JUMPING) {
            m_elapsedTime += Gdx.graphics.getDeltaTime()/2;
            drawJump();
        }

        if(m_state == Player.State.DEAD){
            m_elapsedTime += Gdx.graphics.getDeltaTime()/2;
            if(m_isAlreadyDead < 65) drawPlayerDie();
            else drawDeadPlayer();
        }

    }

    @Override
    public void update(Drawable drawable) {
        update(((Player) drawable));
    }

    private void update(Player player){
        m_position = player.getPosition();
        m_width = player.getWidth();
        m_height = player.getHeight();
        m_state = player.getState();
        m_fat_state = player.getFatState();
    }
}
