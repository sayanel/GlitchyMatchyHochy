//======================================

// Company : GlitchyMatchyHochy
// Project : GlitchyMatchyHochy
// Author :  Mehdi-Antoine 
// Date :    27/04/2015.
//======================================

package com.fatman.graphics;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fatman.engine.LevelModule;

public class LevelModuleDrawer implements Drawer{

    LevelModule m_level_module;
    TileSet m_tile_set;
    SpriteBatch m_batch;

    //TO DO :
    //- ajouter les attributs qui vont régir le dessin

    public LevelModuleDrawer(LevelModule levelModule, TileSet tileSet, SpriteBatch batch){
        m_level_module = levelModule;
        levelModule.setDrawer(this);

        m_tile_set = tileSet;
        m_batch = batch;
    }

    @Override
    public void draw() {

    }

    @Override
    public void update(Drawable drawable) {

    }

}
