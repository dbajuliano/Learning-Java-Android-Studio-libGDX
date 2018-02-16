package uk.jtech.game.jsnake;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.Random;

/**
 * Created by jtech on 15/02/2018.
 * Code from the course developing android game with libGDX by Daniel Ciolfi.
 */

public class GameScreen implements Screen, GestureDetector.GestureListener {

    private Game game;

    private Viewport viewport;
    private SpriteBatch batch;

    private Texture bodyTexture;
    private Texture bgTexture;
    private Texture pointTexture;

    private boolean[][] body;
    private Array<Vector2> parts;

    private int direction; //1=up, 2=right, 3=down, 4=left

    private float timeToMove;

    private Vector2 touch;

    private Array<Vector2> points;

    private float timeToNext;

    private Random rand;

    public GameScreen(Game game) {
        this.game = game;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();

        viewport = new FitViewport( 100, 100 );
        viewport.apply();

        genTexture();

        init();

        touch = new Vector2();

        rand = new Random();

        Gdx.input.setInputProcessor( new GestureDetector( this ) );

    }

    @Override
    public void render(float delta) {
        update( delta );

        batch.setProjectionMatrix( viewport.getCamera().combined );

        Gdx.gl.glClearColor( 0.29f, 0.894f, 0.373f, 1 );
        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT );

        batch.begin();

        batch.draw( bgTexture, 0, 0, 100, 100 );

        for (Vector2 part : parts) {
            batch.draw( bodyTexture, part.x * 5, part.y * 5, 5, 5 );
        }

        for (Vector2 point : points) {
            batch.draw( pointTexture, point.x * 5, point.y * 5, 5, 5 );
        }

        batch.end();

    }

    @Override
    public void resize(int width, int height) {
        viewport.update( width, height, true );
    }

    private void update(float delta) {
        timeToMove -= delta;
        if (timeToMove <= delta) {
            timeToMove = 0.4f;

            Gdx.app.log( "Log", "move" );

            int x1, x2, y1, y2;

            x1 = (int) parts.get( 0 ).x;
            y1 = (int) parts.get( 0 ).y;

            body[x1][y1] = false;

            x2 = x1;
            y2 = y1;

            switch (direction) {
                case 1:
                    y1++;
                    break;
                case 2:
                    x1++;
                    break;
                case 3:
                    y1--;
                    break;
                case 4:
                    x1--;
                    break;
            }

            if (x1 < 0 || y1 < 0 || x1 > 19 || y1 > 19 || body[x1][y1]) {
                //you lose
                return;
            }

            parts.get( 0 ).set( x1, y1 );
            body[x1][y1] = true;

            for (int i = 1; i < parts.size; i++) {
                x1 = (int) parts.get( i ).x;
                y1 = (int) parts.get( i ).y;
                body[x1][y1] = false;

                parts.get( i ).set( x2, y2 );
                body[x2][y2] = true;

                x2 = x1;
                y2 = y1;
            }
        }

        timeToNext -= delta;

        if (timeToNext <= 0) {
            int x = rand.nextInt( 20 );
            int y = rand.nextInt( 20 );
            if (!body[x][y]) {
                points.add( new Vector2( x, y ) );
                timeToNext = 5f;
            }
        }
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        viewport.unproject( touch.set( velocityX, velocityY ) );
        Gdx.app.log( "Log", velocityX + " " + velocityY + " " + touch.x + " " + touch.y );

        if (Math.abs( touch.x ) > Math.abs( touch.y )) touch.y = 0;
        else touch.x = 0;

        if (touch.x > 50 && direction != 4) {
            direction = 2;
        } else if (touch.y > 50 && direction != 3) {
            direction = 1;
        } else if (touch.x < -50 && direction != 2) {
            direction = 4;
        } else if (touch.y > -50 && direction != 1) {
            direction = 3;
        }

        return true;
    }

    private void init() {
        body = new boolean[20][20];
        parts = new Array<Vector2>();

        parts.add( new Vector2( 6, 5 ) );
        body[6][5] = true;

        parts.add( new Vector2( 5, 5 ) );
        body[5][5] = true;

        direction = 2;

        timeToMove = 0.4f;

        points = new Array<Vector2>();

        timeToNext = 3;
    }

    private void genTexture() {
        Pixmap pixmap = new Pixmap( 64, 64, Pixmap.Format.RGB888 );
        pixmap.setColor( 1f, 1f, 1f, 1f );
        pixmap.fillRectangle( 0, 0, 64, 64 );
        bodyTexture = new Texture( pixmap );
        pixmap.dispose();

        Pixmap pixmap2 = new Pixmap( 64, 64, Pixmap.Format.RGB888 );
        pixmap2.setColor( 0.29f, 0.784f, 0.373f, 0.5f );
        pixmap2.fillRectangle( 0, 0, 64, 64 );
        bgTexture = new Texture( pixmap2 );
        pixmap2.dispose();

        Pixmap pixmap3 = new Pixmap( 64, 64, Pixmap.Format.RGB888 );
        pixmap3.setColor( 0.29f, 1f, 1f, 1f );
        pixmap3.fillCircle( 32, 32, 32 );
        pointTexture = new Texture( pixmap3 );
        pixmap3.dispose();
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

    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        return false;
    }

    @Override
    public boolean longPress(float x, float y) {
        return false;
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        return false;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean zoom(float initialDistance, float distance) {
        return false;
    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
        return false;
    }

    @Override
    public void pinchStop() {

    }
}
