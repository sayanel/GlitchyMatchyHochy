//======================================
// Company : GlitchyMatchyHochy
// Project : GlitchyMatchyHochy
// Author :  Mehdi-Antoine 
// Date :    30/04/2015.
//======================================

package com.fatman.graphics;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.fatman.engine.GameObject;

public class GameObjectDrawer implements Drawer{

    //============================ ATTRIBUTES ============================//

    private TileSet m_tile_set;

    private SpriteBatch m_batch;

    private Vector2 m_position;

    private int m_object_image_index;

    private int m_tile_width;
    private int m_tile_height;

    //============================= METHODS ==============================//

    public GameObjectDrawer(TileSet tileSet, SpriteBatch batch, int tileWidth, int tileHeight){
        m_tile_set = tileSet;
        m_batch = batch;
        m_tile_width = tileWidth;
        m_tile_height = tileHeight;
    }

    @Override
    public void draw() {
        m_tile_set.getTile(m_object_image_index);
        m_tile_set.getTile(m_object_image_index).setPosition(m_position.x * 64, m_position.y * m_tile_height);
        m_tile_set.getTile(m_object_image_index).draw(m_batch);
    }

    @Override
    public void update(Drawable drawable) {
        update(((GameObject) drawable));
    }

    public void setImageIndex(GameObject.GameObjectType objectType){
        switch(objectType) {
            case ENNEMY:
                m_object_image_index = 0;
                break;
            case BONUS:
                m_object_image_index = 1;
                break;
            default:

        }
    }

    private void update(GameObject gameObject){
        m_position = gameObject.getPosition();
        setImageIndex(gameObject.getObjectType());
    }
}
