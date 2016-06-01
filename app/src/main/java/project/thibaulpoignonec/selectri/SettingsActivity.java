package project.thibaulpoignonec.selectri;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.Calendar;

public class SettingsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Button bouton_om;
        bouton_om = (Button) findViewById(R.id.bouton_om);
        bouton_om.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGrp1);
                // get selected radio button from radioGroup
                int selectedId = radioGroup.getCheckedRadioButtonId();

                // find the radiobutton by returned id
                RadioButton radioButton = (RadioButton) findViewById(selectedId);

                Toast.makeText(SettingsActivity.this, radioButton.getText(), Toast.LENGTH_SHORT).show();
                SharedPreferences sharedPreferences = getSharedPreferences("MY_SHARED_PREF", MODE_PRIVATE); //Préférences : passage de valeurs à d'autres onglets
                SharedPreferences.Editor prefEditor = sharedPreferences.edit();
                prefEditor.putString("jour_om", (String) radioButton.getText());
                prefEditor.commit();

                SharedPreferences sharedPreferences2 = getSharedPreferences("MY_SHARED_PREF", MODE_PRIVATE);
                SharedPreferences.Editor prefEditor2 = sharedPreferences2.edit();

                switch ((String) radioButton.getText()) {   //Jour sélectionné par le RadioButtonGroup
                    case "Lundi":
                        prefEditor2.putInt("jour_avant_om", Calendar.SUNDAY);
                        prefEditor2.commit();
                        break;
                    case "Mardi":
                        prefEditor2.putInt("jour_avant_om", Calendar.MONDAY);
                        prefEditor2.commit();
                        break;
                    case "Mercredi":
                        prefEditor2.putInt("jour_avant_om", Calendar.TUESDAY);
                        prefEditor2.commit();
                        break;
                    case "Jeudi":
                        prefEditor2.putInt("jour_avant_om", Calendar.WEDNESDAY);
                        prefEditor2.commit();
                        break;
                    case "Vendredi":
                        prefEditor2.putInt("jour_avant_om", Calendar.THURSDAY);
                        prefEditor2.commit();
                        break;
                    default:
                        Toast.makeText(getApplicationContext(), "EASTER EGG", Toast.LENGTH_SHORT).show();
                        break;
                }
                Log.d("DATA","JOUR_om:"+(String) radioButton.getText());
                Log.d("DATA","CALENDAR_om:"+ String.valueOf(sharedPreferences.getInt("jour_avant_om",0)));
                prefEditor2.commit();

            }
        });
//Ici idem mais pour le bac jaune
        Button bouton_jaune;
        bouton_jaune = (Button) findViewById(R.id.bouton_jaune);
        bouton_jaune.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGrp2);
                // get selected radio button from radioGroup
                int selectedId = radioGroup.getCheckedRadioButtonId();

                // find the radiobutton by returned id
                RadioButton radioButton = (RadioButton) findViewById(selectedId);

                Toast.makeText(SettingsActivity.this, radioButton.getText(), Toast.LENGTH_SHORT).show();
                SharedPreferences sharedPreferences = getSharedPreferences("MY_SHARED_PREF", MODE_PRIVATE);
                SharedPreferences.Editor prefEditor = sharedPreferences.edit();
                prefEditor.putString("jour_jaune",(String) radioButton.getText());
                prefEditor.commit();

                SharedPreferences sharedPreferences2 = getSharedPreferences("MY_SHARED_PREF", MODE_PRIVATE);
                SharedPreferences.Editor prefEditor2 = sharedPreferences2.edit();

                switch ((String) radioButton.getText()) {
                    case "Lundi":
                        prefEditor2.putInt("jour_avant_jaune", Calendar.SUNDAY);
                        prefEditor2.commit();
                        break;
                    case "Mardi":
                        prefEditor2.putInt("jour_avant_jaune", Calendar.MONDAY);
                        prefEditor2.commit();
                        break;
                    case "Mercredi":
                        prefEditor2.putInt("jour_avant_jaune", Calendar.TUESDAY);
                        prefEditor2.commit();
                        break;
                    case "Jeudi":
                        prefEditor2.putInt("jour_avant_jaune", Calendar.WEDNESDAY);
                        prefEditor2.commit();
                        break;
                    case "Vendredi":
                        prefEditor2.putInt("jour_avant_jaune", Calendar.THURSDAY);
                        prefEditor2.commit();
                        break;
                    default:
                        Toast.makeText(getApplicationContext(), "EASTER EGG", Toast.LENGTH_SHORT).show();
                        break;
                }
                Log.d("DATA","JOUR_j:"+(String) radioButton.getText());
                Log.d("DATA","CALENDAR_j:"+ String.valueOf(sharedPreferences.getInt("jour_avant_jaune", 0)));
                prefEditor2.commit();
            }
        });

    }
}