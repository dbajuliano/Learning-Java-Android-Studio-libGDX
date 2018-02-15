package uk.jtech.game.jpianotiles;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;

import java.util.Locale;
import java.util.Random;

import static uk.jtech.game.jpianotiles.Cons.curSpeed;
import static uk.jtech.game.jpianotiles.Cons.screenx;
import static uk.jtech.game.jpianotiles.Cons.screeny;
import static uk.jtech.game.jpianotiles.Cons.startSpeed;
import static uk.jtech.game.jpianotiles.Cons.tileHeight;

/**
 * Created by julianotech on 12/02/2018.
 * Code from the course developing android game with libGDX by Daniel Ciolfi.
 * This is the main class of the game clone Piano Tiles
 */

public class MainClass extends ApplicationAdapter {

    private ShapeRenderer shapeRenderer;

    private SpriteBatch batch;

    private Array<Column> columns;

    private float totalTime;

    private int indexBott;

    private int points;

    private Random rand;

    private int state;

    private Texture texStart;

    private Piano piano;

    private BitmapFont font;
    private GlyphLayout glyphLayout;

    @Override
    public void create() {
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setAutoShapeType( true );

        FreeTypeFontGenerator.setMaxTextureSize( 2048 );
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator( Gdx.files.internal( "font.ttf" ) );
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = (int) (0.06f * screeny);
        parameter.color = Color.CYAN;
        font = generator.generateFont( parameter );
        generator.dispose();

        batch = new SpriteBatch();

        columns = new Array<Column>();

        rand = new Random();

        texStart = new Texture( "play.gif" );

        piano = new Piano( "christimas" );

        glyphLayout = new GlyphLayout();

        start();
    }

    @Override
    public void render() {
        input();

        update( Gdx.graphics.getDeltaTime() );

        Gdx.gl.glClearColor( 1, 1, 1, 1 );
        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT );

        shapeRenderer.begin();

        for (Column c : columns) {
            c.draw( shapeRenderer );
        }

        shapeRenderer.end();


        batch.begin();

        if (state == 0) batch.draw( texStart, 0, tileHeight / 4, screenx, tileHeight / 2 );

        font.draw( batch, String.valueOf( points ), 0, screeny );
        font.draw( batch, String.format( Locale.getDefault(), "%.3f", curSpeed / tileHeight ),
                screenx - getWidth( font, String.format( Locale.getDefault(), "%.3f", curSpeed / tileHeight ) ),
                screeny );

        batch.end();

    }

    private void update(float deltaTime) {
        if (state == 1) {
            totalTime += deltaTime;

            curSpeed = startSpeed + tileHeight * totalTime / 8f;

            for (int i = 0; i < columns.size; i++) {
                int retturn = columns.get( i ).update( deltaTime );
                columns.get( i ).anim( deltaTime );
                if (retturn != 0) {
                    if (retturn == 1) {
                        columns.removeIndex( i );
                        i--;
                        indexBott--;
                        add();
                    } else if (retturn == 2) {
                        close( 1 );
                    }
                }
            }
        } else if (state == 2) {
            for (Column c : columns) {
                c.anim( deltaTime );
            }

        }
    }

    private void input() {
        if (Gdx.input.justTouched()) {
            int x = Gdx.input.getX();
            int y = screeny - Gdx.input.getY();
            if (state == 0) {
                state = 1;
            }
            if (state == 1) {
                for (int i = 0; i < columns.size; i++) {
                    int retturn = columns.get( i ).touch( x, y );
                    if (retturn != 0) {
                        if (retturn == 1 && i == indexBott) {
                            points++;
                            indexBott++;
                            piano.play();
                        } else if (retturn == 1) {
                            //end type 1
                            columns.get( indexBott ).error();
                            close( 0 );
                        } else {
                            // end type 2
                            close( 0 );
                        }
                        break;
                    }
                }
            } else if (state == 2) start();
        }

    }

    private void add() {
        float y = columns.get( columns.size - 1 ).y + tileHeight;
        columns.add( new Column( y, rand.nextInt( 4 ) ) );
    }

    private void start() {
        totalTime = 0;
        indexBott = 0;
        points = 0;

        columns.clear();
        columns.add( new Column( tileHeight, rand.nextInt( 4 ) ) );
        add();
        add();
        add();
        add();

        state = 0;

        curSpeed = 0;

        piano.reset();
    }

    private void close(int opt) {
        Gdx.input.vibrate( 200 );
        state = 2;
        if (opt == 1) {
            for (Column c : columns) {
                c.y += tileHeight;
            }
        }
    }


    private float getWidth(BitmapFont fontt, String text) {
        glyphLayout.reset();
        glyphLayout.setText( fontt, text );
        return glyphLayout.width;
    }

    @Override
    public void dispose() {
        shapeRenderer.dispose();
        batch.dispose();
        texStart.dispose();
        piano.dispose();
    }
}
