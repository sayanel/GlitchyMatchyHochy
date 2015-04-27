package com.fatman.screen;

import com.badlogic.gdx.ApplicationAdapter;
import com.fatman.engine.Level;
import com.fatman.engine.Pattern;

import java.io.IOException;

public class TheGame extends ApplicationAdapter {

	@Override
	public void create () {

		Level level = new Level();
		level.print();
	}

	@Override
	public void render () {

	}
}
