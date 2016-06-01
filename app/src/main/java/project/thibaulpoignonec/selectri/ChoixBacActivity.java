package project.thibaulpoignonec.selectri;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;

public class ChoixBacActivity extends Activity
{

    private LocationManager locationManager;
    public void onCreate(Bundle savedInstanceState) {



        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choix_bac);


        //setTitle("SelecTri");
        //getActionBar().setIcon(R.mipmap.logo_selectri);

        ImageButton bacDmr;
        bacDmr = (ImageButton) findViewById(R.id.bacDmr);
        bacDmr.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                showMap("dmr");
            }
        });

        ImageButton bacRecyclable;
        bacRecyclable = (ImageButton) findViewById(R.id.bacRecyclable);
        bacRecyclable.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                showMap("recyclable");
            }
        });

        ImageButton bacVerre;
        bacVerre = (ImageButton) findViewById(R.id.bacVerre);
        bacVerre.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                showMap("verre");
            }
        });

        ImageButton bacTextile;
        bacTextile = (ImageButton) findViewById(R.id.bacTextile);
        bacTextile.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                showMap("textile");
            }
        });

        ImageButton bacPile;
        bacPile = (ImageButton) findViewById(R.id.bacPile);
        bacPile.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                showMap("pile");
            }
        });

        ImageButton bacDecheterie;
        bacDecheterie = (ImageButton) findViewById(R.id.bacDecheterie);
        bacDecheterie.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                showMap("decheterie");
            }
        });



    }
    private void showMap(String type)
    {
        Intent intent = new Intent(ChoixBacActivity.this, MapActivity.class);

        Bundle bundle = new Bundle();
        bundle.putString("TYPE",type);
        intent.putExtras(bundle);
        Log.d("Main","launch MapActivity");
        startActivity(intent);
    }
}
