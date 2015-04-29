
package com.fatman.engine;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.fatman.controller.Controllable;
import com.fatman.controller.Controller;
import com.fatman.graphics.Drawable;
import com.fatman.graphics.Drawer;


public class Player implements Drawable, Controllable {


    public enum State {
        IDLE, WALKING, RUNNING, JUMPING, DYING
    }

    //******************** * PARAMETERS * ********************//
    static final float  SIZE = 0.5f; // half a unit
    static final int    TIME_JUMP = 120;
    private int  m_currentTimeJump;

    private double      m_weight;
    private int         m_lives;
    private Vector2     m_position;
    private Vector2     m_velocity;
    private Vector2     m_acceleration;
    private int         m_height;
    private int         m_width;
    private Rectangle   m_bounds = new Rectangle();
    private State       m_state = State.IDLE;
    boolean m_isJumping = false;

    private Drawer m_drawer;


    //******************** * CONSTRUCTORS * ********************//

    public Player(){
        this.m_weight = 100;
        this.m_lives = 1;
        this.m_position = new Vector2(7,2);
        this.m_velocity = new Vector2(1,1);
        this.m_acceleration = new Vector2(1,1);
        this.m_height = 80;
        this.m_width = 60;
        this.m_bounds.height = this.m_height;
        this.m_bounds.width = this.m_width;
        this.m_currentTimeJump = TIME_JUMP;
        m_state = State.RUNNING;
    }

    public Player(Drawer drawer){
        this.m_weight = 100;
        this.m_lives = 1;
        this.m_position = new Vector2(7,2);
        this.m_velocity = new Vector2(1,1);
        this.m_acceleration = new Vector2(1,1);
        this.m_height = 80;
        this.m_width = 60;
        this.m_bounds.height = this.m_height;
        this.m_bounds.width = this.m_width;
        this.m_currentTimeJump = TIME_JUMP;
        m_state = State.RUNNING;

        setDrawer(drawer);
        notifyChanges();
    }

    //******************** * GETTERS * ********************//

    public Rectangle getBounds() {return m_bounds;}
    public int getWidth(){return m_width;}
    public int getHeight(){return m_height;}
    public Vector2 getPosition(){return m_position;}
    public State getState(){return m_state;}
    public int getCurrentTimeJump(){return m_currentTimeJump;}
    public boolean getIsJumping(){ return m_isJumping;}


    //******************** * SETTERS * ********************//
    public void setState(State new_state){
        this.m_state = new_state;
        System.out.println("setState: " + new_state);
        notifyChanges();
    }

    public void setCurrentTimeJump(int newCurrentTimeJump){
        m_currentTimeJump = newCurrentTimeJump;
    }
    public void setIsJumping(boolean newIsJumping){ m_isJumping = newIsJumping;}

    //******************** * FUNCTIONS * ********************//
    public void run(){
        setState(State.RUNNING);
    }

    public void accelerate(){
        m_position.x = m_position.x + 3;
        System.out.println("ACCELERATE \n");
    }

    public void hit(){
        System.out.println("HIT \n");
    }

    public void jump(){
        setIsJumping(true);
        if(getState() != State.JUMPING) {
            setState(State.JUMPING);
            m_currentTimeJump = TIME_JUMP;
        }

        //m_position.y = m_position.y + 1;
    }

    public void enlarge(){
        System.out.println("ENLARGE \n");

    }

    public void slim(){
        System.out.println("SLIM \n");
    }

    public void die(){
        System.out.println("DIE \n");
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
        //player modifications setters

        m_position.x = m_position.x + 1; //running


        if(m_isJumping){

            if (m_currentTimeJump > 0){
                m_currentTimeJump--;
            }
            else{
                setIsJumping(false);
                this.m_currentTimeJump = TIME_JUMP;
                setState(State.RUNNING);
            }

        }

        this.notifyChanges();
    }





}