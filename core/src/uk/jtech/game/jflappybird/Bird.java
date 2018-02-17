package uk.jtech.game.jflappybird;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;

import static uk.jtech.game.jflappybird.Constants.birdrad;

/**
 * Created by jtech on 17/02/2018.
 */

public class Bird {

    public Circle body;

    private Texture[] frames;
    private float auxFrames;

    public Bird(int posx, int posy) {
        body = new Circle( posx, posy, birdrad );

        frames = new Texture[6];
        for (int i = 1; i <= 6; i++) {
            frames[i - 1] = new Texture( "char/char" + i + ".png" );
        }
    }

    public void draw(SpriteBatch batch) {
        batch.draw( frames[(int) auxFrames % 6], body.x - body.radius, body.y - body.radius,
                body.radius * 2, body.radius * 2 );
    }

    public void update(float time) {
        auxFrames += 6 * time;
    }

    public void dispose() {
        for (int i = 0; i <= 5; i++) {
            frames[i].dispose();
        }
    }
}
