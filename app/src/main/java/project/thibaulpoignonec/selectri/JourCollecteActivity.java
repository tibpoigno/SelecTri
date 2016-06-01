package project.thibaulpoignonec.selectri;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class JourCollecteActivity extends Activity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jour_collecte);
        update();
        final Button notification_button = (Button) findViewById(R.id.add_notif);
        notification_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                notification_button.setClickable(false);
                notification_button.setVisibility(View.INVISIBLE);

                Toast.makeText(getApplicationContext(), "Alarme ajoutée", Toast.LENGTH_SHORT).show();

                SharedPreferences sharedPreferences = getSharedPreferences("MY_SHARED_PREF", MODE_PRIVATE); //On choppe les préférences partagées
                int jaom = sharedPreferences.getInt("jour_avant_om", 0);
                int jaja = sharedPreferences.getInt("jour_avant_jaune", 0);


                Calendar t = Calendar.getInstance();
                Calendar t2 = Calendar.getInstance();

                t.set(Calendar.HOUR_OF_DAY, 19); //Les calendriers qui nous servent ici à paramétrer l'alarme
                t.set(Calendar.MINUTE, 0);
                t.set(Calendar.SECOND, 0);
                t.set(Calendar.DAY_OF_WEEK, jaja);

                t2.add(Calendar.HOUR, 19);
                t2.add(Calendar.MINUTE, 0);
                t2.set(Calendar.DAY_OF_WEEK, jaom);
                t2.set(Calendar.SECOND, 0);



                Log.d("DAY", "day " + String.valueOf(t.get(Calendar.DAY_OF_WEEK))); //Uniquement pour le débug :D
                Log.d("DAY", "hour " + String.valueOf(t.get(Calendar.HOUR_OF_DAY)));
                Log.d("DAY", "minute " + String.valueOf(t.get(Calendar.MINUTE)));
                Log.d("DAY", "seconde" + String.valueOf(t.get(Calendar.SECOND)));

                Log.d("DAY", "day2 " + String.valueOf(t2.get(Calendar.DAY_OF_WEEK)));
                Log.d("DAY", "hour2 " + String.valueOf(t2.get(Calendar.HOUR_OF_DAY)));
                Log.d("DAY","minute2 "+String.valueOf(t2.get(Calendar.MINUTE)));
                Log.d("DAY", "seconde2" + String.valueOf(t2.get(Calendar.SECOND)));



                Intent i = new Intent(JourCollecteActivity.this, AlarmSound.class); //Ici on créer des pendingIntent qui sont en gros des intent à retardement
                PendingIntent pending = PendingIntent.getActivity(JourCollecteActivity.this,(int) System.currentTimeMillis(), i, 0);
                AlarmManager alarm = (AlarmManager)getSystemService(Activity.ALARM_SERVICE);
                alarm.set(AlarmManager.RTC_WAKEUP, t.getTimeInMillis(), pending);

                Intent i2 = new Intent(JourCollecteActivity.this, AlarmSound2.class);
                PendingIntent pending2 = PendingIntent.getActivity(JourCollecteActivity.this,(int) System.currentTimeMillis(), i2, 0);
                AlarmManager alarm2 = (AlarmManager)getSystemService(Activity.ALARM_SERVICE);
                alarm2.set(AlarmManager.RTC_WAKEUP, t2.getTimeInMillis(), pending2);



            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();  // Always call the superclass method first

        update();//On met à jour les jours :x
    }


    //Builder de notification, obsolète

    /*manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

    Button addNotificationBtn = (Button) findViewById(R.id.add_notif);

    addNotificationBtn.setOnClickListener(new View.OnClickListener() {

        @Override
        public void onClick(View arg0) {
            Intent intent = new Intent("com.rj.notitfications.SECACTIVITY");

            PendingIntent pendingIntent = PendingIntent.getActivity(Tab2.this, 1, intent, 0);

            Notification.Builder builder = new Notification.Builder(Tab2.this);

            builder.setAutoCancel(true );
            builder.setTicker("SelecTri!");
            builder.setContentTitle("SelecTri");
            builder.setContentText("N'oubliez pas de sortir les poubelles!");
            builder.setSmallIcon(R.drawable.bac_jaune);
            builder.setContentIntent(pendingIntent);
            builder.setOngoing(true);
            builder.setNumber(100);
            builder.setVibrate(new long[]{1000, 1000});
            builder.setSound(Settings.System.DEFAULT_NOTIFICATION_URI);
            builder.build();

            myNotication = builder.getNotification();
            manager.notify(11, myNotication);
        }
    });

    Button btnClear = (Button) findViewById(R.id.supp_notif);
    btnClear.setOnClickListener(new View.OnClickListener() {

        @Override
        public void onClick(View arg0) {
            manager.cancel(11);
        }
    });*/
    private boolean update()
    {
        SharedPreferences sharedPreferences = getSharedPreferences("MY_SHARED_PREF", MODE_PRIVATE);


        String jour_om = sharedPreferences.getString("jour_om", "");
        String jour_jaune = sharedPreferences.getString("jour_jaune", "");

        TextView text_view_om = (TextView) findViewById(R.id.jour_om);
        TextView text_view_jaune = (TextView) findViewById(R.id.jour_jaune);

        text_view_om.setText(jour_om);
        text_view_jaune.setText(jour_jaune);

        return true;
    }




}

