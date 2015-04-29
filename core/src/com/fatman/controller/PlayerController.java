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

    /*enum Keys {
        ACCELERATE, JUMP, HIT
    }

    static Map<Keys, Boolean> keys = new HashMap<PlayerController.Keys, Boolean>();

    static {
        keys.put(Keys.ACCELERATE, false);
        keys.put(Keys.JUMP, false);
        keys.put(Keys.HIT, false);
    };*/



    //******************** * CONSTRUCTORS * ********************//
    public  PlayerController(Player player){
        this.m_player = player;
    }




    //******************** * FUNCTIONS * ********************//

    //CONTROLLER METHODS
    public void eventHandler(){
        //get keys input

        ///////JUMP
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)){
            m_player.jump();
        }

        ///////HIT
        if (Gdx.input.isKeyPressed(Input.Keys.DPAD_LEFT)){
            m_player.hit();
        }

        ///////ACCELERATE
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            m_player.accelerate();
        }

        if(Gdx.input.isTouched()) {
            Vector3 touchPos = new Vector3();
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            if (touchPos.y < Gdx.graphics.getHeight() / 2) {
               m_player.jump();
            }


        }




        //set new parameters

        //notifyChanges
    }

    public void setControllable(){

    }

    public void notifyChanges() {
        //m_player.update();
    }


    //PLAYERCONTROLLER METHODS
/*
        //PRESSED
    public void rightPressed() {
        keys.get(keys.put(Keys.ACCELERATE, true));
    }

    public void jumpPressed() {
        keys.get(keys.put(Keys.JUMP, true));
    }

    public void firePressed() {
        keys.get(keys.put(Keys.HIT, true));
    }

        //RELEASED
    public void rightReleased() {
        keys.get(keys.put(Keys.ACCELERATE, false));
    }

    public void jumpReleased() {
        keys.get(keys.put(Keys.JUMP, false));
    }

    public void fireReleased() {
        keys.get(keys.put(Keys.HIT, false));
    }
*/




}







