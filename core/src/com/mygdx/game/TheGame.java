package com.mygdx.game;

import com.badlogic.gdx.Game;
import screens.Menu;

public class TheGame extends Game {

	@Override
	public void create () {
		setScreen(new Menu(this)); //Au lancement du jeu, l'écran Menu apparaît
	}
}
