package andoird.fiestapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import andoird.fiestapp.Object.Soiree;

public class ActivityReglages extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reglages);

        Button textDatedebut =  (Button)findViewById(R.id.creation_invitation);
        textDatedebut.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                EditText textDebut = (EditText)findViewById(R.id.date_debut);
                EditText date_fin = (EditText)findViewById(R.id.date_fin);
                EditText nom_soiree = (EditText)findViewById(R.id.nom_soiree);
                EditText position_latitude = (EditText)findViewById(R.id.position_latitude);
                EditText position_longitude = (EditText)findViewById(R.id.position_longitude);

                int[] position_creation={Integer.parseInt(position_latitude.getText().toString()),Integer.parseInt(position_longitude.getText().toString())};


                /*Place pour prévenir le serveur que l'on créé un match*/


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
