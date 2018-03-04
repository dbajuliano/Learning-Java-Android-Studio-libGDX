package uk.jtech.jukebox;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class JukeboxActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_jukebox );
    }

    public void onClickPlay(View view) {
    }

    public void onClickStop(View view) {
    }
}
