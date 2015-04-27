//======================================

// Company : GlitchyMatchyHochy
// Project : GlitchyMatchyHochy
// Author :  Mehdi-Antoine 
// Date :    23/04/2015.
//======================================

package com.fatman.engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Random;

//Level

public class Level{

    //============================ ATTRIBUTES ============================//

    private Array<LevelModule> m_level_modules;
    private Array<Pattern> m_level_scene_patterns;
    private Array<Pattern> m_level_object_patterns;

    private String m_pattern_path;

    //============================= METHODS ==============================//

    public Level(String pattern_path){
        m_level_modules = new Array<LevelModule>();
        m_level_scene_patterns = new Array<Pattern>();
        m_level_object_patterns = new Array<Pattern>();

        m_pattern_path = pattern_path;

        try {
            storeScenePatterns();
            storeObjectPatterns();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        addAtEnd(genLevelModule());
        addAtEnd(genLevelModule(m_level_modules.get(0).getWidth()));
        addAtEnd(genLevelModule(m_level_modules.get(1).getPosition() + m_level_modules.get(1).getWidth()));

    }

    public Level(){
        this("patterns/");
    }

    public void storeScenePatterns() throws IOException{

        //Lire le fichier scene.info pour savoir le nombre de patterns à enregistrer
        FileHandle infoFile = Gdx.files.internal(m_pattern_path + "scene_patterns/scene.info");
        BufferedReader info = infoFile.reader(8);

        int patternNb;
        try {
            patternNb = Integer.parseInt(info.readLine());
            info.close();
        }
        catch (IOException e) {
            IOException f = new IOException("***** ERROR : unable to read " + m_pattern_path + "scene_patterns/scene.info *****");
            throw f;
        }

        //Recuperer tous les fichiers et à chaque fois les enregistrer dans l'array
        for(int i = 0; i < patternNb; ++i){
            Pattern p = new Pattern(m_pattern_path + "scene_patterns/" + i + ".pt");
            m_level_scene_patterns.add(p);
        }

    }

    public void storeObjectPatterns() throws  IOException{
        //Lire le fichier scene.info pour savoir le nombre de patterns à enregistrer
        FileHandle infoFile = Gdx.files.internal(m_pattern_path + "object_patterns/object.info");
        BufferedReader info = infoFile.reader(8);

        int patternNb;
        try {
            patternNb = Integer.parseInt(info.readLine());
            info.close();
        }
        catch (IOException e) {
            IOException f = new IOException("***** ERROR : unable to read " + m_pattern_path + "object_patterns/object.info *****");
            throw f;
        }

        //Recuperer tous les fichiers et à chaque fois les enregistrer dans l'array
        for(int i = 0; i < patternNb; ++i){
            Pattern p = new Pattern(m_pattern_path + "object_patterns/" + i + ".pt");
            m_level_object_patterns.add(p);
        }
    }

    public void addAtEnd(LevelModule levelModule){
        m_level_modules.add(levelModule);
    }

    public void removeFirst(){
        m_level_modules.removeIndex(0);
    }

    public void print(){
        System.out.println("***** THE_LEVEL *****");

        System.out.println("- pattern_path : " + m_pattern_path);

        System.out.println("- level_modules : ");
        for(LevelModule l : m_level_modules){
            l.print();
        }

        System.out.println("- stored scene_patterns : ");
        for(Pattern p : m_level_scene_patterns){
            p.print();
        }

        System.out.println("- stored object_patterns : ");
        for(Pattern p : m_level_object_patterns){
            p.print();
        }
    }

    public LevelModule genLevelModule(double position){

        LevelModule levelModule = new LevelModule();

        //trouver aléatoirement un pattern dans l'array de patterns
        int arraySize = m_level_scene_patterns.size;
        Random r = new Random();
        int randomIndex = r.nextInt(arraySize);
        levelModule.setScenePattern(m_level_scene_patterns.get(randomIndex));

        //trouver aléatoirement un pattern dans l'array de patterns
        arraySize = m_level_object_patterns.size;
        randomIndex = r.nextInt(arraySize);
        levelModule.setObjectPattern(m_level_object_patterns.get(randomIndex));

        levelModule.setPosition(position);

        return levelModule;
    }

    public LevelModule genLevelModule(){
        return genLevelModule(0);
    }

    public void updateList(){
        double position = m_level_modules.peek().getPosition() + m_level_modules.peek().getWidth();
        LevelModule l = genLevelModule(position);
        addAtEnd(l);
        removeFirst();
    }





}
