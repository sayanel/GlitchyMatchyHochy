
package com.fatman.engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.fatman.controller.Controllable;
import com.fatman.controller.Controller;
import com.fatman.graphics.Drawable;
import com.fatman.graphics.Drawer;


public class Player implements Drawable, Controllable {


    public enum State {
        IDLE, WALKING, RUNNING, JUMPING, DOUBLEJUMP, DEAD
    }

    static final float  FAT_PADDING = 22;
    static final float  MEDIUM_PADDING = 26;
    static final float  SLIM_PADDING = 29;

    static final float  FAT_WIDTH = 78;
    static final float  MEDIUM_WIDTH = 70;
    static final float  SLIM_WIDTH = 65;

    static final float  JUMP_DELTA = 0.5f;
    static final float  DOUBLEJUMP_DELTA = 0.25f;
    static final float  JUMP_PENALITY = 0.3f;
    static final float  DOUBLEJUMP_PENALITY = 0.5f;

    static final int    MAX_WEIGHT = 10;
    static final int    PILLS_REQUIRED = 10;

    static final float  RUN_BEGIN = 5f;
    static final float  INITIAL_RUN_DELTA = 0.00005f;



    //******************** * PARAMETERS * ********************//

    private State       m_state;

    private int m_fat_state;

    private int         m_weight;
    private int         m_pills_number;

    private float       m_run_delta;

    private float       m_jump_height;
    private float       m_doublejump_height;

    private float       m_elapsed_time;

    private Vector2     m_position;
    private Vector2     m_velocity;
    private Vector2     m_acceleration;
    private float       m_height;
    private float       m_width;
    private float       m_padding;

    private Rectangle   m_bounds = new Rectangle();

    private Drawer m_drawer;

        /////////////////SOUND
    private Sound m_prout_sound1;
    private Sound m_prout_sound2;
    private Sound m_pills_sound;
    private Sound m_miam_sound;
    private Sound m_miam_burger_sound;
    private Sound m_miam_pills_sound1;
    private Sound m_miam_pills_sound2;
    private Sound m_jump_sound1;
    private Sound m_jump_sound2;
    private Sound m_jump_sound3;

    /////////////////////INTERFACE
    private Drawer m_interface_drawer;
    private int m_pill_button_sprite_tile_number;
    private int m_nb_frame_for_pill_button_sprite_tile_number;
    private float m_score;

    private int m_pause;

    //******************** * CONSTRUCTORS * ********************//

    public Player(Drawer drawer, Drawer interface_drawer){
        this.m_weight = 0;
        this.m_pills_number = 0;
        this.m_position = new Vector2(10,1);
        this.m_velocity = new Vector2(RUN_BEGIN,0);
        this.m_acceleration = new Vector2(0.05f,0);

        this.m_height = 128;
        this.m_width = SLIM_WIDTH;
        this.m_padding = SLIM_PADDING;

        m_fat_state = 0;
        m_jump_height = 15f;
        m_doublejump_height = 9f;

        m_score = 1.0f;

        m_run_delta = INITIAL_RUN_DELTA;

        m_elapsed_time = 0;

        this.m_bounds.height = this.m_height;
        this.m_bounds.width = this.m_width;
        m_state = State.RUNNING;

        m_pill_button_sprite_tile_number = 0;
        m_nb_frame_for_pill_button_sprite_tile_number = 0;

        m_pause = 0;

        ///////////SOUND
        m_prout_sound1 = Gdx.audio.newSound(Gdx.files.internal("audio/PROUT01.wav"));
        m_prout_sound2 = Gdx.audio.newSound(Gdx.files.internal("audio/PROUT02.wav"));
        m_pills_sound = Gdx.audio.newSound(Gdx.files.internal("audio/Blip_Select.wav"));
        m_miam_sound = Gdx.audio.newSound(Gdx.files.internal("audio/Randomize.wav"));
        m_miam_burger_sound = Gdx.audio.newSound(Gdx.files.internal("audio/EAT_BURGER01.wav"));
        m_miam_pills_sound1 = Gdx.audio.newSound(Gdx.files.internal("audio/EAT_PILL01.wav"));
        m_miam_pills_sound2 = Gdx.audio.newSound(Gdx.files.internal("audio/EAT_PILL02.wav"));
        m_jump_sound1 = Gdx.audio.newSound(Gdx.files.internal("audio/SAUT01.wav"));
        m_jump_sound2 = Gdx.audio.newSound(Gdx.files.internal("audio/SAUT02.wav"));
        m_jump_sound3 = Gdx.audio.newSound(Gdx.files.internal("audio/SAUT03.wav"));


        setDrawer(drawer);
        setInterfaceDrawer(interface_drawer);
        notifyChanges();
    }

    //******************** * GETTERS * ********************//

    public Rectangle getBounds() {return m_bounds;}
    public float getWidth(){return m_width;}
    public float getPaddingWidth(){return m_padding;}
    public float getHeight(){return m_height;}
    public Vector2 getPosition(){return m_position;}
    public Vector2 getAcceleration(){return m_acceleration;}
    public Vector2 getVelocity(){return m_velocity;}
    public State getState(){return m_state;}
    public float getRun_delta(){ return m_run_delta; }
    public int getFatState() { return m_fat_state; }
    public int getPillButtonSpriteTileNumber(){ return m_pill_button_sprite_tile_number; }
    public int getNbFrameForPillButtonSpriteTileNumber(){ return m_nb_frame_for_pill_button_sprite_tile_number; }
    public int getScore(){ return (int)m_score; }
    public int getPause(){ return m_pause;}


    //******************** * SETTERS * ********************//
    public void setState(State new_state){
        this.m_state = new_state;
        notifyChanges();
    }

    public void setPillButtonSpriteTileNumber(int new_value){  m_pill_button_sprite_tile_number = new_value; }
    public void setPause(){
        if(m_pause == 0){ m_pause = 1; System.out.println("PAUSE");}
        else{ m_pause = 0; System.out.println("UNPAUSE");}
    }

    //******************** * FUNCTIONS * ********************//

    public void playProutSound(){
        int r = 1 + (int)(Math.random()*2);
        if(r==1) m_prout_sound1.play(1.0f);
        else m_prout_sound2.play(1.0f);
    }
    public void playPillsSound(){
        //m_pills_sound.play(1.0f);
    }
    public void playerMiamSound(){
        m_miam_sound.play(1.0f);
    }

    public void playMiamPillsSound(){
        int r = 1 + (int)(Math.random()*2);
        if(r==1)m_miam_pills_sound1.play(1.0f);
        else m_miam_pills_sound2.play(1.0f);
    }

    public void playJumpSound(){
        int r = 1 + (int)(Math.random()*3);
        if(r==1) m_jump_sound1.play(1.0f);
        else if(r==2)m_jump_sound2.play(1.0f);
        else m_jump_sound3.play(1.0f);
    }

    public void playMiamBurger(){
        m_miam_burger_sound.play(1.0f);
    }

    public void run() {
        setState(State.RUNNING);
    }

    public void updateRun(){
        m_velocity.x += m_elapsed_time * m_run_delta;
    }

    public void accelerate(){
        m_position.x += m_acceleration.x;
    }

    public void hit(){
    }

    public void jump(){
        if(getState() != State.JUMPING) {
            setState(State.JUMPING);
            m_velocity.y = m_jump_height;
        }
    }

    public void doublejump(){
        if(getState() != State.DOUBLEJUMP) {
            setState(State.DOUBLEJUMP);
            m_velocity.y = m_doublejump_height;
        }
    }

    public void updateJump(){

        m_velocity.y -= JUMP_DELTA;

        float temp_position = m_position.y + m_velocity.y * Gdx.graphics.getDeltaTime();

        if(temp_position < 1 && m_velocity.y <= 0){
            setState(State.RUNNING);
            m_velocity.y = 0;
            m_position.y = 1;
        }
    }

    public void updateDoubleJump(){

        m_velocity.y -= DOUBLEJUMP_DELTA;

        float temp_position = m_position.y + m_velocity.y * Gdx.graphics.getDeltaTime();

        if(temp_position < 1 && m_velocity.y <= 0){
            setState(State.RUNNING);
            m_velocity.y = 0;
            m_position.y = 1;
        }
    }


    public void changeFatState(){
        if(m_weight <  MAX_WEIGHT/3){
            m_fat_state = 0;

            m_width = SLIM_WIDTH;
            m_padding = SLIM_PADDING;
        }
        else if(m_weight  < MAX_WEIGHT-MAX_WEIGHT/3){
            m_fat_state = 1;

            m_width = MEDIUM_WIDTH;
            m_padding = MEDIUM_PADDING;
        }
        else{
            m_fat_state = 2;

            m_width = FAT_WIDTH;
            m_padding = FAT_PADDING;
        }
    }

    public void enlarge(){
        m_weight += 10;

        m_run_delta *= 0.5;

        changeFatState();

        m_jump_height -= JUMP_PENALITY;
        m_doublejump_height -= DOUBLEJUMP_PENALITY;
        if(m_weight > MAX_WEIGHT){
            die();
        }
    }

    public void slim(){

        changeFatState();

        if(m_pills_number >= PILLS_REQUIRED){

            if(m_weight > 0){
                removePills(PILLS_REQUIRED);
                m_run_delta *= 2;
                m_weight -= 10;
                m_jump_height += JUMP_PENALITY;
                m_doublejump_height += DOUBLEJUMP_PENALITY;
                playMiamPillsSound();
            }
            else{
                m_weight = 0;
            }

            m_pill_button_sprite_tile_number = 1;
            m_nb_frame_for_pill_button_sprite_tile_number = 15;
        }
    }

    public int getWeight(){
        return m_weight;
    }

    public void die(){
        setState(State.DEAD);
    }

    public void addPills(int pillsNb){
        m_pills_number += pillsNb;
    }

    public void removePills(int pillsNb){
        m_pills_number -= pillsNb;
    }

    public int getPillsNumber(){
        return m_pills_number;
    }

    public void pause(){

        setPause();
    }



    //DRAWABLE METHODS
    @Override
    public void setDrawer(Drawer d) {
        m_drawer = d;
    }

    public void setInterfaceDrawer(Drawer d) {
        m_interface_drawer = d;
    }

    @Override
    public Drawer getDrawer() {
        return m_drawer;
    }
    public Drawer getInterfaceDrawer() {
        return m_interface_drawer;
    }

    @Override
    public void notifyChanges() {
        m_drawer.update(this);
        m_interface_drawer.update(this);
    }




    //CONTROLLABLE METHODS
    public void update(Controller controller){

        m_elapsed_time += Gdx.graphics.getDeltaTime();
        //player modifications setters

        if(m_state == State.JUMPING){
            updateJump();
        }
        if(m_state == State.DOUBLEJUMP){
            updateDoubleJump();
        }
        if(m_state == State.RUNNING){
            updateRun();
        }
        if(m_state != State.DEAD){
            m_position.x += m_velocity.x * Gdx.graphics.getDeltaTime();
        }

        m_position.y += m_velocity.y * Gdx.graphics.getDeltaTime();

        //time duration pour l'effet d'appuie sur le bouton
        if(m_nb_frame_for_pill_button_sprite_tile_number > 0) m_nb_frame_for_pill_button_sprite_tile_number--;
        if(m_nb_frame_for_pill_button_sprite_tile_number <= 0) m_pill_button_sprite_tile_number = 0;

        //SCORE
        if(m_state != State.DEAD) m_score += 0.05;


        this.notifyChanges();
    }





}