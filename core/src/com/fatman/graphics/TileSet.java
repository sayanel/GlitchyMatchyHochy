//======================================
// Company : GlitchyMatchyHochy
// Project : GlitchyMatchyHochy
// Author :  Mehdi-Antoine 
// Date :    27/04/2015.
//======================================

package com.fatman.graphics;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import java.util.ArrayList;

public class TileSet {

    private Texture m_tileset;
    private ArrayList<Sprite> m_tiles;
    private int m_tile_width;
    private int m_tile_heigth;
    private int m_tile_nb;

    public TileSet(Texture texture, int width, int height){

        m_tileset = texture;
        m_tiles = new ArrayList<Sprite>();
        m_tile_width = width;
        m_tile_heigth = height;
        m_tile_nb = m_tileset.getWidth()/width * m_tileset.getHeight()/height;
        for(int j = 0; j < m_tileset.getHeight()/height; ++j){
            for(int i = 0; i < m_tileset.getWidth()/width; ++i){

                m_tiles.add(new Sprite(texture, i * width, j * height, width, height));
            }
        }
    }

    public Sprite getTile(int index) throws IndexOutOfBoundsException {
        try{
            return m_tiles.get(index);
        }
        catch(IndexOutOfBoundsException e){
            throw  new IndexOutOfBoundsException("***** ERROR : tile requested out of bounds *****");
        }
    }

    public int getWidth(){
        return m_tile_width;
    }

    public int getHeight(){
        return m_tile_heigth;
    }
}
