package uk.jtech.game.jflappybird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import static uk.jtech.game.jflappybird.Constants.birdinix;
import static uk.jtech.game.jflappybird.Constants.screeny;

public class MainClass extends ApplicationAdapter {

    private SpriteBatch batch;

    private Background background;

    private Bird bird;


    @Override
    public void create() {
        batch = new SpriteBatch();

        background = new Background();

        bird = new Bird( birdinix, screeny / 2 );

    }

    @Override
    public void render() {
        input();

        update(Gdx.graphics.getDeltaTime());

        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT );

        batch.begin();
        draw();
        batch.end();
    }

    private void draw(){
        background.draw( batch );

        bird.draw( batch );
    }

    private void update(float time){
        background.update( time );

        bird.update( time );
    }

    private void input() {
        if (Gdx.input.justTouched()) {
            bird.impulse();
        }
    }

    @Override
    public void dispose() {
        batch.dispose();
        background.dispose();
        bird.dispose();

    }
}
