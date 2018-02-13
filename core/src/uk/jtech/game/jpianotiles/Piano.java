package uk.jtech.game.jpianotiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by julianotech on 13/02/2018.
 * Code from the course developing android game with libGDX by Daniel Ciolfi.
 * This class implements the Piano sounds.
 */

public class Piano {

    private Map<String, Sound> sounds;

    private Array<String> notes;
    private int index = 0;

    public Piano(String music) {
        FileHandle file = Gdx.files.internal( music + ".txt" );
        String text = file.readString();
        notes = new Array<String>( text.split( " " ) );
        Gdx.app.log( "Log", notes.toString() );

        sounds = new HashMap<String, Sound>();
        for (String s : notes) {
            if (!sounds.containsKey( s )) {
                sounds.put( s, Gdx.audio.newSound( Gdx.files.internal( "sound/" + s + ".wav" ) ) );
            }
        }
    }

    public void play(){
        sounds.get( notes.get(index) ).play();
        index++;
        if (index == notes.size){
            index =0 ;
        }
    }

    public void reset(){
        index = 0;
    }

    public void dispose(){
        for (String key: sounds.keySet()){
            sounds.get( key ).dispose();
        }
    }
}