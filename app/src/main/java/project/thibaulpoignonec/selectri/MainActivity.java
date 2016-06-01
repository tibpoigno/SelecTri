package project.thibaulpoignonec.selectri;



import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TextView;

public class MainActivity extends TabActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Resources res = getResources();
        TabHost tabHost = getTabHost();
        TabHost.TabSpec spec;
        Intent intent;
        intent = new Intent().setClass(this, ChoixBacActivity.class);

        spec = tabHost.newTabSpec("ChoixBac").setIndicator("", res.getDrawable(android.R.drawable.ic_menu_search)).setContent(intent);
        tabHost.addTab(spec);

        intent = new Intent().setClass(this, JourCollecteActivity.class);
        spec = tabHost.newTabSpec("JourCollecte").setIndicator("", res.getDrawable(android.R.drawable.ic_menu_today)).setContent(intent);
        tabHost.addTab(spec);

        intent = new Intent().setClass(this, InformationActivity.class);
        spec = tabHost.newTabSpec("Informations").setIndicator("", res.getDrawable(android.R.drawable.ic_menu_help)).setContent(intent);
        tabHost.addTab(spec);

        intent = new Intent().setClass(this, SettingsActivity.class);
        spec = tabHost.newTabSpec("Settings").setIndicator("", res.getDrawable(android.R.drawable.ic_menu_manage)).setContent(intent);
        tabHost.addTab(spec);

        for(int i=0;i<tabHost.getTabWidget().getChildCount();i++) {
            TextView tv = (TextView) tabHost.getTabWidget().getChildAt(i).findViewById(android.R.id.title);
            tv.setTextColor(Color.parseColor("#ffffff"));
            tv.setTextSize(10);
        }

        tabHost.setCurrentTab(0);


    }}
