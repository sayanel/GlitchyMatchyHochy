package com.fatman.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector3;
import com.fatman.engine.Player;
import com.fatman.screen.GameInit;


/**
 * Created by maximilien on 29/04/2015.
 */


public class PlayerController implements Controller{

    //******************** * PARAMETERS * ********************//
    private Player m_player;

    private float m_slim_button_x_max;
    private float m_slim_button_y_max;
    private float m_pause_button_x_max;
    private float m_pause_button_y_max;


    //******************** * CONSTRUCTORS * ********************//
    public  PlayerController(Player player){
        m_player = player;

        m_slim_button_x_max = 4*(GameInit.WINDOW_WIDTH/32) + GameInit.WINDOW_WIDTH/64;
        m_slim_button_y_max = GameInit.WINDOW_HEIGHT / 4;

        m_pause_button_x_max = 4*(GameInit.WINDOW_WIDTH/32) - GameInit.WINDOW_WIDTH/64;
        m_pause_button_y_max = GameInit.WINDOW_HEIGHT / 4 - GameInit.WINDOW_HEIGHT/16;

    }


    //******************** * FUNCTIONS * ********************//

    public void eventHandler(){

        /************************************************************************************/
        /*************************************CLAVIER****************************************/
        /************************************************************************************/
        if(m_player.getState() != Player.State.DEAD){
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
                //m_player.hit();
            }

            ///////ACCELERATE
            if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
                //m_player.accelerate();
            }

        }



        /************************************************************************************/
        /************************************SMARTPHONE**************************************/
        /************************************************************************************/

        Vector3 touchPos = new Vector3();

        if(m_player.getState() != Player.State.DEAD){



            if(Gdx.input.justTouched()) {
                touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
                boolean screenTouched = false;

                //SLIM -- TAKE PILLS
                if(touchPos.y < GameInit.WINDOW_HEIGHT && touchPos.y > GameInit.WINDOW_HEIGHT - m_slim_button_y_max && touchPos.x > 0 && touchPos.x < m_slim_button_x_max) {
                    m_player.slim();
                    screenTouched = true;
                }

                //PAUSE -- UNPAUSE
                else if(touchPos.y < m_pause_button_y_max && touchPos.y > 0 && touchPos.x > 0 && touchPos.x < m_pause_button_x_max) {
                    m_player.pause();
                    screenTouched = true;
                }
                else if(m_player.getPause() == 1) {
                    m_player.pause();
                    screenTouched = true;
                }

                ///////JUMP
                else if (m_player.getState() != Player.State.JUMPING && m_player.getState() != Player.State.DOUBLEJUMP && !screenTouched) {
                    m_player.jump();
                    m_player.playJumpSound();
                    screenTouched = true;
                }

                ///////DOUBLEJUMP
                if(m_player.getState() == Player.State.JUMPING && !screenTouched) {
                    if(m_player.getState() != Player.State.DOUBLEJUMP){
                        m_player.doublejump();
                        m_player.playProutSound();
                    }
                }

            }

        }







    }

    public void setControllable(){

    }

    public void notifyChanges() {
    }

}







