package uk.jteck.dictionaryawesome;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.io.PrintStream;

import stanford.androidlib.SimpleActivity;

public class AddWordActivity extends SimpleActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_add_word );

        Intent intent = getIntent();
        String text = intent.getStringExtra( "initialtext" ); // "FooBar"
        $TV( R.id.new_word ).setText( text );

    }

    public void addThisWordClick(View view) {
        //add the given word/defn to dictionary
        String newWord = $ET( R.id.new_word ).getText().toString();
        String newDefn = $ET( R.id.new_defn ).getText().toString();

        PrintStream output = new PrintStream( openFileOutput( "added_words.txt", MODE_APPEND ) );
        output.println();
        output.close();

        //go back to start menu activity
        // ("return") new word/defn back to StartMenuActivity
        Intent goBack = new Intent();
        goBack.putExtra( "newword", newWord );
        goBack.putExtra( "newdefn", newDefn );
        setResult( RESULT_OK, goBack );
        finish();

        // I'm back

    }
}
