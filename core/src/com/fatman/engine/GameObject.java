//======================================
// Company : GlitchyMatchyHochy
// Project : GlitchyMatchyHochy
// Author :  Mehdi-Antoine 
// Date :    23/04/2015.
//======================================

package com.fatman.engine;

import com.badlogic.gdx.math.Vector2;
import com.fatman.graphics.Drawable;
import com.fatman.graphics.Drawer;

public class GameObject implements Drawable{

    //============================ ATTRIBUTES ============================//

    public enum GameObjectType{
        ENNEMY, BONUS, VOID
    }

    private  GameObjectType m_object_type;

    private Vector2 m_position;

    private float m_width;
    private float m_padding_width;

    private float m_height;
    private float m_padding_height;

    private Drawer m_drawer;

    //============================= METHODS ==============================//

    static public GameObjectType typeFromInt(int value){
        switch(value) {
            case 0:
                return GameObjectType.ENNEMY;

            case 1:
                return GameObjectType.BONUS;

            default:
                return GameObjectType.ENNEMY;
        }
    }

    public GameObject(Vector2 position, GameObjectType objectType, Drawer drawer){
        m_position = position;
        m_object_type = objectType;

        m_width = 40;
        m_padding_width = 13;

        m_height = 50;
        m_padding_height = 6;

        setDrawer(drawer);
    }

    public void print(){
        System.out.println("--------");
        System.out.println("type : " + m_object_type);
        System.out.println("position : " + m_position);
    }

    public Vector2 getPosition(){
        return m_position;
    }

    public GameObjectType getObjectType(){
        return m_object_type;
    }

    public float getWidth(){
        return m_width;
    }

    public float getPaddingWidth(){
        return m_padding_width;
    }

    public float getHeight(){
        return m_height;
    }

    public float getPaddingHeight(){
        return m_padding_height;
    }

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

}
