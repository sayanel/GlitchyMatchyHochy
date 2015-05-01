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

    public void collisionHandler(Player player, LevelModule levelModule){
        GameObject.GameObjectType collisionType = getModuleCollision(player, levelModule);

        switch(collisionType){
            case ENNEMY:
//                System.out.println("COLLISION ENNEMY");
                break;
            case BONUS:
//                System.out.println("COLLISION BONUS");
                break;
            default:
        }

    }

    public GameObject.GameObjectType getModuleCollision(Player player, LevelModule levelModule){

        GameObject.GameObjectType collisionType = GameObject.GameObjectType.VOID;

        ArrayList<GameObject> objectList = levelModule.getGameObjectList();

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

    public Rectangle genPlayerRectangle(Player player){
        return new Rectangle(player.getPosition().x, player.getPosition().y, player.getWidth(),player.getHeight());
    }

    public Rectangle genObjectBounds(GameObject gameObject){
        return new Rectangle(gameObject.getPosition().x, gameObject.getPosition().y, ((float) gameObject.getWidth()), ((float) gameObject.getHeight()));
    }

}
