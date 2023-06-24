package uk.jtech.game.jpianotiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;

/**
 * Created by julianotech on 12/02/2018.
 * Code from the course developing android game with libGDX by Daniel Ciolfi.
 * This Class defines the statics values for screen, color, size, and tiles
 */

public class Cons {
    public static Color green = new Color( 0, 0, 0, 1 );
    public static Color correctColor = new Color( 0, 0.60f, 0.80f, 1 );
    public static Color incorrectColor = new Color( 0.70f, 0.200f, 0.200f, 1 );

    public static int screenx = Gdx.graphics.getWidth();
    public static int screeny = Gdx.graphics.getHeight();

    public static int tileWidth = screenx / 4;
    public static int tileHeight = screeny / 4;

    public static float startSpeed = 1f * tileHeight / 1f;
    public static float curSpeed = 0;
}
