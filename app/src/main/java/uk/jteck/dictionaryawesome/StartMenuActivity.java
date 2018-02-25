//CS 193A 17wi Lecture 05: Intents

package uk.jteck.dictionaryawesome;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import stanford.androidlib.SimpleActivity;

public class StartMenuActivity extends SimpleActivity {

    private static final int REQ_CODE_ADD_WORD = 1234;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_start_menu );
    }

    public void playTheGameClick(View view) {
        Intent intent = new Intent( this, DictionaryActivity.class );
        startActivity( intent );
    }

    public void addANewWordClick(View view) {
        Intent intent = new Intent( this, AddWordActivity.class );
        intent.putExtra( "initialtext", "FooBar" );
        startActivityForResult( intent, REQ_CODE_ADD_WORD );
    }

    // gets called when AddWordActivity finish()es and comes back to me
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult( requestCode, resultCode, intent );

        if (requestCode == REQ_CODE_ADD_WORD) {
            // intent
            // -> "newword", "newdefn"

            String newWord = intent.getStringExtra( "newword" );
            String newDefn = intent.getStringExtra( "newdefn" );

            toast( "You added the word: " + newWord );
        }
    }
}
