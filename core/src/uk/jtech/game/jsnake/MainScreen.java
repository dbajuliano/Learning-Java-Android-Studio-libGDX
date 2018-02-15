package uk.jtech.game.jsnake;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by jtech on 15/02/2018.
 * Code from the course developing android game with libGDX by Daniel Ciolfi.
 */

public class MainScreen implements Screen {

    private Game game;

    private Texture[] bg;

    private Viewport viewport;
    private SpriteBatch batch;

    private float time;

    public MainScreen(Game game) {
        this.game = game;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();

        viewport = new FillViewport( 1000, 1500 );
        viewport.apply();

        bg = new Texture[2];
        bg[0] = new Texture( "bg0.png" );
        bg[1] = new Texture( "bg1.png" );

        time = 0f;

    }

    @Override
    public void render(float delta) {
        time +=delta;

        batch.setProjectionMatrix( viewport.getCamera().combined );

        Gdx.gl.glClearColor( 0.29f, 0.894f, 0.373f, 1 );
        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT );

        batch.begin();

        batch.draw( bg[(int) time%2], 0, 0, 1000, 1500 );

        batch.end();

    }

    @Override
    public void resize(int width, int height) {

        viewport.update( width, height, true );

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
