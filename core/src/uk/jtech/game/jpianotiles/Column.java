package uk.jtech.game.jpianotiles;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import static uk.jtech.game.jpianotiles.Cons.*;

/**
 * Created by julianotech on 12/02/2018.
 * This class defines the y position and the correct tile
 */

public class Column {
    public float y;

    private int correct; // 0 to 3

    private int pos;

    private boolean ok;

    public Column (float y, int correct){
        this.y = y;
        this.correct = correct;
        ok=false;
    }

    public void draw(ShapeRenderer shapeRenderer){
        shapeRenderer.set(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(green);

        shapeRenderer.rect(correct*tileWidth,y, tileWidth, tileHeight);

        shapeRenderer.set(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor( Color.GRAY);

        for (int i =0; i<=3;i++){
            shapeRenderer.rect( i*tileWidth, y, tileWidth, tileHeight );
        }
    }

    public int update(float time){
        y -= time*curSpeed;
        if (y< 0 - tileHeight){
            if (ok){
                return 1;
            }else {
                return 2;
            }
        }
        return 0;
    }

    public int touch(int tx, int ty){
        if (ty >= y && ty <= y +tileHeight){
            pos = tx/tileWidth;
            if (pos==correct){
                ok=true;
                return 1;
            }else{
                ok=false;
                return 2;
            }
        }
        return 0;
    }
}
