package uk.jtech.jukebox;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import stanford.androidlib.SimpleActivity;

public class JukeboxActivity extends SimpleActivity {

    private MediaPlayer player = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_jukebox );

        ListView list = findViewById( R.id.song_list );
        list.setOnItemClickListener( new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int index, long id) {
                String[] songTitles = getResources().getStringArray( R.array.song_titles );
                String song = songTitles[index].toLowerCase()
                        .replace( " ", "" );
                playSong( song );
            }
        } );
    }

    public void playSong(String song) {
        // s1 => "R.raw.s1"

        // Stanford library
        // int songId = getResourcesId ("raw", song); // R.raw. + song

        //without Stanford Library
        int songID = getResources().getIdentifier( song, "raw", getPackageName() );

        stopSong();
        player = MediaPlayer.create( this, songID );
        player.start();

        System.out.println( "log: " + song );
    }

    public void stopSong() {
        if (player != null) {
            player.stop();
            player = null;
        }
    }

    public void onClickPlay(View view) {
    }

    public void onClickStop(View view) {
        stopSong();
    }
}