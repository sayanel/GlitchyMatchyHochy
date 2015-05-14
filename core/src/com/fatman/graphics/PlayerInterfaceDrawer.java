//======================================
// Company : GlitchyMatchyHochy
// Project : GlitchyMatchyHochy
// Author :  Mehdi-Antoine 
// Date :    14/05/2015.
//======================================

package com.fatman.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fatman.engine.Player;

public class PlayerInterfaceDrawer implements Drawer{

    //============================ ATTRIBUTES ============================//
    private SpriteBatch m_batch;

    private TileSet m_interface_sprite;

    private int m_pills_number;
    private int m_weight;

    private BitmapFont m_bitmap_font;

    //============================= METHODS ==============================//

    public PlayerInterfaceDrawer(TileSet interface_sprite, SpriteBatch interface_batch){
        m_batch = interface_batch;
        m_interface_sprite = interface_sprite;
        m_bitmap_font = new BitmapFont(Gdx.files.internal("fonts/pills_nb.fnt"));
        m_pills_number = 0;
        m_weight = 0;
    }

    @Override
    public void draw() {
        m_interface_sprite.getTile(((int) (m_weight * 0.1))).setOrigin(0, 0);
        m_interface_sprite.getTile(((int) (m_weight * 0.1))).setScale(0.5f);
        m_interface_sprite.getTile(((int) (m_weight * 0.1))).setPosition(690,340);
        m_interface_sprite.getTile(((int) (m_weight * 0.1))).draw(m_batch);

        m_bitmap_font.drawWrapped(m_batch, "x " + m_pills_number, 20f, 400f, 0f);
    }

    @Override
    public void update(Drawable drawable) {
        update(((Player) drawable));
    }

    private void update(Player player){
        m_pills_number = player.getPillsNumber();
        m_weight = player.getWeight();
    }
}
