package uk.jtech.jukebox;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Locale;

import stanford.androidlib.SimpleActivity;

public class JukeboxActivity extends SimpleActivity {
    private static final int SPEECH_TO_TEXT_REQ_CODE = 1234;

    private MediaPlayer player = null;
    private TextToSpeech tts;
    private boolean ttsIsReady = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_jukebox );

        tts = new TextToSpeech( this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                ttsIsReady = true;
                log( "text to speed is ready now" );
            }
        } );

        final ListView list = findViewById( R.id.song_list );
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
        player = MediaPlayer.create( JukeboxActivity.this, songID );
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
        Intent intent = new Intent( RecognizerIntent.ACTION_RECOGNIZE_SPEECH );
        intent.putExtra( RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault() );
        intent.putExtra( RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM );

        // prompt text is shown on screen to tell user what say
        intent.putExtra( RecognizerIntent.EXTRA_PROMPT, "Say a sentence here" );
        startActivityForResult( intent, SPEECH_TO_TEXT_REQ_CODE );
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult( requestCode, resultCode, intent );
        if (requestCode == SPEECH_TO_TEXT_REQ_CODE) {
            // I've come back from speech to text
            ArrayList<String> list = intent.getStringArrayListExtra( RecognizerIntent.EXTRA_RESULTS );
            if (list == null || list.isEmpty()) {
                return;
            }
            String text = list.get( 0 );
            for (int i = 1; i < list.size(); i++) {
                text += " " + list.get( i );
            }
            log( text );
        }
    }

    public void onClickStop(View view) {
        stopSong();

        if (ttsIsReady) {
            tts.speak( "Hello Julian!", TextToSpeech.QUEUE_FLUSH, null );
        }
    }
}