package uk.jtech.game.jflappybird;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

/**
 * Created by jtech on 18/02/2018.
 * Code from the course developing android game with libGDX by Daniel Ciolfi.
 */

public class Sounds {

    private Sound fly;
    private Sound hit;
    private Sound score;

    public Sounds() {
        fly = Gdx.audio.newSound( Gdx.files.internal( "sounds/fly.mp3" ) );
        hit = Gdx.audio.newSound( Gdx.files.internal( "sounds/hit.mp3" ) );
        score = Gdx.audio.newSound( Gdx.files.internal( "sounds/score.mp3" ) );
    }

    public void play(String sounds) {

        if (sounds.equals( "fly" )) {
            fly.play();
        } else if (sounds.equals( "hit" )) {
            hit.play();
        } else if (sounds.equals( "score" )) {
            score.play();
        }
    }

    public void dispose() {
        fly.dispose();
        hit.dispose();
        score.dispose();
    }
}
