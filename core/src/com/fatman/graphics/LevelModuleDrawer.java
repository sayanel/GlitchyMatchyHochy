//======================================

// Company : GlitchyMatchyHochy
// Project : GlitchyMatchyHochy
// Author :  Mehdi-Antoine 
// Date :    27/04/2015.
//======================================

package com.fatman.graphics;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fatman.engine.GameObject;
import com.fatman.engine.LevelModule;

import java.util.ArrayList;

public class LevelModuleDrawer implements Drawer{

    //============================ ATTRIBUTES ============================//

    private TileSet m_tile_set;
    private TileSet m_tile_set_object;
    private TileSet m_game_object_sprite;

    private SpriteBatch m_batch;

    private double m_position;
    private int[][] m_scene_data;
    private int[][] m_object_data;
    private double m_width;

    private ArrayList<GameObject> m_game_objects;

    //============================= METHODS ==============================//

    public LevelModuleDrawer(TileSet tileSet, TileSet tileSetObject, TileSet gameObjectSprite, SpriteBatch batch){
        m_tile_set = tileSet;
        m_tile_set_object = tileSetObject;
        m_game_object_sprite = gameObjectSprite;
        m_batch = batch;
        m_scene_data = null;
        m_object_data = null;
        m_game_objects = null;
    }

    public TileSet getGameObjectSprite(){
        return m_game_object_sprite;
    }

    public SpriteBatch getSpriteBatch(){
        return m_batch;
    }

    public int getTileWidth(){
        return m_tile_set.getWidth();
    }

    public int getTileHeight(){
        return m_tile_set.getHeight();
    }

    @Override
    public void draw() {

        for(int i = 0; i < m_scene_data.length; ++i){
            for(int j = 0; j < m_scene_data[0].length; ++j){
                if(m_scene_data[i][j] > 0){
                    float x = (float)(m_position * m_tile_set.getWidth() +  j * m_tile_set.getWidth());
                    float y = (float)(4*m_tile_set.getHeight()) - (float)(i * m_tile_set.getHeight());
                    m_tile_set.getTile(m_scene_data[i][j]).setPosition(x, y);
                    m_tile_set.getTile(m_scene_data[i][j]).draw(m_batch);
                }
            }
        }

        for(int k = 0; k < m_object_data.length; ++k){
            for(int l = 0; l < m_object_data[0].length; ++l){
                if(m_object_data[k][l] > 0){
                    float x = (float)(m_position * m_tile_set_object.getWidth() +  l * m_tile_set_object.getWidth());
                    float y = (float)(4*m_tile_set_object.getHeight()) - (float)(k * m_tile_set_object.getHeight());
                    m_tile_set_object.getTile(m_object_data[k][l]).setPosition(x, y);
                    m_tile_set_object.getTile(m_object_data[k][l]).draw(m_batch);
                }
            }
        }

        for(GameObject gameObject : m_game_objects){
            gameObject.getDrawer().draw();
        }

    }

    @Override
    public void update(Drawable drawable) {
        update(((LevelModule) drawable));
    }

    private void update(LevelModule levelModule){
        m_position = levelModule.getPosition();
        m_scene_data = levelModule.getScenePattern().getData();
        m_object_data = levelModule.getObjectPattern().getData();
        m_width = levelModule.getWidth();
        m_game_objects = levelModule.getGameObjectList();
    }

}
