package uk.jtech.game.jpianotiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;

/**
 * Created by julianotech on 12/02/2018.
 * Code from the course developing android game with libGDX by Daniel Ciolfi.
 * This Class defines the statics values for screen, color, size, and tiles
 */

public class Cons {
    public static Color green = new Color(0,0,0,0);
    public static Color correct = new Color(0.650f,0.990f,0.600f,0);
    public static Color incorrect = new Color(0.70f,0.280f,0.300f,0);

    public static int screenx = Gdx.graphics.getWidth();
    public static int screeny = Gdx.graphics.getHeight();

    public static int tileWidth = screenx/4;
    public static int tileHeight = screeny/4;

    public static float startSpeed = 1*tileHeight/2f;
    public static float curSpeed = 0;
}
