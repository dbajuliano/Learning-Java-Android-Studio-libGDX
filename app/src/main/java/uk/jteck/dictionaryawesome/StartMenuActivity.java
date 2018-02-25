package uk.jteck.dictionaryawesome;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class StartMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_start_menu );
    }

    public void playTheGameClick(View view) {
        //go to the DictionaryActivity
    }

    public void addANewWordClick(View view) {
        Intent intent = new Intent( this, AddWordActivity.class );
        intent.putExtra( "initialtext", "FooBar" );
        startActivity( intent );
    }
}
