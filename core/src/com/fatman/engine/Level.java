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
import com.fatman.graphics.LevelModuleDrawer;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Level implements Drawable{

    //============================ ATTRIBUTES ============================//

    private ArrayList<LevelModule> m_level_modules;
    private Array<Pattern> m_level_scene_patterns;
    private Array<Pattern> m_level_object_patterns;

    private String m_pattern_path;

    private Drawer m_drawer;

    //============================= METHODS ==============================//



    //------ CONSTRUCTEURS

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

    //------ CHARGEMENT FICHIERS

    /*  storeScenePatterns()
        fonction qui récupère tous les
        fichiers présents dans le dossier
        assets/scene_patterns/ et les
        enregistre dans la variable
        m_level_scene_patterns
    */
    public void storeScenePatterns() throws IOException{
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

        for(int i = 0; i < patternNb; ++i){
            Pattern p = new Pattern(m_pattern_path + "scene_patterns/" + i + ".pt");
            m_level_scene_patterns.add(p);
        }
    }

    /*  storeObjectPatterns()
        fonction qui récupère tous les
        fichiers présents dans le dossier
        assets/object_patterns/ et les
        enregistre dans la variable
        m_level_object_patterns
    */
    public void storeObjectPatterns() throws  IOException{
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

        for(int i = 0; i < patternNb; ++i){
            Pattern p = new Pattern(m_pattern_path + "object_patterns/" + i + ".pt");
            m_level_object_patterns.add(p);
        }
    }


    //------ GESTION LEVEL MODULES

    /*  addAtEnd()
        ajoute un levelModule à la fin
        de la liste des modules
    */
    public void addAtEnd(LevelModule levelModule){
        m_level_modules.add(levelModule);
    }

    /*  removeFirst()
        supprime le premier levelModule
    */
    public void removeFirst(){
        m_level_modules.remove(0);
    }

    public void print(){
        System.out.println("********** THE_LEVEL **********");

        System.out.println("- pattern_path : " + m_pattern_path);

        System.out.println("- level_modules : ");
        for(LevelModule l : m_level_modules){
            l.print();
        }

        System.out.println("**** stored scene_patterns ****");
        for(Pattern p : m_level_scene_patterns){
            p.print();
        }

        System.out.println("*** stored object_patterns ****");
        for(Pattern p : m_level_object_patterns){
            p.print();
        }
    }

    /*  genLevelModule()
        créée et retourne un levelModule
        à la position spécifiée
        les object et scene patterns sont générés
        aléatoirement
        le drawer est celui du premier module de la liste
    */
    public LevelModule genLevelModule(double position, Drawer levelModuleDrawer){

        //trouver aléatoirement un pattern dans l'array de patterns
        int arraySize = m_level_scene_patterns.size;
        Random r = new Random();
        int randomIndex = r.nextInt(arraySize);

        //trouver aléatoirement un pattern dans l'array de patterns
        arraySize = m_level_object_patterns.size;
        int randomIndex2 = r.nextInt(arraySize);

        LevelModule levelModule = new LevelModule(m_level_scene_patterns.get(randomIndex), m_level_object_patterns.get(randomIndex2), levelModuleDrawer);

        levelModule.setPosition(position);

        return levelModule;
    }

    public LevelModule genLevelModule(Drawer levelModuleDrawer){
        return genLevelModule(0, levelModuleDrawer);
    }

    /*  peek()
        retourne le dernier level
        module de la liste
    */
    public LevelModule peek(){
        return m_level_modules.get(m_level_modules.size() - 1);
    }

    /*  first()
        retourne le premier level
        module de la liste
    */
    public LevelModule first(){
        return m_level_modules.get(0);
    }

    /*  updateList()
        génére un nouveau module
        et le place en fin de liste
        supprime le premier
    */
    public void updateList(){
        double position = peek().getPosition() + peek().getWidth();
        LevelModule l = genLevelModule(position, first().getDrawer());
        addAtEnd(l);
        removeFirst();
        l.notifyChanges();
    }

    public ArrayList<LevelModule> getLevelModules(){
        return m_level_modules;
    }

    public LevelModule getModule(int index){
        return m_level_modules.get(index);
    }

    public void checkPlayerPosition(double playerPosition){
        double modulePosition = getModule(0).getPosition() + getModule(0).getWidth();
        //System.out.println("PlayerPosition : " + playerPosition);

        if(playerPosition > modulePosition){

            System.out.println("Module Changed !");
            updateList();
            notifyChanges();
        }
    }
    //------ GESTION DRAWERS

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
