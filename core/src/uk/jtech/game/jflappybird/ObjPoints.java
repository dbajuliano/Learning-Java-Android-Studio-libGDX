package uk.jtech.game.jflappybird;

import com.badlogic.gdx.math.Rectangle;

import static uk.jtech.game.jflappybird.Constants.gap;
import static uk.jtech.game.jflappybird.Constants.pipespeedx;

/**
 * Created by jtech on 17/02/2018.
 */

public class ObjPoints {

    public Rectangle body;

    public ObjPoints(float posx, float posy) {
        body = new Rectangle( posx, posy, 10, gap );
    }

    public int update(float time) {
        body.x += pipespeedx * time;
        if (body.x + body.getWidth() <= 0) {
            return 1;
        }
        return 0;
    }
}
