package uk.jtech.game.jflappybird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MainClass extends ApplicationAdapter {

    private SpriteBatch batch;

    private Background background;


    @Override
    public void create() {
        batch = new SpriteBatch();

        background = new Background();

    }

    @Override
    public void render() {
        update(Gdx.graphics.getDeltaTime());

        Gdx.gl.glClearColor( 1, 0, 0, 1 );
        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT );

        batch.begin();
        draw();
        batch.end();
    }

    private void draw(){
        background.draw( batch );
    }

    private void update(float time){
        background.update( time );
    }

    @Override
    public void dispose() {
        batch.dispose();
        background.dispose();

    }
}
