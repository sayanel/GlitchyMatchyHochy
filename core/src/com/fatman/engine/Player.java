
package com.fatman.engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.fatman.controller.Controllable;
import com.fatman.controller.Controller;
import com.fatman.graphics.Drawable;
import com.fatman.graphics.Drawer;


public class Player implements Drawable, Controllable {


    public enum State {
        IDLE, WALKING, RUNNING, JUMPING, DEAD
    }

    static final float  JUMP_DELTA = 0.6f;
    static final float  JUMP_PENALITY = 0.3f;

    static final int    MAX_WEIGHT = 100;
    static final int    PILLS_REQUIRED = 10;

    static final float  RUN_BEGIN = 5f;
    static final float  INITIAL_RUN_DELTA = 0.00005f;

    //******************** * PARAMETERS * ********************//

    private State       m_state;

    private int         m_weight;
    private int         m_pills_number;

    private float       m_run_delta;

    private float       m_jump_height;

    private float       m_elapsed_time;

    private Vector2     m_position;
    private Vector2     m_velocity;
    private Vector2     m_acceleration;
    private int         m_height;
    private int         m_width;

    private Rectangle   m_bounds = new Rectangle();

    private Drawer m_drawer;


    //******************** * CONSTRUCTORS * ********************//

    public Player(Drawer drawer){
        this.m_weight = 0;
        this.m_pills_number = 0;
        this.m_position = new Vector2(10,1);
        this.m_velocity = new Vector2(RUN_BEGIN,0);
        this.m_acceleration = new Vector2(0.05f,0);
        this.m_height = 2;

        m_jump_height = 15f;

        m_run_delta = INITIAL_RUN_DELTA;

        m_elapsed_time = 0;

        this.m_bounds.height = this.m_height;
        this.m_bounds.width = this.m_width;
        m_state = State.RUNNING;

        setDrawer(drawer);
        notifyChanges();
    }

    //******************** * GETTERS * ********************//

    public Rectangle getBounds() {return m_bounds;}
    public int getWidth(){return m_width;}
    public int getHeight(){return m_height;}
    public Vector2 getPosition(){return m_position;}
    public Vector2 getVelocity(){return m_velocity;}
    public State getState(){return m_state;}


    //******************** * SETTERS * ********************//
    public void setState(State new_state){
        this.m_state = new_state;
        notifyChanges();
    }

    //******************** * FUNCTIONS * ********************//
    public void run(){
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

    public void updateJump(){

        m_velocity.y -= JUMP_DELTA;

        float temp_position = m_position.y + m_velocity.y * Gdx.graphics.getDeltaTime();

        if(temp_position < 1 && m_velocity.y <= 0){
            setState(State.RUNNING);
            m_velocity.y = 0;
            m_position.y = 1;
        }
    }

    public void enlarge(){
        m_weight += 10;

        m_run_delta *= 0.5;

        m_jump_height -= JUMP_PENALITY;
        if(m_weight > MAX_WEIGHT){
            die();
        }
    }

    public void slim(){
        if(m_pills_number >= PILLS_REQUIRED){

            if(m_weight > 0){
                removePills(PILLS_REQUIRED);
                m_run_delta *= 2;
                m_weight -= 10;
                m_jump_height += JUMP_PENALITY;
            }
            else{
                m_weight = 0;
            }
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

    //DRAWABLE METHODS
    @Override
    public void setDrawer(Drawer d) {
        m_drawer = d;
    }

    @Override
    public Drawer getDrawer() {
        return m_drawer;
    }

    @Override
    public void notifyChanges() {
        m_drawer.update(this);
    }




    //CONTROLLABLE METHODS
    public void update(Controller controller){

        m_elapsed_time += Gdx.graphics.getDeltaTime();
        //player modifications setters

        if(m_state == State.JUMPING){
            updateJump();
        }
        if(m_state == State.RUNNING){
            updateRun();
        }
        if(m_state != State.DEAD){
            m_position.x += m_velocity.x * Gdx.graphics.getDeltaTime();
        }

        m_position.y += m_velocity.y * Gdx.graphics.getDeltaTime();

        this.notifyChanges();
    }





}