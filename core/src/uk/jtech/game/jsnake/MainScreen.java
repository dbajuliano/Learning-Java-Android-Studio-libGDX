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

    private boolean hold;

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

        hold = false;

        Gdx.input.setInputProcessor( null );

    }

    @Override
    public void render(float delta) {
        time += delta;

        input();

        batch.setProjectionMatrix( viewport.getCamera().combined );

        Gdx.gl.glClearColor( 0.29f, 0.894f, 0.373f, 1 );
        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT );

        batch.begin();

        batch.draw( bg[(int) time % 2], 0, 0, 1000, 1500 );

        batch.end();

    }

    private void input() {
        if (Gdx.input.isTouched()) {
            hold = true;
        } else if (!Gdx.input.isTouched() && hold) {
            hold = false;
            game.setScreen( new GameScreen( game ) );
        }
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
