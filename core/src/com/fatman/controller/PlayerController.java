package com.fatman.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector3;
import com.fatman.engine.Player;


/**
 * Created by maximilien on 29/04/2015.
 */


public class PlayerController implements Controller{

    //******************** * PARAMETERS * ********************//
    Player m_player;


    //******************** * CONSTRUCTORS * ********************//
    public  PlayerController(Player player){
        this.m_player = player;
    }


    //******************** * FUNCTIONS * ********************//

    public void eventHandler(){

        ///////JUMP
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
            if(m_player.getState() != Player.State.JUMPING) m_player.jump();
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.A)){
            m_player.slim();
        }

        ///////HIT
        if (Gdx.input.isKeyJustPressed(Input.Keys.DPAD_LEFT)){
            m_player.hit();
        }

        ///////ACCELERATE
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            m_player.accelerate();
        }

        if(Gdx.input.justTouched()) {
            Vector3 touchPos = new Vector3();
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            if (touchPos.y > Gdx.graphics.getHeight() / 2) {
               m_player.jump();
            }


        }

    }

    public void setControllable(){

    }

    public void notifyChanges() {
    }

}







