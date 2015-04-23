//======================================

// Company : GlitchyMatchyHochy
// Project : GlitchyMatchyHochy
// Author :  Mehdi-Antoine 
// Date :    23/04/2015.
//======================================

package com.fatman.engine;

public class Pattern {


    //============================ ATTRIBUTES ============================//

    private int[][] m_pattern;

    public static int PATTERN_HEIGHT = 5;
    public static int PATTERN_WIDTH = 10;


    //============================= METHODS ==============================//

    public Pattern(){
        m_pattern = new int[PATTERN_HEIGHT][PATTERN_WIDTH];
        for(int i = 0; i< PATTERN_HEIGHT; ++i){
            for(int j = 0; j < PATTERN_WIDTH; ++j){
                m_pattern[i][j] = j;
            }
            System.out.print("\n");
        }
    }

    public void fillPattern(String filePath){

    }

    public void print(){
        for(int i = 0; i< PATTERN_HEIGHT; ++i){
            for(int j = 0; j < PATTERN_WIDTH; ++j){
                System.out.print(m_pattern[i][j]);
            }
            System.out.print("\n");
        }
    }




}
