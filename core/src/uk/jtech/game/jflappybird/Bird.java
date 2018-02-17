package uk.jtech.game.jflappybird;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

import static uk.jtech.game.jflappybird.Constants.birdrad;
import static uk.jtech.game.jflappybird.Constants.impulse;
import static uk.jtech.game.jflappybird.Constants.screeny;
import static uk.jtech.game.jflappybird.Constants.speedRed;

/**
 * Created by jtech on 17/02/2018.
 */

public class Bird {

    public Circle body;

    private Texture[] frames;
    private float auxFrames;

    private Vector2 speed;

    public Bird(int posx, int posy) {
        body = new Circle( posx, posy, birdrad );

        frames = new Texture[6];
        for (int i = 1; i <= 6; i++) {
            frames[i - 1] = new Texture( "char/char" + i + ".png" );
        }

        speed = new Vector2( 0, 0 );
    }

    public void draw(SpriteBatch batch) {
        batch.draw( frames[(int) auxFrames % 6], body.x - body.radius, body.y - body.radius,
                body.radius * 2, body.radius * 2 );
    }

    public void update(float time) {
        auxFrames += 6 * time;
        body.x += speed.x * time;
        body.y += speed.y * time;
        speed.y -= speedRed * time;

        if (body.y + body.radius >= screeny) {
            body.y = screeny - body.radius;
            speed.y = -impulse;
        }

        if (body.y - body.radius <= 0) {
            body.y = body.radius;
            speed.y = impulse;
        }
    }

    public void impulse() {
        speed.y += impulse;
    }

    public void dispose() {
        for (int i = 0; i <= 5; i++) {
            frames[i].dispose();
        }
    }
}
