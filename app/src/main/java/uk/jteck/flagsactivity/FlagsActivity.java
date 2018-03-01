package uk.jteck.flagsactivity;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import stanford.androidlib.SimpleActivity;

public class FlagsActivity extends SimpleActivity {

    // array of all countries to display
    private static final String[] COUNTRIES = {
            "Australia",
            "Brazil",
            "China",
            "Egypt",
            "France",
            "Germany",
            "Ghana",
            "Italy",
            "Japan",
            "Mexico",
            "Nepal",
            "Nigeria",
            "Spain",
            "United Kingdom",
            "United States"
    };

    // instance initializer
    // runs before any other code (on construction)

    {
        setTraceLifecycle( true );
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_flags );

        // add a new button to the screen
        //Button butt = new Button (this);
        //butt.setText( "My Button" );
        //butt.setPadding( 30,10,30,10 );

        GridLayout layout = findViewById( R.id.activity_flags );

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT );

        //butt.setLayoutParams( params );
        //layout.addView( butt, 1 );

        for (String name : COUNTRIES) {
            addFlag( name, layout );
        }
    }

    private void addFlag(final String countryName, GridLayout layout) {
        View flag = getLayoutInflater().inflate( R.layout.flag, /* parent */  null );

        TextView tv = flag.findViewById( R.id.flag_text );
        tv.setText( countryName );

        String countryName2 = countryName.replace( " ", "" ).toLowerCase();

        //R.drawable.United States
        //R.drawable.unitedstates
        int flagImageID = getResources().getIdentifier(
                countryName2, "drawable", getPackageName() );

        ImageButton img = flag.findViewById( R.id.flag_image );
        img.setImageResource( flagImageID );
        img.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText( FlagsActivity.this, "You clicked " + countryName,
                //Toast.LENGTH_SHORT ).show();

                AlertDialog.Builder builder = new AlertDialog.Builder( FlagsActivity.this );
                builder.setTitle( "My Dialog" );
                builder.setMessage( "You clicked " + countryName );

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        } );

        layout.addView( flag );
    }
}
