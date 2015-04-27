//======================================

// Company : GlitchyMatchyHochy
// Project : GlitchyMatchyHochy
// Author :  Mehdi-Antoine 
// Date :    23/04/2015.
//======================================

package com.fatman.engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import java.io.*;

public class Pattern {


    //============================ ATTRIBUTES ============================//

    private int[][] m_pattern;

    public static int PATTERN_HEIGHT = 5;
    private int PATTERN_WIDTH;


    //============================= METHODS ==============================//

    public Pattern(){
        m_pattern = null;
        PATTERN_WIDTH = 0;
    }

    public Pattern(String filePath){
        try {
            fillPattern(filePath);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void fillPattern(String filePath) throws IOException {

        FileHandle file = Gdx.files.internal(filePath);

        BufferedReader input = file.reader(8);

        try {

            PATTERN_WIDTH = Integer.parseInt(input.readLine()); // convertit la première ligne du fichier en int
            //System.out.println("PATTERN_WIDTH = " + PATTERN_WIDTH); // La première ligne définit la width du pattern

            m_pattern = new int[PATTERN_HEIGHT][PATTERN_WIDTH];

            for(int i = 0; i < PATTERN_HEIGHT; ++i) {
                String s = input.readLine();
                for(int j=0; j < PATTERN_WIDTH; ++j){
                    m_pattern[i][j] = Integer.parseInt(s.split(" ")[j]);
                }
            }
            input.close();

        }
        catch (IOException e) {
            throw new IOException("***** ERROR : error while reading pattern file at : " + filePath + " *****");
        }
    }

    public void writePattern(String filePath) throws IOException{

        if(PATTERN_WIDTH == 0){
            throw new IOException("***** ERROR : empty pattern! *****");
        }

        FileHandle file = Gdx.files.local(filePath);


        try {
            Writer output = file.writer(false);

            output.write(String.valueOf(PATTERN_WIDTH) + "\n");

            for(int i = 0; i < PATTERN_HEIGHT; ++i){
                for(int j = 0; j < PATTERN_WIDTH; ++j){
                    output.write(String.valueOf(m_pattern[i][j]) + " ");
                }
                output.write("\n");
            }

            output.close();
        }
        catch (IOException e) {
            throw new IOException("***** ERROR : unable to write pattern at : " + filePath + " *****");

        }
    }

    public void print(){
        System.out.println("***** PATTERN *****");
        for(int i = 0; i< PATTERN_HEIGHT; ++i){
            for(int j = 0; j < PATTERN_WIDTH; ++j){
                System.out.print(m_pattern[i][j]);
            }
            System.out.print("\n");
        }
    }

    public int getWidth(){
        return PATTERN_WIDTH;
    }




}
