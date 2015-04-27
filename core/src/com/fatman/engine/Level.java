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
import com.fatman.graphics.Drawable;
import com.fatman.graphics.Drawer;
import com.fatman.graphics.LevelDrawer;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

//Level

public class Level implements Drawable{

    //============================ ATTRIBUTES ============================//

    private ArrayList<LevelModule> m_level_modules;
    private Array<Pattern> m_level_scene_patterns;
    private Array<Pattern> m_level_object_patterns;

    private String m_pattern_path;

    private Drawer m_drawer;

    //============================= METHODS ==============================//

    public Level(String pattern_path, ArrayList<LevelModule> levelModules, LevelDrawer levelDrawer){
        m_level_modules = levelModules;
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

        setDrawer(levelDrawer);

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
        m_level_modules.remove(0);
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

        //trouver aléatoirement un pattern dans l'array de patterns
        int arraySize = m_level_scene_patterns.size;
        Random r = new Random();
        int randomIndex = r.nextInt(arraySize);

        //trouver aléatoirement un pattern dans l'array de patterns
        arraySize = m_level_object_patterns.size;
        int randomIndex2 = r.nextInt(arraySize);

        LevelModule levelModule = new LevelModule(m_level_scene_patterns.get(randomIndex), m_level_object_patterns.get(randomIndex2), first().getDrawer());

        levelModule.setPosition(position);

        return levelModule;
    }

    public LevelModule genLevelModule(){
        return genLevelModule(0);
    }

    public LevelModule peek(){
        return m_level_modules.get(m_level_modules.size() - 1);
    }

    public LevelModule first(){
        return m_level_modules.get(0);
    }

    public void updateList(){
        double position = peek().getPosition() + peek().getWidth();
        LevelModule l = genLevelModule(position);
        addAtEnd(l);
        removeFirst();
        l.notifyChanges();
    }

    public ArrayList<LevelModule> getLevelModules(){
        return m_level_modules;
    }


    @Override
    public void setDrawer(Drawer d) {
        m_drawer = d;
    }

    @Override
    public Drawer getDrawer() {
        return m_drawer;
    }

    @Override
    public void notifyChanges() {
        m_drawer.update(this);
    }
}
