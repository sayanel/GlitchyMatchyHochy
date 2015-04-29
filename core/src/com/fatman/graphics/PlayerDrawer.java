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

    Vector2 m_position;
    int m_height;
    int m_width;
    Player.State m_state;



    /////////ANIMATION & TEXTURE .
    private Animation m_playerRunAnimation;
    private Animation m_playerJumpAnimation;

    private int m_currentTimeJump;

    boolean m_isJumping;

    private float m_elapsedTime = 0;



    //******************** * CONSTRUCTORS * ********************//
    public PlayerDrawer(SpriteBatch batch, Texture texturePlayer) {
        m_batch = batch;


        TextureRegion[] split = new TextureRegion(texturePlayer).split(113, 113)[0];
        m_playerRunAnimation = new Animation(0.1f, split[0], split[5]);
        split = new TextureRegion(texturePlayer).split(113, 113)[1];
        m_playerJumpAnimation = new Animation(0.1f, split[0], split[5]);





    }

    //******************** * GETTERS * ********************//



    //******************** * SETTERS * ********************//



    //******************** * FUNCTION * ********************//


    public void drawJump(){
        //System.out.println("DRAW JUMP");
        m_batch.draw(m_playerJumpAnimation.getKeyFrame(m_elapsedTime, true), m_position.x, m_position.y);

    }

    public void drawPlayerRun(){

        //System.out.println("DRAW RUN");
        m_batch.draw(m_playerRunAnimation.getKeyFrame(m_elapsedTime, true), m_position.x, m_position.y);

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
        m_isJumping = player.getIsJumping();
    }
}
