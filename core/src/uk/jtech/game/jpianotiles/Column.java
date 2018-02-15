package uk.jtech.game.jpianotiles;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import static uk.jtech.game.jpianotiles.Cons.correctColor;
import static uk.jtech.game.jpianotiles.Cons.curSpeed;
import static uk.jtech.game.jpianotiles.Cons.green;
import static uk.jtech.game.jpianotiles.Cons.incorrectColor;
import static uk.jtech.game.jpianotiles.Cons.tileHeight;
import static uk.jtech.game.jpianotiles.Cons.tileWidth;

/**
 * Created by julianotech on 12/02/2018.
 * Code from the course developing android game with libGDX by Daniel Ciolfi.
 * This class defines the y position and the correct tiles
 */

public class Column {
    public float y;

    private int correct; // 0 to 3

    private int pos;

    private boolean ok;

    private boolean bye;

    private float anim;

    public Column(float y, int correct) {
        this.y = y;
        this.correct = correct;
        ok = false;
        bye = false;
        anim = 0;
    }

    public void draw(ShapeRenderer shapeRenderer) {
        shapeRenderer.set( ShapeRenderer.ShapeType.Filled );
        shapeRenderer.setColor( green );

        shapeRenderer.rect( correct * tileWidth, y, tileWidth, tileHeight );

        if (bye) {
            if (ok) {
                shapeRenderer.setColor( correctColor );
            } else {
                shapeRenderer.setColor( incorrectColor );
            }

            shapeRenderer.rect( pos * tileWidth + (tileWidth - anim * tileWidth) / 2f,
                    y + (tileHeight - anim * tileHeight) / 2,
                    anim * tileWidth, anim * tileHeight );
        }

        shapeRenderer.set( ShapeRenderer.ShapeType.Line );
        shapeRenderer.setColor( Color.GRAY );

        for (int i = 0; i <= 3; i++) {
            shapeRenderer.rect( i * tileWidth, y, tileWidth, tileHeight );
        }
    }

    public void anim(float time) {
        if (bye && anim < 1) {
            anim += 5 * time;
            if (anim >= 1) {
                anim = 1;
            }
        }
    }

    public int update(float time) {
        y -= time * curSpeed;
        if (y < 0 - tileHeight) {
            if (ok) {
                return 1;
            } else {
                error();
                return 2;
            }
        }
        return 0;
    }

    public int touch(int tx, int ty) {
        if (ty >= y && ty <= y + tileHeight) {
            pos = tx / tileWidth;
            if (pos == correct) {
                ok = true;
                bye = true;
                return 1;
            } else {
                ok = false;
                bye = true;
                return 2;
            }
        }
        return 0;
    }

    public void error() {
        bye = true;
        pos = correct;
    }
}
