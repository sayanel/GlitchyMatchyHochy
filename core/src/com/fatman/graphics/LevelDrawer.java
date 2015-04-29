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

import java.util.ArrayList;

public class LevelDrawer implements Drawer {

    private ArrayList<LevelModule> m_level_modules;

    public LevelDrawer(){
        m_level_modules = null;
    }

    @Override
    public void draw() {
        if(m_level_modules != null){
            for(LevelModule levelModule : m_level_modules){
                levelModule.getDrawer().draw();
            }
        }
    }

    @Override
    public void update(Drawable drawable){
        update(((Level) drawable));
    }

    private void update(Level level){
        m_level_modules = level.getLevelModules();
        for(LevelModule levelModule : m_level_modules){
            levelModule.notifyChanges();
        }
    }
}
