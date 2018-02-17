package uk.jtech.game.jflappybird;

import com.badlogic.gdx.Gdx;

/**
 * Created by jtech on 17/02/2018.
 */

public class Constants {
    public static int screenx = Gdx.graphics.getWidth();
    public static int screeny = Gdx.graphics.getHeight();

    public static float pipespeedx = -0.3f * screenx;

    public static int birdrad = (int) (0.06f * screeny);

    public static int birdinix = (int) (0.2f * screenx);

    public static float speedRed = screeny / 1.5f;

    public static float impulse = screeny / 5;
}
