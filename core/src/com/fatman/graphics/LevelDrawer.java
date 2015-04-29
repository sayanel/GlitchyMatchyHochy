//======================================
// Company : GlitchyMatchyHochy
// Project : GlitchyMatchyHochy
// Author :  Mehdi-Antoine 
// Date :    27/04/2015.
//======================================

package com.fatman.graphics;

import com.fatman.engine.Level;
import com.fatman.engine.LevelModule;

import java.util.ArrayList;

public class LevelDrawer implements Drawer {

    Level m_level;

    //TO DO :
    //- ajouter les attributs qui vont régir le dessin

    public LevelDrawer(Level level){
        m_level = level;
        level.setDrawer(this);
    }

    @Override
    public void draw() {
        for(LevelModule levelModule : m_level.getLevelModules()){
            levelModule.getDrawer().draw();
        }
    }

    @Override
    public void update(Drawable drawable){
    }
}
