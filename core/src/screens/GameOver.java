//======================================
// Company : GlitchyMatchyHochy
// Project : GlitchyMatchyHochy
// Author :  Mehdi-Antoine 
// Date :    16/04/2015.
//======================================

package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL30;
import com.mygdx.game.TheGame;

public class GameOver implements Screen{

    TheGame game;

    public GameOver(TheGame g){
        this.game = g;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0.2f, 0.4f, 1); // Notre jolie couleur rose :p
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
        Gdx.app.log("GameOver", "Dans le GameOver Rose"); // On affiche dans la log que l'on est bien dans notre menu
        if(Gdx.input.isKeyPressed(Input.Keys.SPACE)){
            game.setScreen(new Menu(game));
        }
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void show() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
    }

}
