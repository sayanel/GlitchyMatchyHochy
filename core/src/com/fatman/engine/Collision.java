//======================================
// Company : GlitchyMatchyHochy
// Project : GlitchyMatchyHochy
// Author :  Mehdi-Antoine 
// Date :    30/04/2015.
//======================================

package com.fatman.engine;

import com.badlogic.gdx.math.Rectangle;
import com.fatman.graphics.LevelModuleDrawer;

import java.util.ArrayList;

public class Collision {

    public Collision(){

    }

    public void collisionHandler(Player player, LevelModule levelModule){
        GameObject.GameObjectType collisionType = getGameObjectCollision(player, levelModule.getGameObjectList());

        switch(collisionType){
            case ENNEMY:
                player.enlarge();
                break;
            case BONUS:

                break;
            default:
        }

        ArrayList<Integer> listOfObject = getObjectPatternCollision(player, levelModule);

        for(int object : listOfObject) {
            if(object == 1){
                player.addPills(1);
            }
        }

    }

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

    public ArrayList<Integer> getObjectPatternCollision(Player player, LevelModule module) {

        //Converti la position du joueur dans le repère du module
        double posPlayerRelx = player.getPosition().x - module.getPosition();
        double posPlayerRely = player.getPosition().y;

        //Liste contenant toutes les valeurs int des cases avec lesquels le joueur est entré en collision
        ArrayList<Integer> listOfObject = new ArrayList<Integer>();


        for(int i = 0; i < module.getObjectPattern().getHeight(); ++i){
            for(int j = 0; j < module.getObjectPattern().getWidth(); ++j){
                Rectangle playerBounds = new Rectangle((float) posPlayerRelx, (float)posPlayerRely, player.getWidth(), player.getHeight());
                Rectangle objectPatternBound = genTileBounds(module.getObjectPattern().getHeight() - i - 1, j);

                if(playerBounds.overlaps(objectPatternBound) && module.getObjectPattern().getData()[i][j] > 0) {
                    listOfObject.add(module.getObjectPattern().getData()[i][j]); //On a touché ce qu'il y avait en (i,j) donc on l'ajoute
                    module.getObjectPattern().setData(i, j, 0); //Puisque on a touché ce qu'il y avait en i,j, on met sa valeur à 0 (=rien)
                }
            }
        }

        return listOfObject;
    }


    public Rectangle genPlayerRectangle(Player player){
        return new Rectangle(player.getPosition().x, player.getPosition().y, player.getWidth(),player.getHeight());
    }

    public Rectangle genObjectBounds(GameObject gameObject){
        return new Rectangle(gameObject.getPosition().x, gameObject.getPosition().y, ((float) gameObject.getWidth()), ((float) gameObject.getHeight()));
    }

    public Rectangle genTileBounds(int i, int j) {
        return new Rectangle(j+0.25f, i + 0.15f, 0.7f, 0.5f);
    }

}
