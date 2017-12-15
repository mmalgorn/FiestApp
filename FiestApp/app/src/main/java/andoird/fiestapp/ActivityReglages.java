package andoird.fiestapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import andoird.fiestapp.Object.Soiree;
import andoird.fiestapp.rest.Rest;

public class ActivityReglages extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reglages);
        final MyApplication app = (MyApplication) getApplicationContext();

        Button textDatedebut =  (Button)findViewById(R.id.creation_invitation);
        textDatedebut.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                EditText textDebut = (EditText)findViewById(R.id.date_debut);
                EditText date_fin = (EditText)findViewById(R.id.date_fin);
                EditText nom_soiree = (EditText)findViewById(R.id.nom_soiree);

                JSONObject soireeACreer = new JSONObject();
                try {
                    soireeACreer.put("idCreateur",app.getMyUser().getId());
                    soireeACreer.put("position",app.getMyUser().getPosition());
                    soireeACreer.put("date",textDebut.getText().toString());
                    soireeACreer.put("dateFin",date_fin.getText().toString());
                    soireeACreer.put("nom",nom_soiree.getText().toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.d("CREATION SOIREE", soireeACreer.toString() );
                try {
                    Rest rest = new Rest();
                    Object ret = rest.execute("/AddSoiree",soireeACreer);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                //on redirige vers la liste d'amis à selectionner
                Intent activite_a_lancer = new Intent(ActivityReglages.this, ActivityAmis.class);
                activite_a_lancer.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(activite_a_lancer);
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        //ajoute les entrées de menu_test à l'ActionBar
        getMenuInflater().inflate(R.menu.barre_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        Intent activite_a_lancer;
        switch (item.getItemId()){
            case R.id.etat:
                activite_a_lancer = new Intent(ActivityReglages.this, ActivityEtat.class);
                activite_a_lancer.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(activite_a_lancer);
                return true;
            case R.id.soirees:
                activite_a_lancer = new Intent(ActivityReglages.this, Activity_MainActivity2.class);
                activite_a_lancer.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(activite_a_lancer);
                return true;
            case R.id.notifications:
                activite_a_lancer = new Intent(ActivityReglages.this, ActivityNotifications.class);
                activite_a_lancer.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(activite_a_lancer);
                return true;
            case R.id.amis:
                activite_a_lancer = new Intent(ActivityReglages.this, ActivityAmis.class);
                activite_a_lancer.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(activite_a_lancer);
                return true;
            case R.id.ajouter_soiree:
                activite_a_lancer = new Intent(ActivityReglages.this, ActivityReglages.class);
                activite_a_lancer.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(activite_a_lancer);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
