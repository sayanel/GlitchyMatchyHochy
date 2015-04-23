//======================================

// Company : GlitchyMatchyHochy
// Project : GlitchyMatchyHochy
// Author :  Mehdi-Antoine 
// Date :    23/04/2015.
//======================================

package com.fatman.engine;

import com.badlogic.gdx.utils.Array;

public class Level{

    //============================ ATTRIBUTES ============================//

    private Array<LevelModule> m_level_modules;


    //============================= METHODS ==============================//

    public Level(){
        m_level_modules = new Array<LevelModule>();
    }

    public void addAtEnd(LevelModule levelModule){
        m_level_modules.add(levelModule);
    }

    public void removeFirst(){
        m_level_modules.removeIndex(0);
    }

    public void printLevel(){

    }

    public LevelModule genLevelModule(){
        return new LevelModule();
    }



}
