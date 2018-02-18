package uk.jtech.game.jflappybird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Intersector;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static uk.jtech.game.jflappybird.Constants.birdinix;
import static uk.jtech.game.jflappybird.Constants.birdrad;
import static uk.jtech.game.jflappybird.Constants.gap;
import static uk.jtech.game.jflappybird.Constants.pipesTime;
import static uk.jtech.game.jflappybird.Constants.pipew;
import static uk.jtech.game.jflappybird.Constants.posMax;
import static uk.jtech.game.jflappybird.Constants.screenx;
import static uk.jtech.game.jflappybird.Constants.screeny;

public class MainClass extends ApplicationAdapter {

    private SpriteBatch batch;

    private Background background;

    private Bird bird;

    private List<Pipe> pipes;

    private List<ObjPoints> objPoints;

    private float pipetime;

    private int state = 0; //0=stopped, 1=running, 3=lose, 4=restart button

    private int points = 0;

    private boolean goal = false;

    private BitmapFont font;
    private GlyphLayout glyphLayout = new GlyphLayout();

    @Override
    public void create() {
        batch = new SpriteBatch();

        background = new Background();

        bird = new Bird( birdinix, screeny / 2 );

        pipes = new ArrayList<Pipe>();

        objPoints = new ArrayList<ObjPoints>();

        pipetime = pipesTime;

        FreeTypeFontGenerator.setMaxTextureSize( 2048 );
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator( Gdx.files.internal( "font.ttf" ) );
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = (int) (0.2f * screenx);
        parameter.color = new Color( 1, 1, 1, 1 );
        font = generator.generateFont( parameter );
        generator.dispose();
    }

    @Override
    public void render() {
        input();

        update( Gdx.graphics.getDeltaTime() );

        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT );

        batch.begin();
        draw();
        batch.end();
    }

    private void draw() {
        background.draw( batch );

        for (Pipe p : pipes) {
            p.draw( batch );
        }

        bird.draw( batch );

        font.draw( batch, String.valueOf( points ),
                (screenx - getSizex( font, String.valueOf( points ) )) / 2,
                0.98f * screeny );
    }

    private void update(float time) {
        if (state == 1) {
            background.update( time );

            for (int i = 0; i < pipes.size(); i++) {
                if (pipes.get( i ).update( time ) == 1) {
                    pipes.remove( i );
                    i--;
                }
            }

            for (int i = 0; i < objPoints.size(); i++) {
                if (objPoints.get( i ).update( time ) == 1) {
                    objPoints.remove( i );
                    i--;
                }
            }

            pipetime -= time;
            if (pipetime <= 0) {
                Random random = new Random();
                int pos = random.nextInt( posMax );
                pos -= posMax / 2;
                pipes.add( new Pipe( screenx, screeny / 2 + pos + gap / 2, true ) );
                pipes.add( new Pipe( screenx, screeny / 2 + pos - gap / 2, false ) );
                objPoints.add( new ObjPoints( screenx + pipew + 2 * birdrad, screeny / 2 + pos - gap / 2 ) );
                pipetime = pipesTime;
            }

            for (Pipe p : pipes) {
                if (Intersector.overlaps( bird.body, p.body )) {
                    Gdx.app.log( "Log", "Crash" );
                    bird.lose();
                    state = 2;
                }
            }

            boolean inter = false;

            for (ObjPoints o : objPoints) {
                if (Intersector.overlaps( bird.body, o.body )) {
                    if (!goal) {
                        points++;
                        Gdx.app.log( "Log", String.valueOf( points ) );
                        goal = true;
                    }
                    inter = true;
                }
            }
            if (!inter) goal = false;
        }

        if (state == 1 || state == 2) {
            if (bird.update( time ) == 1) {
                state = 3;
            }
        }
    }

    private void input() {
        if (Gdx.input.justTouched()) {
            if (state == 0) {
                state = 1;
            } else if (state == 1) {
                bird.impulse();
            } else if (state == 3) {
                state = 1;
                bird.restart( birdinix, screeny / 2 );
                pipes.clear();
                pipetime = pipesTime;
                points = 0;
                goal = false;
                objPoints.clear();
            }
        }
    }

    private float getSizex(BitmapFont font, String text) {
        glyphLayout.reset();
        glyphLayout.setText( this.font, text );
        return glyphLayout.width;
    }

    @Override
    public void dispose() {

        background.dispose();

        bird.dispose();

        for (Pipe p : pipes) {
            p.dispose();
        }

        font.dispose();
    }
}
