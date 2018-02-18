package uk.jtech.game.jflappybird;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import static uk.jtech.game.jflappybird.Constants.pipespeedx;
import static uk.jtech.game.jflappybird.Constants.screenx;
import static uk.jtech.game.jflappybird.Constants.screeny;

/**
 * Created by jtech on 17/02/2018.
 * Code from the course developing android game with libGDX by Daniel Ciolfi.
 */

public class Background {

    private Texture tex;

    private float posx1;
    private float posx2;

    public Background() {
        tex = new Texture( "bg.png" );

        posx1 = 0;
        posx2 = screenx;
    }

    public void draw(SpriteBatch batch) {
        batch.draw( tex, posx1, 0, screenx, screeny );
        batch.draw( tex, posx2, 0, screenx, screeny );

    }

    public void update(float time) {
        posx1 += time * pipespeedx;
        posx2 += time * pipespeedx;

        if (posx1 + screenx <= 0) {
            posx1 = screenx;
            posx2 = 0;
        }
        if (posx2 + screenx <= 0) {
            posx2 = screenx;
            posx1 = 0;
        }
    }

    public void dispose() {
        tex.dispose();
    }
}
