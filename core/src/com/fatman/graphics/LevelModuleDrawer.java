//======================================

// Company : GlitchyMatchyHochy
// Project : GlitchyMatchyHochy
// Author :  Mehdi-Antoine 
// Date :    27/04/2015.
//======================================

package com.fatman.graphics;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fatman.engine.Level;
import com.fatman.engine.LevelModule;

public class LevelModuleDrawer implements Drawer{

    private TileSet m_tile_set;

    private SpriteBatch m_batch;

    private double m_position;
    private int[][] m_scene_data;
    private int[][] m_object_data;
    private double m_width;



    //TO DO :
    //- ajouter les attributs qui vont régir le dessin

    public LevelModuleDrawer(TileSet tileSet, SpriteBatch batch){
        m_tile_set = tileSet;
        m_batch = batch;
        m_scene_data = null;
        m_object_data = null;
    }

    @Override
    public void draw() {
            for(int i = 0; i < m_scene_data.length; ++i){
                for(int j = 0; j < m_scene_data[0].length; ++j){
                    float x = (float)(m_position * m_tile_set.getWidth() +  j * m_tile_set.getWidth());
                    float y = (float)(4*m_tile_set.getHeight()) - (float)(i * m_tile_set.getHeight());
                    m_tile_set.getTile(m_scene_data[i][j]).setPosition(x, y);
                    m_tile_set.getTile(m_scene_data[i][j]).draw(m_batch);
                }
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
    }

}
