package uk.jtech.jukebox;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class JukeboxActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_jukebox );

        ListView list = findViewById( R.id.song_list );
        list.setOnItemClickListener( new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int index, long id) {
                String[] songTitles = getResources().getStringArray( R.array.song_titles );
                String song = songTitles[index];
                Log.d( "result", "onItemClick: " + song );
            }
        } );
    }

    public void onClickPlay(View view) {
    }

    public void onClickStop(View view) {
    }
}
