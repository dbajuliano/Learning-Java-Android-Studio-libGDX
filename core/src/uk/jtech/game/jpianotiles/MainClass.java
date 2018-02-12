package uk.jtech.game.jpianotiles;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;

import static uk.jtech.game.jpianotiles.Cons.*;

public class MainClass extends ApplicationAdapter {

	private ShapeRenderer shapeRenderer;
	private Array<Column> columns;

	@Override
	public void create () {
		shapeRenderer = new ShapeRenderer(  );
		shapeRenderer.setAutoShapeType( true );

		columns = new Array<Column>();

		columns.add( new Column( 0,0 ) );
		columns.add( new Column( tileHeight,1 ) );
		columns.add( new Column( 2*tileHeight,2 ) );
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		shapeRenderer.begin();
		for (Column f:columns){
			f.draw( shapeRenderer );
		}
		shapeRenderer.end();

	}
	
	@Override
	public void dispose () {
		shapeRenderer.dispose();
	}
}
