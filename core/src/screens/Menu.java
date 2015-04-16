//======================================
// Company : GlitchyMatchyHochy
// Project : GlitchyMatchyHochy
// Author :  Mehdi-Antoine 
// Date :    16/04/2015.
//======================================

package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL30;
import com.mygdx.game.TheGame;

public class Menu implements Screen{

    TheGame game; //Instance de TheGame pour accéder à ses méthodes

    public Menu(TheGame g){
        game = g;
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(1, 1, 1, 1); // Notre jolie couleur rose :p
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
        Gdx.app.log("Menu", "Dans le Menu Jaune"); // On affiche dans la log que l'on est bien dans notre menu
        if(Gdx.input.isKeyPressed(Keys.ENTER)){
            this.game.setScreen(new GameOver(this.game));
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
