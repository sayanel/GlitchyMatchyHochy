
package com.fatman.engine;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.fatman.graphics.Drawable;
import com.fatman.graphics.Drawer;


public class Player implements Drawable {


    public enum State {
        IDLE, WALKING, RUNNING, JUMPING, DYING
    }

    //******************** * PARAMETERS * ********************//
    static final float SIZE = 0.5f; // half a unit
    double      m_weight;
    int         m_lives;
    Vector2     m_position;
    Vector2     m_velocity;
    Vector2     m_acceleration;
    int         m_height;
    int         m_width;
    Rectangle   m_bounds = new Rectangle();
    State       m_state = State.IDLE;
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

        setDrawer(drawer);
        notifyChanges();
    }

    //******************** * GETTERS * ********************//

    public Rectangle getBounds() {return m_bounds;}
    public int getWidth(){return m_width;}
    public int getHeight(){return m_height;}
    public Vector2 getPosition(){return m_position;}
    public State getState(){return m_state;}

    //******************** * SETTERS * ********************//



    //******************** * FUNCTION * ********************//
    private void run(){}
    private void hit(){}
    private void jump(){}
    private void enlarge(){}
    private void slim(){}
    private void die(){}

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

    public void increaseX(){
        m_position.x = m_position.x + 1;
    }




}