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

    private double m_width;
    private double m_heigth;

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
        m_width = 1;
        m_heigth = 1;
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

    public double getWidth(){
        return m_width;
    }

    public double getHeight(){
        return m_heigth;
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
