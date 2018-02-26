package uk.jteck.dictionaryawesome;

import android.os.Bundle;
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
    /*
     * This method runs when the app is first loading up.
     * It sets up the dictionary of words and definitions.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_dictionary );

        // read file data and choose initial words/definitions
        dictionary = new HashMap<>();
        readFileData();
        chooseWords();

        // set up event listener to run when the user taps items in the list
        $LV( R.id.word_list ).setOnItemClickListener( this );
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
            toast( "AWESOME!" );
        } else {
            toast( ":-( LOLOLOL duuuh" );
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
}
