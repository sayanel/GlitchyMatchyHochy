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
import com.fatman.graphics.GameObjectDrawer;
import com.fatman.graphics.LevelModuleDrawer;

import java.util.ArrayList;
import java.util.Random;

public class LevelModule implements Drawable{


    //============================ ATTRIBUTES ============================//

    //TO ADD :
    //DecorPattern
    //ObjectPattern
    //ObjectRandom

    private Pattern m_scene_pattern;
    private Pattern m_object_pattern;
    private ArrayList<GameObject> m_objects;

    private double m_position;
    private double m_width;
    private double m_height;

    private Drawer m_drawer;

    //============================= METHODS ==============================//

    public LevelModule(LevelModuleDrawer levelModuleDrawer){
        setDrawer(levelModuleDrawer);
        m_objects = new ArrayList<GameObject>();
        notifyChanges();
    }

    public LevelModule(Pattern scene_pattern, Pattern object_pattern, Drawer levelModuleDrawer){
        m_scene_pattern = scene_pattern;
        m_object_pattern = object_pattern;
        m_width = m_scene_pattern.getWidth();
        setDrawer(levelModuleDrawer);

        m_objects = new ArrayList<GameObject>();
        notifyChanges();
    }

    public void setScenePattern(Pattern scenePattern){
        m_scene_pattern = scenePattern;
        m_width = ((double) m_scene_pattern.getWidth());
        notifyChanges();
    }

    public void setObjectPattern(Pattern objectPattern){
        m_object_pattern = objectPattern;
        notifyChanges();
    }


    public GameObject genRandomGameObject(){
        Random r = new Random();
        Vector2 vector = new Vector2();

        vector.x = r.nextInt(9) + ((float) m_position);
        vector.y = 1;
        getDrawer().getSpriteBatch();
        GameObjectDrawer gameObjectDrawer = new GameObjectDrawer(getDrawer().getGameObjectSprite(), getDrawer().getSpriteBatch(), getDrawer().getTileWidth(), getDrawer().getTileHeight());
        GameObject g = new GameObject(vector, GameObject.typeFromInt(r.nextInt(2)), gameObjectDrawer);
        g.notifyChanges();
        return g;
    }

    public void genRandomObjects(int maxObjectNumber){
        Random r = new Random();
        int ennemyNumber = r.nextInt(maxObjectNumber);

        for(int i = 0; i < ennemyNumber; ++i){
            m_objects.add(genRandomGameObject());
        }
        notifyChanges();
    }

    public void genRandomObjects(){
        genRandomObjects(3);
    }

    public void setPosition(double position){
        m_position = position;
        notifyChanges();
    }

    public double getPosition(){
        return m_position;
    }

    public double getWidth(){
        return m_width;
    }

    public double getHeight(){
        return m_height;
    }


    public ArrayList<GameObject> getGameObjectList(){
        return m_objects;
    }

    public void print(){

        System.out.println("******** LEVEL_MODULE *********");
        System.out.println("- module width : " + m_width);
        System.out.println("- module position : " + m_position);
        System.out.println("- scene_pattern : ");
        m_scene_pattern.print();
        System.out.println("- object_pattern : ");
        m_object_pattern.print();
        System.out.println("- random_objects : ");
        for(GameObject gameObject : m_objects){
            gameObject.print();
        }


    }

    public Pattern getScenePattern(){
        return m_scene_pattern;
    }

    public Pattern getObjectPattern(){
        return m_object_pattern;
    }


    @Override
    public void setDrawer(Drawer d) {
        m_drawer = d;
    }

    @Override
    public LevelModuleDrawer getDrawer() {
        return ((LevelModuleDrawer) m_drawer);
    }

    @Override
    public void notifyChanges() {
        m_drawer.update(this);
    }


}
