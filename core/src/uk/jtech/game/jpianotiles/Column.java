package uk.jtech.game.jpianotiles;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import static uk.jtech.game.jpianotiles.Cons.*;

/**
 * Created by julianotech on 12/02/2018.
 * This class defines the y position and the correct tile
 */

public class Column {
    private float y;

    private int correct; // 0 to 3

    public Column (float y, int correct){
        this.y = y;
        this.correct = correct;
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

}
