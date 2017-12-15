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


import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.maps.model.LatLng;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

import andoird.fiestapp.Object.Soiree;
import andoird.fiestapp.rest.Rest;

public class ActivityReglages extends AppCompatActivity {

    private static final String TAG = "ACTIVITY REGLAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reglages);
        final MyApplication app = (MyApplication) getApplicationContext();

        PlaceAutocompleteFragment autocompleteFragment = (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);

        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                // TODO: Get info about the selected place.
                Log.i(TAG, "Place: " + place.getName());
                MyApplication app = (MyApplication) getApplicationContext();
                app.latLng=place.getLatLng();
            }

            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
                Log.i(TAG, "An error occurred: " + status);
            }
        });


        Button textDatedebut =  (Button)findViewById(R.id.creation_invitation);
        textDatedebut.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                EditText dateDebut = (EditText)findViewById(R.id.date_debut);
                EditText dateFin = (EditText)findViewById(R.id.date_fin);
                EditText nom_soiree = (EditText)findViewById(R.id.nom_soiree);
                Log.d("CREATION SOIREE","je suis au point 1" );
                Rest rest = null;
                JSONObject soireeACreer = new JSONObject();
                LatLng lgtd = app.latLng;
                double lat = lgtd.latitude;
                double lng = lgtd.longitude;
                String position = (lat+","+lng);
                try {
                    soireeACreer.put("idCreateur",app.getMyUser().getId());
                    soireeACreer.put("position",position);
                    soireeACreer.put("date",dateDebut.getText());
                    soireeACreer.put("dateFin",dateFin.getText());
                    soireeACreer.put("nom_soiree",nom_soiree.getText());
                    soireeACreer.put("participants","[]");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.d("CREATION SOIREE", soireeACreer.toString() );
                try {
                    rest = new Rest();
                    Object ret = rest.execute("/AddSoiree",soireeACreer);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                //on redirige vers la liste d'amis à selectionner
                Intent activite_a_lancer = new Intent(ActivityReglages.this, ActivityAmis.class);
                activite_a_lancer.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(activite_a_lancer);

                JSONObject s = new JSONObject();

                try {
                    rest = new Rest();
                    s.put("idCreateur",app.getMyUser().getId());
                    s.put("date",textDebut.getText());
                    s.put("nom_soiree",nom_soiree.getText());
                    Soiree mySoiree = (Soiree) rest.execute("/GetSoiree",s).get();
                    Log.d("RECUPERATION SOIREE", mySoiree.getId().toString());
                    app.laSoiree=mySoiree;
                    Log.d("RECUPERATION SOIREE", app.laSoiree.toString() );
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }

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
