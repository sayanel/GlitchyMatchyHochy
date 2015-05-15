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
import com.fatman.screen.TheGame;

public class PlayerInterfaceDrawer implements Drawer{

    //============================ ATTRIBUTES ============================//
    private SpriteBatch m_batch;

    private TileSet m_stomach_sprite;
    private TileSet m_pill_can_sprite;
    private TileSet m_pill_button_sprite;
    private TileSet m_pause_button_sprite;

    private int m_pills_number;
    private int m_weight;

    private int m_score;

    private int m_pill_button_sprite_tile_number;
    private int m_nb_frame_for_pill_button_sprite_tile_number;

    private BitmapFont m_bitmap_font;

    //============================= METHODS ==============================//

    public PlayerInterfaceDrawer(TileSet stomach_sprite, TileSet pill_can_sprite, TileSet pill_button_sprite, TileSet pause_button_sprite, SpriteBatch interface_batch){
        m_batch = interface_batch;

        m_stomach_sprite = stomach_sprite;
        m_pill_can_sprite = pill_can_sprite;
        m_pill_button_sprite = pill_button_sprite;
        m_pause_button_sprite = pause_button_sprite;

        m_bitmap_font = new BitmapFont(Gdx.files.internal("fonts/pills_nb.fnt"));

        m_pills_number = 0;
        m_weight = 0;
        m_pill_button_sprite_tile_number = 0;
        m_nb_frame_for_pill_button_sprite_tile_number = 0;

        m_score = 1;
    }

    @Override
    public void draw() {

        //STOMACH
        m_stomach_sprite.getTile(((int) (m_weight * 0.1))).setOrigin(0, 0);
        m_stomach_sprite.getTile(((int) (m_weight * 0.1))).setScale(0.5f);
        m_stomach_sprite.getTile(((int) (m_weight * 0.1))).setPosition(TheGame.GAME_WIDTH - 128, TheGame.GAME_HEIGHT - 128 - 20);
        m_stomach_sprite.getTile(((int) (m_weight * 0.1))).draw(m_batch);

        //PAUSE BUTTON
        m_pause_button_sprite.getTile(0).setOrigin(0, 0);
        m_pause_button_sprite.getTile(0).setScale(1f);
        m_pause_button_sprite.getTile(0).setPosition(20 , TheGame.GAME_HEIGHT - 64 - 20);
        m_pause_button_sprite.getTile(0).draw(m_batch);

        //PILLS_BUTTON
        m_pill_button_sprite.getTile(m_pill_button_sprite_tile_number).setOrigin(0, 0);
        m_pill_button_sprite.getTile(m_pill_button_sprite_tile_number).setScale(0.75f);
        m_pill_button_sprite.getTile(m_pill_button_sprite_tile_number).setPosition(20,20);
        m_pill_button_sprite.getTile(m_pill_button_sprite_tile_number).draw(m_batch);

        //PILL CAN
        m_pill_can_sprite.getTile(0).setOrigin(0, 0);
        m_pill_can_sprite.getTile(0).setScale(0.5f);
        m_pill_can_sprite.getTile(0).setPosition(20, 128 - 5);
        m_pill_can_sprite.getTile(0).draw(m_batch);

        //PILLS NUMBER
        m_bitmap_font.drawWrapped(m_batch, "x " + m_pills_number, 20 + 32 + 5, 128 + 28 - 5, 0f);

        //SCORE
        m_bitmap_font.drawWrapped(m_batch, "Score: " + m_score, 300, 460, 0f);
    }

    @Override
    public void update(Drawable drawable) {
        update(((Player) drawable));
    }

    private void update(Player player){
        m_pills_number = player.getPillsNumber();
        m_weight = player.getWeight();
        m_pill_button_sprite_tile_number = player.getPillButtonSpriteTileNumber();
        m_nb_frame_for_pill_button_sprite_tile_number = player.getNbFrameForPillButtonSpriteTileNumber();
        m_score = player.getScore();

    }
}
