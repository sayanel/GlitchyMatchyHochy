//======================================
// Company : GlitchyMatchyHochy
// Project : GlitchyMatchyHochy
// Author :  Mehdi-Antoine 
// Date :    30/04/2015.
//======================================

package com.fatman.engine;

import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;

public class Collision {

    public Collision(){

    }

    //recupere toutes les collisions
    public void collisionHandler(Player player, LevelModule levelModule){
        //get les collisions avec les RandomGameObject
        GameObject.GameObjectType collisionType = getGameObjectCollision(player, levelModule.getGameObjectList());

        switch(collisionType){
            case ENNEMY:
                player.enlarge();
                player.playMiamBurger();
                break;
            case BONUS:

                break;
            default:
        }

        //stock les collisions avec les ObjectPattern
        ArrayList<Integer> listOfObject = getObjectPatternCollision(player, levelModule);

        for(int object : listOfObject) {
            if(object == 1){
                player.addPills(1);
                player.playPillsSound();
            }
        }

    }

    //Vérifie les collisions avec les random Game Objects
    public GameObject.GameObjectType getGameObjectCollision(Player player, ArrayList<GameObject> objectList){

        GameObject.GameObjectType collisionType = GameObject.GameObjectType.VOID;

        for(int i = 0; i < objectList.size(); ++i){
            Rectangle playerBounds = genPlayerRectangle(player);
            Rectangle objectBounds = genObjectBounds(objectList.get(i));
            if(playerBounds.overlaps(objectBounds)){
                collisionType = objectList.get(i).getObjectType();
                objectList.remove(i);
            }
        }

        return collisionType;
    }

    //Vérifie les collisions avec les object Patterns
    public ArrayList<Integer> getObjectPatternCollision(Player player, LevelModule module) {

        //Converti la position du joueur dans le repère du module
        double posPlayerRelx = player.getPosition().x - module.getPosition();
        double posPlayerRely = player.getPosition().y;

        //Liste contenant toutes les valeurs int des cases avec lesquels le joueur est entré en collision
        ArrayList<Integer> listOfObject = new ArrayList<Integer>();


        for(int i = 0; i < module.getObjectPattern().getHeight(); ++i){
            for(int j = 0; j < module.getObjectPattern().getWidth(); ++j){
                Rectangle playerBounds = new Rectangle((float) posPlayerRelx * 64 + player.getPaddingWidth(),
                                                       (float)posPlayerRely * 64,
                                                       player.getWidth(),
                                                       player.getHeight());

                Rectangle objectPatternBound = genTileBounds(module.getObjectPattern().getHeight() - i - 1, j);

                //System.out.println("player graphic pos x:" + playerBounds.getX());
                //System.out.println("player graphic width x:" + playerBounds.getWidth() + "\n");

                if(playerBounds.overlaps(objectPatternBound) && module.getObjectPattern().getData()[i][j] > 0) {
                    listOfObject.add(module.getObjectPattern().getData()[i][j]); //On a touché ce qu'il y avait en (i,j) donc on l'ajoute
                    module.getObjectPattern().setData(i, j, 0); //Puisque on a touché ce qu'il y avait en i,j, on met sa valeur à 0 (=rien)
                }
            }
        }

        return listOfObject;
    }

    //genere le rectangle correspondant au player
    public Rectangle genPlayerRectangle(Player player){
        return new Rectangle(player.getPosition().x * 64 + player.getPaddingWidth(),
                             player.getPosition().y * 64,
                             player.getWidth(),
                             player.getHeight());
    }

    //genere le rectangle correspondant à un random Object
    public Rectangle genObjectBounds(GameObject gameObject){
        return new Rectangle(gameObject.getPosition().x * 64 + gameObject.getPaddingWidth(),
                             gameObject.getPosition().y * 64 + gameObject.getPaddingHeight(),
                             ((float) gameObject.getWidth()),
                             ((float) gameObject.getHeight()));
    }

    //genere le rectangle correspondant à un un pattern object
    public Rectangle genTileBounds(int i, int j) {
        return new Rectangle(j * 64 + 8, i * 64 + 6, 50, 50);
    }

}
