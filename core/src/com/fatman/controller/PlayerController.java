package com.fatman.controller;

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
        //get keys input

        //set new parameters

        //notifyChanges
    }

    public void setControllable(){

    }

    public void notifyChanges() {
        //m_player.update();
    }





}







