package project.thibaulpoignonec.selectri;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;

import com.example.x.circlelayout.CircleLayout;


public class ChoixBacActivity extends Activity implements SeekBar.OnSeekBarChangeListener {
    int progressChanged = 0;


    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        progressChanged = i;
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {   }

    CircleLayout circularLayout;

    public void tglClick(View v)
    {
        circularLayout.init();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choix_bac);

        circularLayout = (CircleLayout) findViewById(R.id.circularLayout);

        MyCircleLayoutAdapter ad = new MyCircleLayoutAdapter(this);
        ad.add(R.mipmap.bouton_selection_dmr);
        ad.add(R.mipmap.bouton_selection_recyclable);
        ad.add(R.mipmap.bouton_selection_verre);
        ad.add(R.mipmap.bouton_selection_textile);
        ad.add(R.mipmap.bouton_selection_pile);
        ad.add(R.mipmap.bouton_selection_decheterie);
        ad.add(R.mipmap.bouton_selection_dmr);
        ad.add(R.mipmap.bouton_selection_recyclable);
        ad.add(R.mipmap.bouton_selection_verre);
        ad.add(R.mipmap.bouton_selection_textile);
        ad.add(R.mipmap.bouton_selection_pile);
        ad.add(R.mipmap.bouton_selection_decheterie);
        circularLayout.setAdapter(ad);
        circularLayout.setChildrenCount(10);
        circularLayout.setRadius(30);
        circularLayout.setChildrenPinned(true);
        circularLayout.setOffsetX(0);
        circularLayout.setOffsetY(30);


    }
    public void showMap(String type)
    {
        Intent intent = new Intent(ChoixBacActivity.this, MapActivity.class);

        Bundle bundle = new Bundle();
        bundle.putString("TYPE",type);
        intent.putExtras(bundle);
        Log.d("Main","launch MapActivity");
        startActivity(intent);
    }
}
/*


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

}
*/