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

        /************************************************************************************/
        /*************************************CLAVIER****************************************/
        /************************************************************************************/

        ///////DOUBLEJUMP
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE) && m_player.getState() == Player.State.JUMPING){
            if(m_player.getState() != Player.State.DOUBLEJUMP) m_player.doublejump();
            m_player.playProutSound();
        }

        ///////JUMP
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
            if(m_player.getState() != Player.State.JUMPING && m_player.getState() != Player.State.DOUBLEJUMP){
                m_player.jump();
                m_player.playJumpSound();
            }

        }

        //SLIM -- TAKE PILLS
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



        /************************************************************************************/
        /************************************SMARTPHONE**************************************/
        /************************************************************************************/

        Vector3 touchPos = new Vector3();

        ///////DOUBLEJUMP
        if(Gdx.input.justTouched() && m_player.getState() == Player.State.JUMPING ) {
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            if(m_player.getState() != Player.State.DOUBLEJUMP){
                m_player.doublejump();
                m_player.playProutSound();
            }
        }


        if(Gdx.input.justTouched()) {
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);

            //SLIM -- TAKE PILLS
            if(touchPos.y < 480 - 15 && touchPos.y > 480 - (20+0.75*128) && touchPos.x > 15 && touchPos.x < (20+0.75*128)) {
                m_player.slim();
            }

            //PAUSE -- UNPAUSE
            else if(touchPos.y < 20 + 64 && touchPos.y > 20 && touchPos.x > 20 && touchPos.x < 20 + 64) {
                m_player.pause();
            }
            else if(m_player.getPause() == 1) {
                m_player.pause();
            }


            ///////JUMP
            else if ( m_player.getState() != Player.State.JUMPING && m_player.getState() != Player.State.DOUBLEJUMP) {
                m_player.jump();
                m_player.playJumpSound();
            }





        }






    }

    public void setControllable(){

    }

    public void notifyChanges() {
    }

}







