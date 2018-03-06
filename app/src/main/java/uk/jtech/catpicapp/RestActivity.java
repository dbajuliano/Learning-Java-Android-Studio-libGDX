package uk.jtech.catpicapp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import org.json.JSONException;
import org.json.JSONObject;

import stanford.androidlib.SimpleActivity;

public class RestActivity extends SimpleActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_rest );
    }

    public void chuckNorrisClick(View view) {
        Ion.with( this )
                .load( "http://api.icndb.com/jokes/random" )
                .asString()
                .setCallback( new FutureCallback<String>() {
                    // {
                    //   "type": "success",
                    //   "value": {
                    //              "id": 268,
                    //               "joke": "Time waits for no man. Unless that man is Chuck Norris."
                    //       }
                    // }
                    @Override
                    public void onCompleted(Exception e, String result) {
                        //data has arrived
                        try {
                            JSONObject json = new JSONObject( result );
                            JSONObject value = json.getJSONObject( "value" );
                            String joke = value.getString( "joke" );
                            $TV( R.id.output ).setText( joke );
                        }
                        catch (JSONException jsone){
                            Log.wtf("help", jsone);
                        }
                    }
                } );
    }

    public void catPicsClick(View view) {
    }
}
