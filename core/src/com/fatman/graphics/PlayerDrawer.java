package com.fatman.graphics;

/**
 * Created by maximilien on 28/04/2015.
 */

import com.badlogic.gdx.Gdx;
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
    private int m_height;
    private int m_width;
    private Player.State m_state;

    private int m_tile_width;



    /////////ANIMATION & TEXTURE .
    private Animation m_playerRunAnimation;
    private Animation m_playerJumpAnimation;

    private int m_currentTimeJump;


    private float m_elapsedTime = 0;



    //******************** * CONSTRUCTORS * ********************//
    public PlayerDrawer(SpriteBatch batch, Texture texturePlayer, int tileWidth, int tileHeight) {
        m_batch = batch;

        setTileWidth(tileWidth);
        setTileHeight(tileHeight);

        TextureRegion[] split = new TextureRegion(texturePlayer).split(113, 113)[0];
        m_playerRunAnimation = new Animation(0.1f, split[0], split[5]);
        split = new TextureRegion(texturePlayer).split(113, 113)[1];
        m_playerJumpAnimation = new Animation(0.1f, split[0], split[5]);
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
        //System.out.println("DRAW JUMP");

        double jump = 0.0;
        if(m_currentTimeJump >= 50){ jump = 0.1f; }
        else if(m_currentTimeJump > 40){ jump = 0.06f; }
        else if(m_currentTimeJump > 32){ jump = 0.02f; }
        else if(m_currentTimeJump > 26){ jump = 0.0f; }
        else if(m_currentTimeJump >= 19){ jump = -0.02f; m_position.x += 0.005f; }
        else if(m_currentTimeJump >= 10){ jump = -0.06f; }
        else{ jump = -0.1f;}

        //System.out.println("currentTimeJump: " + m_currentTimeJump + " === jump: " + jump + "  ===    y: " + m_position.y);

        float posJump = m_position.y + (float)jump;
        m_position.y = posJump;
        m_batch.draw(m_playerJumpAnimation.getKeyFrame(m_elapsedTime, true), m_position.x * m_tile_width, posJump * m_tile_height);
    }

    public void drawPlayerRun(){

        //System.out.println("DRAW RUN");

        m_batch.draw(m_playerRunAnimation.getKeyFrame(m_elapsedTime, true), m_position.x * m_tile_width, m_position.y * m_tile_height);

    }


    //DRAWER METHODS
    public void draw() {
        m_elapsedTime += Gdx.graphics.getDeltaTime();


        if(m_state == Player.State.RUNNING){
            drawPlayerRun();
        }

        if(m_state == Player.State.JUMPING) {
            drawJump();
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
        m_currentTimeJump = player.getCurrentTimeJump();
    }
}
