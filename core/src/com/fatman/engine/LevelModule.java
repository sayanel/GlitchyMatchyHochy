//======================================

// Company : GlitchyMatchyHochy
// Project : GlitchyMatchyHochy
// Author :  Mehdi-Antoine 
// Date :    23/04/2015.
//======================================

package com.fatman.engine;

public class LevelModule {


    //============================ ATTRIBUTES ============================//

    //TO ADD :
    //DecorPattern
    //ObjectPattern
    //ObjectRandom

    private Pattern m_scene_pattern;
    private Pattern m_object_pattern;

    private double m_position;
    private double m_width;

    //============================= METHODS ==============================//

    public LevelModule(){

    }

    public void setScenePattern(Pattern scenePattern){
        m_scene_pattern = scenePattern;
        m_width = ((double) m_scene_pattern.getWidth());
    }

    public void setObjectPattern(Pattern objectPattern){
        m_object_pattern = objectPattern;
    }

    public void genRandomObjects(){

    }

    public void setPosition(double position){
        m_position = position;
    }

    public double getPosition(){
        return m_position;
    }

    public double getWidth(){
        return m_width;
    }

    public void print(){
        System.out.println("***** LEVEL_MODULE *****");
        System.out.println("- scene_pattern : ");
        m_scene_pattern.print();
        System.out.println("- object_pattern : ");
        m_object_pattern.print();
        System.out.println("- module width : " + m_width);
        System.out.println("- module position : " + m_position);
    }
}
