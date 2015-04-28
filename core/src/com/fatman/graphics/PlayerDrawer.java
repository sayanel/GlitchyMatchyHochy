package com.fatman.graphics;

/**
 * Created by maximilien on 28/04/2015.
 */

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.fatman.engine.Player;

public class PlayerDrawer implements Drawer {

    //******************** * PARAMETERS * ********************//

    private TileSet m_tile_set;
    private SpriteBatch m_batch;

    Vector2 m_position;
    int m_height;
    int m_width;


    //******************** * CONSTRUCTORS * ********************//
    public PlayerDrawer(TileSet tileSet, SpriteBatch batch) {
        m_tile_set = tileSet;
        m_batch = batch;
    }

    //******************** * GETTERS * ********************//
    //******************** * SETTERS * ********************//

    //******************** * FUNCTION * ********************//

    @Override
    public void draw() {
        m_batch.begin();
            float x = 0;
            float y = 0;
            m_tile_set.getTile(4).setPosition(x, y);
            m_tile_set.getTile(4).draw(m_batch);
        m_batch.end();
    }

    @Override
    public void update(Drawable drawable) {
        update(((Player) drawable));
    }

    private void update(Player player){
        m_position = player.getPosition();
        m_width = player.getWidth();
        m_height = player.getHeight();
    }
}
