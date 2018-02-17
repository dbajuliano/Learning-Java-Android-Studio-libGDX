package uk.jtech.game.jflappybird;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import static uk.jtech.game.jflappybird.Constants.pipespeedx;
import static uk.jtech.game.jflappybird.Constants.pipew;
import static uk.jtech.game.jflappybird.Constants.screeny;

/**
 * Created by jtech on 17/02/2018.
 */

public class Pipe {

    private Texture tex;
    private Rectangle body;

    private boolean up;

    public Pipe(float posx, float posy, boolean up) {
        this.up = up;
        if (up) {
            body = new Rectangle( posx, posy, pipew, screeny );
        } else {
            body = new Rectangle( posx, posy - screeny, pipew, screeny );
        }

        tex = new Texture( "pipe.png" );
    }

    public void draw(SpriteBatch batch) {
        batch.draw( tex, body.x, body.y, body.getWidth(), body.getHeight(), 0, 0,
                tex.getWidth(), tex.getHeight(), false, up );
    }

    public int update(float time) {
        body.x += pipespeedx * time;
        if (body.x + body.getWidth() <= 0) {
            return 1;
        }
        return 0;
    }

    public void dispose() {
        tex.dispose();
    }
}


