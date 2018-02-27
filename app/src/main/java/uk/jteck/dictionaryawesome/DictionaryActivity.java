package uk.jteck.dictionaryawesome;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

import stanford.androidlib.SimpleActivity;
import stanford.androidlib.SimpleList;

public class DictionaryActivity extends SimpleActivity {
    // a dictionary of {word -> definition} pairs for lookup
    private Map<String, String> dictionary;
    private MediaPlayer mp;

    private int points;

    private int highScore;

    /*
     * This method runs when the app is first loading up.
     * It sets up the dictionary of words and definitions.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_dictionary );

        setTraceLifecycle( true );

        // read file data and choose initial words/definitions
        dictionary = new HashMap<>();
        readFileData();
        chooseWords();

        // set up event listener to run when the user taps items in the list
        $LV( R.id.word_list ).setOnItemClickListener( this );

        // Load the high score
        SharedPreferences prefs = getSharedPreferences( "myprefs", MODE_PRIVATE );
        highScore = prefs.getInt( "highScore", 0 );


        mp = new MediaPlayer().create( this, R.raw.jeopardy );
        mp.start();

        points = 0;
    }

    @Override
    protected void onPause() {
        super.onPause();

        mp.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();

        mp.start();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState( outState );
        outState.putInt( "points", points );
        outState.putString( "the_word", $TV( R.id.the_word ).getText().toString() );
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState( savedInstanceState );
        points = savedInstanceState.getInt( "points", 0 );
    }

    /*
                     * This method is called when items in the ListView are clicked.
                     * We check whether the user clicked on the right definition and then pick a new random word
                     * and 5 new random definitions.
                     * Note: This version of onItemClick (with just two parameters) is provided by the SimpleActivity
                     * from the Stanford library.  It wouldn't work if we didn't extend SimpleActivity up above.
                     */
    @Override
    public void onItemClick(ListView list, int index) {
        String defnClicked = list.getItemAtPosition( index ).toString();
        String theWord = $TV( R.id.the_word ).getText().toString();
        String correctDefn = dictionary.get( theWord );
        if (defnClicked.equals( correctDefn )) {
            points++;
            if (points > highScore) {
                highScore = points;

                SharedPreferences prefs = getSharedPreferences( "myprefs", MODE_PRIVATE );
                SharedPreferences.Editor prefsEditor = prefs.edit();
                prefsEditor.putInt( "highScore", highScore );
                prefsEditor.apply();
            }
            toast( "COOL! Score: " + points + ", hi: " + highScore );
        } else {
            if (points > 0) {
                points--;
                toast( "LOL. Score: " + points + ", hi: " + highScore );
            } else {
                toast( "LOL. Score: " + points + ", hi: " + highScore );
            }

        }
        chooseWords();
    }

    /*
     * Reads data from two files: grewords.txt (built into the app),
     * and added_words.txt (extra words added by the user).
     */
    private void readFileData() {
        // read from grewords.txt
        Scanner scan = new Scanner(
                getResources().openRawResource( R.raw.grewords2 ) );
        readFileHelper( scan );

        // read from added_words.txt (try/catch in case file is not found)
        try {
            Scanner scan2 = new Scanner( openFileInput( "added_words.txt" ) );
            readFileHelper( scan2 );
        } catch (Exception e) {
            // do nothing
        }
    }

    /*
     * Reads all words from the given file scanner.
     * Each word is on a line of the form, "word \t definition."
     */
    private void readFileHelper(Scanner scan) {
        while (scan.hasNextLine()) {
            // "abate	to lessen to subside"
            String line = scan.nextLine();
            String[] parts = line.split( "\t" );
            if (parts.length < 2) continue;
            dictionary.put( parts[0], parts[1] );
        }
    }

    /*
     * pick the word,
     * pick ~5 random defns for that word (1 of which is correct)
     * show everything on screen
     */
    private void chooseWords() {
        // choose the correct word
        List<String> words = new ArrayList<>( dictionary.keySet() );
        Random randy = new Random();
        int randomIndex = randy.nextInt( words.size() );
        String theWord = words.get( randomIndex );
        String theDefn = dictionary.get( theWord );

        // pick 4 other (wrong) definitions at random
        List<String> defns = new ArrayList<>( dictionary.values() );
        defns.remove( theDefn );
        Collections.shuffle( defns );
        defns = defns.subList( 0, 4 );
        defns.add( theDefn );
        Collections.shuffle( defns );

        // display everything on screen
        $TV( R.id.the_word ).setText( theWord );
        SimpleList.with( this ).setItems( R.id.word_list, defns );
    }

    public void addAwordClick(View view) {
        // go to the add word activity
        Intent intent = new Intent( this, AddWordActivity.class );
        startActivity( intent );
    }
}
