package uk.jtech.game.jflappybird;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by jtech on 18/02/2018.
 * Code from the course developing android game with libGDX by Daniel Ciolfi.
 */

public class Button {

    public boolean high = false;
    private Rectangle butt;
    private float highf = 1.1f;

    private Texture texture;

    public Button(Texture texture, int posx, int posy, int size) {
        this.texture = texture;
        butt = new Rectangle( posx, posy, size, size );
    }

    public void draw(SpriteBatch batch) {
        if (high) {
            batch.draw( texture, butt.x - (butt.getWidth() * (highf - 1)) / 2,
                    butt.y - (butt.getHeight() * (highf - 1)) / 2,
                    butt.getWidth() * highf, butt.getHeight() * highf );
        } else {
            batch.draw( texture, butt.x, butt.y, butt.getWidth(), butt.getHeight() );
        }
    }

    public boolean verif(int x, int y) {
        high = butt.x <= x && butt.x + butt.width >= x &&
                butt.y <= y && butt.y + butt.getHeight() >= y;
        return high;
    }
}
