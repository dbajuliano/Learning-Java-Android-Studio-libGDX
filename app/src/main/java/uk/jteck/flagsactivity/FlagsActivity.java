package uk.jteck.flagsactivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class FlagsActivity extends AppCompatActivity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_flags );

        // add a new button to the screen
        Button butt = new Button (this);
        butt.setText( "My Button" );
        butt.setPadding( 30,10,30,10 );

        LinearLayout layout = (LinearLayout) findViewById( R.id.activity_flags );

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
        ViewGroup.LayoutParams.WRAP_CONTENT,
        ViewGroup.LayoutParams.WRAP_CONTENT);

        butt.setLayoutParams( params );
        layout.addView( butt, 1 );

        for (String name : COUNTRIES){
            addFlag( name, layout );
        }
    }

    private void addFlag(String countryName, LinearLayout layout){
        View flag = getLayoutInflater().inflate( R.layout.flag, layout );

        TextView tv = (TextView) flag.findViewById(  R.id.flag_text);
        tv.setText( countryName );

        countryName = countryName.replace( " ", "" ).toLowerCase();

        //R.drawable.United States
        //R.drawable.unitedstates
        int flagImageID = getResources().getIdentifier( countryName, "drawable", getPackageName() );

        ImageView img = (ImageView) flag.findViewById( R.id.flag_image );
        img.setImageResource( flagImageID );


    }
}
