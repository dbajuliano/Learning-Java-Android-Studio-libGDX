package uk.jtech.game.jflappybird;

import com.badlogic.gdx.Gdx;

/**
 * Created by jtech on 17/02/2018.
 * Code from the course developing android game with libGDX by Daniel Ciolfi.
 */

public class Constants {
    public static int screenx = Gdx.graphics.getWidth();
    public static int screeny = Gdx.graphics.getHeight();

    public static float pipespeedx = -0.3f * screenx;

    public static int birdrad = (int) (0.06f * screeny);

    public static int birdinix = (int) (0.2f * screenx);

    public static float speedRed = screeny / 2.0f;

    public static float impulse = screeny / 4.0f;

    public static int pipew = (int) (0.2f * screenx);

    public static float pipesTime = 3f;

    public static int posMax = (int) (0.7f * screeny);

    public static int gap = (int) (0.2f * screeny);

    public static int buttSize = (int) (0.4f * screenx);
    public static int buttx = (int) (0.3f * screenx);
    public static int butty = (screeny - buttSize) / 2;
}
